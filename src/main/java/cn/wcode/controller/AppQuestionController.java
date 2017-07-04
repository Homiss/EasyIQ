package cn.wcode.controller;

import cn.wcode.dto.Result;
import cn.wcode.model.Question;
import cn.wcode.model.QuestionGroup;
import cn.wcode.service.QuestionGroupService;
import cn.wcode.service.QuestionService;
import cn.wcode.service.ReciteRecordService;
import com.github.pagehelper.PageInfo;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Homiss
 * @since 2017-6-13 10:23
 */
@RestController
@RequestMapping("/api/app/question")
public class AppQuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private ReciteRecordService reciteRecordService;
    @Autowired
    private QuestionGroupService questionGroupService;

    /**
     * 添加题库到我的
     */
    @RequestMapping("/v1/group/addToMine")
    @ResponseBody
    public Result<String> addQuestionGroup(Integer userId, Integer groupId){
        List<Question> questions = questionService.getByQuestionGroupId(groupId);
        reciteRecordService.addQuestions(userId, questions);
        return new Result<>("添加题库成功～");
    }

    /**
     * 从我的题库中移除
     */
    @RequestMapping(value = "/v1/group/removeFromMine", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> removeGroupFromMine(Integer userId, Integer groupId){
        reciteRecordService.deleteByGroupId(userId, groupId);
        return new Result<>("移除题库成功～");
    }

    /**
     * 获取所有题库列表
     * @param userId
     * @return
     */
    @RequestMapping("/v1/groups")
    public Result<List<Map<String, String>>> allGroup(Integer userId) {
        List<QuestionGroup> questiongroupList = questionGroupService.selectAll();
        List<Map<String, String>> groups = new ArrayList<>();
        for(QuestionGroup questionGroup : questiongroupList) {
            Map<String, String> temp = new HashMap<>();
            temp.put("name", questionGroup.getName());
            temp.put("id", String.valueOf(questionGroup.getId()));
            Integer sumCount = questionService.selectCountByGroupId(String.valueOf(questionGroup.getId()));
            if(sumCount == null || sumCount == 0) continue;
            temp.put("sumCount", String.valueOf(sumCount));
            Integer hasReciteCount = reciteRecordService.selectHasReciteRecordNum(userId,
                String.valueOf(questionGroup.getId()));
            temp.put("hasReciteCount", hasReciteCount == null ? "0" : String.valueOf(hasReciteCount));
            groups.add(temp);
        }
        return new Result<>(groups);
    }

    /**
     * 获取我的题库列表
     * @param userId
     * @return
     */
    @RequestMapping("/v1/groups/mine")
    public Result<List<Map<String, String>>> mineGroups(Integer userId) {
        List<QuestionGroup> questiongroupList = questionGroupService.selectAll();
        List<Map<String, String>> groups = new ArrayList<>();
        for(QuestionGroup questionGroup : questiongroupList) {
            Map<String, String> temp = new HashMap<>();
            temp.put("name", questionGroup.getName());
            temp.put("id", String.valueOf(questionGroup.getId()));
            Integer sumCount = reciteRecordService.selectCountByUserIdAndGroupId(userId, String.valueOf(questionGroup.getId()));
            if(sumCount == null || sumCount == 0) continue;
            temp.put("sumCount", String.valueOf(sumCount));
            Integer hasReciteCount = reciteRecordService.selectHasReciteRecordNum(userId,
                String.valueOf(questionGroup.getId()));
            temp.put("hasReciteCount", hasReciteCount == null ? "0" : String.valueOf(hasReciteCount));
            groups.add(temp);
        }
        return new Result<>(groups);
    }

    /**
     * 通过题库获取题目列表
     */
    @RequestMapping("/v1/groups/list")
    public Result<Map<String, Object>> questionList(@RequestParam("userId") Integer userId,
        @RequestParam("groupId") Integer groupId){
        Map<String, Object> result = new HashMap<>();

        List<Question> questions = questionService.getByQuestionGroupId(groupId);
        Boolean hasAdd = reciteRecordService.hasAdd(userId, groupId);
        result.put("list", questions);
        result.put("hasAdd", hasAdd);
        return new Result<>(result);
    }

    /**
     * 修改题目答案
     */
    @RequestMapping(value = "/v1/modify/answer", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> modifyAnswer(int id, String answer){
        questionService.modifyAnswer(id, answer);
        return new Result<>("操作成功~");
    }
}

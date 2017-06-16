package cn.wcode.controller;

import cn.wcode.dto.Result;
import cn.wcode.model.Question;
import cn.wcode.model.QuestionGroup;
import cn.wcode.service.QuestionGroupService;
import cn.wcode.service.QuestionService;
import cn.wcode.service.ReciteRecordService;
import com.github.pagehelper.PageInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
     * 获取题库列表
     * @param userId
     * @return
     */
    @RequestMapping("/v1/list")
    public Result<List<Map<String, String>>> getAll(Integer userId) {
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
     * 添加题库到我的
     */
    @RequestMapping("/v1/add")
    @ResponseBody
    public Result<String> addQuestionGroup(Integer userId, int qGroupId){
        List<Question> questions = questionService.getByQuestionGroupId(qGroupId);
        reciteRecordService.addQuestions(userId, questions);
        return new Result<>("添加题库成功～");
    }

}


package cn.wcode.controller;

import cn.wcode.dto.Result;
import cn.wcode.model.Question;
import cn.wcode.model.ReciteRecord;
import cn.wcode.service.QuestionService;
import cn.wcode.service.ReciteRecordService;
import cn.wcode.util.FileUtil;
import com.github.pagehelper.PageInfo;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private ReciteRecordService reciteRecordService;

    /**
     *
     * @param question
     * @return
     */
    @RequestMapping
    public PageInfo<Question> getAll(Question question) {
        List<Question> questionList = questionService.getAll(question);
        return new PageInfo<>(questionList);
    }

    @RequestMapping(value = "/add")
    public Question add() {
        return new Question();
    }

    @RequestMapping(value = "/view/{id}")
    public Question view(@PathVariable Integer id) {
        ModelAndView result = new ModelAndView();
        Question question = questionService.getById(id);
        return question;
    }

    @RequestMapping(value = "/delete/{id}")
    public ModelMap delete(@PathVariable Integer id) {
        ModelMap result = new ModelMap();
        questionService.deleteById(id);
        result.put("msg", "删除成功!");
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelMap save(Question question) {
        ModelMap result = new ModelMap();
        String msg = question.getId() == null ? "新增成功!" : "更新成功!";
        questionService.save(question);
        result.put("question", question);
        result.put("msg", msg);
        return result;
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public Result<String> importData(String filepath, int groupId) {
        String txt = FileUtil.readTxtFile(filepath);
        Pattern p = Pattern.compile("<h3[\\s\\S]*?(?=<h3)");
        Matcher m = p.matcher(txt);
        while(m.find()) {
            String content = m.group();
            Document doc = Jsoup.parse(content);
            String question = doc.select("h3").first().text().trim();
            doc.select("h3").first().remove();
            String answer = doc.body().html();

            Integer id = questionService.selectIdByQuestion(question);
            Question bean = Question.builder()
                .question(question)
                .answer(answer)
                .groupId(groupId)
                .build();
            bean.setId(id);
            questionService.save(bean);
            String idTmp = reciteRecordService.selectIdByQuestionId(bean.getId());
            if(idTmp == null){
                // 获取所有添加了当前题库的用户id
                List<Integer> userIds = reciteRecordService.selectUserIdsByGroupId(groupId);
                for(Integer userId : userIds){
                    ReciteRecord reciteRecord = ReciteRecord.builder()
                        .userId(userId)
                        .groupId(groupId)
                        .questionId(bean.getId())
                        .level(0)
                        .strange(0)
                        .startDate(new Date())
                        .nextDate(new Date())
                        .needRecite(1)
                        .build();
                    reciteRecordService.save(reciteRecord);
                }
            }

        }
        return new Result<>("");
    }

}

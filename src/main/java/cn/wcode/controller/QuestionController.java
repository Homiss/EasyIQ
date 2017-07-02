
package cn.wcode.controller;

import cn.wcode.dto.Result;
import cn.wcode.model.Question;
import cn.wcode.service.QuestionService;
import cn.wcode.util.FileUtil;
import com.github.pagehelper.PageInfo;
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

/**
 * @author liuzh
 * @since 2015-12-19 11:10
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

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
    public Result<String> importData(String filepath) {
        String txt = FileUtil.readTxtFile(filepath);
        Pattern p = Pattern.compile("<h3[\\s\\S]*?(?=<h3)");
        Matcher m = p.matcher(txt);
        while(m.find()) {
            Integer groupId = 1;
            String content = m.group();
            Document doc = Jsoup.parse(content);
            String question = doc.select("h3").first().text();
            doc.select("h3").first().remove();
            String answer = doc.body().html();
            Question bean = Question.builder()
                .question(question)
                .answer(answer)
                .qGroupId(groupId)
                .build();
            questionService.save(bean);
        }
        return new Result<>("");
    }

}

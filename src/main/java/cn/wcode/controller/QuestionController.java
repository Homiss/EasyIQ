
package cn.wcode.controller;

import cn.wcode.model.Question;
import cn.wcode.service.QuestionService;
import com.github.pagehelper.PageInfo;
import java.util.List;
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
}

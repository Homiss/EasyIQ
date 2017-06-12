/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package cn.wcode.controller;

import cn.wcode.dto.Result;
import cn.wcode.model.Question;
import cn.wcode.service.QuestionService;
import cn.wcode.service.ReciteRecordService;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author liuzh
 * @since 2015-12-19 11:10
 */
@RestController
@RequestMapping("/app/question")
public class AppQuestionBankController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private ReciteRecordService reciteRecordService;

    /**
     * 获取题库列表
     * @param question
     * @return
     */
    @RequestMapping("/v1/list")
    public PageInfo<Question> getAll(Integer userId, Question question) {
        List<Question> questionList = questionService.getAll(question);
        return new PageInfo<>(questionList);
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

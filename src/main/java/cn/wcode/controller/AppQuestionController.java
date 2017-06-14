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
 * @author liuzh
 * @since 2015-12-19 11:10
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

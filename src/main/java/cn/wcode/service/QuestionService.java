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

package cn.wcode.service;

import cn.wcode.mapper.QuestionMapper;
import cn.wcode.model.Question;
import com.github.pagehelper.PageHelper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuzh
 * @since 2015-12-19 11:09
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 从题库中取出面试题
     */
    public List<Question> getAll(Question question) {
        if (question.getPage() != null && question.getRows() != null) {
            PageHelper.startPage(question.getPage(), question.getRows());
        }
        return questionMapper.selectAll();
    }

    public Question getById(Integer id) {
        return questionMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        questionMapper.deleteByPrimaryKey(id);
    }

    public void save(Question question) {
        if (question.getId() != null) {
            questionMapper.updateByPrimaryKey(question);
        } else {
            questionMapper.insert(question);
        }
    }


    public List<Question> getByQuestionGroupId(int qGroupId) {
        return questionMapper.getByQuestionGroupId(qGroupId);
    }

  public void modifyAnswer(int id, String answer) {
      Question question = new Question();
      question.setId(id);
      question.setAnswer(answer);
      questionMapper.updateByPrimaryKeySelective(question);
  }
}

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

import cn.wcode.core.Ebbinghaus;
import cn.wcode.mapper.QuestionMapper;
import cn.wcode.mapper.ReciteRecordMapper;
import cn.wcode.model.Question;
import cn.wcode.model.ReciteRecord;
import com.github.pagehelper.PageHelper;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReciteRecordService {

    @Autowired
    private ReciteRecordMapper reciteRecordMapper;

    public List<ReciteRecord> selectTodayTask(ReciteRecord reciteRecord) {
        if (reciteRecord.getPage() != null && reciteRecord.getRows() != null) {
            PageHelper.startPage(reciteRecord.getPage(), reciteRecord.getRows());
        }
        return reciteRecordMapper.selectTodayTask();
    }

    public ReciteRecord getById(Integer id) {
        return reciteRecordMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        reciteRecordMapper.deleteByPrimaryKey(id);
    }

    public void save(ReciteRecord reciteRecord) {
        if (reciteRecord.getId() != null) {
            reciteRecord.setLastDate(new Date());
            reciteRecordMapper.updateByPrimaryKey(reciteRecord);
        } else {
            reciteRecord.setStartDate(new Date());
            reciteRecord.setLastDate(new Date());
            reciteRecord.setLevel(0);
            reciteRecord.setStrange(0);
            reciteRecordMapper.insert(reciteRecord);
        }
    }

    public void addQuestions(List<Question> questions) {
        for(Question q : questions){
            ReciteRecord reciteRecord = ReciteRecord.builder()
                .userId(1)
                .questionId(q.getId())
                .level(0)
                .strange(0)
                .startDate(new Date())
                .nextDate(new Date())
                .build();
            reciteRecordMapper.insert(reciteRecord);
        }
    }

    /**
     * 修改已背单条记录
     * status : -1,完全记得，当前题目不再出现 0, 不记得 1，记得
     */
    public void modifyReciteRecord(int id, int status) {
        ReciteRecord reciteRecord = reciteRecordMapper.selectByPrimaryKey(id);
        if(status == 1){
            Integer level = reciteRecord.getLevel();
            if(level >= 7) { // 当前题目不需要背诵
                reciteRecord.setNeedRecite(0);
            } else { // level + 1
                reciteRecord.setLastDate(new Date());
                long nextTime = System.currentTimeMillis() + Ebbinghaus.FORGETTIN_CURVE[reciteRecord.getLevel()];
                reciteRecord.setNextDate(new Date(nextTime));
                reciteRecord.setLevel(reciteRecord.getLevel() + 1);
            }
        } else if(status == -1) { // 当前题目不需要背诵
            reciteRecord.setNeedRecite(0);
        }
        reciteRecordMapper.updateByPrimaryKey(reciteRecord);
    }
}

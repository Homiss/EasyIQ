package cn.wcode.controller;

import cn.wcode.dto.Result;
import cn.wcode.model.Question;
import cn.wcode.model.ReciteRecord;
import cn.wcode.service.QuestionService;
import cn.wcode.service.ReciteRecordService;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by homiss on 2017/6/8.
 */

@RestController
@RequestMapping("/app/v1/recite")
public class AppReciteController {

  @Autowired
  private ReciteRecordService reciteRecordService;
  @Autowired
  private QuestionService questionService;

  /**
   * 添加题库到我的
   */
  @RequestMapping("/add")
  @ResponseBody
  public Result<String> addQuestionGroup(int qGroupId){
    List<Question> questions = questionService.getByQuestionGroupId(qGroupId);
    reciteRecordService.addQuestions(questions);
    return new Result<>("添加题库成功～");
  }

  /**
   * 获取需要背诵的题目列表
   * @param reciteRecord
   * @return
   */
  @RequestMapping("/today/task")
  @ResponseBody
  public Result<PageInfo<ReciteRecord>> selectTodayTask(ReciteRecord reciteRecord) {
    List<ReciteRecord> reciteRecordList = reciteRecordService.selectTodayTask(reciteRecord);
    return new Result<>(new PageInfo<>(reciteRecordList));
  }

  /**
   * 修改已背单条记录
   * status : -1,完全记得，当前题目不再出现 0, 不记得 1，记得
   */
  @RequestMapping(value = "/modify/record", method = RequestMethod.POST)
  @ResponseBody
  public Result<String> modifyReciteRecord(int id, int status){
    reciteRecordService.modifyReciteRecord(id, status);
    return new Result<>("更新记录成功～");
  }


}

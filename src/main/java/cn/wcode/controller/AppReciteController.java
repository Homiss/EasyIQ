package cn.wcode.controller;

import cn.wcode.dto.ReciteQuestionDto;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by homiss on 2017/6/8.
 */

@RestController
@RequestMapping("/api/app/recite")
public class AppReciteController {

  @Autowired
  private ReciteRecordService reciteRecordService;

  /**
   * 获取需要背诵的题目列表
   * @param reciteRecord
   * @return
   */
  @RequestMapping("/v1/today/task")
  @ResponseBody
  public Result<PageInfo<ReciteQuestionDto>> selectTodayTask(@RequestParam("userId") int userId,
      @RequestParam("groupId") int groupId, ReciteRecord reciteRecord) {
    List<ReciteQuestionDto> reciteRecordList = reciteRecordService.selectTodayTask(reciteRecord);
    return new Result<>(new PageInfo<>(reciteRecordList));
  }

  /**
   * 修改已背单条记录
   * status : -1,完全记得，当前题目不再出现 0, 不记得 1，记得
   */
  @RequestMapping(value = "/v1/modify/record", method = RequestMethod.POST)
  @ResponseBody
  public Result<String> s(int id, int status){
    reciteRecordService.modifyReciteRecord(id, status);
    return new Result<>("更新记录成功～");
  }


}

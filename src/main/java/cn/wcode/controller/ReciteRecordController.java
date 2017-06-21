package cn.wcode.controller;

import cn.wcode.model.Question;
import cn.wcode.model.ReciteRecord;
import cn.wcode.service.QuestionService;
import cn.wcode.service.ReciteRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by homiss on 2017/6/8.
 */
@RestController
@RequestMapping("/reciteRecord")
public class ReciteRecordController {

  @Autowired
  private ReciteRecordService reciteRecordService;
  @Autowired
  private QuestionService questionService;

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public ModelMap save(ReciteRecord reciteRecord) {
    ModelMap result = new ModelMap();
    String msg = reciteRecord.getId() == null ? "新增成功!" : "更新成功!";
    reciteRecordService.save(reciteRecord);
    result.put("reciteRecord", reciteRecord);
    result.put("msg", msg);
    return result;
  }




}

package cn.wcode.controller;

import cn.wcode.dto.Result;
import cn.wcode.model.Setting;
import cn.wcode.service.QuestionService;
import cn.wcode.service.ReciteRecordService;
import cn.wcode.service.SettingService;
import freemarker.ext.beans.HashAdapter;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by homiss on 2017/6/13.
 */

@RequestMapping("/api/app/setting")
@RestController
public class AppSettingController {

  @Autowired
  private SettingService settingService;
  @Autowired
  private ReciteRecordService reciteRecordService;

  /**
   * 获取当前用户设置
   */
  @RequestMapping(value = "/v1", method = RequestMethod.POST)
  @ResponseBody
  public Result<Map<String, Object>> setting(Integer userId){
    Map<String, Object> result = settingService.selectMapByUserId(userId);

    // 根据groupId获取总题数
    Integer sumCount = reciteRecordService.selectCountByUserIdAndGroupId(userId, String.valueOf(result.get("qGroupId")));
    // 获取已背题数
    Integer hasReciteCount = reciteRecordService.selectHasReciteRecordNum(userId, String.valueOf(result.get("qGroupId")));
    result.put("sumCount", sumCount);
    result.put("hasReciteCount", hasReciteCount);
    return new Result<>(result);
  }

  /**
   * 修改当前用户的背题模式
   */
  @RequestMapping(value = "/v1/modify/model", method = RequestMethod.POST)
  @ResponseBody
  public Result<String> modifyModel(Integer userId, Integer model){
    settingService.updateModelByUserId(userId, model);
    return new Result<>("操作成功～");
  }



}

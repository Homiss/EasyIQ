package cn.wcode.service;

import cn.wcode.mapper.SettingMapper;
import cn.wcode.model.Setting;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by homiss on 2017/6/13.
 */
@Service
public class SettingService {
  @Autowired
  private SettingMapper settingMapper;

  public Map<String, Object> selectMapByUserId(Integer userId) {
    return settingMapper.selectMapByUserId(userId);
  }

  public Setting selectByUserId(Integer userId) {
    return settingMapper.selectByUserId(userId);
  }

  public void updateModelByUserId(Integer userId, Integer model) {
    Setting setting = selectByUserId(userId);
    setting.setReciteModel(model);
    settingMapper.updateByPrimaryKey(setting);
  }

  public void insert(Setting setting) {
    settingMapper.insertSelective(setting);
  }

  public void updateGroupIdByUserId(Integer userId, Integer groupId) {
    Setting setting = selectByUserId(userId);
    setting.setQGroupId(groupId);
    settingMapper.updateByPrimaryKey(setting);
  }
}

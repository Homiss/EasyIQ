package cn.wcode.mapper;

import cn.wcode.model.Setting;
import cn.wcode.util.MyMapper;
import java.util.Map;

public interface SettingMapper extends MyMapper<Setting> {

  Map<String, Object> selectMapByUserId(Integer userId);

  Setting selectByUserId(Integer userId);
}
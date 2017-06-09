package cn.wcode.mapper;

import cn.wcode.model.UserAuths;
import cn.wcode.util.MyMapper;

public interface UserAuthsMapper extends MyMapper<UserAuths> {

  UserAuths selectByPhoneAndPassword(String phone, String password);
}
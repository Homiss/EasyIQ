package cn.wcode.mapper;

import cn.wcode.model.UserAuths;
import cn.wcode.util.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface UserAuthsMapper extends MyMapper<UserAuths> {

  UserAuths selectByPhoneAndPassword(@Param("phone") String phone, @Param("password") String password);
}
package cn.wcode.mapper;

import cn.wcode.model.User;
import cn.wcode.util.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends MyMapper<User> {

  User selectUserByToken(@Param("userId") Integer userId, @Param("token") String token);
}
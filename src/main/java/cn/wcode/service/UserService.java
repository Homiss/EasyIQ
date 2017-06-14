package cn.wcode.service;

import cn.wcode.convert.UserAndUserInfoConvert;
import cn.wcode.dto.UserInfoDto;
import cn.wcode.mapper.UserAuthsMapper;
import cn.wcode.mapper.UserMapper;
import cn.wcode.model.User;
import cn.wcode.model.UserAuths;
import cn.wcode.util.MD5Util;
import java.util.Date;
import java.util.UUID;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by homiss on 2017/6/8.
 */
@Service
public class UserService {

  @Autowired
  private UserMapper userMapper;
  @Autowired
  private UserAuthsMapper userAuthsMapper;


  public void regist(String name, String phone, String password) throws Exception {
    User user = User.builder()
        .name(name)
        .phone(phone)
        .registDate(new Date())
        .build();
    userMapper.insertSelective(user);
    UserAuths userAuths = UserAuths.builder()
        .userId(user.getId())
        .identity(name)
        .identityType("phone")
        .credential(MD5Util.getMD5(password))
        .build();
    userAuthsMapper.insertSelective(userAuths);
  }

  public UserInfoDto login(String phone, String password) throws Exception {
    UserAuths userAuths = userAuthsMapper.selectByPhoneAndPassword(phone, MD5Util.getMD5(password));
    if(userAuths != null){
      User user = userMapper.selectByPrimaryKey(userAuths.getUserId());
      // 更新用户token
      String uuidToken = UUID.randomUUID().toString();
      user.setToken(uuidToken);
      userMapper.updateByPrimaryKey(user);
      return UserAndUserInfoConvert.toUserInfoDto(user);
    } else {
      throw new Exception("账号或密码错误～");
    }
  }

  public User selectUserByToken(Integer userId, String token) {
    return userMapper.selectUserByToken(userId, token);
  }

}

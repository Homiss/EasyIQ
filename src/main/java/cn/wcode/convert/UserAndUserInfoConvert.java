package cn.wcode.convert;

import cn.wcode.dto.UserInfoDto;
import cn.wcode.model.User;

/**
 * Created by homiss on 2017/6/9.
 */
public class UserAndUserInfoConvert {

  public static User toUser(UserInfoDto userInfoDto){
    User user = User.builder()
        .name(userInfoDto.getName())
        .phone(userInfoDto.getPhone())
        .enabled(userInfoDto.getEnabled())
        .token(userInfoDto.getToken())
        .email(userInfoDto.getEmail())
        .registDate(userInfoDto.getRegistDate())
        .build();
    user.setId(userInfoDto.getUserId());
    return user;
  }

  public static UserInfoDto toUserInfoDto(User user){
    UserInfoDto userInfoDto = UserInfoDto.builder()
        .userId(user.getId())
        .name(user.getName())
        .phone(user.getPhone())
        .enabled(user.getEnabled())
        .token(user.getToken())
        .email(user.getEmail())
        .registDate(user.getRegistDate())
        .build();
    return userInfoDto;
  }

}

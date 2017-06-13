package cn.wcode.controller;

import cn.wcode.dto.Result;
import cn.wcode.dto.UserInfoDto;
import cn.wcode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by homiss on 2017/6/8.
 */
@RestController
@RequestMapping("/api/app/user")
public class AppUserController {

  @Autowired
  private UserService userService;

  /**
   * 用户注册
   */
  @RequestMapping("/v1/regist")
  @ResponseBody
  public Result<String> regist(String name, String phone, String password){
    userService.regist(name, phone, password);
    return new Result<>("注册账号成功～");
  }

  /**
   * 三方用户注册
   */
  @RequestMapping("/v1/third/regist")
  @ResponseBody
  public Result<String> thirdRegist(){
    return new Result<>("");
  }

  /**
   * 用户登录
   */
  @RequestMapping("/v1/login")
  @ResponseBody
  public Result<UserInfoDto> login(String phone, String password){
    UserInfoDto userInfo = null;
    try {
      userInfo = userService.login(phone, password);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e.getMessage());
      return new Result<>(e.getMessage(), false);
    }
    return new Result<>(userInfo);
  }


}

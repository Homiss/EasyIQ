package cn.wcode.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by homiss on 2017/6/9.
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {

  private Integer userId;
  /**
   * 用户名
   */
  private String name;

  private String headpic;

  /**
   * 邮箱
   */
  private String email;

  /**
   * 联系电话
   */
  private String phone;

  /**
   * 是否可用 0,不可用 1，可用
   */
  private Integer enabled;

  private String token;

  private Date registDate;

}

package cn.wcode.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Table(name = "user_info")
public class UserInfo extends BaseEntity{

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户类型
     */
    private String usertype;

    /**
     * 是否可用
     */
    private Integer enabled;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * QQ
     */
    private String qq;

    private String email;

    /**
     * 联系电话
     */
    private String tel;

}
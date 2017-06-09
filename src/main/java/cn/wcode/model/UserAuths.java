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
@Table(name = "user_auths")
public class UserAuths extends BaseEntity{
    /**
     * 用户的唯一标示
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 该方式的用户名或ID
     */
    private String identity;

    /**
     * 登录方式
     */
    @Column(name = "identity_type")
    private String identityType;

    /**
     * 对于第一方是密码，对于第三方是access_token和refresh_token
     */
    private String credential;

    /**
     * 是否是第一方登录：0，不是 1，是
     */
    @Column(name = "first_party")
    private Byte firstParty;

}
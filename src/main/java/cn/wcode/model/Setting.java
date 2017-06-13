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
public class Setting extends BaseEntity{

    @Column(name = "user_id")
    private Integer userId;

    /**
     * 当前选中题库id
     */
    @Column(name = "q_group_id")
    private Integer qGroupId;

    /**
     * 背题模式：0，顺序 1，随机
     */
    @Column(name = "recite_model")
    private Integer reciteModel;

    /**
     * 每日背题数
     */
    @Column(name = "recite_num")
    private Integer reciteNum;

}
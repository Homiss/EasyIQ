package cn.wcode.model;

import java.util.Date;
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
@Table(name = "recite_record")
public class ReciteRecord extends BaseEntity{

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "last_date")
    private Date lastDate;

    private Integer level;

    private Integer strange;

    @Column(name = "next_date")
    private Date nextDate;

    /**
     * 是否需要复习 0,不需要 1,需要
     */
    @Column(name = "need_recite")
    private Integer needRecite;

}
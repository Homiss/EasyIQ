package cn.wcode.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "recite_record")
public class ReciteRecord extends BaseEntity {

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "last_time")
    private Date lastTime;

    private Integer stage;

    private Integer strange;

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return question_id
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    /**
     * @return start_time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return last_time
     */
    public Date getLastTime() {
        return lastTime;
    }

    /**
     * @param lastTime
     */
    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * @return stage
     */
    public Integer getStage() {
        return stage;
    }

    /**
     * @param stage
     */
    public void setStage(Integer stage) {
        this.stage = stage;
    }

    /**
     * @return strange
     */
    public Integer getStrange() {
        return strange;
    }

    /**
     * @param strange
     */
    public void setStrange(Integer strange) {
        this.strange = strange;
    }
}
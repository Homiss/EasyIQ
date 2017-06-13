package cn.wcode.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by homiss on 2017/6/13.
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReciteQuestionDto {

  private Long id;
  private String question;
  private Integer questionId;
  private String answer;
  private Integer level;
  private Integer strange;
  private String groupName;


}

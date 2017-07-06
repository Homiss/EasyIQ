package cn.wcode.mapper;

import cn.wcode.model.Question;
import cn.wcode.util.MyMapper;
import java.util.List;

public interface QuestionMapper extends MyMapper<Question> {

  List<Question> getByQuestionGroupId(int groupId);

  Integer selectCountByGroupId(String groupId);

  Integer selectIdByQuestion(String question);
}
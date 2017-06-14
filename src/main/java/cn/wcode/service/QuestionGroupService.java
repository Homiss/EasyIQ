package cn.wcode.service;

import cn.wcode.mapper.QuestionGroupMapper;
import cn.wcode.model.QuestionGroup;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by homiss on 2017/6/14.
 */
@Service
public class QuestionGroupService {

  @Autowired
  private QuestionGroupMapper questionGroupMapper;


  public List<QuestionGroup> selectAll() {
    return questionGroupMapper.selectAll();
  }
}

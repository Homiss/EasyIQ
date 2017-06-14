package cn.wcode.mapper;

import cn.wcode.dto.ReciteQuestionDto;
import cn.wcode.model.ReciteRecord;
import cn.wcode.util.MyMapper;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ReciteRecordMapper extends MyMapper<ReciteRecord> {

  List<ReciteQuestionDto> selectTodayTask();

  Integer selectCountByUserIdAndGroupId(@Param("userId") Integer userId, @Param("groupId") String groupId);

  Integer selectHasReciteRecordNum(@Param("userId") Integer userId, @Param("groupId") String groupId);
}
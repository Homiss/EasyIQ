package cn.wcode.mapper;

import cn.wcode.model.ReciteRecord;
import cn.wcode.util.MyMapper;
import java.util.List;

public interface ReciteRecordMapper extends MyMapper<ReciteRecord> {

  List<ReciteRecord> selectTodayTask();
}
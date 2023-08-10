package zq.scoremanager.mapper;

import org.apache.ibatis.annotations.Mapper;
import zq.scoremanager.bean.StudentCourseAndScore;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<StudentCourseAndScore> getAllScores(String studentNo);

}

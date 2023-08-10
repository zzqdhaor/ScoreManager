package zq.scoremanager.mapper;

import org.apache.ibatis.annotations.Mapper;
import zq.scoremanager.bean.Course;
import zq.scoremanager.bean.StatisticInfo;
import zq.scoremanager.bean.StudentScore;

import java.util.List;

@Mapper
public interface TeacherMapper {
    List<Course> getTeacherCourses(String teacherNo);

    List<StudentScore> getStudentScores(String teacherNo, int courseId);

    void updateScore(int courseId, Integer score, String studentNo, String teacherNo);

    StatisticInfo getStatisticInfo(int courseId, String teacherNo);
}

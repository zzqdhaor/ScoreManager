package zq.scoremanager.mapper;

import org.apache.ibatis.annotations.Mapper;
import zq.scoremanager.bean.Course;
import zq.scoremanager.bean.Teach;
import zq.scoremanager.bean.User;

import java.util.List;
@Mapper
public interface TeachMapper {

    List<Teach> getAllTeach();

    void addTeach(String teacherNo, int courseId);

    List<User> getAllTeachers();
    List<Course> getAllCourses();
    List<User> getAllStudents();
    void registerStudents(int courseId, String teacherNo, List<String> students);

    List<User> getStudentsByTeach(String teacherNo, int courseId);

    void removeTeach(String teacherNo, int courseId);
}

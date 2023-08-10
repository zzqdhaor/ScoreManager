package zq.scoremanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zq.scoremanager.Result;
import zq.scoremanager.bean.Course;
import zq.scoremanager.bean.TeachAddBean;
import zq.scoremanager.bean.TeachInfo;
import zq.scoremanager.bean.User;
import zq.scoremanager.mapper.TeachMapper;

import java.util.List;

@RestController
@RequestMapping("/api/teach")
public class TeachController {

    @Autowired
    private TeachMapper mapper;

    @GetMapping("/all")
    public Result getAllTeach() {
        return Result.success(mapper.getAllTeach());
    }

    @GetMapping("/students")
    public Result getAllStudents() {
        return Result.success(mapper.getAllStudents());
    }

    @GetMapping("/info")
    public Result getInfo() {
        List<Course> courses = mapper.getAllCourses();
        List<User> teachers = mapper.getAllTeachers();
        TeachInfo info = new TeachInfo(teachers, courses);
        return Result.success(info);
    }

    @PostMapping("/add-teach")
    public Result addTeach(@RequestBody TeachAddBean bean) {
        mapper.addTeach(bean.getTeacherNo(), bean.getCourseId());
        mapper.registerStudents(bean.getCourseId(), bean.getTeacherNo(), bean.getStudents());
        return Result.success(null);
    }

    @GetMapping("/get-info")
    public Result getStudentsByTeach(String teacherNo, int courseId) {
        return Result.success(mapper.getStudentsByTeach(teacherNo, courseId));
    }

    @PostMapping("/remove-teach")
    public Result removeTeach(String teacherNo, int courseId) {
        mapper.removeTeach(teacherNo, courseId);
        return Result.success(null);
    }

}

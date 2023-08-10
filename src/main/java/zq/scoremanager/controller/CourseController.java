package zq.scoremanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zq.scoremanager.Result;
import zq.scoremanager.mapper.CourseMapper;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseMapper mapper;

    @GetMapping("/all")
    public Result getAllCourse() {
        return Result.success(mapper.getAllCourse());
    }

    @PostMapping("/add-course")
    public Result addCourse(String name, int score, int time) {
        mapper.addCourse(name, score, time);
        return Result.success(null);
    }

    @PostMapping("/delete-course")
    public Result deleteCourse(int id) {
        mapper.deleteCourse(id);
        return Result.success(null);
    }

}

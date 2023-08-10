package zq.scoremanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zq.scoremanager.Result;
import zq.scoremanager.bean.StudentCourseAndScore;
import zq.scoremanager.bean.User;
import zq.scoremanager.local.Locals;
import zq.scoremanager.mapper.StudentMapper;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentMapper mapper;

    @GetMapping("/scores")
    public Result getAllScores() {
        List<StudentCourseAndScore> scaList = mapper.getAllScores(getNo());
        return Result.success(scaList);
    }

    private String getNo() {
        return ((User) Locals.servletRequest.get().getSession().getAttribute("user")).getNo();
    }

}

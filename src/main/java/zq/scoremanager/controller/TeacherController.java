package zq.scoremanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zq.scoremanager.Result;
import zq.scoremanager.bean.User;
import zq.scoremanager.local.Locals;
import zq.scoremanager.mapper.TeacherMapper;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    private TeacherMapper mapper;

    @GetMapping("/get-my-teach")
    public Result getMyTeach() {
        return Result.success(mapper.getTeacherCourses(getNo()));
    }

    private static String getNo() {
        return ((User) Locals.servletRequest.get().getSession().getAttribute("user")).getNo();
    }

    @GetMapping("/get-scores")
    public Result getScores(int courseId) {
        return Result.success(mapper.getStudentScores(getNo(), courseId));
    }

    @PostMapping("/update-score")
    public Result updateScore(int score, int courseId, String studentNo) {
        mapper.updateScore(courseId, score == -1 ? null : score, studentNo, getNo());
        return Result.success(null);
    }

    @GetMapping("/get-statistic-info")
    public Result getStatisticInfo(int courseId) {
        return Result.success(mapper.getStatisticInfo(courseId, getNo()));
    }

}

package zq.scoremanager.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseAndScore {

    private String courseName;
    private Integer courseTime;
    private Integer courseScore;
    private Integer score;
    private String teacherName;

}

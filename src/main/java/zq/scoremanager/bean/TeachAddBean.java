package zq.scoremanager.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeachAddBean {

    private int courseId;
    private String teacherNo;
    private List<String> students;

}

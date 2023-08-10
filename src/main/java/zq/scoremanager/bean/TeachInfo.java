package zq.scoremanager.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeachInfo {
    private List<User> teacher;
    private List<Course> course;
}

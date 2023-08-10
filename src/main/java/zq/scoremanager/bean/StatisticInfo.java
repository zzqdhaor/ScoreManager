package zq.scoremanager.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticInfo {

    private int number;
    private int averageScore;
    private int highestScore;
    private int passPercentage;
    private int greatPercentage;

}

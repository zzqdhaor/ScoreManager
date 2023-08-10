package zq.scoremanager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private Object data;
    private int status;
    private String message;

    public static Result success(Object data) {
        Result result = new Result();
        result.status = 0;
        result.message = "OK";
        result.data = data;
        return result;
    }

    public static Result error(String message, Object data) {
        Result result = new Result();
        result.status = 1;
        result.message = message;
        result.data = data;
        return result;
    }

}

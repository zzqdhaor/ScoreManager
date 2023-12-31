package zq.scoremanager;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> error(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(Result.error("Internal Error!", null), HttpStatusCode.valueOf(200));
    }

}

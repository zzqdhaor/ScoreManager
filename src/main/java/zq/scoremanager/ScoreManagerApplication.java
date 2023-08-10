package zq.scoremanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ScoreManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScoreManagerApplication.class, args);
    }

}

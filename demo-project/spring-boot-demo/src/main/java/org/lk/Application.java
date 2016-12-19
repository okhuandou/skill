package org.lk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lk on 2016/12/14.
 */
@SpringBootApplication
@RestController
public class Application {

    @RequestMapping("/")
    public String get() {
        return "hello world ! ";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

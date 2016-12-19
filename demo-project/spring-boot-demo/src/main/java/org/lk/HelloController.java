package org.lk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lk on 2016/12/14.
 */

@Controller
public class HelloController {

    @RequestMapping("/hello/{user}")
    public String helleUser(@PathVariable("user") String user, Model model) {
        model.addAttribute("name",user);
        return "hello";
    }

}

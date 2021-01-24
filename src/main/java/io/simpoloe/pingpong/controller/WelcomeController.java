package io.simpoloe.pingpong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {

    @ResponseBody
    @RequestMapping({"/", "/index", "/welcome"})
    public String welcome() {
        return "Springboot Pingpong";
    }
}

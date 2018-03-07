package com.carleton.CapstoneSystem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.*;

@Controller
public class CapstoneSystemConfiguration extends WebMvcConfigurerAdapter {
    @RequestMapping(value = "/{[path:[^\\.]*}")
    public String redirect() {
        return "forward:/";
    }
}

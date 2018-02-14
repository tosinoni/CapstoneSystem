package com.carleton.CapstoneSystem;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
public class CapstoneSystemConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login/**")
    public String app() {
        return "login";
    }
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        super.addViewControllers(registry);
//        registry.addViewController("/").setViewName("static/home/home.html");
//        registry.addViewController("/login").setViewName("static/login/login.html");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    }
}

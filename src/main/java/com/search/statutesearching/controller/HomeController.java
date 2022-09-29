package com.search.statutesearching.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
//    @RequestMapping(value = "/", method= RequestMethod.GET)
    @GetMapping("/")
    public String goHome(HttpServletRequest request) {
        return "index";
    }
}
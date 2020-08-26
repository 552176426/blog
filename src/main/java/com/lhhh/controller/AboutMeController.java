package com.lhhh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: lhhh
 * @date: Created in 2020/8/1
 * @description:
 * @version:1.0
 */
@Controller
public class AboutMeController {

    @RequestMapping("/aboutMe")
    public String aboutMe(){
        return "aboutme";
    }
}

package com.powernode.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 饺子！
 * @since 2024/7/1 9:34
 **/

@Controller
public class FirstController {
    @RequestMapping(value = "/")
    public String haha(){
        System.out.println("正在处理请求");
        return "index";
    }
}

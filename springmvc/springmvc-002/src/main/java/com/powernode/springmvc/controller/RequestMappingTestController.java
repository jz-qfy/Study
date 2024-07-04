package com.powernode.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 饺子！
 * @since 2024/7/1 15:36
 **/

@Controller
public class RequestMappingTestController {
    @RequestMapping(value="/testRESTful/{id}/{username}/{age}")
    public String testRESTful(
            @PathVariable("id")
            int id,
            @PathVariable("username")
            String username,
            @PathVariable("age")
            int age){
        System.out.println(id + "," + username + "," + age);
        return "testRESTful";
    }
}

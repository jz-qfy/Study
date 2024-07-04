package com.powernode.springmvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 饺子！
 * @since 2024/7/1 16:51
 **/

@Controller
public class RequestScopeTestController {

    @RequestMapping("/testServletAPI")
    public String testServletAPI(HttpServletRequest request) {
        request.setAttribute("testRequestScope", "在SpringMVC中使用原生Servlet API实现request域数据共享");
        return "view";
    }

    @RequestMapping("/testModel1")
    public String testModel1(Model model) {
        model.addAttribute("testRequestScope", "在SpringMVC中使用Model接口实现request域数据共享");
        return "view";
    }
}

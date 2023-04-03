package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ExceptionController implements ErrorController {
    @RequestMapping("/error")
    public RedirectView handle(Exception e){
        return new RedirectView("/error/404");
    }
}

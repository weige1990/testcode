package com.my.test.cglib.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cglib")
public class CglibController {

    @RequestMapping("/cglibWork")
    public void cglibWork()
    {

    }
}

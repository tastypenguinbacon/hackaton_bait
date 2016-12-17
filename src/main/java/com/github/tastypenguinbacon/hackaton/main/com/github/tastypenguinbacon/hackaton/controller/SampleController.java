package com.github.tastypenguinbacon.hackaton.main.com.github.tastypenguinbacon.hackaton.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pingwin on 17.12.16.
 */
@RestController
public class SampleController {
    @RequestMapping(path="hello world")
    public String helloWorld() {
        return "Hello World!";
    }
}

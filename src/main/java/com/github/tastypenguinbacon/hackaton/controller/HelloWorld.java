package com.github.tastypenguinbacon.hackaton.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pingwin on 17.12.16.
 */
@RestController
@RequestMapping("helloworld")
public class HelloWorld {

    @RequestMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String helloWorld() {
        return "Hello World";
    }
}

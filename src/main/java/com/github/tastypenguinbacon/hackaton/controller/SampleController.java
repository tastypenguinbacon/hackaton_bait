package com.github.tastypenguinbacon.hackaton.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by pingwin on 17.12.16.
 */
@RestController
@RequestMapping("cudo")
public class SampleController {

    @RequestMapping(path = "hello world", produces = MediaType.TEXT_HTML_VALUE)
    public String helloWorld() {
        try (InputStream source = getClass().getResourceAsStream("/hello_world.html")) {
            Scanner scanner = new Scanner(source);
            StringBuilder builder = new StringBuilder();
            while(scanner.hasNext()) {
                builder.append(scanner.nextLine());
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

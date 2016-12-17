package com.github.tastypenguinbacon.hackaton.controller;

import com.github.tastypenguinbacon.hackaton.relational_data_access.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by pingwin on 17.12.16.
 */
@RestController
@RequestMapping("cudo")
public class SampleController {

    @Autowired
    public JdbcTemplate jdbcTemplate;

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

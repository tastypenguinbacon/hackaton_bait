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

    @RequestMapping(path = "hello space", produces = MediaType.TEXT_HTML_VALUE)
    public String helloSpace() {
        try (InputStream source = getClass().getResourceAsStream("/hello_space.html")) {
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

    @RequestMapping(path = "hello base", produces = MediaType.TEXT_PLAIN_VALUE)
    public String helloBase() {

        //jdbcTemplate.execute("DROP TABLE students IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS students " +
                        "(id SERIAL, name VARCHAR(255), academy VARCHAR(255)) "
        );

        String student_name = "Josh";
        String academy = "AGH";
        PreparedStatement stmt = null;
        Connection conn = null;
        javax.sql.DataSource DS = jdbcTemplate.getDataSource();
        try {
            conn = DS.getConnection();
            stmt = conn.prepareStatement("INSERT INTO students(name, academy) VALUES (?,?)");
            stmt.setString( 1, student_name );
            stmt.setString( 2, academy );
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Split up the array of whole name and academy into an array of name/Academy
        List<Object[]> splitUpNames = Arrays.asList("John AGH", "Jeff UR", "Josh PK", "Josh IG").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        //Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO students(name, academy) VALUES (?,?)", splitUpNames);

        List<Student> query = jdbcTemplate.query(
                "SELECT id, name, academy FROM students WHERE name = ?", new Object[]{"Josh"},
                (rs, rowNum) -> new Student(rs.getLong("id"), rs.getString("name"), rs.getString("academy"))
        );
        return query.toString();
    }
}

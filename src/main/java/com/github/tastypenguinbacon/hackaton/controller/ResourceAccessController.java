package com.github.tastypenguinbacon.hackaton.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by pingwin on 17.12.16.
 */
@RestController
public class ResourceAccessController {

    @RequestMapping(value = "/css/{cssName}", method = RequestMethod.GET)
    public String getCssFile(@PathVariable String cssName) {
        try (InputStream css = getClass().getResourceAsStream("/css/" + cssName + ".css")) {
            Scanner scanner = new Scanner(css);
            StringBuilder builder = new StringBuilder();
            while(scanner.hasNext()) {
                builder.append(scanner.nextLine());
            }
            return builder.toString();
        } catch (IOException e) {
            return null;
        }
    }

    @RequestMapping(value = "/script/{scriptName}", method = RequestMethod.GET)
    public String getScript(@PathVariable String scriptName) {
        try (InputStream script = getClass().getResourceAsStream("/script/" + scriptName)) {
            Scanner scanner = new Scanner(script);
            StringBuilder builder = new StringBuilder();
            while(scanner.hasNext()) {
                builder.append(scanner.nextLine());
            }
            return builder.toString();
        } catch (IOException e) {
            return null;
        }
    }
}

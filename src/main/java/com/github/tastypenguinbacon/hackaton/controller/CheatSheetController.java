package com.github.tastypenguinbacon.hackaton.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tastypenguinbacon.hackaton.data.CheatSheet;
import com.github.tastypenguinbacon.hackaton.database.DataBaseConnection;
import org.springframework.web.bind.annotation.*;

/**
 * Created by pingwin on 17.12.16.
 */
@RestController
@RequestMapping("cheatsheet")
public class CheatSheetController {

    @RequestMapping(value = "/query")
    public String getCheatSheetAccordingToRegex(@RequestParam String regex) throws JsonProcessingException {
        DataBaseConnection db = new DataBaseConnection();
        CheatSheet cheatSheet = db.getCheatSheetByRegex(regex);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(cheatSheet);
    }

    @RequestMapping(value = "/{cheatSheetName}", method = RequestMethod.POST)
    public String addElementsToCheatSheet(@PathVariable String cheatSheetName,
                                          @RequestBody CheatSheet body) {
        DataBaseConnection db = new DataBaseConnection();
        boolean successful = db.addCheatSheet(cheatSheetName, body);
        return successful ? "SUCCESS" : "FAIL";
    }
}

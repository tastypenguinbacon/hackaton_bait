package com.github.tastypenguinbacon.hackaton.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tastypenguinbacon.hackaton.data.CheatSheet;
import com.github.tastypenguinbacon.hackaton.databaseaccess.CheatSheetManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by pingwin on 17.12.16.
 */
@RestController
public class CheatSheetController {
    @Autowired
    private CheatSheetManager cheatSheets;

    @RequestMapping(value = "/cheatsheet", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getCheatSheetNames() {
        cheatSheets.initializeDataBase();
        return cheatSheets.getAllCheatSheetNames();
    }

    @RequestMapping(value = "/cheatsheet/{cheatsheetName}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public CheatSheet getCheatSheetAccordingToRegex(@PathVariable String cheatSheetName, @RequestParam String like)
            throws JsonProcessingException {
        return cheatSheets.getCheatSheet(cheatSheetName, like);
    }

    @RequestMapping(value = "/{cheatSheetName}", method = RequestMethod.POST)
    public void addElementsToCheatSheet(@PathVariable String cheatSheetName,
                                          @RequestBody CheatSheet body) {
        cheatSheets.mergeToCheatSheet(cheatSheetName, body);
    }
}


package com.github.tastypenguinbacon.hackaton.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tastypenguinbacon.hackaton.data.CheatSheet;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by pingwin on 17.12.16.
 */
@RestController
public class CheatSheetController {

    @RequestMapping(value = "/cheat_sheet", method = RequestMethod.GET)
    public List<String> getCheatSheetNames() {
        return null;
    }

    @RequestMapping(value = "/{user}/cheat_sheet/{cheatsheetName}",
            method = RequestMethod.GET)
    public String getCheatSheetAccordingToRegex(@RequestParam String like)
            throws JsonProcessingException {
        return null;
    }

    @RequestMapping(value = "/{cheatSheetName}", method = RequestMethod.POST)
    public String addElementsToCheatSheet(@PathVariable String cheatSheetName,
                                          @RequestBody CheatSheet body) {
        return null;
    }
}

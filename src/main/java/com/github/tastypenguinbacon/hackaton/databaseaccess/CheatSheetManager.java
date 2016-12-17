package com.github.tastypenguinbacon.hackaton.databaseaccess;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Dominik Baljon on 17.12.2016.
 */
@Component
public class CheatSheetManager {

    public List<String> getAllCheatSheetNames() {
        return Arrays.asList("cudo", "cudo", "cudo", "bajgos");
    }
}

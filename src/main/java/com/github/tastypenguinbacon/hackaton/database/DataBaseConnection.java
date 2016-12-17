package com.github.tastypenguinbacon.hackaton.database;

import com.github.tastypenguinbacon.hackaton.data.CheatSheet;

/**
 * Created by pingwin on 17.12.16.
 */
public class DataBaseConnection {
    public CheatSheet getCheatSheetByRegex(String regex) {
        // test purpose only
        CheatSheet cheatSheet = new CheatSheet();
        for (int i = 0; i < 100; i++) {
            cheatSheet.addAnotherCheatEntry(Integer.toString(i), Integer.toBinaryString(i));
        }
        return cheatSheet;
    }

    public boolean addCheatSheet(String cheatSheetName, CheatSheet body) {
        return false;
    }
}

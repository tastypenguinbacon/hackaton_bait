package com.github.tastypenguinbacon.hackaton.databaseaccess;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tastypenguinbacon.hackaton.data.CheatSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import static com.github.tastypenguinbacon.hackaton.data.CheatSheet.CheatSheetElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominik Baljon on 17.12.2016.
 */
@Component
public class CheatSheetManager {

    @Autowired
    public JdbcTemplate jdbcTemplate;
    private ObjectMapper OM = new ObjectMapper();

    public void sheetsInitialize () {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS cheat_sheet_names " +
                            "(id SERIAL, name VARCHAR(255)) "
        );

        List<CheatSheetElement> cheatSheet = new ArrayList<CheatSheetElement>();

        CheatSheetElement temp = new CheatSheetElement("What is BCD?", "Binary coded decimal.");

        try {
            String serialized = new ObjectMapper().writeValueAsString(temp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    public List<String> getAllCheatSheetNames() {

        List<String> cheatSheetNames = new ArrayList<String>();

        jdbcTemplate.query(
            "SELECT id, name, academy FROM cheat_sheet_names",
            (rs, rowNum) -> new String(rs.getString("name"))).forEach(String -> cheatSheetNames.add(String));

        return cheatSheetNames;
    }

    public void mergeToCheatSheet(String name, CheatSheet cs) {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS " + name +
                "(id SERIAL, question VARCHAR(255), academy VARCHAR(255)) "
        );
    }

    public void queryShett (String Shett) {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS cheatsheet1 " +
                "(id SERIAL, question VARCHAR(255), academy VARCHAR(255)) "
        );
    }


}

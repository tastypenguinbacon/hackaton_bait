package com.github.tastypenguinbacon.hackaton.databaseaccess;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tastypenguinbacon.hackaton.data.CheatSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import static com.github.tastypenguinbacon.hackaton.data.CheatSheet.CheatSheetElement;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dominik Baljon on 17.12.2016.
 */
@Component
public class CheatSheetManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private String cheatSheetTableName = "cheat_sheet_names";
    private String firtsCheatSheetName = "cheatshett_1";

    public void initializeDataBase () {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS  " + cheatSheetTableName +
                            "(id SERIAL, name VARCHAR(255)) "
        );

        CheatSheetElement temp = new CheatSheetElement("What is BCD?", "Binary coded decimal.");

        //jdbcTemplate.execute("INSERT INTO " + cheatSheetTableName + "(name) VALUES (?)", firtsCheatSheetName);

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS " + firtsCheatSheetName +
                "(id SERIAL, question VARCHAR(255), answer VARCHAR(255)) ");

        // insert firstCheatSheetName


        String question;
        String answer;
        PreparedStatement stmt = null;
        Connection conn = null;
        javax.sql.DataSource DS = jdbcTemplate.getDataSource();

        try {
            ObjectMapper mapper = new ObjectMapper();
            String serialized = mapper.writeValueAsString(temp);
            mapper.writeValue(new File("cheatsheet1.json"), serialized);
            question = mapper.readValue(new File("cheatsheet1.json"), String.class);
            answer = mapper.readValue(new File("cheatsheet1.json"), String.class);
            conn = DS.getConnection();
            stmt = conn.prepareStatement("INSERT INTO " + firtsCheatSheetName + " (question, answer ) VALUES (?,?)");
            stmt.setString(1, question);
            stmt.setString(2, answer);
            stmt.executeUpdate();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllCheatSheetNames() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS " + cheatSheetTableName +
                "(id SERIAL, name VARCHAR(255))");

        List<String> cheatSheetNames = new LinkedList<>();

        jdbcTemplate.query(
            "SELECT id, name FROM " + cheatSheetTableName,
            (rs, rowNum) -> new String(rs.getString("name"))).forEach(String -> cheatSheetNames.add(String));

        return cheatSheetNames;
    }

    public void mergeToCheatSheet(String name, CheatSheet cs) {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS " + name +
                "(id SERIAL, question VARCHAR(255), answer VARCHAR(255)) "
        );

        PreparedStatement stmt = null;
        Connection conn = null;
        javax.sql.DataSource DS = jdbcTemplate.getDataSource();

        for ( CheatSheetElement CSE: cs.getcheatSheetList()) {
            String question = CSE.getQuestion();
            String answer = CSE.getAnswer();
            try {
                conn = DS.getConnection();
                stmt = conn.prepareStatement("INSERT INTO " + name + " (question, answer ) VALUES (?,?)");
                stmt.setString(1, question);
                stmt.setString(2, answer);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public CheatSheet getCheatSheet(String Shett, String like) {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS " + cheatSheetTableName +
                "(id SERIAL, name VARCHAR(255))");

        List<String> cheatSheetNames = new LinkedList<>();

        jdbcTemplate.query(
                "SELECT id, name FROM " + cheatSheetTableName,
                (rs, rowNum) -> new String(rs.getString("name"))).forEach(String -> cheatSheetNames.add(String));

        List<CheatSheetElement> CSEL = new ArrayList<CheatSheetElement>();
        if (cheatSheetNames.contains(Shett))
        {
            jdbcTemplate.query(
                    "SELECT id, naqme FROM " + Shett,
                    (rs, rowNum) -> new CheatSheetElement(rs.getString("question"), rs.getString("qnswer"))).forEach(CheatSheetElement -> CSEL.add(CheatSheetElement));
        }

        CheatSheet CS = new CheatSheet(CSEL);
        return CS;
    }
}

package com.github.tastypenguinbacon.hackaton.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by pingwin on 17.12.16.
 */
public class CheatSheetTest {
    @Test
    public void whenElementAddedIsAbleToRetrieveIt() {
        CheatSheet cheatSheet = new CheatSheet();
        cheatSheet.addAnotherCheatEntry("cudo", "cudo");
        assertEquals("cudo", cheatSheet.getAnswer("cudo"));
    }

    @Test
    public void cheatSheetIsSerializableToJson() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        CheatSheet cheatSheet = new CheatSheet();
        for (int i = 0; i < 10; i++) {
            cheatSheet.addAnotherCheatEntry(Integer.toString(i), Integer.toBinaryString(i));
        }
        objectMapper.writeValueAsString(cheatSheet);
    }

    @Test
    public void whenGivenAValidJsonIsAbleToSerialize() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String validJson = "{\"cheatSheet\":[{\"question\":\"0\",\"answer\":\"0\"},{\"question\":\"1\",\"answer\":\"1\"},{\"question\":\"2\",\"answer\":\"10\"}]}";
        CheatSheet cheatSheet = objectMapper.readValue(validJson, CheatSheet.class);
        System.out.println(cheatSheet);
    }
}
package com.github.tastypenguinbacon.hackaton.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Created by pingwin on 17.12.16.
 */
public class CheatSheet implements Serializable {
    @JsonProperty
    private List<CheatSheetElement> cheatSheet = new LinkedList<>();

    public CheatSheet() {
    }

    public void addAnotherCheatEntry(String question, String answer) {
        Objects.nonNull(question);
        Objects.nonNull(answer);
        cheatSheet.add(new CheatSheetElement(question, answer));
    }

    public String getAnswer(String question) {
        for (CheatSheetElement entry : cheatSheet) {
            if (entry.question.equals(question)) {
                return entry.answer;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return cheatSheet.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheatSheet that = (CheatSheet) o;

        return cheatSheet != null ? cheatSheet.equals(that.cheatSheet) : that.cheatSheet == null;
    }

    @Override
    public int hashCode() {
        return cheatSheet != null ? cheatSheet.hashCode() : 0;
    }

    public static class CheatSheetElement implements Serializable {
        @JsonProperty
        private String question;
        @JsonProperty
        private String answer;

        public CheatSheetElement(String question, String answer) {
            this.question = question;
            this.answer = answer;
        }

        public CheatSheetElement() {
        }

        public String getQuestion() {
            return question;
        }

        public String getAnswer() {
            return answer;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CheatSheetElement that = (CheatSheetElement) o;

            return (question != null ? question.equals(that.question) : that.question == null) && (answer != null ? answer.equals(that.answer) : that.answer == null);
        }

        @Override
        public int hashCode() {
            int result = question != null ? question.hashCode() : 0;
            result = 31 * result + (answer != null ? answer.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "[" + "question=" + question + ", answer=" + answer + "]";
        }
    }
}


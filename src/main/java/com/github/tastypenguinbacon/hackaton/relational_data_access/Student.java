package com.github.tastypenguinbacon.hackaton.relational_data_access;

/**
 * Created by Dominik Baljon on 17.12.2016.
 */
public class Student {
    private long id;
    private String Name;
    private String Academy;

    public Student( long id, String Name, String Academy) {
        this.id = id;
        this.Name = Name;
        this.Academy = Academy;
    }

    @Override
    public String toString() {
        return String.format(
            "Student[id=%d, Name='%s', Academy='%s']",
                id, Name, Academy
        );
    }
}

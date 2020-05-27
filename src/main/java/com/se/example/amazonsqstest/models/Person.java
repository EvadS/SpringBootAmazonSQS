package com.se.example.amazonsqstest.models;

public class Person {
    String name;
    String lname;

    public Person() {
    }

    public Person(String name, String lname) {
        this.name = name;
        this.lname = lname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lname='" + lname + '\'' +
                '}';
    }
}

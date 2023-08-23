package com.api.ura.demo.model;

public class URAModel {
    int age;
    String name;

    int id;

    public URAModel(int id, int age, String name) {
        this.age = age;
        this.name = name;
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

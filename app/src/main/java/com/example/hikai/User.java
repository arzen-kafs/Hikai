package com.example.hikai;

public class User {
    int id;
    private String name,standard,password;

    public User(int id, String name, String standard,String password) {
        this.id = id;
        this.standard= standard;

        this.name = name;
        this.password=password;
    }

    public int getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }
}

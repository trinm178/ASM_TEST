package com.example.asm_mob403.model;

public class Users {
    int id;
    String name;
    String email;
    String password;
    String create_date;
    String update_date;

    public Users(int id, String name, String email, String password, String create_date, String update_date) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.create_date = create_date;
        this.update_date = update_date;
    }

    public int getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }
}

package com.example.asm_mob403.model;

import java.io.Serializable;

public class SinhVien implements Serializable {
    int id;
    String name;
    String students_code;
    String birthday;
    String phone;
    String address;

    public SinhVien(int id, String name, String students_code, String birthday, String phone, String address) {
        this.id = id;
        this.name = name;
        this.students_code = students_code;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
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

    public String getStudents_code() {
        return students_code;
    }

    public void setStudents_code(String students_code) {
        this.students_code = students_code;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

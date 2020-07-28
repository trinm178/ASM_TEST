package com.example.asm_mob403.model;

public class SinhVien {
    int id;
    String name;
    String students_code;
    int birthday;
    int phone;
    String address;

    public SinhVien(int id, String name, String students_code, int birthday, int phone, String address) {
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

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

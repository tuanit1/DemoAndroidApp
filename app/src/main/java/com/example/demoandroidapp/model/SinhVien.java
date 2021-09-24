package com.example.demoandroidapp.model;

import java.util.Date;

public class SinhVien {
    private String username;
    private String password;
    private String name;
    private int age;
    private Date birthday;
    private String address;
    private String image;

    //alt + insert

    public SinhVien(String username, String password, String name, int age, Date birthday, String address, String image) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.address = address;
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

package com.example.databaseapp;

public class People {
    private int id;
    private String name;
    private int year;
    private byte[] img;

    People(int id, String name, int year, byte[] img) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}

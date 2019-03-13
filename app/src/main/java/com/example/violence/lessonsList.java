package com.example.violence;


public class lessonsList {
    private String name;
    private int id;

    public lessonsList(String name, int id){
        this.name = name;
        this.id = id;
    }


    public void setName(String name){
        this.name = name;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public int getId(){
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}

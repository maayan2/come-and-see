package com.example.comeandsee;

public class Volunteer {

    private String type;
    private String area;
    private String name;
    private String description;

    public Volunteer() {}

    public Volunteer (String type, String area, String name, String description){
        this.type = type;
        this.area = area;
        this.name = name;
        this.description = description;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

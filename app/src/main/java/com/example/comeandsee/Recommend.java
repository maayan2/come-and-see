package com.example.comeandsee;

public class Recommend {
        private Boolean accessibility;
        private String seasons;
        private String area;
        private String name;
        private String description;

        public Recommend(){}

    public Recommend(Boolean accessibility, String seasons, String area, String name, String description){
        this.accessibility = accessibility;
        this.seasons = seasons;
        this.name = name;
        this.description = description;
    }

    public Boolean getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(Boolean accessibility) {
        this.accessibility = accessibility;
    }

    public String getSeasons() {
        return seasons;
    }

    public void setSeasons(String seasons) {
        this.seasons = seasons;
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


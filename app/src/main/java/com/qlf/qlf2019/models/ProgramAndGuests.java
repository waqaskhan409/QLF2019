package com.qlf.qlf2019.models;

public class ProgramAndGuests {
    String name, image, position, description, order, day, moderatedby, time, title, venue;

    public ProgramAndGuests(String name, String image
            , String position, String description
            , String order, String day, String moderatedby
            , String time, String title, String venue) {
        this.name = name;
        this.image = image;
        this.position = position;
        this.description = description;
        this.order = order;
        this.day = day;
        this.moderatedby = moderatedby;
        this.time = time;
        this.title = title;
        this.venue = venue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public ProgramAndGuests() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getModeratedby() {
        return moderatedby;
    }

    public void setModeratedby(String moderatedby) {
        this.moderatedby = moderatedby;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}

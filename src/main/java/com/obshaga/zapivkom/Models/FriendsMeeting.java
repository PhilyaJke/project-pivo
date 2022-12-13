package com.obshaga.zapivkom.Models;

import lombok.Data;

@Data
public class FriendsMeeting {
    private String name;
    private String date;
    private String place;
    private String message;

    public FriendsMeeting(String name, String date, String place, String message) {
        this.name = name;
        this.date = date;
        this.place = place;
        this.message = message;
    }
}

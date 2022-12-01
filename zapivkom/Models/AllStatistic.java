package com.obshaga.zapivkom.Models;

public class AllStatistic {

    private int countComments;
    private int countUsers;
    private int countReviews;
    private String data;

    public AllStatistic(int countComments, int countUsers, int countReviews, String data) {
        this.countComments = countComments;
        this.countUsers = countUsers;
        this.countReviews = countReviews;
        this.data = data;
    }

    public int getCountComments() {
        return countComments;
    }

    public int getCountUsers() {
        return countUsers;
    }

    public int getCountReviews() {
        return countReviews;
    }

    public String getData() {
        return data;
    }
}

package com.obshaga.zapivkom.Models;

import com.obshaga.zapivkom.Entity.Rank;
import com.obshaga.zapivkom.Entity.Status;

public class UserProfileInfo {
    private String username;
    private String data;
    private Rank rank;
    private Long countOfRev;

    public UserProfileInfo(String username, String data, Rank rank, Long countOfRev) {
        this.username = username;
        this.data = data;
        this.rank = rank;
        this.countOfRev = countOfRev;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Long getCountOfRev() {
        return countOfRev;
    }

    public void setCountOfRev(Long countOfRev) {
        this.countOfRev = countOfRev;
    }
}

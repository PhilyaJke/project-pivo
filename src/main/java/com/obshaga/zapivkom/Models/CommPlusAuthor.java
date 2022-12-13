package com.obshaga.zapivkom.Models;

import com.obshaga.zapivkom.Entity.Rank;

public class CommPlusAuthor {

    private String text;
    private String data;
    private String author;
    private Rank authorRank;

    public CommPlusAuthor(String text, String data, String author, Rank authorRank) {
        this.text = text;
        this.data = data;
        this.author = author;
        this.authorRank = authorRank;
    }

    public Rank getAuthorRank() {
        return authorRank;
    }

    public void setAuthorRank(Rank authorRank) {
        this.authorRank = authorRank;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


}

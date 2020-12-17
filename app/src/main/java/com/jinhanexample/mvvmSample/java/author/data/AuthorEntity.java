package com.jinhanexample.mvvmSample.java.author.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "authorEntity")
public class AuthorEntity {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    int id ;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "books")
    String books;

    @ColumnInfo(name = "nation")
    String nation;

    public AuthorEntity(String name, String books, String nation) {
        this.name = name;
        this.books = books;
        this.nation = nation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBooks() {
        return books;
    }

    public void setBooks(String books) {
        this.books = books;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }
}

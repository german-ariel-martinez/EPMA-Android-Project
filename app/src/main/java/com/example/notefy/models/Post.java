package com.example.notefy.models;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "posts")
public class Post {

    // Variables
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String text;

    // Constructor
    public Post(String title, String text) {
        this.title = title;
        this.text = text;
    }

    // Getters n setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}

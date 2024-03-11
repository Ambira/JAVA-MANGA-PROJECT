package org.example.models;

public class Manga {
    private int id;
    private String title;
    private String author;
    private String status;
    private int volumes;


    // constructors
    public Manga(int id, String title, String author, String status, int volumes) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.status = status;
        this.volumes = volumes;
    }

    //getters and setters
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getVolumes() {
        return volumes;
    }

    public void setVolumes(int volumes) {
        this.volumes = volumes;
    }
}



package com.xtreme.blogapi.modelos;

import com.google.gson.annotations.SerializedName;

public class Post {
    private int id;
    private String titulo;
    @SerializedName("body")
    private String text;
    private int commets;
    private boolean liked;
    private int likes;
    private String tags[];
    private String useremail;
    private int userid;
    private String username;
    private int view;


    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getText() {
        return text;
    }

    public int getCommets() {
        return commets;
    }

    public boolean isLiked() {
        return liked;
    }

    public int getLikes() {
        return likes;
    }

    public String[] getTags() {
        return tags;
    }

    public String getUseremail() {
        return useremail;
    }

    public int getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public int getView() {
        return view;
    }

    public Post(int id, String titulo, String text, int commets, boolean liked, int likes, String[] tags, String useremail, int userid, String username, int view) {
        this.id = id;
        this.titulo = titulo;
        this.text = text;
        this.commets = commets;
        this.liked = liked;
        this.likes = likes;
        this.tags = tags;
        this.useremail = useremail;
        this.userid = userid;
        this.username = username;
        this.view = view;
    }


}

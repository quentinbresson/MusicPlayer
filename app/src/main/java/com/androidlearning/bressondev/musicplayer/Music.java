package com.androidlearning.bressondev.musicplayer;

public class Music {
    private String Name;
    private String Artist;
    private String Album;
    private String Duration;
    private int ID;

    public Music (String name, String artist, String album, String duration, int id) {
        this.Name = name;
        this.Artist = artist;
        this.Album = album;
        this.Duration = duration;
        this.ID = id;
    }
}

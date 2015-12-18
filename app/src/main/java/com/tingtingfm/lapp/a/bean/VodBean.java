package com.tingtingfm.lapp.a.bean;

/**
 * Created by lenovo on 2015/12/10.
 */
public class VodBean {
    private int album_id;
    private int album_type;
    private String img;
    private int mtime;
    private int playtimes;
    private String recommendation;
    private String title;

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public int getAlbum_type() {
        return album_type;
    }

    public void setAlbum_type(int album_type) {
        this.album_type = album_type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getMtime() {
        return mtime;
    }

    public void setMtime(int mtime) {
        this.mtime = mtime;
    }

    public int getPlaytimes() {
        return playtimes;
    }

    public void setPlaytimes(int playtimes) {
        this.playtimes = playtimes;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

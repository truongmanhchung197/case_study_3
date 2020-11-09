package model;

import java.sql.Date;
import java.time.LocalDateTime;

public class Post {
    String avatar;
    String userName;
    LocalDateTime timePost;
    String image;
    String content;
    int likeCount;

    public void setTimePost(LocalDateTime timePost) {
        this.timePost = timePost;
    }

    public Post() {
    }

    public Post(String image, String content, LocalDateTime timePost) {
        this.image = image;
        this.content = content;
        this.timePost = timePost;
    }

    public Post(String avatar, String userName, LocalDateTime timePost, String image, String content, int likeCount) {
        this.avatar = avatar;
        this.userName = userName;
        this.timePost = timePost;
        this.image = image;
        this.content = content;
        this.likeCount = likeCount;
    }


    public String getImage() {
        return "page/images/resources/" + image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimePost() {
        return LocalDateTime.now();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}

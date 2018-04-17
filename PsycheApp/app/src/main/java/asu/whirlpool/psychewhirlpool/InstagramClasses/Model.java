package asu.whirlpool.psychewhirlpool.InstagramClasses;
import asu.whirlpool.psychewhirlpool.R;
/**
 * Created by jperez60 on 1/4/2018.
 * Model to hold the Json objects for date, image, content, number of likes and number of comments */


public class Model
{
    String
            date
            ,image_url
            ,content
            ,likes
            ,comments;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

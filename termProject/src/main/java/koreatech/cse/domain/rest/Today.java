package koreatech.cse.domain.rest;

import koreatech.cse.domain.Book;

import java.util.ArrayList;
import java.util.Date;

public class Today {
    private int id;
    private String appKey;
    private Date createTime;
    private String userName;
    private Weather weather;
    private ArrayList<Movie> movie;
    private ArrayList<Book> book;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getAppKey() {
        return appKey;
    }
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public ArrayList<Movie> getMovie() {
        return movie;
    }

    public void setMovie(ArrayList<Movie> movie) {
        this.movie = movie;
    }

    public ArrayList<Book> getBook() {
        return book;
    }

    public void setBook(ArrayList<Book> book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Today{" +
                "id=" + id +
                ", appKey='" + appKey + '\'' +
                ", createTime=" + createTime +
                ", userName='" + userName + '\'' +
                ", weather=" + weather +
                ", movie=" + movie +
                ", book=" + book +
                '}';
    }
}

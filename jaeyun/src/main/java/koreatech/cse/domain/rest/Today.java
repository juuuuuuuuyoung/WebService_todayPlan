package koreatech.cse.domain.rest;

import koreatech.cse.domain.Book;

import java.util.ArrayList;
import java.util.Date;

public class Today {

    private String recommend;
    private Weather weather;
    private ArrayList<Movie> movie;
    private ArrayList<Book> book;
    private ArrayList<Festival> festival;

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
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

    public ArrayList<Festival> getFestival() {
        return festival;
    }

    public void setFestival(ArrayList<Festival> festival) {
        this.festival = festival;
    }

    @Override
    public String toString() {
        return "Today{" +
                "recommend='" + recommend + '\'' +
                ", weather=" + weather +
                ", movie=" + movie +
                ", book=" + book +
                ", festival=" + festival +
                '}';
    }
}

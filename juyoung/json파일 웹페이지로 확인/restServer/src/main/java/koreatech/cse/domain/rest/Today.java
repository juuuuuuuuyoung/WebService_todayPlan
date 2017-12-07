package koreatech.cse.domain.rest;


import java.util.ArrayList;

public class Today{
    private String location;
    private String searchNumber;
    private String searchType;
    private String activity;

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getSearchNumber() {
        return searchNumber;
    }
    public void setSearchNumber(String searchNumber) {
        this.searchNumber = searchNumber;
    }
    public String getSearchType() {
        return searchType;
    }
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
    public String getActivity() {
        return activity;
    }
    public void setActivity(String activity) {
        this.activity = activity;
    }


    private int id;
    private String recommend;
    private String weather;
    private String dust;
    private ArrayList<String> movie;
    private ArrayList<String> book;
    private ArrayList<String> festival;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getRecommend() {
        return recommend;
    }
    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
    public String getWeather() {
        return weather;
    }
    public void setWeather(String weather) {
        this.weather = weather;
    }
    public String getDust() {
        return dust;
    }
    public void setDust(String dust) {
        this.dust = dust;
    }
    public ArrayList<String> getMovie() {
        return movie;
    }
    public void setMovie(ArrayList<String> movie) {
        this.movie = movie;
    }
    public ArrayList<String> getBook() {
        return book;
    }
    public void setBook(ArrayList<String> book) {
        this.book = book;
    }
    public ArrayList<String> getFestival() {
        return festival;
    }
    public void setFestival(ArrayList<String> festival) {
        this.festival = festival;
    }

    @Override
    public String toString() {
        return "Today{" +
                "id=" + id +
                ", recommend='" + recommend + '\'' +
                ", weather='" + weather + '\'' +
                ", dust='" + dust + '\'' +
                ", movie=" + movie +
                ", book=" + book +
                ", festival=" + festival +
                '}';
    }
}



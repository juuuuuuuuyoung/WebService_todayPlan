package koreatech.cse.domain.rest;

public class Movie {
    private int id;
    private String targetDt;
    private String rnum;
    private String rank;
    private String movieCd;
    private String movieNm;
    private String openDt;
    private String wideArea;

    public void setId(int id) {
        this.id = id;
    }

    public void setRnum(String rnum) {
        this.rnum = rnum;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setMovieCd(String movieCd) {
        this.movieCd = movieCd;
    }

    public void setMovieNm(String movieNm) {
        this.movieNm = movieNm;
    }

    public void setOpenDt(String openDt) {
        this.openDt = openDt;
    }

    public void setTargetDt(String targetDt) {
        this.targetDt = targetDt;
    }

    public int getId() {
        return id;
    }

    public String getRnum() {
        return rnum;
    }

    public String getRank() {
        return rank;
    }

    public String getMovieCd() {
        return movieCd;
    }

    public String getMovieNm() {
        return movieNm;
    }

    public String getOpenDt() {
        return openDt;
    }

    public String getTargetDt() {
        return targetDt;
    }

    public String getWideArea() {
        return wideArea;
    }

    public void setWideArea(String wideArea) {
        this.wideArea = wideArea;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", targetDt='" + targetDt + '\'' +
                ", rnum='" + rnum + '\'' +
                ", rank='" + rank + '\'' +
                ", movieCd='" + movieCd + '\'' +
                ", movieNm='" + movieNm + '\'' +
                ", openDt='" + openDt + '\'' +
                ", wideAreaCd='" + wideArea + '\'' +
                '}';
    }
}

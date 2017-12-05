package koreatech.cse.domain.rest;


public class Festival implements Comparable<Festival>{
    private int id;
    private String name;            // 축제이름
    private String mobileapp;
    private String mapX;
    private String mapY;
    private String startLocation;   // 출발 좌표
    private String destLocation;    // 도착 좌표
    private String destAddress;     // 목적지 주소
    private String path;            // 경로
    private Long duration;
    private String arrivalTime;
    private String departureTime;
    private String imgUrl;          // 이미지 url
    private String recommend;
    private String sky;
    private String dustValue;
    private String dustGrade;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getDestLocation() {
        return destLocation;
    }

    public void setDestLocation(String destLocation) {
        this.destLocation = destLocation;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getMapX() {
        return mapX;
    }

    public void setMapX(String mapX) {
        this.mapX = mapX;
    }

    public String getMapY() {
        return mapY;
    }

    public void setMapY(String mapY) {
        this.mapY = mapY;
    }

    public String getSky() {
        return sky;
    }

    public void setSky(String sky) {
        this.sky = sky;
    }

    public String getDustValue() {
        return dustValue;
    }

    public void setDustValue(String dustValue) {
        this.dustValue = dustValue;
    }

    public String getDustGrade() {
        return dustGrade;
    }

    public void setDustGrade(String dustGrade) {
        this.dustGrade = dustGrade;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMobileapp() {
        return mobileapp;
    }

    public void setMobileapp(String mobileapp) {
        this.mobileapp = mobileapp;
    }


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    @Override
    public String toString() {
        return "Festival{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobileapp='" + mobileapp + '\'' +
                ", mapX='" + mapX + '\'' +
                ", mapY='" + mapY + '\'' +
                ", startLocation='" + startLocation + '\'' +
                ", destLocation='" + destLocation + '\'' +
                ", destAddress='" + destAddress + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", path='" + path + '\'' +
                ", duration=" + duration +
                ", imgUrl='" + imgUrl + '\'' +
                ", recommend='" + recommend + '\'' +
                ", sky='" + sky + '\'' +
                ", dustValue='" + dustValue + '\'' +
                ", dustGrade='" + dustGrade + '\'' +
                '}';
    }

    public int compareTo(Festival f) {

        if (this.duration > f.duration) {
            return 1;
        } else if(this.duration < f.duration) {
            return -1;
        } else {
            return 0;
        }

    }
}

package koreatech.cse.domain.rest;


public class Weather {
    private int id;
    private String sky;
    private String tmax;
    private String tmin;
    private String dustValue;
    private String dustGrade;
    private String lon;
    private String lat;
    private String province;
    private String city;
    private String region;
    private String location;
    
    private String version = "1";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSky() {
        return sky;
    }

    public void setSky(String sky) {
        this.sky = sky;
    }

    public String getTmax() {
        return tmax;
    }

    public void setTmax(String tmax) {
        this.tmax = tmax;
    }

    public String getTmin() {
        return tmin;
    }

    public void setTmin(String tmin) {
        this.tmin = tmin;
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

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", sky='" + sky + '\'' +
                ", tmax='" + tmax + '\'' +
                ", tmin='" + tmin + '\'' +
                ", dustValue='" + dustValue + '\'' +
                ", dustGrade='" + dustGrade + '\'' +
                ", lon='" + lon + '\'' +
                ", lat='" + lat + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", location='" + location + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}

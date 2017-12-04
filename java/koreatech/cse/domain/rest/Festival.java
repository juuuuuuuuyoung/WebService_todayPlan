package koreatech.cse.domain.rest;


public class Festival {
    private int id;
    private String locationname;
    private String name;
    private String mobileapp;
    private String location;
    private String destination;
    private String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocationname() {
        return locationname;
    }

    public void setLocationname(String locationname) {
        this.locationname = locationname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Festival{" + '\'' +
                "id=" + id + '\'' +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}

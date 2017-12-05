package koreatech.cse.domain.rest;

public class Steps {
    private String distance;
    private String duration;
    private String startLocLat;
    private String startLocLon;
    private String endLocLat;
    private String endLocLon;
    private String instructions;
    private String travelMode;

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEndLocLat() {
        return endLocLat;
    }

    public void setEndLocLat(String endLocLat) {
        this.endLocLat = endLocLat;
    }

    public String getEndLocLon() {
        return endLocLon;
    }

    public void setEndLocLon(String endLocLon) {
        this.endLocLon = endLocLon;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getStartLocLat() {
        return startLocLat;
    }

    public void setStartLocLat(String startLocLat) {
        this.startLocLat = startLocLat;
    }

    public String getStartLocLon() {
        return startLocLon;
    }

    public void setStartLocLon(String startLocLon) {
        this.startLocLon = startLocLon;
    }

    public String getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

    @Override
    public String toString() {
        return "Steps {" +
                "distance='" + distance + '\'' +
                ", duration='" + duration + '\'' +
                ", startLocLat='" + startLocLat + '\'' +
                ", startLocLon='" + startLocLon + '\'' +
                ", endLocLat='" + endLocLat + '\'' +
                ", endLocLon='" + endLocLon + '\'' +
                ", instructions='" + instructions + '\'' +
                ", travelMode='" + travelMode + '\'' +
                '}';
    }
}

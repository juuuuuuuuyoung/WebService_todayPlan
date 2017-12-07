package koreatech.cse.domain.rest;

import java.util.ArrayList;

public class Legs {
    private Point2 departure;
    private Point2 arrival;

    private String distance;
    private long distanceValue;
    private String duration;
    private long durationValue;

    private ArrayList<Path> paths = new ArrayList<Path>();

    public Point2 getDeparture() {
        return departure;
    }

    public void setDeparture(Point2 departure) {
        this.departure = departure;
    }

    public Point2 getArrival() {
        return arrival;
    }

    public void setArrival(Point2 arrival) {
        this.arrival = arrival;
    }

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

    public ArrayList<Path> getPaths() {
        return paths;
    }

    public void setPaths(ArrayList<Path> paths) {
        this.paths = paths;
    }

    public long getDistanceValue() {
        return distanceValue;
    }

    public void setDistanceValue(long distanceValue) {
        this.distanceValue = distanceValue;
    }

    public long getDurationValue() {
        return durationValue;
    }

    public void setDurationValue(long durationValue) {
        this.durationValue = durationValue;
    }

    public void setAll(Point2 departure, Point2 arrival, String distance, String duration, long distanceValue, long durationValue) {
        this.departure = departure;
        this.arrival = arrival;
        this.distance = distance;
        this.duration = duration;
        this.distanceValue = distanceValue;
        this.durationValue = durationValue;
    }


    @Override
    public String toString() {
        return "Legs{" +
                "departure=" + departure +
                ", arrival=" + arrival +
                ", distance='" + distance + '\'' +
                ", duration='" + duration + '\'' +
                ", paths=" + paths +
                '}';
    }
}


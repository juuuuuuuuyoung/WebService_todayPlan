package koreatech.cse.domain.rest;

import java.util.ArrayList;

public class Path {
    private String startAddress;
    private String destAddress;
    private String departureTime;
    private String arrivalTime;
    private String totalDistance;
    private String totalDuration;
    private ArrayList<Travel> way;

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public String getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(String totalDuration) {
        this.totalDuration = totalDuration;
    }

    public ArrayList<Travel> getWay() {
        return way;
    }

    public void setWay(ArrayList<Travel> way) {
        this.way = way;
    }

    @Override
    public String toString() {
        return "Path{" +
                "startAddress='" + startAddress + '\'' +
                ", destAddress='" + destAddress + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", totalDistance='" + totalDistance + '\'' +
                ", totalDuration='" + totalDuration + '\'' +
                ", way=" + way +
                '}';
    }
}


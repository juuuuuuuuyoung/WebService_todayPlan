package koreatech.cse.domain.rest;


public class Path {
    private Point start;
    private Point end;
    private String distance;
    private String duration;
    private String instructions;
    private String travelMode;
    private Travel way;

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

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

    public void setWay(Travel way) {
        this.way = way;
    }

    public Travel getWay() {
        return way;
    }

    public void setAll(Point start, Point end, String distance, String duration, String instructions, String travelMode, Travel way) {
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.duration = duration;
        this.instructions = instructions;
        this.travelMode = travelMode;
        this.way = way;
    }

    @Override
    public String toString() {
        return "Path{" +
                "start=" + start +
                ", end=" + end +
                ", distance='" + distance + '\'' +
                ", duration='" + duration + '\'' +
                ", instructions='" + instructions + '\'' +
                ", travelMode='" + travelMode + '\'' +
                ", way=" + way +
                '}';
    }
}


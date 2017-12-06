package koreatech.cse.domain.rest;


public class Path {
    private Point start;
    private Point end;
    private String distance;
    private String duration;
    private String instructions;
    private String travelMode;
    private Travel travel;

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

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setAll(Point start, Point end, String distance, String duration, String instructions, String travelMode, Travel travel) {
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.duration = duration;
        this.instructions = instructions;
        this.travelMode = travelMode;
        this.travel = travel;
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
                ", travel=" + travel +
                '}';
    }
}


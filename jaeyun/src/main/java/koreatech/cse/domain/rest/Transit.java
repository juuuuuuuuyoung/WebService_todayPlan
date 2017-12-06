package koreatech.cse.domain.rest;

public class Transit implements Travel {

    private Point2 start;
    private Point2 end;
    private String duration;        /***/
    private String transitName;     // name;
    private String transitShortName;// shortName; -- 있으면

    public Point getStart() {return null;}
    public Point getEnd() {return null;}
    public void setStart(Point start) {}
    public void setEnd(Point end) {}

    public Point2 getStart2() {
        return start;
    }

    public void setStart2(Point2 start) {
        this.start = start;
    }

    public Point2 getEnd2() {
        return end;
    }

    public void setEnd2(Point2 end) {
        this.end = end;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTransitName() {
        return transitName;
    }

    public void setTransitName(String transitName) {
        this.transitName = transitName;
    }

    public String getTransitShortName() {
        return transitShortName;
    }

    public void setTransitShortName(String transitShortName) {
        this.transitShortName = transitShortName;
    }

    @Override
    public String toString() {
        return "Transit{" +
                "start=" + start +
                ", end=" + end +
                ", duration='" + duration + '\'' +
                ", transitName='" + transitName + '\'' +
                ", transitShortName='" + transitShortName + '\'' +
                '}';
    }
}

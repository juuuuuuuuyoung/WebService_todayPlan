package koreatech.cse.domain.rest;

public class Transit implements Travel {

    private Long startLocX;         /***/
    private Long startLocY;         /***/
    private Long endLocX;           /***/
    private Long endLocY;           /***/
    private String distance;        /***/
    private String duration;        /***/
    private String instructions;    /***/
    private String travelMode;      /***/

    private Long departureLocX;
    private Long departureLocY;
    private String departureName;
    private String departureTime;

    private Long arrivalLocX;
    private Long arrivalLocY;
    private String arrivalName;
    private String arrivalTime;
    private String vehicleName;     // vehicle name
    private String transitName;     // name;
    private String transitShortName;// shortName; -- 있으면


    public Long getStartLocX() {
        return startLocX;
    }

    public void setStartLocX(Long startLocX) {
        this.startLocX = startLocX;
    }

    public Long getStartLocY() {
        return startLocY;
    }

    public void setStartLocY(Long startLocY) {
        this.startLocY = startLocY;
    }

    public Long getEndLocX() {
        return endLocX;
    }

    public void setEndLocX(Long endLocX) {
        this.endLocX = endLocX;
    }

    public Long getEndLocY() {
        return endLocY;
    }

    public void setEndLocY(Long endLocY) {
        this.endLocY = endLocY;
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

    public Long getDepartureLocX() {
        return departureLocX;
    }

    public void setDepartureLocX(Long departureLocX) {
        this.departureLocX = departureLocX;
    }

    public Long getDepartureLocY() {
        return departureLocY;
    }

    public void setDepartureLocY(Long departureLocY) {
        this.departureLocY = departureLocY;
    }

    public String getDepartureName() {
        return departureName;
    }

    public void setDepartureName(String departureName) {
        this.departureName = departureName;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public Long getArrivalLocX() {
        return arrivalLocX;
    }

    public void setArrivalLocX(Long arrivalLocX) {
        this.arrivalLocX = arrivalLocX;
    }

    public Long getArrivalLocY() {
        return arrivalLocY;
    }

    public void setArrivalLocY(Long arrivalLocY) {
        this.arrivalLocY = arrivalLocY;
    }

    public String getArrivalName() {
        return arrivalName;
    }

    public void setArrivalName(String arrivalName) {
        this.arrivalName = arrivalName;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
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
         String str = "Transit{" +
                "startLocX=" + startLocX +
                ", startLocY=" + startLocY +
                ", endLocX=" + endLocX +
                ", endLocY=" + endLocY +
                ", distance='" + distance + '\'' +
                ", duration='" + duration + '\'' +
                ", instructions='" + instructions + '\'' +
                ", travelMode='" + travelMode + '\'' +
                ", departureLocX=" + departureLocX +
                ", departureLocY=" + departureLocY +
                ", departureName='" + departureName + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalLocX=" + arrivalLocX +
                ", arrivalLocY=" + arrivalLocY +
                ", arrivalName='" + arrivalName + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", vehicleName='" + vehicleName + '\'' +
                ", transitName='" + transitName + '\'';
        if (transitShortName != null)
            str += ", transitShortName='" + transitShortName + '\'' +
            '}';
        else
            str += '}';

        return str;
    }
}

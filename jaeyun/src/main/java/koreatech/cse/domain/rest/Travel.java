package koreatech.cse.domain.rest;

public interface Travel {
    void setDistance(String distance);
    String getDistance();

    void setDuration(String duration);
    String getDuration();

    void setStartLocX(Long x);
    Long getStartLocX();

    void setStartLocY(Long y);
    Long getStartLocY();

    void setEndLocX(Long x);
    Long getEndLocX();

    void setEndLocY(Long y);
    Long getEndLocY();

    void setInstructions(String instructions);
    String getInstructions();

    void setTravelMode(String travelMode);
    String getTravelMode();
}

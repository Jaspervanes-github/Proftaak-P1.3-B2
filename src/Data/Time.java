package Data;


import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Time {

    private StringProperty startTime;
    private StringProperty endTime;
    private List<Time> timesList;

    public Time(){
        this.timesList = new ArrayList<>();
    }

    public void setStartTime(String startTime) {
        this.startTime.set(startTime);
    }

    public String getStartTime() {
        return startTime.get();
    }

    public void setEndTime(String endTime) {
        this.endTime.set(endTime);
    }

    public String getEndTime() {
        return endTime.get();
    }
}

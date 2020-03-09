package Objects;

import java.io.Serializable;
import java.util.ArrayList;


public class Time implements Serializable {

    private int startTime;
    private int endTime;
    private ArrayList<String> listOfTime;

    public Time() {

        this.startTime = 0000;
        this.endTime = 2400;
        this.listOfTime = new ArrayList<>();

    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public int formatTime(String time) {

        int result = 0;
        if (time.contains(":")) {
            result = Integer.parseInt(time.replaceAll(":", ""));
        }
        return result;
    }

    public ArrayList<String> LoadListOfTime() {

        String[] halfHours = {"00","30"};
        ArrayList<String> times = new ArrayList<>(); // <-- List instead of array

        for(int i = 0; i < 24; i++) {
            for(int j = 0; j < 2; j++) {
                String time = i + ":" + halfHours[j];
                if(i < 10) {
                    time = "0" + time;
                }
                times.add(time); // <-- no need to care about indexes
            }
        }

        return times;
    }

    public String getTimeString(int time) {

        String timeString = Integer.toString(time);
        StringBuilder stringBuilder = new StringBuilder(timeString);

        if(timeString.equals("0")){
          return "00:" + timeString + "0";
        } else if(timeString.equals("30")){
            return "00:" + timeString;
        } else if (timeString.length() == 1 || timeString.length() == 2) {
            return timeString;
        } else if (timeString.length() == 3) {
            stringBuilder.insert(1, ":");
        } else if (timeString.length() == 4) {
            stringBuilder.insert(2, ":");
        } else {
            return "Bad time friend";
        }

        return stringBuilder.toString();
    }


}

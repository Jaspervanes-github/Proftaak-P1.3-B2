package Data;

import Data.Person.Artist;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import java.util.Observable;

public class Performance {

    private int startTime;
    private int endTime;
    private Artist artist;
    private Stage stage;

    public Performance(int startTime, int endTime, Artist artist, Stage stage) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.artist = artist;
        this.stage = stage;
    }

    public int getStartTime() {
        return startTime;
    }

    public ObservableValue<String> getObservableString(int inputInteger){

        ObservableValue result = new SimpleStringProperty(getTimeString(inputInteger));

        return result;
    }

    public ObservableValue<String> getObservableString(String inputString){

        ObservableValue result = new SimpleStringProperty(inputString);

        return result;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String getTimeString(int time){

        String timeString = Integer.toString(time);
        StringBuilder stringBuilder = new StringBuilder(timeString);

        if(timeString.length() == 1 || timeString.length() == 2){
            return timeString;
        }else if(timeString.length() == 3){
            stringBuilder.insert(1, ":");
        }else if(timeString.length() == 4){
            stringBuilder.insert(2, ":");
        }else{
            return "Bad time friend";
        }

        return stringBuilder.toString();
    }

}
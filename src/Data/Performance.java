package Data;

import Data.Person.Artist;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import java.io.Serializable;


public class Performance implements Serializable {

    private int startTime;
    private int endTime;
    private Artist artist;
    private Stage stage;
    private Time time;

    public Performance(int startTime, int endTime, Artist artist, Stage stage) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.artist = artist;
        this.stage = stage;
        this.time = new Time();
    }

    public int getStartTime() {
        return startTime;
    }

    public ObservableValue<String> getObservableString(int inputInteger){

        ObservableValue result = new SimpleStringProperty(time.getTimeString(inputInteger));

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

}
package Data;

import Data.Person.Artist;
<<<<<<< HEAD
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import java.io.Serializable;
=======
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
>>>>>>> Robin


public class Performance implements Serializable {

    private Integer startTime;
    private Integer endTime;
    private Artist artist;
    private Stage stage;
    private Time time;

    public Performance(Integer startTime, Integer endTime, Artist artist, Stage stage) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.artist = artist;
        this.stage = stage;
        this.time = new Time();
    }

    public Integer getStartTime() {
        return startTime;
    }

<<<<<<< HEAD
    public ObservableValue<String> getObservableString(int inputInteger){
        return new SimpleStringProperty(time.getTimeString(inputInteger));
    }

    public ObservableValue<String> getObservableString(String inputString){
        return new SimpleStringProperty(inputString);
    }

    public void setStartTime(int startTime) {
=======
    public void setStartTime(Integer startTime) {
>>>>>>> Robin
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
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
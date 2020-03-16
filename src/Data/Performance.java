package Data;

import Objects.Person.Artist;
import Objects.Stage;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Performance {

    private Integer startTime;
    private Integer endTime;
    private Artist artist;
    private Stage stage;

    public Performance(Integer startTime, Integer endTime, Artist artist, Stage stage) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.artist = artist;
        this.stage = stage;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
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
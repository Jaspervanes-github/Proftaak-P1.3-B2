package Data;

import Data.Person.Artist;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Performance {

    private IntegerProperty startTime;
    private IntegerProperty endTime;
    private Artist artist;
    private Stage stage;

    public Performance(Integer startTime, Integer endTime, Artist artist, Stage stage) {
        this.startTime = new SimpleIntegerProperty(startTime);
        this.endTime = new SimpleIntegerProperty(endTime);
        this.artist = artist;
        this.stage = stage;
    }

    public IntegerProperty getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = new SimpleIntegerProperty(startTime);
    }

    public IntegerProperty getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = new SimpleIntegerProperty(endTime);
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
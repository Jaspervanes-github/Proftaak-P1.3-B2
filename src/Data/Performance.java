package Data;

import Data.Person.Artist;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Performance {

    private int starttime;
    private int endtime;
    private Artist artist;
    private Stage stage;

    public Performance(int starttime, int endtime, Artist artist, Stage stage) {
        this.starttime = starttime;
        this.endtime = endtime;
        this.artist = artist;
        this.stage = stage;
    }

    public int getStarttime() {
        return starttime;
    }

    public void setStarttime(int starttime) {
        this.starttime = starttime;
    }

    public int getEndtime() {
        return endtime;
    }

    public void setEndtime(int endtime) {
        this.endtime = endtime;
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
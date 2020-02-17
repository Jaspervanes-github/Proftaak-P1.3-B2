package Objects.Person;

import Objects.Genre;
import Objects.Time;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import java.io.Serializable;

public class Artist extends Person implements Serializable {

    private String name;
    private int popularity;
    private Genre genre;
    private Time time;

    public Artist(String name, int popularity, Genre genre) {
        this.name = name;
        this.popularity = popularity;
        this.genre = genre;
        this.time = new Time();
    }

    public ObservableValue<String> getObservableString(int inputInteger){
        return new SimpleStringProperty(time.getTimeString(inputInteger));
    }

    public ObservableValue<String> getObservableString(String inputString){
        return new SimpleStringProperty(inputString);
    }

    public void setName(String name) {
        name = name;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName() {

    }
}
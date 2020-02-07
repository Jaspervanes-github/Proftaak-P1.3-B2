package Data.Person;

import Data.Genre;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Artist extends Person {

    private StringProperty name;
    private IntegerProperty popularity;
    private Genre genre;

    public Artist(String name, int popularity, Genre genre) {
        this.name = new SimpleStringProperty(name);
        this.popularity = new SimpleIntegerProperty(popularity);
        this.genre = genre;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public IntegerProperty getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = new SimpleIntegerProperty(popularity);
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public StringProperty getName() {
        return this.name;
    }

    @Override
    public void setName() {

    }
}
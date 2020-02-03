package Data.Person;

import Data.Genre;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Artist extends Person {

    private IntegerProperty popularity;
    private Genre genre;
    private StringProperty name;

    public Artist(int popularity, Genre genre, String name) {
        this.popularity = new SimpleIntegerProperty(popularity);
        this.genre = genre;
        this.name = new SimpleStringProperty(name);
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getPopularity() {
        return popularity.get();
    }

    public IntegerProperty popularityProperty() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity.set(popularity);
    }

    public StringProperty nameProperty() {
        return name;
    }


    @Override
    public String  getName() {
        return this.name.toString();
    }

    @Override
    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }


}
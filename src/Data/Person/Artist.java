package Data.Person;

import Data.Genre;
import javafx.beans.property.IntegerProperty;

public class Artist extends Person {

    private String name;
    private int popularity;
    private Genre genre;

    public Artist(String name, int popularity, Genre genre) {
        this.name = name;
        this.popularity = popularity;
        this.genre = genre;
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
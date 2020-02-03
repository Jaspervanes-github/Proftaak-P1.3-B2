package Data.Person;

import Data.Genre;
import javafx.beans.property.IntegerProperty;

public class Artist extends Person {

    private String Name;
    private int popularity;
    private Genre genre;

    public Artist(String name, int popularity, Genre genre) {
        Name = name;
        this.popularity = popularity;
        this.genre = genre;
    }

    public void setName(String name) {
        Name = name;
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
    public void getName() {

    }

    @Override
    public void setName() {

    }
}
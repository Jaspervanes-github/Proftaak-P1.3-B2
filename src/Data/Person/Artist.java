package Data.Person;

import Data.Genre;
import javafx.beans.property.IntegerProperty;

public class Artist extends Person {

    private IntegerProperty popularity;
    private Genre genre;

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

    @Override
    public void getName() {

    }

    @Override
    public void setName() {

    }
}
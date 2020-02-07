package Data.Person;

import Data.Genre;
import javafx.beans.property.StringProperty;

public class Customer extends Person {

    private Genre favoriteGenre;
    private StringProperty name;

    public Genre getFavoriteGenre() {
        return favoriteGenre;
    }

    public void setFavoriteGenre(Genre favoriteGenre) {
        this.favoriteGenre = favoriteGenre;
    }

    @Override
    public StringProperty getName() {
        return this.name;
    }


    @Override
    public void setName() {

    }

}

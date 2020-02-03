package Data.Person;

import Data.Genre;

public class Customer extends Person {

    private Genre favoriteGenre;

    public Genre getFavoriteGenre() {
        return favoriteGenre;
    }

    public void setFavoriteGenre(Genre favoriteGenre) {
        this.favoriteGenre = favoriteGenre;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void setName(String name) {

    }
}

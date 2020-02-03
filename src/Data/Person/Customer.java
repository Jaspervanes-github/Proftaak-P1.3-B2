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
    public void getName() {

    }

    @Override
    public void setName() {

    }
}

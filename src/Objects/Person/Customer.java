package Objects.Person;

import Objects.Genre;

public class Customer extends Person {

    private Genre favoriteGenre;
    private String name;

    public Genre getFavoriteGenre() {
        return favoriteGenre;
    }

    public void setFavoriteGenre(Genre favoriteGenre) {
        this.favoriteGenre = favoriteGenre;
    }

    @Override
    public String getName() {
        return this.name;
    }


    @Override
    public void setName() {

    }

}

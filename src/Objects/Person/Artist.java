package Objects.Person;

import Objects.Genre;

import java.io.Serializable;

public class Artist extends Person implements Serializable {

    private String name;
    private int popularity;
    private Genre genre;

    public Artist(String name, int popularity, Genre genre) {
        this.name = name;
        this.popularity = popularity;
        this.genre = genre;
    }

//    public boolean equals(Artist artist){
//       return (this.name.equals(artist.name) && this.popularity == artist.popularity && this.genre.equals(artist.genre));
//    }

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
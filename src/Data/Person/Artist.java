package Data.Person;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Artist {

    private String name;
    private String description;
    private String popularity;

    public Artist(String name, String description, String popularity){
        this.name = name;
        this.description = description;
        this.popularity = popularity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getPopularity() {
        return popularity;
    }
}
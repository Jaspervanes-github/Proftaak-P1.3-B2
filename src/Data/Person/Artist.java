package Data.Person;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Artist extends Person {

    private StringProperty name;

    public Artist(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
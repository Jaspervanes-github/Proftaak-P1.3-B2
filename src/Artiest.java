import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Artiest {

    private StringProperty name;

    public Artiest(String name) {
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
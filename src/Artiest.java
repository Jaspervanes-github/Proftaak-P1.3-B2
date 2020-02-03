import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Artiest {

    private StringProperty name;
    private StringProperty afkomst;
    private StringProperty populariteit;

    public Artiest(String name, String afkomst, String populariteit) {
        this.name = new SimpleStringProperty(name);
        this.afkomst = new SimpleStringProperty(afkomst);
        this.populariteit = new SimpleStringProperty(populariteit);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getAfkomst() {
        return afkomst.get();
    }

    public StringProperty afkomstProperty() {
        return afkomst;
    }

    public void setAfkomst(String afkomst) {
        this.afkomst.set(afkomst);
    }

    public String getPopulariteit() {
        return populariteit.get();
    }

    public StringProperty populariteitProperty() {
        return populariteit;
    }

    public void setPopulariteit(String populariteit) {
        this.populariteit.set(populariteit);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public class Podium {

    private List<Podium> podiums;

    private IntegerProperty tijd;


    public Podium(int tijd) {
        this.podiums = new ArrayList<>();
        this.tijd = new SimpleIntegerProperty(tijd);

    }

    public List<Podium> getPodiums() {
        return podiums;
    }

    public int getTijd() {
        return tijd.get();
    }

    public void setTijd(int tijd) {
        this.tijd.set(tijd);
    }


}
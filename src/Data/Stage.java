package Data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public class Stage {

    private List<Stage> stages;

    private IntegerProperty tijd;


    public Stage(int tijd) {
        this.stages = new ArrayList<>();
        this.tijd = new SimpleIntegerProperty(tijd);

    }

    public List<Stage> getStages() {
        return stages;
    }

    public int getTijd() {
        return tijd.get();
    }

    public void setTijd(int tijd) {
        this.tijd.set(tijd);
    }


}
package Data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Stage {

    private List<Stage> stages;

    private StringProperty name;


    public Stage(String name) {
        this.stages = new ArrayList<>();
        this.name = new SimpleStringProperty(name);

    }

    public List<Stage> getStages() {
        return stages;
    }

    public String getName(){
        return this.name.toString();
    }


}
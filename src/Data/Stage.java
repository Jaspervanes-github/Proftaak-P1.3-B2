package Data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Stage implements Serializable {

    private String stageName;
    private int size;

<<<<<<< HEAD
=======
    public String stageNameProperty() {
        return stageName;
    }

>>>>>>> Robin
    public Stage(String stageName, int size) {
        this.stageName = stageName;
        this.size = size;
    }

<<<<<<< HEAD
    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getStageName() {
        return stageName;
=======
    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
>>>>>>> Robin
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
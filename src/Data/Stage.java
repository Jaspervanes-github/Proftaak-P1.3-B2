package Data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Stage {

    private StringProperty stageName;
    private int size;

    public StringProperty stageNameProperty() {
        return stageName;
    }

    public Stage(String stageName, int size) {
        this.stageName = new SimpleStringProperty(stageName);
        this.size = size;
    }

    public String getStageName() {
        return stageName.get();
    }

    public void setStageName(String stageName) {
        this.stageName.set(stageName);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
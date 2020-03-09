package Objects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import java.io.Serializable;

public class Stage implements Serializable {

    private String stageName;
    private int size;
    private Time time;

    public Stage(String stageName, int size) {
        this.stageName = stageName;
        this.size = size;
        this.time = new Time();
    }

    public ObservableValue<String> getObservableString(int inputInteger){
        return new SimpleStringProperty(""+inputInteger);
    }

    public ObservableValue<String> getObservableString(String inputString){
        return new SimpleStringProperty(inputString);
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getStageName() {
        return stageName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
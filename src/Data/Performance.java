package Data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Performance {

    private IntegerProperty Starttime;
    private IntegerProperty Endtime;
    private Stage stage;


    public int getStarttime() {
        return Starttime.get();
    }

    public IntegerProperty starttimeProperty() {
        return Starttime;
    }

    public void setStarttime(int starttime) {
        this.Starttime.set(starttime);
    }

    public int getEndtime() {
        return Endtime.get();
    }

    public IntegerProperty endtimeProperty() {
        return Endtime;
    }

    public void setEndtime(int endtime) {
        this.Endtime.set(endtime);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

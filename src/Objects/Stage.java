package Objects;

import java.io.Serializable;

public class Stage implements Serializable {

    private String stageName;
    private int size;

    public Stage(String stageName, int size) {
        this.stageName = stageName;
        this.size = size;
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
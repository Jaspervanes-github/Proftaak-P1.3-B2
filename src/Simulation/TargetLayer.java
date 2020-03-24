package Simulation;

import javax.json.JsonObject;
import java.util.ArrayList;

public class TargetLayer {

    private ArrayList<JsonObject> targets = new ArrayList<>();
    private JsonObject jsondata;

    public void setTargets(ArrayList<JsonObject> targets) {
        this.targets = targets;
    }

    public JsonObject getJsondata() {
        return jsondata;
    }

    public void setJsondata(JsonObject jsondata) {
        this.jsondata = jsondata;
    }

    public TargetLayer(JsonObject jsonData) {
        this.targets = targets;
        this.jsondata = jsonData;
        setTargets();
    }

    public ArrayList<JsonObject> getTargets() {
        return targets;
    }

    public void setTargets() {
        for (int j = 0; j < 14; j++) {
            this.targets.add(this.jsondata.getJsonArray("objects").getJsonObject(j));
//            System.out.println(this.targets);
//            System.out.println("SIze: " + this.jsondata.size());
        }
    }




}


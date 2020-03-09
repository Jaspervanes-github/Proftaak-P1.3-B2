package Objects;

import java.util.ArrayList;

public class Schedule {

    private ArrayList<Performance> performances;

    public void addPerformance(Performance performance){
        if (!performances.contains(performance)){
            performances.add(performance);
        }
    }

    public ArrayList<Performance> getPerformances(){
        return performances;
    }

    public void setPerformances(ArrayList<Performance> performances) {
        this.performances = performances;
    }
}

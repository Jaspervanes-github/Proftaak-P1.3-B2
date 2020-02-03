package Data;

import Data.Person.Artist;

import java.util.ArrayList;

public class Test {

    private Schedule schedule = new Schedule();
    private ArrayList<Stage> stages = new ArrayList<>();
    private ArrayList<Performance> performances = new ArrayList<>();
    private ArrayList<Artist> artists = new ArrayList<>();

    public void test(){
        stages.add(new Stage("Test",200));


        schedule.addPerformance(new Performance(1500,1700,new Artist("Armin van Bruuren",9,Genre.TECHNO),new Stage("Test",200)));
        performances.add(schedule.getPerformances());


        for (Performance performance : schedule);
    }
}

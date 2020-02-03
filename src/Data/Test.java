package Data;

import Data.Person.Artist;

import java.util.ArrayList;

public class Test {

    private static Schedule schedule = new Schedule();
    private static ArrayList<Stage> stages = new ArrayList<>();
    private static ArrayList<Performance> performances = new ArrayList<>();
    private static ArrayList<Artist> artists = new ArrayList<>();

    public static void main(String[] args) {
        test();
    }

    public static void test(){
        stages.add(new Stage("Test",200));

        artists.add(new Artist("Armin van Bruuren",9,Genre.TECHNO));
        performances.add(new Performance(1500,1700,new Artist("Armin van Buuren",9,Genre.TECHNO),new Stage("Test",200)));
        performances.add(new Performance(1400,1700,new Artist("besti",9,Genre.TECHNO),new Stage("Test",200)));


        System.out.println(performances.get(0).getArtist().getName());
        System.out.println(performances.get(1).getArtist().getName());

        System.out.println(performances.get(0).getStage());
        System.out.println(performances.get(1).getStage());

    }
}

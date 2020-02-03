package Data;

import Data.Person.Artist;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Performance {

    private ArrayList<Stage> stages;
    private ArrayList<Artist> artists;
    private ArrayList<Performance> performances;

    public Performance(){
        this.artists = new ArrayList<>();
        this.stages = new ArrayList<>();
        this.performances = new ArrayList<>();
    }

    public void createStage(String genre, String hostName, String description){

        stages.add(new Stage(genre, hostName, description));

    }

    public void createArtist(String name, String description, String popularity){

        artists.add(new Artist(name, description, popularity));

    }

    public void createPerformance(){



    }



}

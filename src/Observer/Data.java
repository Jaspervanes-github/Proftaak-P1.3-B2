package Observer;

import Objects.Performance;
import Objects.Person.Artist;
import Objects.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Data {

    private ObservableList<Stage> stages;
    private ObservableList<Artist> artists;
    private ObservableList<Performance> performances;
    private GUI gui;

    public Data(GUI gui) {
        this.stages = FXCollections.observableList(new ArrayList<Stage>());
        this.artists = FXCollections.observableList(new ArrayList<Artist>());
        this.performances = FXCollections.observableList(new ArrayList<Performance>());
        this.gui = gui;
    }


    public ObservableList<Stage> getStages() {
        return stages;
    }

    public void setStages(ObservableList<Stage> stages) {
        this.stages = stages;
    }

    public ObservableList<Artist> getArtists() {
        return artists;
    }

    public void setArtists(ObservableList<Artist> artists) {
        this.artists = artists;
    }

    public ObservableList<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(ObservableList<Performance> performances) {
        this.performances = performances;
    }

    public void init(){
        //Populates the observableList with data from file.
        //if file is empty an EOFException is thrown in src/File_IO
        try {
            this.performances = FXCollections.observableList(gui.file_io.readFilePerformances("Performances.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found, generating new Performances.txt");
        } catch (EOFException e) {
            System.out.println("File is empty");
        }

        try {
            this.artists = FXCollections.observableList(gui.file_io.readFileArtist("Artists.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found, generating new Performances.txt");
        } catch (EOFException e) {
            System.out.println("File is empty");
        }
    }
}
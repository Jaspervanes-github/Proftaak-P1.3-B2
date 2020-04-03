package Observer;

import Objects.Performance;
import Objects.Person.Artist;
import Objects.Person.Customer;
import Objects.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Data {

    private ObservableList<Stage> stages;
    private ObservableList<Artist> artists;
    private ObservableList<Performance> performances;
    private GUI gui;

    private ArrayList<Customer> customers;


    public Data(GUI gui) {
        this.stages = FXCollections.observableList(new ArrayList<Stage>());
        this.artists = FXCollections.observableList(new ArrayList<Artist>());
        this.performances = FXCollections.observableList(new ArrayList<Performance>());
        this.gui = gui;

        this.customers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            try {
                this.customers.add(new Customer(new Point2D.Double(Math.random() * 1000, Math.random() * 500),
                        ImageIO.read(getClass().getResourceAsStream("/images/TestNPC.png"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void init() {
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
            System.out.println("File not found, generating new Artists.txt");
        } catch (EOFException e) {
            System.out.println("File is empty");
        }

        try {
            this.stages = FXCollections.observableList(gui.file_io.readFileStage("Stages.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found, generating new Stages.txt");
        } catch (EOFException e) {
            System.out.println("File is empty");
        }


    }

    public GUI getGui() {
        return gui;
    }
}

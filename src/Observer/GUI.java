package Observer;

import File_IO.File_IO;
import Objects.Performance;
import Objects.Person.Artist;
import Objects.Person.Customer;
import Objects.Time;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class GUI extends Application {

    Time time = new Time();
    File_IO file_io = new File_IO();


    protected TableView<Performance> tableViewPerformance;
    protected TableView<Artist> tableViewArtist;
    protected Button buttonDelPerf;
    protected Button buttonDelArt;
    protected Button buttonEditPerformance;
    protected Button buttonAddPerformance;
    protected Button buttonAddArtist;
    protected Button buttonEditArtist;

    protected Canvas canvas;

    private Logic logic;
    private Simulation simulation;


    public GUI() {
    }


    @Override
    public void start(javafx.stage.Stage stage) throws Exception {
        //This is the general Agenda.Observer.Observer lay out

        Data data = new Data(this);
        Logic logic = new Logic(data, this);
        simulation = new Simulation();
        data.init();
        simulation.init();

        BorderPane bp = new BorderPane();

        this.tableViewPerformance = new TableView();
        this.tableViewArtist = new TableView<>();
        logic.setTableViewLogicPerformance();
        logic.setTableViewLogicArtist();

        bp.setCenter(getTabPane());
        bp.setBottom(getButton());

        logic.setButtonLogic();

        Scene scene = new Scene(bp, 1600, 900);

        stage.setScene(scene);
        stage.setTitle("User interface: Agenda");
        stage.show();
    }

    public Node getButton() {
        FlowPane fp = new FlowPane();


        buttonDelPerf = new Button("Delete Performance");
        buttonDelArt = new Button("Delete Artist");
        buttonEditPerformance = new Button("Edit Performance");
        buttonEditArtist = new Button("Edit Artist");
        buttonAddPerformance = new Button("Adding a performance");
        buttonAddArtist = new Button("Adding a Artist");

        fp.getChildren().addAll(buttonAddPerformance, buttonAddArtist, buttonDelPerf, buttonDelArt, buttonEditPerformance, buttonEditArtist);
        return fp;
    }

    private Node getTabPane() {
        //Here we make the tabs above so you can navigate in between tabs
        TabPane tabPane = new TabPane();
        Tab tabPerformances = new Tab("Performances");
        Tab tabArtists = new Tab("Artists");
        Tab tabSimulation = new Tab("Simulation");

        tabPane.getTabs().addAll(tabPerformances, tabArtists, tabSimulation);

        tabPerformances.setContent(this.tableViewPerformance);
        tabArtists.setContent(this.tableViewArtist);
        tabSimulation.setContent(this.simulation.getCanvas());

        tabPerformances.setClosable(false);
        tabArtists.setClosable(false);
        tabSimulation.setClosable(false);




        return tabPane;
    }

}
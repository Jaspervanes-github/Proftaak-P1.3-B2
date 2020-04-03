package Observer;

import File_IO.File_IO;
import Objects.Performance;
import Objects.Person.Artist;
import Objects.Stage;
import Objects.Time;
import Simulation.TerrainDemo;
import javafx.scene.layout.FlowPane;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GUI extends Application {

    Time time = new Time();
    public File_IO file_io = new File_IO();


    protected TableView<Performance> tableViewPerformance;
    protected TableView<Artist> tableViewArtist;
    protected TableView<Stage> tableViewStages;
    protected Button buttonDelPerf, buttonEditPerformance, buttonAddPerformance;
    protected Button buttonDelArt, buttonAddArtist, buttonEditArtist;
    protected Button buttonDelStage, buttonEditStage, buttonAddStage;
    protected Button buttonPausePlay;
    protected Button buttonSlowSpeed;
    protected Button buttonMediumSpeed;
    protected Button buttonFastSpeed;
    protected Button buttonBackwards;
    protected Button buttonForward;
    protected Button buttonSave;
    protected Button buttonOpen;
    protected Label timerLabel;

    protected Canvas canvas;

    private Logic logic;
    private TerrainDemo terrainDemo;


    public GUI() {
    }


    public TerrainDemo getTerrainDemo() {
        return terrainDemo;
    }

    @Override
    public void start(javafx.stage.Stage stage) throws Exception {
        //This is the general Agenda.Observer.Observer lay out

        Data data = new Data(this);
        Logic logic = new Logic(data, this);
        terrainDemo = new TerrainDemo();
        data.init();
        terrainDemo.init(this);

        BorderPane bp = new BorderPane();

        this.tableViewPerformance = new TableView();
        this.tableViewArtist = new TableView<>();
        this.tableViewStages = new TableView<>();
        logic.setTableViewLogicPerformance();
        logic.setTableViewLogicArtist();
        logic.setTableViewLogicStage();

        bp.setCenter(getTabPane());


        logic.setButtonLogic();

        Scene scene = new Scene(bp, terrainDemo.getMap().getWidth() * terrainDemo.getMap().getTileWidth(), terrainDemo.getMap().getHeight() * terrainDemo.getMap().getTileHeight());

        stage.setScene(scene);
        stage.setTitle("User interface: Agenda");
        stage.setResizable(false);
        stage.show();
    }

    public Node getButtonPerformance() {
        FlowPane fp = new FlowPane();


        buttonDelPerf = new Button("Delete Performance");
        buttonEditPerformance = new Button("Edit Performance");
        buttonAddPerformance = new Button("Adding a performance");

        fp.getChildren().addAll(buttonAddPerformance, buttonDelPerf, buttonEditPerformance);

        return fp;
    }

    public Node getButtonArtist() {
        FlowPane fp = new FlowPane();

        buttonDelArt = new Button("Delete Artist");
        buttonEditArtist = new Button("Edit Artist");
        buttonAddArtist = new Button("Adding a Artist");

        fp.getChildren().addAll(buttonAddArtist, buttonDelArt, buttonEditArtist);

        return fp;
    }

    public Node getButtonStages() {
        FlowPane fp = new FlowPane();

        buttonDelStage = new Button("Delete Stage");
        buttonEditStage = new Button("Edit Stage");
        buttonAddStage = new Button("Adding a Stage");

        fp.getChildren().addAll(buttonAddStage, buttonDelStage, buttonEditStage);

        return fp;
    }

    private Node getTabPane() {
        //Here we make the tabs above so you can navigate in between tabs
        TabPane tabPane = new TabPane();
        Tab tabPerformances = new Tab("Performances");
        Tab tabArtists = new Tab("Artists");
        Tab tabStages = new Tab("Stages");
        Tab tabSimulation = new Tab("Simulation");

        tabPane.getTabs().addAll(tabPerformances, tabArtists, tabStages, tabSimulation);

        tabPerformances.setContent(getBorderPanePerformance());

        tabArtists.setContent(getBorderPaneArtist());

        tabStages.setContent(getBorderPaneStages());

        tabSimulation.setContent(this.terrainDemo.getCanvas());
        tabSimulation.setContent(getBorderPaneSimulation());

        tabPerformances.setClosable(false);
        tabArtists.setClosable(false);
        tabStages.setClosable(false);
        tabSimulation.setClosable(false);

        return tabPane;
    }

    private Node getBorderPaneSimulation() {
        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(this.terrainDemo.getCanvas());
        borderPane.setBottom(getButtonSimulation());

        return borderPane;
    }

    private Node getButtonSimulation() {
        FlowPane fp = new FlowPane();

        buttonSave = new Button("Save");
        buttonOpen = new Button("Open");
        buttonBackwards = new Button("Backwards");
        buttonForward = new Button("Forward");
        buttonPausePlay = new Button("Pause/Play");
        buttonSlowSpeed = new Button(">");
        buttonMediumSpeed = new Button(">>");
        buttonFastSpeed = new Button(">>>");
        timerLabel = new Label();
        this.setTimerLabelText(0);

        fp.getChildren().addAll(buttonSave, buttonOpen, buttonBackwards, buttonForward, buttonPausePlay, buttonSlowSpeed, buttonMediumSpeed, buttonFastSpeed, timerLabel);

        return fp;
    }

    private Node getBorderPaneArtist() {
        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(this.tableViewArtist);
        borderPane.setBottom(getButtonArtist());

        return borderPane;
    }

    private Node getBorderPanePerformance() {
        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(this.tableViewPerformance);
        borderPane.setBottom(getButtonPerformance());

        return borderPane;
    }

    private Node getBorderPaneStages() {
        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(this.tableViewStages);
        borderPane.setBottom(getButtonStages());

        return borderPane;
    }

    public void setButtonsPerformanceVisable(boolean visable) {
        buttonAddPerformance.setVisible(visable);
        buttonDelPerf.setVisible(visable);
        buttonEditPerformance.setVisible(visable);
    }

    public void setButtonsArtistVisable(boolean visable) {
        buttonAddArtist.setVisible(visable);
        buttonDelArt.setVisible(visable);
        buttonEditArtist.setVisible(visable);
    }

    public Label getTimerLabel() {
        return timerLabel;
    }

    public void setTimerLabelText(double text) {
        this.timerLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        this.timerLabel.setText("Time: " + Math.round(text));
    }
}
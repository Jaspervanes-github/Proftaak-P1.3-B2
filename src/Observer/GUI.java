package Observer;

import File_IO.File_IO;
import Objects.Performance;
import Objects.Person.Artist;
import Objects.Time;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class GUI extends Application {

    Time time = new Time();
    File_IO file_io = new File_IO();


    protected TableView<Performance> tableViewPerformance;
    protected TableView<Artist> tableViewArtist;
    protected Button buttonDelPerf;
    protected Button buttonDelArt;
    protected Button buttonEdit;
    protected Button buttonAddPerformance;
    protected Button buttonAddArtist;

    private Logic logic;


    public GUI() {
    }


    @Override
    public void start(javafx.stage.Stage stage) throws Exception {
        //This is the general Agenda.Observer.Observer lay out
        Data data = new Data(this);
        Logic logic = new Logic(data, this);
        data.init();

        BorderPane bp = new BorderPane();

        this.tableViewPerformance = new TableView();
        this.tableViewArtist = new TableView<>();
        logic.setTableViewLogicPerformance();
        logic.setTableViewLogicArtist();

        bp.setCenter(getTabPane());
        bp.setBottom(getButton());

        logic.setButtonLogic();

        Scene scene = new Scene(bp, 1000, 650);

        stage.setScene(scene);
        stage.setTitle("User interface: Agenda");
        stage.show();
    }

    public Node getButton() {
        FlowPane fp = new FlowPane();


        buttonDelPerf = new Button("Delete Performance");
        buttonDelArt = new Button("Delete Artist");
        buttonEdit = new Button("Edit");
        buttonAddPerformance = new Button("Adding a performance");
        buttonAddArtist = new Button("Adding a Artist");

        fp.getChildren().addAll(buttonAddPerformance, buttonAddArtist, buttonDelPerf, buttonDelArt, buttonEdit);
        return fp;
    }

    private Node getTabPane() {
        //Here we make the tabs above so you can navigate in between tabs
        TabPane tabPane = new TabPane();
        Tab tabPerformances = new Tab("Performances");
        Tab tabArtists = new Tab("Artists");

        tabPane.getTabs().addAll(tabPerformances, tabArtists);

        tabPerformances.setContent(this.tableViewPerformance);
        tabArtists.setContent(this.tableViewArtist);

        tabPerformances.setClosable(false);
        tabArtists.setClosable(false);

        return tabPane;
    }


}
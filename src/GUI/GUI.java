package GUI;

import Data.Person.Artist;
import Data.Stage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public class GUI extends Application {

    private ObservableList<Stage> stages;
    private ObservableList<Artist> artists;


    private TableView tableView;


    public GUI() {
<<<<<<< Updated upstream:src/GUI/GUI.java
        this.stages = FXCollections.observableList(new ArrayList<>());
=======
        this.podiums = FXCollections.observableList(new ArrayList<>());
        this.artiests = FXCollections.observableList(new ArrayList<>());

>>>>>>> Stashed changes:src/GUI.java

        int tijd = 9;
        for (int i = 0; i < 19; i++) {
            tijd += 1;
            if (tijd == 25){
                tijd = 1;
            }
            this.addPodia(new Stage(tijd));
        }

        this.addArtist(new Artiest("Oliver Heldens", "NL", "9"));
        this.addArtist(new Artiest("Netsky", "BE", "6"));
        this.addArtist(new Artiest("Oliver Heldens", "NL", "9"));
        this.addArtist(new Artiest("Oliver Heldens", "NL", "9"));

    }

<<<<<<< Updated upstream:src/GUI/GUI.java
    public void addPodia(Stage stage) {
        this.stages.add(stage);
=======


    public void addPodia(Podium podium) {
        this.podiums.add(podium);
>>>>>>> Stashed changes:src/GUI.java
    }

    public void addArtist(Artist artist) {
        this.artists.add(artist);
    }


    @Override
    public void start(javafx.stage.Stage stage) throws Exception {
        //This is the general Agenda.GUI.GUI lay out
        BorderPane bp = new BorderPane();

        bp.setCenter(getTabPane());


        Scene scene = new Scene(bp, 1000, 650);

        stage.setScene(scene);
        stage.setTitle("User interface: Agenda");
        stage.show();
    }

    private Node getTabPane() {
        //Here we make the tabs above so you can navigate in between tabs

        TabPane tabPane = new TabPane();
        Tab tabLijst = new Tab("List");

        tabPane.getTabs().addAll(tabLijst);

        tabLijst.setContent(getTableView());

        tabLijst.setClosable(false);


        return tabPane;
    }


    private Node getTableView() {
        //Here we make a tableview and we can add podiums and numbers
        this.tableView = new TableView();


        TableColumn tcTijd = new TableColumn("Tijd");
        TableColumn tcMainStage = new TableColumn("Mainstage");
        TableColumn tcStage1 = new TableColumn("Freedom");
        TableColumn tcStage2 = new TableColumn("Sexy by nature");
        TableColumn tcStage3 = new TableColumn("Q-Dance");
        TableColumn tcStage4 = new TableColumn("Generation Smash");

        tableView.getColumns().addAll(tcTijd, tcMainStage, tcStage1, tcStage2, tcStage3, tcStage4);

        tcTijd.setCellValueFactory(new PropertyValueFactory<Stage, String>("tijd"));
        tcStage1.setCellValueFactory(new PropertyValueFactory<Artist, String>("name"));


        tcStage2.setCellValueFactory(new PropertyValueFactory<Artiest, String>("afkomst"));
        tcStage3.setCellValueFactory(new PropertyValueFactory<Artiest, String>("populariteit"));


        // BUG: tableView.setItems(FXCollections.observableArrayList(this.persons));
        // Let op: this.persons is een List => dus gebruik observableList!! en niet observableArrayList
<<<<<<< Updated upstream:src/GUI/GUI.java
        tableView.setItems(this.stages);
=======
        tableView.setItems(this.artiests);


>>>>>>> Stashed changes:src/GUI.java

        return tableView;
    }

}
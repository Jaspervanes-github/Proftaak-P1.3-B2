package GUI;

import Data.Genre;
import Data.Performance;
import Data.Person.Artist;
import Data.Stage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import sun.nio.cs.Surrogate;

import java.util.ArrayList;
import java.util.Optional;

public class GUI extends Application {

    private ObservableList<Stage> stages;
    private ObservableList<Artist> artists;
    private ObservableList<Performance> performances;


    private TableView tableView;
    private Button buttonDel;
    private Button buttonEdit;
    private Button buttonAdd;


    public GUI() {
        this.stages = FXCollections.observableList(new ArrayList<>());
        this.artists = FXCollections.observableList(new ArrayList<>());
        this.performances = FXCollections.observableList((new ArrayList<>()));



        this.addPodia(new Stage("Heldeep", 200));
        this.addPodia(new Stage("Rampage", 200));
        this.addPodia(new Stage("Boiler Room", 200));
        this.addPodia(new Stage("Disco Snolly", 200));
        this.addPodia(new Stage("Techy Techno", 200));

        this.addArtist(new Artist("Oliver heldens", 9, Genre.HOUSE));
        this.addArtist(new Artist("Charlotte de Witte", 7, Genre.TECHNO));
        this.addArtist(new Artist("Nina Kraviz", 8, Genre.TECHNO));
        this.addArtist(new Artist("Oliver heldens", 9, Genre.HOUSE));
    }

    public void addPodia(Stage stage) {
        this.stages.add(stage);
    }


    public void addArtist(Artist artist) {
        this.artists.add(artist);
    }


    @Override
    public void start(javafx.stage.Stage stage) throws Exception {
        //This is the general Agenda.GUI.GUI lay out
        BorderPane bp = new BorderPane();

        bp.setCenter(getTabPane());
        bp.setBottom(getButton());


        buttonAdd.setOnAction(event -> {
            Dialog dialog = new Dialog();

            GridPane gridPane = new GridPane();

            TextField textFieldStage = new TextField();
            TextField textFieldStartingTime = new TextField();
            TextField textFieldEndTime = new TextField();
            TextField textFieldArtist = new TextField();

            dialog.setTitle("Adding a new Performance");
            dialog.setHeaderText("Adding a new Performance");
            dialog.setContentText("Please enter the data: ");
            dialog.hide();

            ComboBox comboBoxArtists = new ComboBox();
            ComboBox comboBoxStartingTime = new ComboBox();
            ComboBox comboBoxEndingTime = new ComboBox();
            ComboBox comboBoxStages = new ComboBox();

            ObservableList<String> options =
                    FXCollections.observableArrayList(
                            "9:00",
                            "9:30",
                            "10:00",
                            "10:30",
                            "11:00",
                            "11:30",
                            "12:00",
                            "12:30",
                            "13:00",
                            "13:30",
                            "14:00",
                            "14:30",
                            "15:00",
                            "15:30",
                            "16:00",
                            "16:30",
                            "17:00"
                    );

            for (Artist artist : artists){
                comboBoxArtists.getItems().add(artist.getName());
            }

            for (Stage stage2 : stages){
                comboBoxStages.getItems().add(stage2.getStageName());
            }
            comboBoxStartingTime.setItems(options);
            comboBoxEndingTime.setItems(options);


            int startTime = 0;
            int endTime = comboBoxEndingTime.getSelectionModel().getSelectedIndex();

            Button buttonSave = new Button("Save");
            Button buttonQuit = new Button("Quit");



            gridPane.add(comboBoxStages, 1, 1);
            gridPane.add(new Label("Stage"), 0, 1);

            gridPane.add(comboBoxStartingTime, 1, 2);
            gridPane.add(new Label("Starting time"), 0, 2);

            gridPane.add(comboBoxEndingTime, 1, 3);
            gridPane.add(new Label("Ending Time"), 0, 3);

            gridPane.add(comboBoxArtists, 1, 4);
            gridPane.add(new Label("Artist"), 0, 4);

            gridPane.add(buttonSave, 0, 5);
            gridPane.add(buttonQuit, 1, 5);

            dialog.getDialogPane().setContent(gridPane);

// Traditional way to get the response value.
            buttonSave.setOnAction(event1 -> {
                Artist artist;
                for (Artist a : artists){
                    if(a.getName().equals(comboBoxArtists.getValue())){
                        artist = a;
                        for (Stage s : stages){
                            if (s.getStageName().equals(comboBoxStages.getValue())){
                                this.performances.add(new Performance(startTime, endTime, artist, s));

                            }
                        }
                    }

                }

                for (Performance p : performances){
                    System.out.println(p.getArtist());
                    System.out.println(p.getStage());
                    System.out.println(p.getEndtime());
                    System.out.println(p.getStarttime());
                }
            });

            buttonQuit.setOnAction(event1 -> {
                dialog.setResult(Boolean.TRUE);
                dialog.close();

            });
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                dialog.close();

        }
                });


        Scene scene = new Scene(bp, 1000, 650);

        stage.setScene(scene);
        stage.setTitle("User interface: Agenda");
        stage.show();
    }

    public Node getButton() {
        FlowPane fp = new FlowPane();


        buttonDel = new Button("del");
        buttonEdit = new Button("edit");
        buttonAdd = new Button("Adding a performance");

        fp.getChildren().addAll(buttonAdd, buttonDel, buttonEdit);
        return fp;
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
        tcStage2.setCellValueFactory(new PropertyValueFactory<Genre, String>("genre"));
        tcStage3.setCellValueFactory(new PropertyValueFactory<Artist, String>("popularity"));


        // BUG: tableView.setItems(FXCollections.observableArrayList(this.persons));
        // Let op: this.persons is een List => dus gebruik observableList!! en niet observableArrayList

        tableView.setItems(this.stages);

        tableView.setItems(this.artists);


        return tableView;
    }

}

package GUI;

import Data.Genre;
import Data.Performance;
import Data.Person.Artist;
import Data.Stage;
import Data.Time;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import javax.naming.Binding;
import java.util.ArrayList;
import java.util.Optional;

public class GUI extends Application {

    private ObservableList<Stage> stages;
    private ObservableList<Artist> artists;
    private ObservableList<Performance> performances;

    Time time = new Time();


    private TableView<Performance> tableView;
    private Button buttonDel;
    private Button buttonEdit;
    private Button buttonAdd;


    public GUI() {
        this.stages = FXCollections.observableList(new ArrayList<>());
        this.artists = FXCollections.observableList(new ArrayList<>());
        this.performances = FXCollections.observableList((new ArrayList<>()));


        //Test Values
        this.addPodia(new Stage("Heldeep", 200));
        this.addPodia(new Stage("Rampage", 200));
        this.addPodia(new Stage("Boiler Room", 200));
        this.addPodia(new Stage("Disco Snolly", 200));
        this.addPodia(new Stage("Techy Techno", 200));

        this.addArtist(new Artist("Oliver heldens", 9, Genre.HOUSE));
        this.addArtist(new Artist("Charlotte de Witte", 7, Genre.TECHNO));
        this.addArtist(new Artist("Nina Kraviz", 8, Genre.TECHNO));
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

            dialog.getDialogPane().getButtonTypes().add(new ButtonType("Quit", ButtonBar.ButtonData.CANCEL_CLOSE));
            dialog.setTitle("Adding a new Performance");
            dialog.setHeaderText("Adding a new Performance");
            dialog.setContentText("Please enter the data: ");
            dialog.hide();

            ComboBox comboBoxArtists = new ComboBox();
            ComboBox comboBoxStartingTime = new ComboBox();
            ComboBox comboBoxEndingTime = new ComboBox();
            ComboBox comboBoxStages = new ComboBox();

            ObservableList<String> options = FXCollections.observableList(time.LoadListOfTime());

            for (Artist artist : artists) {
                comboBoxArtists.getItems().add(artist.getName());
            }

            for (Stage stage2 : stages) {
                comboBoxStages.getItems().add(stage2.getStageName());
            }
            comboBoxStartingTime.setItems(options);
            comboBoxEndingTime.setItems(options);


            // String startTime = comboBoxStartingTime.getValue().toString();
            // int startTime = Integer.parseInt(comboBoxStartingTime.getValue().toString());

            Button buttonSave = new Button("Save");


            gridPane.add(comboBoxStages, 1, 1);
            gridPane.add(new Label("Stage"), 0, 1);

            gridPane.add(comboBoxStartingTime, 1, 2);
            gridPane.add(new Label("Starting time"), 0, 2);

            gridPane.add(comboBoxEndingTime, 1, 3);
            gridPane.add(new Label("Ending Time"), 0, 3);

            gridPane.add(comboBoxArtists, 1, 4);
            gridPane.add(new Label("Artist"), 0, 4);

            gridPane.add(buttonSave, 0, 5);

            dialog.getDialogPane().setContent(gridPane);

// Traditional way to get the response value.
            buttonSave.setOnAction(event1 -> {
                Artist artist;
                for (Artist a : artists) {
                    if (a.getName().equals(comboBoxArtists.getValue())) {
                        artist = a;

                        System.out.println("artist:" + artist);
                        System.out.println("getName: " + a.getName());
                        System.out.println("artist.getName: " + artist.getName());

                        for (Stage s : stages) {
                            if (s.getStageName().equals(comboBoxStages.getValue())) {

                                System.out.println("Stage: " + s);
                                System.out.println("getStageName: " + s.getStageName());

                                int startTime = time.formatTime(comboBoxStartingTime.getValue().toString());
                                int endTime = time.formatTime(comboBoxEndingTime.getValue().toString());

                                this.performances.add(new Performance(startTime, endTime, artist, s));
                                System.out.println("Performances list: " + this.performances.size());
                                System.out.println("cbx StartingTime: " + comboBoxStartingTime.getValue());
                                System.out.println("cbx StartingTime.getValue.ToString: " + comboBoxStartingTime.getValue().toString());


                            }
                        }
                    }

                }

                for (Performance p : performances) {
//                    System.out.println(p.getArtist());
//                    System.out.println(p.getStage());
//                    System.out.println(p.getEndTime());
                    System.out.println(p.getStartTime());
                }
            });

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                dialog.close();
            }
        });

        buttonDel.setOnAction(event -> {
            Performance selectedPerformance = tableView.getSelectionModel().getSelectedItem();
            tableView.getItems().remove(selectedPerformance);
        });

        buttonEdit.setOnAction(event -> {
            Performance help = tableView.getSelectionModel().getSelectedItem();
            Dialog dialog = new Dialog();

            GridPane gridPane = new GridPane();

            dialog.getDialogPane().getButtonTypes().add(new ButtonType("Quit", ButtonBar.ButtonData.CANCEL_CLOSE));
            dialog.setTitle("Editing a Performance");
            dialog.setHeaderText("Editing a Performance");
            dialog.setContentText("Please enter the data: ");
            dialog.hide();

            ComboBox comboBoxArtists = new ComboBox();
            ComboBox comboBoxStartingTime = new ComboBox();
            ComboBox comboBoxEndingTime = new ComboBox();
            ComboBox comboBoxStages = new ComboBox();

            comboBoxArtists.setValue(help.getArtist().getName());
            comboBoxEndingTime.setValue(time.getTimeString(help.getEndTime()));
            comboBoxStartingTime.setValue(time.getTimeString(help.getStartTime()));
            comboBoxStages.setValue(help.getStage().getStageName());

            ObservableList<String> options = FXCollections.observableList(time.LoadListOfTime());

            for (Artist artist : artists) {
                comboBoxArtists.getItems().add(artist.getName());
            }

            for (Stage stage2 : stages) {
                comboBoxStages.getItems().add(stage2.getStageName());
            }
            comboBoxStartingTime.setItems(options);
            comboBoxEndingTime.setItems(options);

            Button buttonSave = new Button("Save");

            gridPane.add(comboBoxStages, 1, 1);
            gridPane.add(new Label("Stage"), 0, 1);

            gridPane.add(comboBoxStartingTime, 1, 2);
            gridPane.add(new Label("Starting time"), 0, 2);

            gridPane.add(comboBoxEndingTime, 1, 3);
            gridPane.add(new Label("Ending Time"), 0, 3);

            gridPane.add(comboBoxArtists, 1, 4);
            gridPane.add(new Label("Artist"), 0, 4);

            gridPane.add(buttonSave, 0, 5);

            dialog.getDialogPane().setContent(gridPane);

// Traditional way to get the response value.
            buttonSave.setOnAction(event1 -> {
                Artist artist;
                for (Artist a : artists) {
                    if (a.getName().equals(comboBoxArtists.getValue())) {
                        artist = a;

                        System.out.println("artist:" + artist);
                        System.out.println("getName: " + a.getName());
                        System.out.println("artist.getName: " + artist.getName());

                        for (Stage s : stages) {
                            if (s.getStageName().equals(comboBoxStages.getValue())) {

                                System.out.println("Stage: " + s);
                                System.out.println("getStageName: " + s.getStageName());

                                int startTime = time.formatTime(comboBoxStartingTime.getValue().toString());
                                int endTime = time.formatTime(comboBoxEndingTime.getValue().toString());

                                this.performances.add(new Performance(startTime, endTime, artist, s));
                                this.performances.remove(help);
                                System.out.println("Performances list: " + this.performances.size());
                                System.out.println("cbx StartingTime: " + comboBoxStartingTime.getValue());
                                System.out.println("cbx StartingTime.getValue.ToString: " + comboBoxStartingTime.getValue().toString());


                            }
                        }
                    }

                }

                for (Performance p : performances) {
//                    System.out.println(p.getArtist());
//                    System.out.println(p.getStage());
//                    System.out.println(p.getEndTime());
                    System.out.println(p.getStartTime());
                }
            dialog.close();
            });
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
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


        buttonDel = new Button("Delete");
        buttonEdit = new Button("Edit");
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

        TableColumn<Performance, String> startingTime = new TableColumn<>("Start Time");
        TableColumn<Performance, String> endTime = new TableColumn<>("End Time");
        TableColumn<Performance, String> stage = new TableColumn<>("Stage");
        TableColumn<Performance, String> artistName = new TableColumn<>("Artist");
        TableColumn<Performance, String> genre = new TableColumn<>("Genre");
        TableColumn<Performance, String> popularity = new TableColumn<>("Popularity");
        startingTime.setMinWidth(100);
        endTime.setMinWidth(100);
        stage.setMinWidth(200);
        artistName.setMinWidth(200);
        genre.setMinWidth(100);
        popularity.setMinWidth(50);

        startingTime.setCellValueFactory(performances -> performances.getValue().getObservableString(performances.getValue().getStartTime()));
        endTime.setCellValueFactory(performances -> performances.getValue().getObservableString(performances.getValue().getEndTime()));
        stage.setCellValueFactory(performances -> performances.getValue().getObservableString(performances.getValue().getStage().getStageName()));
        artistName.setCellValueFactory(performances -> performances.getValue().getObservableString(performances.getValue().getArtist().getName()));
        genre.setCellValueFactory(performances -> performances.getValue().getObservableString(performances.getValue().getArtist().getGenre().toString()));
        popularity.setCellValueFactory(performances -> performances.getValue().getObservableString(performances.getValue().getArtist().getPopularity()));


        tableView.getColumns().addAll(startingTime, endTime, stage, artistName, genre, popularity);
        tableView.setPlaceholder(new Label("No Performances"));
        tableView.setItems(this.performances);


//
//        TableColumn tcStartingTime = new TableColumn("Starting time");
//        TableColumn tcStage = new TableColumn("Stage");
//        TableColumn tcArtist = new TableColumn("Artist");
//        TableColumn tcEndingTime = new TableColumn("Ending time");
//        TableColumn tcGenre = new TableColumn("Genre");
//        TableColumn tcPopularity = new TableColumn("Popularity");
//
//        tableView.getColumns().addAll(tcStage, tcArtist, tcPopularity, tcGenre, tcStartingTime, tcEndingTime);
//
//        tableView.setItems(this.performances);
//
//        tcStartingTime.setCellValueFactory(new PropertyValueFactory<Performance, String>("startingTime"));
//        tcStage.setCellValueFactory(new PropertyValueFactory<Stage, String>("stageName"));
//        tcArtist.setCellValueFactory(new PropertyValueFactory<Artist, String>("name"));
//        tcEndingTime.setCellValueFactory(new PropertyValueFactory<Performance, String>("genre"));
//        tcGenre.setCellValueFactory(new PropertyValueFactory<Artist, String>("popularity"));


        // BUG: tableView.setItems(FXCollections.observableArrayList(this.persons));
        // Let op: this.persons is een List => dus gebruik observableList!! en niet observableArrayList

        //tableView.setItems(this.performances);
//
//        for (Performance p : this.performances){
//            tableView.getItems().add(p.getArtist().getName());
//            tableView.getItems().add(p.getStage().getStageName());
//            tableView.getItems().add(p.getArtist().getPopularity());
//            tableView.getItems().add(p.getArtist().getGenre());
//            tableView.getItems().add(p.getStartTime());
//            tableView.getItems().add(p.getEndTime());
//
//        }


        return tableView;
    }

}

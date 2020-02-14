package GUI;

import Data.Genre;
import Data.Performance;
import Data.Person.Artist;
import Data.Stage;
import Data.Time;
import File_IO.File_IO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class GUI extends Application {

    Time time = new Time();
    File_IO file_io = new File_IO();

    private ObservableList<Stage> stages;
    private ObservableList<Artist> artists;
    private ObservableList<Performance> performances;

    private TableView<Performance> tableView;
    private Button buttonDelPerf;
    private Button buttonDelArt;
    private Button buttonEdit;
    private Button buttonAddPerformance;
    private Button buttonAddArtist;


    public GUI() {

        this.stages = FXCollections.observableList(new ArrayList<>());
        this.artists = FXCollections.observableList(new ArrayList<>());

        //Populates the observableList with data from file.
        //if file is empty an EOFException is thrown in src/File_IO
        try {
            this.performances = FXCollections.observableList(file_io.readFilePerformances("Performances.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found, generating new Performances.txt");
        } catch (EOFException e) {
            System.out.println("File is empty");
        }

        try {
            this.artists = FXCollections.observableList(file_io.readFileArtist("Artists.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found, generating new Performances.txt");
        } catch (EOFException e) {
            System.out.println("File is empty");
        }


        //Test Values
        this.addPodia(new Stage("Heldeep", 200));
        this.addPodia(new Stage("Rampage", 200));
        this.addPodia(new Stage("Boiler Room", 200));
        this.addPodia(new Stage("Disco Snolly", 200));
        this.addPodia(new Stage("Techy Techno", 200));


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


        buttonAddPerformance.setOnAction(event -> {
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

                        boolean isInArrayList = false;

                        for (Stage s : stages) {
                            if (s.getStageName().equals(comboBoxStages.getValue())) {

                                int startTime = time.formatTime(comboBoxStartingTime.getValue().toString());
                                int endTime = time.formatTime(comboBoxEndingTime.getValue().toString());

                                if (startTime < endTime) {
                                    Performance performance = new Performance(startTime, endTime, artist, s);

                                    System.out.println(this.performances);
                                    this.performances.add(performance);
                                }
                            }
                        }
                    }
                }
                //Writes to Performances.txt
                try {
                    file_io.writeFilePerformances("Performances.txt", this.performances);
                } catch (IOException e) {
                    System.out.println("IO Exception");
                    e.printStackTrace();
                }
            });

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                dialog.close();
            }
        });

        buttonAddArtist.setOnAction(event -> {
            Dialog dialog = new Dialog();

            GridPane gridPane = new GridPane();

            dialog.getDialogPane().getButtonTypes().add(new ButtonType("Quit", ButtonBar.ButtonData.CANCEL_CLOSE));
            dialog.setTitle("Adding a new Artist");
            dialog.setHeaderText("Adding a new Artist");
            dialog.setContentText("Please enter the data: ");
            dialog.hide();

            TextField artistNameText = new TextField();
            ComboBox<Integer> comboBoxPopularity = new ComboBox<>();
            ComboBox<Genre> comboBoxArtistGenre = new ComboBox<>();
            Integer[] popularity = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

            comboBoxArtistGenre.setItems(FXCollections.observableArrayList(Genre.values()));
            comboBoxPopularity.getItems().addAll(popularity);

            Button buttonSave = new Button("Save");

            gridPane.add(artistNameText, 1, 1);
            gridPane.add(new Label("Artist Name "), 0, 1);

            gridPane.add(comboBoxPopularity, 1, 2);
            gridPane.add(new Label("Popularity "), 0, 2);

            gridPane.add(comboBoxArtistGenre, 1, 3);
            gridPane.add(new Label("Genre "), 0, 3);

            gridPane.add(buttonSave, 0, 5);

            dialog.getDialogPane().setContent(gridPane);

            buttonSave.setOnAction(event1 -> {
                if (!artistNameText.getText().trim().isEmpty() || !comboBoxPopularity.getSelectionModel().isEmpty() || !comboBoxArtistGenre.getSelectionModel().isEmpty()) {
                    addArtist(new Artist(artistNameText.getText(), comboBoxPopularity.getValue(), comboBoxArtistGenre.getValue()));

                }
                try {
                    file_io.writeFileArtist("Artists.txt", this.artists);
                } catch (IOException e) {
                    System.out.println("IO Exception");
                    e.printStackTrace();
                }
                dialog.close();
            });
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                dialog.close();
            }
        });

        buttonDelPerf.setOnAction(event -> {
            Performance selectedPerformance = tableView.getSelectionModel().getSelectedItem();
            tableView.getItems().remove(selectedPerformance);
            try {
                file_io.writeFilePerformances("Performances.txt", this.performances);
            } catch (IOException e) {
                System.out.println("IO Exception");
                e.printStackTrace();
            }
        });

        buttonDelArt.setOnAction(event -> {
            Dialog dialog = new Dialog();

            GridPane gridPane = new GridPane();

            dialog.getDialogPane().getButtonTypes().add(new ButtonType("Quit", ButtonBar.ButtonData.CANCEL_CLOSE));
            dialog.setTitle("Deleting an Artist");
            dialog.setHeaderText("Deleting an Artist");
            dialog.setContentText("Please enter the data: ");
            dialog.hide();

            ComboBox comboBoxArtistName = new ComboBox<>();
            Button buttonDelArtist = new Button("Delete");

            buttonDelArtist.setOnAction(event1 -> {
                Artist artistHelp = new Artist("Help", 3, Genre.TECHNO);
                for (Artist a : artists) {
                    if (a.getName().equals(comboBoxArtistName.getValue())) {
                        artistHelp = a;
                        try {
                            file_io.writeFileArtist("Artists.txt", this.artists);
                        } catch (IOException e) {
                            System.out.println("IO Exception");
                            e.printStackTrace();
                        }
                        for (Artist artist : artists) {
                            comboBoxArtistName.getItems().add(artist.getName());
                        }
                    }
                }
                this.artists.remove(artistHelp);
                dialog.close();
            });

            for (Artist artist : artists) {
                comboBoxArtistName.getItems().add(artist.getName());
            }

            gridPane.add(comboBoxArtistName, 1, 1);
            gridPane.add(new Label("Artist Name "), 0, 1);

            gridPane.add(buttonDelArtist, 2, 1);

            dialog.getDialogPane().setContent(gridPane);

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                dialog.close();
            }
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

                        int startTime = time.formatTime(comboBoxStartingTime.getValue().toString());
                        int endTime = time.formatTime(comboBoxEndingTime.getValue().toString());

                        for (Stage s : stages) {
                            if (startTime < endTime) {
                                if (s.getStageName().equals(comboBoxStages.getValue())) {


                                    this.performances.add(new Performance(startTime, endTime, artist, s));
                                    this.performances.remove(help);
                                }
                            }
                        }
                    }

                }
                try {
                    file_io.writeFilePerformances("Performances.txt", this.performances);
                } catch (IOException e) {
                    System.out.println("IO Exception");
                    e.printStackTrace();
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

        return tableView;
    }

}
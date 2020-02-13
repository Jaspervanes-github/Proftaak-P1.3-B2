package GUI;

import Data.Genre;
import Data.Performance;
import Data.Person.Artist;
import Data.Stage;
import Data.Time;
import File_IO.File_IO;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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

    private TableView<Performance> performanceTableView;
    private TableView<Artist> artistTableView;

    private Button buttonAddArtist;
    private Button buttonDeleteArtist;
    private Button buttonEditArtist;

    private Button buttonAddPerformance;
    private Button buttondDeletePerformance;
    private Button buttonEditPerformance;



    public GUI() {

        this.stages = FXCollections.observableList(new ArrayList<>());

        try {
            this.artists = FXCollections.observableList(file_io.readFile("Artists.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found, generating new Performances.txt");
        } catch (EOFException e) {
            System.out.println("File is empty");
        }
        //Populates the observableList with data from file.
        //if file is empty an EOFException is thrown in src/File_IO
        try {
            this.performances = FXCollections.observableList(file_io.readFile("Performances.txt"));
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

        bp.setCenter(getPane());
        //bp.setBottom(getButton());


        // Logic to add performance
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

                        for (Stage s : stages) {
                            if (s.getStageName().equals(comboBoxStages.getValue())) {

                                int startTime = time.formatTime(comboBoxStartingTime.getValue().toString());
                                int endTime = time.formatTime(comboBoxEndingTime.getValue().toString());

                                this.performances.add(new Performance(startTime, endTime, artist, s));
                            }
                        }
                    }
                }
                //Writes to Performances.txt
                try {
                    file_io.writeFile("Performances.txt", this.performances);
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
//Logic to add an artist
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
            Integer[] popularity = {1,2,3,4,5,6,7,8,9,10};

            comboBoxArtistGenre.setItems( FXCollections.observableArrayList(Genre.values()));
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
                if(!artistNameText.getText().trim().isEmpty() || !comboBoxPopularity.getSelectionModel().isEmpty() || !comboBoxArtistGenre.getSelectionModel().isEmpty()) {
                    this.artists.add(new Artist(artistNameText.getText(), comboBoxPopularity.getValue(), comboBoxArtistGenre.getValue()));
                }
                try {
                    file_io.writeArtistFile("Artists.txt", this.artists);
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

        //END buttonAddArtist

        //buttonDeleteArtist
            buttonDeleteArtist.setOnAction(event -> {
                Artist selectedArtist = artistTableView.getSelectionModel().getSelectedItem();
                artistTableView.getItems().remove(selectedArtist);
                try {
                    file_io.writeArtistFile("Artists.txt", this.artists);
                } catch (IOException e) {
                    System.out.println("IO Exception");
                    e.printStackTrace();
                }
            });

            buttonEditArtist.setOnAction(event -> {

                Artist artist = artistTableView.getSelectionModel().getSelectedItem();
                Dialog dialog = new Dialog();

                GridPane gridPane = new GridPane();

                dialog.getDialogPane().getButtonTypes().add(new ButtonType("Quit", ButtonBar.ButtonData.CANCEL_CLOSE));
                dialog.setTitle("Editing a Artist");
                dialog.setHeaderText("Editing a Artist");
                dialog.setContentText("Please enter the data: ");
                dialog.hide();

                TextField txtArtistName = new TextField();
                ComboBox comboBoxPopularity = new ComboBox();
                ComboBox comboBoxGenre = new ComboBox();

                txtArtistName.setText(artist.getName());
                comboBoxPopularity.setValue(artist.getPopularity());
                comboBoxGenre.setValue(artist.getGenre());

                Integer[] popularity = {1,2,3,4,5,6,7,8,9,10};

                comboBoxPopularity.getItems().addAll(popularity);
                comboBoxGenre.setItems(FXCollections.observableArrayList(Genre.values()));

                Button buttonSave = new Button("Save Artist");

                gridPane.add(txtArtistName, 1, 2);
                gridPane.add(new Label("Artist"), 0, 2);

                gridPane.add(comboBoxPopularity, 1, 3);
                gridPane.add(new Label("Popularity"), 0, 3);

                gridPane.add(comboBoxGenre, 1, 4);
                gridPane.add(new Label("Genre"), 0, 4);

                gridPane.add(buttonSave, 0, 5);

                dialog.getDialogPane().setContent(gridPane);

// Traditional way to get the response value.
                buttonSave.setOnAction(event1 -> {
                    System.out.println("Hallo");
                        artists.get(artists.indexOf(artist)).setName(txtArtistName.getText());
                    System.out.println(artists.get(artists.indexOf(artist)).getName());
                    System.out.println(txtArtistName.getText());
                        artists.get(artists.indexOf(artist)).setPopularity(Integer.parseInt(comboBoxPopularity.getValue().toString()));
                        artists.get(artists.indexOf(artist)).setGenre(Genre.valueOf(comboBoxGenre.getValue().toString()));
                        //this.artists.add(new Artist(txtArtistName.getText(), Integer.parseInt(comboBoxPopularity.getValue().toString()), Genre.valueOf(comboBoxGenre.getValue().toString())));
                    try {
                        file_io.writeArtistFile("Artists.txt", this.artists);
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


            buttonEditPerformance.setOnAction(event -> {

                Performance help = performanceTableView.getSelectionModel().getSelectedItem();
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

                            for (Stage s : stages) {
                                if (s.getStageName().equals(comboBoxStages.getValue())) {

                                    int startTime = time.formatTime(comboBoxStartingTime.getValue().toString());
                                    int endTime = time.formatTime(comboBoxEndingTime.getValue().toString());

                                    this.performances.add(new Performance(startTime, endTime, artist, s));
                                    this.performances.remove(help);
                                }
                            }
                        }
                    }
                    try {
                        file_io.writeFile("Performances.txt", this.performances);
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

        //End of start() method

//        public Node getButton () {
//            FlowPane fp = new FlowPane();
//
//
//       // buttonDeleteArtist = new Button("Delete Artist");
//        buttonEditPerformance = new Button("Edit Performance");
//
//            buttonDeleteArtist = new Button("Delete Artist");
//            buttonEditPerformance = new Button("Edit Performance");
////            buttonAddPerformance = new Button("Adding a performance");
////            buttonAddArtist = new Button("Adding a Artist");
//
//            fp.getChildren().addAll(buttonDeleteArtist, buttonEditPerformance);
//            return fp;
//        }

        private Node getPane () {
            //Here we make the tabs above so you can navigate in between tabs
            FlowPane artistPane = new FlowPane();
            FlowPane performancePane = new FlowPane();
            TabPane tabPane = new TabPane();

            buttonAddArtist = new Button("Add a Artist");
            buttonEditArtist = new Button("Edit a Artist");
            buttonDeleteArtist = new Button("Delete a Artist");

            buttonAddPerformance = new Button("Add a performance");
            buttonEditPerformance = new Button("Edit a performance");
            buttondDeletePerformance = new Button("Delete a performance");

            Tab tabPerformanceList = new Tab("Performance List");
            Tab tabArtistList = new Tab("Artist List");

            tabPane.getTabs().addAll(tabPerformanceList, tabArtistList);

            performancePane.getChildren().addAll(getPerformanceTable(), buttonAddPerformance, buttonEditPerformance, buttondDeletePerformance);
            tabPerformanceList.setContent(performancePane);

            artistPane.getChildren().addAll(getArtistTable(), buttonAddArtist, buttonEditArtist,buttonDeleteArtist);
            tabArtistList.setContent(artistPane);


            //tabPerformanceList.setContent(getPerformanceTable());
            //tabArtistList.setContent(getArtistTable());


            tabPerformanceList.setClosable(false);
            tabArtistList.setClosable(false);

            return tabPane;
        }

    public ObservableValue<String> getObservableString(int inputInteger){
        return new SimpleStringProperty(time.getTimeString(inputInteger));
    }

    public ObservableValue<String> getObservableString(String inputString){
        return new SimpleStringProperty(inputString);
    }

        private Node getPerformanceTable () {
            //Here we make a tableview and we can add podiums and numbers
            this.performanceTableView = new TableView();

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

        startingTime.setCellValueFactory(performances -> getObservableString(performances.getValue().getStartTime()));
        endTime.setCellValueFactory(performances -> getObservableString(performances.getValue().getEndTime()));
        stage.setCellValueFactory(performances -> getObservableString(performances.getValue().getStage().getStageName()));
        artistName.setCellValueFactory(performances -> getObservableString(performances.getValue().getArtist().getName()));
        genre.setCellValueFactory(performances -> getObservableString(performances.getValue().getArtist().getGenre().toString()));
        popularity.setCellValueFactory(performances -> getObservableString(performances.getValue().getArtist().getPopularity()));

            performanceTableView.getColumns().addAll(startingTime, endTime, stage, artistName, genre, popularity);
            performanceTableView.setPlaceholder(new Label("No Performances"));
            performanceTableView.setItems(this.performances);

        return performanceTableView;
    }

    //Tableview for artist
    private Node getArtistTable(){

        this.artistTableView = new TableView<>();

        TableColumn<Artist, String> artistName = new TableColumn<>("Artist name");
        TableColumn<Artist, String> popularity = new TableColumn<>("Popularity");
        TableColumn<Artist, String> genre = new TableColumn<>("Genre");

        artistName.setMinWidth(200);
        genre.setMinWidth(100);
        popularity.setMinWidth(50);

        artistName.setCellValueFactory(artists -> getObservableString(artists.getValue().getName()));
        popularity.setCellValueFactory(artists -> getObservableString(artists.getValue().getPopularity()));
        genre.setCellValueFactory(artists -> getObservableString(artists.getValue().getGenre().toString()));

        artistTableView.getColumns().addAll(artistName, popularity, genre);
        artistTableView.setPlaceholder(new Label("No Artists"));
        artistTableView.setItems(this.artists);

        return artistTableView;
    }

}

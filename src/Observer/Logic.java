package Observer;

import Objects.Genre;
import Objects.Performance;
import Objects.Person.Artist;
import Objects.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class Logic {

    private Data data;
    private GUI gui;


    public Logic(Data data, GUI gui) {
        this.data = data;
        this.gui = gui;

        //Test Values
        this.addPodia(new Stage("Heldeep", 200));
        this.addPodia(new Stage("Rampage", 200));
        this.addPodia(new Stage("Boiler Room", 200));
        this.addPodia(new Stage("Disco Snolly", 200));
        this.addPodia(new Stage("Techy Techno", 200));
    }

    public void setButtonLogic() {
        this.gui.buttonAddPerformance.setOnAction(event -> {
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

            ObservableList<String> options = FXCollections.observableList(gui.time.LoadListOfTime());

            for (Artist artist : this.data.getArtists()) {
                comboBoxArtists.getItems().add(artist.getName());
            }

            for (Stage stage2 : this.data.getStages()) {
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
                for (Artist a : this.data.getArtists()) {
                    if (a.getName().equals(comboBoxArtists.getValue())) {
                        artist = a;

                        boolean isInArrayList = false;

                        for (Stage s : this.data.getStages()) {
                            if (s.getStageName().equals(comboBoxStages.getValue())) {

                                int startTime = gui.time.formatTime(comboBoxStartingTime.getValue().toString());
                                int endTime = gui.time.formatTime(comboBoxEndingTime.getValue().toString());

                                if (startTime < endTime) {
                                    Performance performance = new Performance(startTime, endTime, artist, s);

                                    System.out.println(this.data.getPerformances());
                                    this.data.getPerformances().add(performance);
                                    dialog.close();
                                }
                            }
                        }
                    }
                }
                //Writes to Performances.txt
                try {
                    gui.file_io.writeFilePerformances("Performances.txt", this.data.getPerformances());
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


        gui.buttonAddArtist.setOnAction(event -> {
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
                    if (this.data.getArtists().isEmpty()) {
                        addArtist(new Artist(artistNameText.getText(), comboBoxPopularity.getValue(), comboBoxArtistGenre.getValue()));
                    } else {
                        boolean isInList = false;
                        for (Artist artist : this.data.getArtists()) {
                            if (artist.getName().equals(artistNameText.getText())) {
                                isInList = true;
                                break;
                            }
                        }
                        if (!isInList) {
                            this.data.getArtists().add(new Artist(artistNameText.getText(), comboBoxPopularity.getValue(), comboBoxArtistGenre.getValue()));
                        }
                    }
                }
                try {
                    gui.file_io.writeFileArtist("Artists.txt", this.data.getArtists());
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

        gui.buttonDelPerf.setOnAction(event -> {
            Performance selectedPerformance = gui.tableViewPerformance.getSelectionModel().getSelectedItem();
            gui.tableViewPerformance.getItems().remove(selectedPerformance);
            try {
                gui.file_io.writeFilePerformances("Performances.txt", this.data.getPerformances());
            } catch (IOException e) {
                System.out.println("IO Exception");
                e.printStackTrace();
            }
        });

        gui.buttonDelArt.setOnAction(event -> {
            Artist selectedArtist = gui.tableViewArtist.getSelectionModel().getSelectedItem();
            gui.tableViewArtist.getItems().remove(selectedArtist);
            try {
                gui.file_io.writeFileArtist("Artist.txt", this.data.getArtists());
            } catch (IOException e) {
                System.out.println("IO Exception");
                e.printStackTrace();
            }
        });


        gui.buttonEditPerformance.setOnAction(event -> {
            if (gui.tableViewPerformance.getSelectionModel().getSelectedItem() != null) {
                Performance help = gui.tableViewPerformance.getSelectionModel().getSelectedItem();
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
                comboBoxEndingTime.setValue(gui.time.getTimeString(help.getEndTime()));
                comboBoxStartingTime.setValue(gui.time.getTimeString(help.getStartTime()));
                comboBoxStages.setValue(help.getStage().getStageName());

                ObservableList<String> options = FXCollections.observableList(gui.time.LoadListOfTime());

                for (Artist artist : this.data.getArtists()) {
                    comboBoxArtists.getItems().add(artist.getName());
                }

                for (Stage stage2 : this.data.getStages()) {
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
                    for (Artist a : this.data.getArtists()) {
                        if (a.getName().equals(comboBoxArtists.getValue())) {
                            artist = a;

                            int startTime = gui.time.formatTime(comboBoxStartingTime.getValue().toString());
                            int endTime = gui.time.formatTime(comboBoxEndingTime.getValue().toString());

                            for (Stage s : this.data.getStages()) {
                                if (startTime < endTime) {
                                    if (s.getStageName().equals(comboBoxStages.getValue())) {


                                        this.data.getPerformances().add(new Performance(startTime, endTime, artist, s));
                                        this.data.getPerformances().remove(help);
                                    }
                                }
                            }
                        }

                    }
                    try {
                        gui.file_io.writeFilePerformances("Performances.txt", this.data.getPerformances());
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
            }
        });

        gui.buttonEditArtist.setOnAction(event -> {
            Artist help = gui.tableViewArtist.getSelectionModel().getSelectedItem();
            Dialog dialog = new Dialog();

            GridPane gridPane = new GridPane();

            dialog.getDialogPane().getButtonTypes().add(new ButtonType("Quit", ButtonBar.ButtonData.CANCEL_CLOSE));
            dialog.setTitle("Editing an Artist");
            dialog.setHeaderText("Editing an Artist");
            dialog.setContentText("Please enter the data: ");
            dialog.hide();

            TextField artistNameText = new TextField();
            ComboBox<Integer> comboBoxPopularity = new ComboBox<>();
            ComboBox<Genre> comboBoxArtistGenre = new ComboBox<>();
            Integer[] popularity = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

            artistNameText.setText(help.getName());
            comboBoxPopularity.setValue(help.getPopularity());
            comboBoxArtistGenre.setValue(help.getGenre());

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
                if (!artistNameText.getText().trim().isEmpty()) {
                   addArtist(new Artist(artistNameText.getText(), comboBoxPopularity.getValue(), comboBoxArtistGenre.getValue()));
                   this.data.getArtists().remove(help);
                }
                try {
                    gui.file_io.writeFileArtist("Artists.txt", this.data.getArtists());
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
    }

    public void addPodia(Stage stage) {
        this.data.getStages().add(stage);
    }


    public void addArtist(Artist artist) {
        this.data.getArtists().add(artist);
    }

    public void setTableViewLogicArtist() {
        TableColumn<Artist, String> artistName = new TableColumn<>("Artist Name");
        TableColumn<Artist, String> popularity = new TableColumn<>("Popularity");
        TableColumn<Artist, String> genre = new TableColumn<>("Genre");
        artistName.setMinWidth(100);
        popularity.setMinWidth(100);
        genre.setMinWidth(200);

        artistName.setCellValueFactory(artist -> artist.getValue().getObservableString(artist.getValue().getName()));
        popularity.setCellValueFactory(artist -> artist.getValue().getObservableString(artist.getValue().getPopularity()));
        genre.setCellValueFactory(artist -> artist.getValue().getObservableString(artist.getValue().getGenre().toString()));

        gui.tableViewArtist.getColumns().addAll(artistName, popularity, genre);
        gui.tableViewArtist.setPlaceholder(new Label("No Artists"));
        gui.tableViewArtist.setItems(this.data.getArtists());
    }

    public void setTableViewLogicPerformance() {
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

        gui.tableViewPerformance.getColumns().addAll(startingTime, endTime, stage, artistName, genre, popularity);
        gui.tableViewPerformance.setPlaceholder(new Label("No Performances"));
        gui.tableViewPerformance.setItems(this.data.getPerformances());
    }
}
package GUI;

import Data.Genre;
import Data.Performance;
import Data.Person.Artist;
import Data.Stage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

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
            dialog.getDialogPane().getButtonTypes().add(new ButtonType("Quit",ButtonBar.ButtonData.CANCEL_CLOSE));

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



            ObservableList<Integer> options =
                    FXCollections.observableArrayList();

            setItems(options);

            for (Artist artist : artists){
                comboBoxArtists.getItems().add(artist.getName());
            }

            for (Stage stage2 : stages){
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
                /*String startTimeComboBox = comboBoxStartingTime.getSelectionModel().toString();
                String replacementStartTime = "";
                replacementStartTime += startTimeComboBox.substring(0, 1);
                replacementStartTime += startTimeComboBox.substring(2);*/

                Integer startTimeInt = options.get(comboBoxStartingTime.getSelectionModel().getSelectedIndex());
                Integer endTime = options.get(comboBoxEndingTime.getSelectionModel().getSelectedIndex());

                Artist artist;
                for (Artist a : artists){
                    if(a.getName().equals(comboBoxArtists.getValue())){
                        artist = a;
                        for (Stage s : stages){
                            if (s.getStageName().equals(comboBoxStages.getValue())){
                                this.performances.add(new Performance(startTimeInt, endTime, artist, s));

                            }
                        }
                    }

                }

                for (Performance p : performances){
                    System.out.println(p.getArtist());
                    System.out.println(p.getStage());
                    System.out.println(p.getEndTime());
                    System.out.println(p.getStartTime());
                }
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


        TableColumn tcStartingTime = new TableColumn("Starting time");
        TableColumn tcStage = new TableColumn("Stage");
        TableColumn tcArtist = new TableColumn("Artist");
        TableColumn tcEndingTime = new TableColumn("Ending time");
        TableColumn tcGenre = new TableColumn("Genre");
        TableColumn tcPopularity = new TableColumn("Popularity");

        tableView.getColumns().addAll(tcStartingTime, tcStage, tcArtist, tcEndingTime, tcGenre, tcPopularity);

        tcStartingTime.setCellValueFactory(new PropertyValueFactory<Performance, String>("startTime"));
        tcStage.setCellValueFactory(new PropertyValueFactory<Stage, String>("stageName"));
        tcArtist.setCellValueFactory(new PropertyValueFactory<Artist, String>("name"));
        tcEndingTime.setCellValueFactory(new PropertyValueFactory<Performance, String>("endTime"));
        tcGenre.setCellValueFactory(new PropertyValueFactory<Artist, String>("genre"));
        tcPopularity.setCellValueFactory(new PropertyValueFactory<Artist, Integer>("popularity"));


        // BUG: tableView.setItems(FXCollections.observableArrayList(this.persons));
        // Let op: this.persons is een List => dus gebruik observableList!! en niet observableArrayList

        tableView.setItems(this.performances);


        return tableView;
    }


    public void setItems(ObservableList<Integer> options) {
        String tijd = "0000";
        for (int i = 10; i < 26; i++) {
            tijd = i + tijd.substring(3);
            for (int j = 0; j < 2; j++) {
                if(j != 0) {
                    tijd = tijd.substring(0, 2) + j * 30;
                } else{
                    tijd = tijd.substring(0,2) + "00";
                }
                options.add(Integer.parseInt(tijd));
//                System.out.println(options);
            }
//            System.out.println(options.toString());
        }
    }


}

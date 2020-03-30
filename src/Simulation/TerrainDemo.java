package Simulation;

import Objects.Genre;
import Objects.Person.Artist;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.dyn4j.dynamics.World;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import javax.imageio.ImageIO;
import javax.json.JsonObject;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TerrainDemo extends Application {

    private TerrainMap map;
    private ResizableCanvas canvas;
    private BufferedImage imageMap = null;
    private AffineTransform tx = new AffineTransform();
    private boolean isFullScreen = false;
    private DirectionMap directionMap;
    private HashMap<JsonObject,ArrayList<Tile>> directionMaps;
    private ArrayList<JsonObject> targets = new ArrayList<>();
    private ArrayList<Visitor> visitors = new ArrayList<>();
    private ArrayList<Visitor> artists = new ArrayList<>();
    private Random r = new Random();

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane mainPane = new BorderPane();
        this.canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);

        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        stage.setScene(new Scene(mainPane, imageMap.getWidth(), imageMap.getHeight()));
        //stage.setScene(new Scene(mainPane));

        stage.setTitle("Festival Planner");
        stage.show();
        draw(g2d);
    }

    public void init() {
        map = new TerrainMap("/Terrain/Target.json");
        try {
            imageMap = ImageIO.read(getClass().getResource("/Map.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.directionMap = new DirectionMap(this.map);
        this.directionMaps = new HashMap<>();
        this.targets = map.getTarget().get(0).getTargets();
        for(int i = 0;i<this.targets.size();i++) {
            this.directionMaps.put(this.targets.get(i),this.directionMap.generateDirectionMap(new Tile(new Point2D.Double(this.targets.get(i).getInt("x"),this.targets.get(i).getInt("y")))));
        }

//            for(int i =0;i<10;i++) {
//                Visitor visitor = new Visitor(new Point2D.Double((int) 450, (int) 800-(i*30)), this.directionMaps.get(this.targets.get(i%14)));
////                Visitor visitor1 = new Visitor(new Point2D.Double((int) 450, (int) 800-(i*50)), this.directionMaps.get(this.targets.get(i%14)),new Artist("Piet",10, Genre.TECHNO));
//                visitors.add(visitor);
////                visitors.add(visitor1);
//            }

        int lowBound = 6;
        int highBound = 10;

            for(int i = 0; i< 5;i++){
                int num = r.nextInt(highBound-lowBound)+lowBound;
                System.out.println(num);
                Visitor artist = new Visitor(new Point2D.Double(450,800-(i*30)),this.directionMaps.get(this.targets.get(num)));

                artists.add(artist);
            }
    }

    public void draw(Graphics2D g) {
        g.setBackground(Color.black);
        g.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        g.setTransform(tx);
        map.draw(g);


        g.drawImage(imageMap, tx, null);

        for (Visitor v : visitors) {
            v.draw(g);

//            for(Tile t:v.getTiles()){
//                g.drawString(t.getDirection()+ "",(int)t.getPosition().getX(),(int)t.getPosition().getY());
//            }
        }
        for(Visitor a: artists){
            a.draw(g);
        }
    }

    public void update(double deltaTime) {
        if (canvas.getWidth() > 1000 && !isFullScreen) {
            tx = AffineTransform.getScaleInstance(canvas.getWidth() / imageMap.getWidth(), canvas.getHeight() / imageMap.getHeight());
            isFullScreen = true;
        } else if (canvas.getWidth() < 1000) {
            tx = AffineTransform.getScaleInstance(canvas.getWidth() / imageMap.getWidth(), canvas.getHeight() / imageMap.getHeight());
            isFullScreen = false;
        }
        for(Visitor v:visitors){
           v.update(this.visitors);
        }
        for(Visitor a:artists){
            a.update(this.artists);
        }
    }


    public static void main(String[] args) {
        launch(TerrainDemo.class);
    }

}

package Simulation;

import Objects.Performance;
import Objects.Person.Artist;
import Observer.Data;
import Observer.GUI;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import javax.imageio.ImageIO;
import javax.json.JsonObject;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
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
    private HashMap<JsonObject, ArrayList<Tile>> directionMaps;
    private ArrayList<JsonObject> targets = new ArrayList<>();
    private ArrayList<Visitor> visitors = new ArrayList<>();
    private ArrayList<Visitor> artists = new ArrayList<>();
    private Random r = new Random();
    private double timer = 0;
    private int timerSpeed = 1;
    private boolean isPaused = true;
    private boolean isForward = true;

    private Stage stage = new Stage();
    private GUI gui;
    private Data data = new Data(new GUI());


    public TerrainDemo() {
        try {
            start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
    }

    public TerrainMap getMap() {
        return map;
    }

    public void init(GUI gui) {
        this.gui = gui;
        data.init();

        map = new TerrainMap("/Terrain/Target.json");
        try {
            imageMap = ImageIO.read(getClass().getResource("/Map.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.directionMap = new DirectionMap(this.map);
        this.directionMaps = new HashMap<>();
        this.targets = map.getTarget().get(0).getTargets();
        for (int i = 0; i < this.targets.size(); i++) {
            this.directionMaps.put(this.targets.get(i), this.directionMap.generateDirectionMap(new Tile(new Point2D.Double(this.targets.get(i).getInt("x"), this.targets.get(i).getInt("y")))));
        }

        for (int i = 0; i < 30; i++) {
            Performance p = getRandomPerformance(timer);
            Visitor visitor = new Visitor(new Point2D.Double((int) 450, (int) 800 - (i * 10)), getTiles(p, false), p);
//                Visitor visitor1 = new Visitor(new Point2D.Double((int) 450, (int) 800-(i*50)), this.directionMaps.get(this.targets.get(i%14)),new Artist("Piet",10, Genre.TECHNO));
            visitors.add(visitor);
//                visitors.add(visitor1);
        }

        for (int i = 0; i < data.getArtists().size(); i++) {
            Performance p = getRandomPerformance(data.getArtists().get(i), timer);
            if (p != null) {
                Visitor artist = new Visitor(new Point2D.Double(450, 800 - (i * 30)), getTiles(p, true), p.getArtist(), p);
                artists.add(artist);

            } else {
                Visitor artist = new Visitor(new Point2D.Double(450, 800 - (i * 30)), getTiles(p, true), null, p);
                artists.add(artist);
            }
        }
    }

    public void init(boolean b) {
        data.init();

        map = new TerrainMap("/Terrain/Target.json");
        try {
            imageMap = ImageIO.read(getClass().getResource("/Map.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.directionMap = new DirectionMap(this.map);
        this.directionMaps = new HashMap<>();
        this.targets = map.getTarget().get(0).getTargets();
        for (int i = 0; i < this.targets.size(); i++) {
            this.directionMaps.put(this.targets.get(i), this.directionMap.generateDirectionMap(new Tile(new Point2D.Double(this.targets.get(i).getInt("x"), this.targets.get(i).getInt("y")))));
        }

    }

    public void init(int nothing){
        this.isPaused = true;
        this.timer = 0;
        this.timerSpeed = 1;
        this.isForward = true;

        data.init();

        this.visitors.clear();
        this.artists.clear();

        for (int i = 0; i < 30; i++) {
            Performance p = getRandomPerformance(timer);
            Visitor visitor = new Visitor(new Point2D.Double((int) 450, (int) 800 - (i * 10)), getTiles(p, false), p);
//                Visitor visitor1 = new Visitor(new Point2D.Double((int) 450, (int) 800-(i*50)), this.directionMaps.get(this.targets.get(i%14)),new Artist("Piet",10, Genre.TECHNO));
            visitors.add(visitor);
//                visitors.add(visitor1);
        }

        for (int i = 0; i < data.getArtists().size(); i++) {
            Performance p = getRandomPerformance(data.getArtists().get(i), timer);
            if (p != null) {
                Visitor artist = new Visitor(new Point2D.Double(450, 800 - (i * 30)), getTiles(p, true), p.getArtist(), p);
                artists.add(artist);

            } else {
                Visitor artist = new Visitor(new Point2D.Double(450, 800 - (i * 30)), getTiles(p, true), null, p);
                artists.add(artist);
            }
            this.gui.setTimerLabelText(timer);
        }

    }

    public void draw(Graphics2D g) {
        g.setBackground(Color.black);
        g.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        AffineTransform affineTransformImage = g.getTransform();

        tx.scale(1.7, 1);

//        map.draw(g);

        g.transform(affineTransformImage);
        g.drawImage(imageMap, affineTransformImage, null);

        for (Visitor v : visitors) {
            v.draw(g);

        }
        for (Visitor a : artists) {
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
        if (!isPaused) {
            if (isForward) {
                for (Visitor v : visitors) {
                    v.update(this.visitors, timerSpeed, timer);
                }
                for (Visitor a : artists) {
                    a.update(this.artists, timerSpeed, timer);
                }
                timer += 0.1 * timerSpeed;
                if(timer>=2400){
                    timer = 2359;
                    setPaused(true);
                }
            } else {
                for (Visitor v : visitors) {
                    v.update(this.visitors, timerSpeed * -1, timer);
                }
                for (Visitor a : artists) {
                    a.update(this.artists, timerSpeed * -1, timer);
                }
                timer -= 0.1 * timerSpeed;
                if(timer<=0){
                    timer = 0;
                    setPaused(true);
                }
            }
            this.gui.setTimerLabelText(timer);
        }
    }


    public static void main(String[] args) {
        launch(TerrainDemo.class);
    }

    public Performance getRandomPerformance(Artist artist, double timer) {
        for (Performance p : data.getPerformances()) {
            if (p.getArtist().getName().equals(artist.getName()) && timer >= p.getStartTime() && timer < p.getEndTime()) {
                return p;
            }
        }
        return null;
    }

    public Performance getRandomPerformance(double timer) {
        int som = 0;

        for (Performance p : data.getPerformances()) {
            som += p.getArtist().getPopularity();
        }
        int random = r.nextInt(som + 1);
        if (random < data.getArtists().get(0).getPopularity()) {
            for (Performance p : data.getPerformances()) {
                if (p.getArtist().getName().equals(data.getArtists().get(0).getName()) && timer >= p.getStartTime() && timer < p.getEndTime()) {
                    return p;
                }
            }
        } else if (random < data.getArtists().get(1).getPopularity() + data.getArtists().get(0).getPopularity()) {
            for (Performance p : data.getPerformances()) {
                if (p.getArtist().getName().equals(data.getArtists().get(1).getName()) && timer >= p.getStartTime() && timer < p.getEndTime()) {
                    return p;
                }
            }
        } else if (random < data.getArtists().get(2).getPopularity() + data.getArtists().get(0).getPopularity() + data.getArtists().get(1).getPopularity()) {
            for (Performance p : data.getPerformances()) {
                if (p.getArtist().getName().equals(data.getArtists().get(2).getName()) && timer >= p.getStartTime() && timer < p.getEndTime()) {
                    return p;
                }
            }
        } else if (random <= data.getArtists().get(3).getPopularity() + data.getArtists().get(0).getPopularity() + data.getArtists().get(1).getPopularity() + data.getArtists().get(2).getPopularity()) {
            for (Performance p : data.getPerformances()) {
                if (p.getArtist().getName().equals(data.getArtists().get(3).getName()) && timer >= p.getStartTime() && timer < p.getEndTime()) {
                    return p;
                }
            }
        }
        return null;
    }

    public ArrayList<Tile> getTiles(Performance p, boolean isArtist) {
        if (!isArtist) {
            if (p == null) {
                return getThirstLocation();
            } else {
                switch (p.getStage().getStageName()) {
                    case "Links boven":
                        return this.directionMaps.get(this.targets.get(10));
                    case "Rechts boven":
                        return this.directionMaps.get(this.targets.get(11));
                    case "Rechts onder":
                        return this.directionMaps.get(this.targets.get(12));
                    case "Links onder":
                        return this.directionMaps.get(this.targets.get(13));
                    default:
                        return null;
                }
            }
        } else {
            if (p == null) {
//                System.out.println("Artist thirst");
                return getThirstLocation();

            } else {
                switch (p.getStage().getStageName()) {
                    case "Links boven":
                        return this.directionMaps.get(this.targets.get(9));
                    case "Rechts boven":
                        return this.directionMaps.get(this.targets.get(8));
                    case "Rechts onder":
                        return this.directionMaps.get(this.targets.get(7));
                    case "Links onder":
                        return this.directionMaps.get(this.targets.get(6));
                    default:
                        return null;
                }
            }
        }
    }

    public ArrayList<Tile> getFoodLocation() {
        return this.directionMaps.get(this.targets.get(5));
    }

    public ArrayList<Tile> getWCLocation() {
        return this.directionMaps.get(this.targets.get(4));
    }

    public ArrayList<Tile> getThirstLocation() {
        double num = Math.random();
        if (num < 0.3) {
            return this.directionMaps.get(this.targets.get(0));
        } else if (num < 0.6) {
            return this.directionMaps.get(this.targets.get(1));
        } else {
            return this.directionMaps.get(this.targets.get(2));
        }
    }

    public HashMap<JsonObject, ArrayList<Tile>> getDirectionMaps() {
        return directionMaps;
    }

    public ArrayList<JsonObject> getTargets() {
        return targets;
    }

    public void setDirectionMaps(HashMap<JsonObject, ArrayList<Tile>> directionMaps) {
        this.directionMaps = directionMaps;
    }

    public ResizableCanvas getCanvas() {
        return canvas;
    }

    public double getTimer() {
        return timer;
    }

    public void setTimer(double timer) {
        this.timer = timer;
    }

    public int getTimerSpeed() {
        return timerSpeed;
    }

    public void setTimerSpeed(int timerSpeed) {
        this.timerSpeed = timerSpeed;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public boolean isForward() {
        return isForward;
    }

    public void setForward(boolean forward) {
        isForward = forward;
    }

}

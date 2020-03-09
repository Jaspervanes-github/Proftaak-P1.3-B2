import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TerrainDemo extends Application {

    private TerrainMap map;
    private ResizableCanvas canvas;
    private BufferedImage imageMap = null;
    private AffineTransform tx;


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
                if(last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        stage.setScene(new Scene(mainPane));
        stage.setTitle("Festival Planner");
        stage.show();
        draw(g2d);
    }

    public void init()
    {
            map = new TerrainMap("/Terrain/Map.json");
        try {
            imageMap = ImageIO.read(getClass().getResource("/Map.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void draw(Graphics2D g)
    {
        g.setBackground(Color.black);
        g.clearRect(0,0,(int)canvas.getWidth(), (int)canvas.getHeight());
        tx = AffineTransform.getScaleInstance(canvas.getWidth()/imageMap.getWidth(),canvas.getHeight()/imageMap.getHeight());
        g.drawImage(imageMap, tx,null);

    }

    public void update(double deltaTime)
    {

    }

    public static void main(String[] args)
    {
        launch(TerrainDemo.class);
    }

}

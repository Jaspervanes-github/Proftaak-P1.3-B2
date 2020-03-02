package Observer;

import Objects.Person.Customer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.channels.NetworkChannel;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Simulation extends Application {

    private ResizableCanvas canvas;
    private Stage stage = new Stage();
    private Data data = new Data(new GUI());

    public Simulation() {

        try {
            start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
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

        stage.setScene(new Scene(mainPane, 1500, 800));
        draw(g2d);

        canvas.setOnMouseMoved(e ->
        {
            for(Customer customer : data.getCustomers()) {
                customer.setTarget(new Point2D.Double(e.getX(), e.getY()));
            }
        });

        canvas.setOnMouseClicked( event -> {
            try {
                data.getCustomers().add(new Customer(new Point2D.Double(event.getX(), event.getY()), ImageIO.read(getClass().getResourceAsStream("/images/TestNPC.png"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }





    public void init() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(this.getClass().getResourceAsStream("/images/TestNPC.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 10; i++) {
            this.data.getCustomers().add(new Customer(new Point2D.Double(Math.random()*1800, Math.random()*1000), image));
        }
    }


    public void draw(FXGraphics2D g2)
    {
        g2.setTransform(new AffineTransform());
        g2.setBackground(new Color(100,75,75));
        g2.clearRect(0,0,(int)canvas.getWidth(), (int)canvas.getHeight());


        for(Customer customer : data.getCustomers()) {
            customer.draw(g2);
        }

    }

    public void update(double frameTime) {
        for(Customer customer : data.getCustomers()) {
            customer.update(data.getCustomers());
        }
    }

    public ResizableCanvas getCanvas() {
        return canvas;
    }
}
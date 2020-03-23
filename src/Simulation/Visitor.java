package Simulation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Visitor {

    private BufferedImage imageDOWN = null;
    private BufferedImage imageUP = null;
    private BufferedImage imageRIGHT = null;
    private BufferedImage imageLEFT = null;
    private Direction direction;
    private Point2D pos;
    private Point2D speed;
    private Point2D targetPos;
    private BufferedImage image;
    private Tile[] tiles;

    public Visitor(Point2D pos) {
        init();
        this.pos = pos;
        this.speed = new Point2D.Double(200, 100);
        this.targetPos = null;
        this.tiles = null;
        this.image = null;
    }

    public void init() {
        try {
            this.imageDOWN = ImageIO.read(getClass().getResource("/images/DOWN.png"));
            this.imageUP = ImageIO.read(getClass().getResource("/images/UP.png"));
            this.imageRIGHT = ImageIO.read(getClass().getResource("/images/RIGHT.png"));
            this.imageLEFT = ImageIO.read(getClass().getResource("/images/LEFT.png"));
            //this.imageDOWN.getSubimage(0,0,32,32);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        Point2D oldPos = new Point2D.Double(pos.getX() - speed.getX(), pos.getY() - speed.getY());
        direction = Direction.DOWN;

        switch (direction) {
            case UP:
                this.image = imageUP;
                this.pos = new Point2D.Double(this.pos.getX(), this.pos.getY() + 16);
                break;

            case DOWN:
                this.image = imageDOWN;
                this.pos = new Point2D.Double(this.pos.getX(), this.pos.getY() - 16);
                break;

            case LEFT:
                this.image = imageLEFT;
                this.pos = new Point2D.Double(this.pos.getX() - 16, this.pos.getY());
                break;

            case RIGHT:
                this.image = imageRIGHT;
                this.pos = new Point2D.Double(this.pos.getX() + 16, this.pos.getY());
                break;

            case STAY:
                break;
            default:
                this.pos = new Point2D.Double(this.pos.getX(), this.pos.getY());
        }

    }

    public void draw(Graphics2D g) {
        g.drawImage(this.image, (int) this.pos.getX(), (int) this.pos.getY(), null);


    }

}

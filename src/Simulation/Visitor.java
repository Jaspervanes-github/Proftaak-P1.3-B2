package Simulation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

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
    private ArrayList<Tile> tiles;

    public Visitor(Point2D pos, ArrayList<Tile> tiles) {
        this.pos = pos;
        this.speed = new Point2D.Double(16, 16);
        this.tiles = tiles;
        this.targetPos = new Point2D.Double(this.tiles.get(0).getPosition().getX(), this.tiles.get(0).getPosition().getY());
        this.image = null;
        this.direction = null;
        init();

    }

    public void init() {
        try {
            this.imageDOWN = ImageIO.read(getClass().getResource("/images/DOWN.png"));
            this.imageUP = ImageIO.read(getClass().getResource("/images/UP.png"));
            this.imageRIGHT = ImageIO.read(getClass().getResource("/images/RIGHT.png"));
            this.imageLEFT = ImageIO.read(getClass().getResource("/images/LEFT.png"));
            //this.imageDOWN.getSubimage(0,0,32,32);

            this.image = imageUP;
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Tile t : this.tiles) {
            if (this.pos.getX() >= t.getPosition().getX() && this.pos.getX() <= t.getPosition().getX() + t.getWidth()
                    && this.pos.getY() >= t.getPosition().getY() - t.getHeight() && this.pos.getY() <= t.getPosition().getY()) {
                this.direction = t.getDirection();
                break;
            }
        }
    }

    public void update() {

        this.direction = getDirection();
        if (this.direction != null) {

            switch (direction) {
                case UP:
                    this.image = imageUP;
                    this.pos = new Point2D.Double(this.pos.getX(), this.pos.getY() - 16);
                    break;

                case DOWN:
                    this.image = imageDOWN;
                    this.pos = new Point2D.Double(this.pos.getX(), this.pos.getY() + 16);
                    break;

                case LEFT:
                    this.image = imageLEFT;
                    this.pos = new Point2D.Double(this.pos.getX() + 16, this.pos.getY());
                    break;

                case RIGHT:
                    this.image = imageRIGHT;
                    this.pos = new Point2D.Double(this.pos.getX() - 16, this.pos.getY());
                    break;

                case STAY:
                    System.out.println("Stay");
                    break;
                default:
                    this.pos = new Point2D.Double(this.pos.getX(), this.pos.getY());
            }
        }
    }

    private Direction getDirection() {
        Direction direction = null;
//        System.out.println("In here");
        for (Tile t : this.tiles) {
//            System.out.println(t.getDirection());
//            System.out.println("This.pos: " + this.pos + " Tile.pos: " + t.getPosition());
            if (this.pos.getX() >= t.getPosition().getX() && this.pos.getX() <= t.getPosition().getX() + t.getWidth()
                    && this.pos.getY() >= t.getPosition().getY() - t.getHeight() && this.pos.getY() <= t.getPosition().getY()) {
                direction = t.getDirection();
                System.out.println(direction);
                break;
            }
        }
        return direction;
    }

    public void draw(Graphics2D g) {
        g.drawImage(this.image, (int) this.pos.getX(), (int) this.pos.getY(), null);
    }

}

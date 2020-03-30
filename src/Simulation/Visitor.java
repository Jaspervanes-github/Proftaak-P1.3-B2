package Simulation;

import Objects.Person.Artist;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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
    private Artist artist;

    public Visitor(Point2D pos, ArrayList<Tile> tiles) {
        this.pos = pos;
        this.speed = new Point2D.Double(0, 0);
        this.tiles = tiles;
        this.targetPos = new Point2D.Double(this.tiles.get(0).getPosition().getX(), this.tiles.get(0).getPosition().getY());
        this.image = null;
        this.direction = null;
        this.artist = null;
        init();
    }

    public Visitor(Point2D pos, ArrayList<Tile> tiles, Artist artist) {
        this.pos = pos;
        this.speed = new Point2D.Double(0, 0);
        this.tiles = tiles;
        this.targetPos = new Point2D.Double(this.tiles.get(0).getPosition().getX(), this.tiles.get(0).getPosition().getY());
        this.image = null;
        this.direction = null;
        this.artist = artist;
        init();
    }

    public void init() {
        try {
            this.imageUP = ImageIO.read(getClass().getResource("/images/DOWN.png"));
            this.imageDOWN = ImageIO.read(getClass().getResource("/images/UP.png"));
            this.imageRIGHT = ImageIO.read(getClass().getResource("/images/RIGHT.png"));
            this.imageLEFT = ImageIO.read(getClass().getResource("/images/LEFT.png"));

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

    public void update(ArrayList<Visitor> visitors) {

        this.direction = getDirection();
        if (this.direction != null) {

            switch (direction) {
                case DOWN:
                    this.image = imageUP;
                    this.pos = new Point2D.Double(this.pos.getX(), this.pos.getY() + 1);
                    break;

                case UP:
                    this.image = imageDOWN;
                    this.pos = new Point2D.Double(this.pos.getX(), this.pos.getY() - 1);
                    break;

                case LEFT:
                    this.image = imageLEFT;
                    this.pos = new Point2D.Double(this.pos.getX() - 1, this.pos.getY());
                    break;

                case RIGHT:
                    this.image = imageRIGHT;
                    this.pos = new Point2D.Double(this.pos.getX() + 1, this.pos.getY());
                    break;

                case STAY:
                    Random random = new Random();
                    int num = random.nextInt(100);
                    if (num < 25) {
                        this.image = imageRIGHT;
                    } else if (num < 50) {
                        this.image = imageLEFT;
                    } else if (num < 75) {
                        this.image = imageUP;
                    } else {
                        this.image = imageUP;
                    }
                    break;
                default:
                    this.pos = new Point2D.Double(this.pos.getX(), this.pos.getY());
            }

            for (Visitor v : visitors) {
                if (v != this) {
                    if (Math.abs(v.getPos().getX() - this.pos.getX()) < 30) {
                        //change position
//                        this.pos = new Point2D.Double(this.pos.getX()+(30-(this.pos.getX()-v.getPos().getX())),this.pos.getY());
                    }
                    if (Math.abs(v.getPos().getY() - this.pos.getY()) < 30) {
                        //change position
//                        this.pos = new Point2D.Double(this.pos.getX(),this.pos.getY()+(30-(this.pos.getY()-v.getPos().getY())));
                    }
                }
            }
        }
    }

    private Direction getDirection() {
        Direction direction = null;
        for (Tile t : this.tiles) {
            if (this.pos.getX() >= t.getPosition().getX() && this.pos.getX() <= t.getPosition().getX() + t.getWidth()
                    && this.pos.getY() >= t.getPosition().getY() - t.getHeight() && this.pos.getY() <= t.getPosition().getY()) {
                direction = t.getDirection();
                break;
            }
        }
        return direction;
    }

    public void draw(Graphics2D g) {
        g.drawImage(this.image, (int) this.pos.getX(), (int) this.pos.getY(), null);
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }
}

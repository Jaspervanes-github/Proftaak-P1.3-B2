package Simulation;

import Objects.Performance;
import Objects.Person.Artist;

import javax.imageio.ImageIO;
import javax.json.JsonObject;
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
    private Performance performance;

    private double hungerValue;
    private double peeValue;
    private double thirstValue;

    private boolean isDoingIdle = false;
    private TerrainDemo td = new TerrainDemo();

    public Visitor(Point2D pos, ArrayList<Tile> tiles, Performance performance) {
        this.pos = pos;
        this.speed = new Point2D.Double(0, 0);
        this.tiles = tiles;
        this.targetPos = new Point2D.Double(this.tiles.get(0).getPosition().getX(), this.tiles.get(0).getPosition().getY());
        this.image = null;
        this.direction = null;
        this.artist = null;
        this.performance = performance;

        this.hungerValue = (Math.random() * 100);
        this.thirstValue = (Math.random() * 100);
        this.peeValue = (Math.random() * 100);

        init();
    }

    public Visitor(Point2D pos, ArrayList<Tile> tiles, Artist artist, Performance performance) {
        this.pos = pos;
        this.speed = new Point2D.Double(0, 0);
        this.tiles = tiles;
        this.targetPos = new Point2D.Double(this.tiles.get(0).getPosition().getX(), this.tiles.get(0).getPosition().getY());
        this.image = null;
        this.direction = null;
        this.artist = artist;
        this.performance = performance;
        init();
    }

    public void init() {
        td.init(true);
        for (int i = 0; i < td.getTargets().size(); i++) {
        }
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
                    if (!isDoingIdle) {
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
                    } else if (isDoingIdle) {
                        isDoingIdle = !isDoingIdle;
                        if (this.artist == null) {
                            this.performance = td.getRandomPerformance();
                            this.tiles = td.getTiles(this.performance, false);
                        } else if (this.artist != null) {
                            this.performance = td.getRandomPerformance(this.artist);
                            this.tiles = td.getTiles(this.performance, true);
                        }
                    }
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
        this.hungerValue += 0.01;
        this.thirstValue += 0.01;
        this.peeValue += 0.01;
//        System.out.println("Hunger: " + hungerValue + " + Thirst: " + thirstValue + " + Pee: " + peeValue);

        if (this.peeValue >= 100 && !isDoingIdle) {
            this.peeValue = 0;
            this.tiles = td.getWCLocation();
            isDoingIdle = true;
        } else if (this.hungerValue >= 100 && !isDoingIdle) {
            this.hungerValue = 0;
            this.tiles = td.getFoodLocation();
            isDoingIdle = true;
        } else if (this.thirstValue >= 100 && !isDoingIdle) {
            this.thirstValue = 0;
            this.tiles = td.getThirstLocation();
            isDoingIdle = true;
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

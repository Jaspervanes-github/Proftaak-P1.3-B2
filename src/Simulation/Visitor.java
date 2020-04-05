package Simulation;

import Objects.Performance;
import Objects.Person.Artist;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Visitor {

    private BufferedImage imageDOWN1 = null;
    private BufferedImage imageUP1 = null;
    private BufferedImage imageRIGHT1 = null;
    private BufferedImage imageLEFT1 = null;
    private BufferedImage imageDOWN2 = null;
    private BufferedImage imageUP2 = null;
    private BufferedImage imageRIGHT2 = null;
    private BufferedImage imageLEFT2 = null;
    private Direction direction;
    private Point2D pos;
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
        this.tiles = tiles;
        this.image = null;
        this.direction = null;
        this.artist = null;
        if (performance != null) {
            this.performance = performance;
        }

        this.hungerValue = (Math.random() * 100);
        this.thirstValue = (Math.random() * 100);
        this.peeValue = (Math.random() * 100);

        init();
    }

    public Visitor(Point2D pos, ArrayList<Tile> tiles, Artist artist, Performance performance) {
        this.tiles = tiles;
        this.pos = new Point2D.Double(this.tiles.get(0).getPosition().getX(), this.tiles.get(0).getPosition().getY());
        this.image = null;
        this.direction = null;
        this.artist = artist;
        if (performance != null) {
            this.performance = performance;
        }
        init();
    }

    public void init() {
        td.init(true);
        for (int i = 0; i < td.getTargets().size(); i++) {
        }
        try {
            if (this.artist == null) {
                this.imageUP1 = ImageIO.read(getClass().getResource("/images/UP.png"));
                this.imageDOWN1 = ImageIO.read(getClass().getResource("/images/DOWN.png"));
                this.imageRIGHT1 = ImageIO.read(getClass().getResource("/images/RIGHT.png"));
                this.imageLEFT1 = ImageIO.read(getClass().getResource("/images/LEFT.png"));

                this.imageDOWN2 = ImageIO.read(getClass().getResource("/images/VisitorDown.png"));
                this.imageUP2 = ImageIO.read(getClass().getResource("/images/VisitorUp.png"));
                this.imageRIGHT2 = ImageIO.read(getClass().getResource("/images/VisitorRight.png"));
                this.imageLEFT2 = ImageIO.read(getClass().getResource("/images/VisitorLeft.png"));
            } else {
                this.imageUP1 = ImageIO.read(getClass().getResource("/images/ArtistUp.png"));
                this.imageDOWN1 = ImageIO.read(getClass().getResource("/images/ArtistDown.png"));
                this.imageRIGHT1 = ImageIO.read(getClass().getResource("/images/ArtistRight.png"));
                this.imageLEFT1 = ImageIO.read(getClass().getResource("/images/ArtistLeft.png"));

                this.imageUP2 = ImageIO.read(getClass().getResource("/images/ArtistUpRight.png"));
                this.imageDOWN2 = ImageIO.read(getClass().getResource("/images/ArtistDownRight.png"));
                this.imageRIGHT2 = ImageIO.read(getClass().getResource("/images/ArtistRightRight.png"));
                this.imageLEFT2 = ImageIO.read(getClass().getResource("/images/ArtistLeftRight.png"));
            }
            this.image = imageUP1;
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

    public void update(ArrayList<Visitor> visitors, int speed, double timer) {
//        System.out.println(timer);
        this.direction = getDirection();
        if (this.direction != null) {

            switch (direction) {
                case DOWN:
                    offsetDown(speed);
                    break;

                case UP:
                    offsetUp(speed);
                    break;

                case LEFT:
                    offsetLeft(speed);
                    break;

                case RIGHT:
                    offsetRight(speed);
                    break;

                case STAY:
                     if (!isDoingIdle) {
                        Random random = new Random();
                        int num = random.nextInt(100);
                        if (this.artist == null) {
                            if (num < 2 * speed) {
                                offsetRight(speed);
                            } else if (num < 4 * speed) {
                                offsetLeft(speed);
                            } else if (num < 6 * speed) {
                                offsetUp(speed);
                            } else if (num < 8 * speed) {
                                offsetDown(speed);
                            } else {
                                //Nothing
                            }
                        } else {
                            if (num < 2 * speed) {
                                offsetRight(speed);
                            } else if (num < 4 * speed) {
                                offsetLeft(speed);
                            } else {
                                //Nothing
                            }
                        }
                        break;
                    } else if (isDoingIdle) {
                        isDoingIdle = !isDoingIdle;
                        if (this.artist == null) {
                            this.performance = td.getRandomPerformance(timer);
                            this.tiles = td.getTiles(this.performance, false);
                        } else if (this.artist != null) {
                            this.performance = td.getRandomPerformance(this.artist, timer);
                            this.tiles = td.getTiles(this.performance, true);
                        }
                    }
                    break;
                default:
                    this.pos = new Point2D.Double(this.pos.getX(), this.pos.getY());
                    break;
            }
            if (this.performance == null) {
                if (this.artist == null) {
                    this.performance = td.getRandomPerformance(timer);
                    this.tiles = td.getTiles(this.performance, false);
                } else if (this.artist != null) {
                    this.performance = td.getRandomPerformance(this.artist, timer);
                    this.tiles = td.getTiles(this.performance, true);
                }
            } else if (this.performance.getEndTime() < timer) {
                if (this.artist == null) {
                    this.performance = td.getRandomPerformance(timer);
                    this.tiles = td.getTiles(this.performance, false);

                } else if (this.artist != null) {
//                            System.out.println("Performance over");

                    this.performance = td.getRandomPerformance(this.artist, timer);
                    this.tiles = td.getTiles(this.performance, true);

//                            System.out.println(this.tiles);
//                            System.out.println(this.getDirection());
                }
            }
        } else {
            Random random = new Random();
            int num = random.nextInt(100);
            if (this.artist == null) {
                if (num < 2 * speed) {
                    offsetRight(speed);
                } else if (num < 4 * speed) {
                    offsetLeft(speed);
                } else if (num < 6 * speed) {
                    offsetUp(speed);
                } else if (num < 8 * speed) {
                    offsetDown(speed);
                } else {
                    //Nothing
                }
            } else {
                if (num < 2 * speed) {
                    offsetRight(speed);
                } else if (num < 4 * speed) {
                    offsetLeft(speed);
                } else {
                    //Nothing
                }
            }
        }
        this.hungerValue += Math.random() / 100;
        this.thirstValue += Math.random() / 100;
        this.peeValue += Math.random() / 100;

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

    public void offsetLeft(int speed) {
        if (this.image.equals(imageLEFT1)) {
            this.image = imageLEFT2;
        } else {
            this.image = imageLEFT1;
        }

        this.pos = new Point2D.Double(this.pos.getX() - speed, this.pos.getY());
        if (this.pos.getX() < 0) {
            this.pos = new Point2D.Double(5, this.pos.getY());
        } else if (this.pos.getX() > td.getMap().getWidth() * td.getMap().getTileWidth()) {
            this.pos = new Point2D.Double((td.getMap().getWidth() * td.getMap().getTileWidth()) - 5, this.pos.getY());
        } else if (this.pos.getY() < 0) {
            this.pos = new Point2D.Double(this.pos.getX(), 5);
        } else if (this.pos.getY() > td.getMap().getHeight() * td.getMap().getTileHeight()) {
            this.pos = new Point2D.Double(this.pos.getX(), (td.getMap().getHeight() * td.getMap().getTileHeight()) - 5);
        }
    }

    public void offsetRight(int speed) {
        if (this.image.equals(imageRIGHT1)) {
            this.image = imageRIGHT2;
        } else {
            this.image = imageRIGHT1;
        }

        this.pos = new Point2D.Double(this.pos.getX() + speed, this.pos.getY());
        if (this.pos.getX() < 0) {
            this.pos = new Point2D.Double(5, this.pos.getY());
        } else if (this.pos.getX() > td.getMap().getWidth() * td.getMap().getTileWidth()) {
            this.pos = new Point2D.Double((td.getMap().getWidth() * td.getMap().getTileWidth()) - 5, this.pos.getY());
        } else if (this.pos.getY() < 0) {
            this.pos = new Point2D.Double(this.pos.getX(), 5);
        } else if (this.pos.getY() > td.getMap().getHeight() * td.getMap().getTileHeight()) {
            this.pos = new Point2D.Double(this.pos.getX(), (td.getMap().getHeight() * td.getMap().getTileHeight()) - 5);
        }
    }

    public void offsetDown(int speed) {
        if (this.image.equals(imageDOWN1)) {
            this.image = imageDOWN2;
        } else {
            this.image = imageDOWN1;
        }

        this.pos = new Point2D.Double(this.pos.getX(), this.pos.getY() + speed);
        if (this.pos.getX() < 0) {
            this.pos = new Point2D.Double(5, this.pos.getY());
        } else if (this.pos.getX() > td.getMap().getWidth() * td.getMap().getTileWidth()) {
            this.pos = new Point2D.Double((td.getMap().getWidth() * td.getMap().getTileWidth()) - 5, this.pos.getY());
        } else if (this.pos.getY() < 0) {
            this.pos = new Point2D.Double(this.pos.getX(), 5);
        } else if (this.pos.getY() > td.getMap().getHeight() * td.getMap().getTileHeight()) {
            this.pos = new Point2D.Double(this.pos.getX(), (td.getMap().getHeight() * td.getMap().getTileHeight()) - 5);
        }
    }

    public void offsetUp(int speed) {
        if (this.image.equals(imageUP1)) {
            this.image = imageUP2;
        } else {
            this.image = imageUP1;
        }
        this.pos = new Point2D.Double(this.pos.getX(), this.pos.getY() - speed);
        if (this.pos.getX() < 0) {
            this.pos = new Point2D.Double(5, this.pos.getY());
        } else if (this.pos.getX() > td.getMap().getWidth() * td.getMap().getTileWidth()) {
            this.pos = new Point2D.Double((td.getMap().getWidth() * td.getMap().getTileWidth()) - 5, this.pos.getY());
        } else if (this.pos.getY() < 0) {
            this.pos = new Point2D.Double(this.pos.getX(), 5);
        } else if (this.pos.getY() > td.getMap().getHeight() * td.getMap().getTileHeight()) {
            this.pos = new Point2D.Double(this.pos.getX(), (td.getMap().getHeight() * td.getMap().getTileHeight()) - 5);
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
//        if(this.performance != null) {
//            if (this.performance.getStage().getStageName().equals("Links boven")) {
//                for (int i = 0; i < this.tiles.size(); i++) {
//                    g.drawString("" + this.tiles.get(i).getDirection(), (int) this.tiles.get(i).getPosition().getX(), (int) this.tiles.get(i).getPosition().getY());
//                }
//            }
//        }
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

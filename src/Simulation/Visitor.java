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

    public Visitor(Point2D pos) {
        init();
        this.pos = pos;
        this.speed = new Point2D.Double(200,100);

    }

    public void init(){
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

    public void draw(Graphics2D g) {
        Point2D oldPos = new Point2D.Double(pos.getX() - speed.getX(), pos.getY() - speed.getY());
        direction = Direction.DOWN;
        switch (direction) {
            case UP:
                g.drawImage(imageUP, (int) (oldPos.getX() - speed.getX()), (int) (oldPos.getX() - speed.getX()), null);
                break;

            case DOWN:
                g.drawImage(imageDOWN, (int) (oldPos.getX()), (int) (oldPos.getX()), null);

                break;

            case LEFT:
                g.drawImage(imageLEFT, (int) (oldPos.getX() - speed.getX()), (int) (oldPos.getX() - speed.getX()), null);
                break;

            case RIGHT:
                g.drawImage(imageRIGHT, (int) (oldPos.getX() - speed.getX()), (int) (oldPos.getX() - speed.getX()), null);
                break;

            case STAY:
                break;
            default:
                g.drawImage(imageUP, (int) (oldPos.getX() - speed.getX()), (int) (oldPos.getX() - speed.getX()), null);
        }


    }

}

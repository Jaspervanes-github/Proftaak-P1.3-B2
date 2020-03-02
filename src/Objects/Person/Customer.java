package Objects.Person;

import Objects.Genre;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Customer extends Person {

    private Genre favoriteGenre;
    private String name;

    private Point2D posistion;
    private double angle;
    private BufferedImage sprite;
    private Point2D target;
    private double rotationSpeed;

    public Customer(Point2D posistion, BufferedImage sprite) {
        this.posistion = posistion;
        this.sprite = sprite;
        this.favoriteGenre = Genre.HOUSE;
        this.name = "";
        this.angle = 0.1;
        this.target = new Point2D.Double(200, 200);
        this.rotationSpeed = 0.1;
    }

    public void draw (Graphics2D g){
        
    }

    public void update(ArrayList<Customer> customers){

    }

    public Genre getFavoriteGenre() {
        return favoriteGenre;
    }

    public void setFavoriteGenre(Genre favoriteGenre) {
        this.favoriteGenre = favoriteGenre;
    }

    @Override
    public String getName() {
        return this.name;
    }


    @Override
    public void setName() {

    }

}

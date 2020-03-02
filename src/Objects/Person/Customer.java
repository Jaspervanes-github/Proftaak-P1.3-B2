package Objects.Person;

import Objects.Genre;
import Observer.Data;
import Observer.GUI;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Customer extends Person {

    private Genre favoriteGenre;
    private String name;

    private Point2D position;
    private double angle;
    private BufferedImage sprite;
    private Point2D target;
    private double rotationSpeed;
    private double speed;

    public Customer(Point2D position, BufferedImage sprite) {
        this.position = position;
        this.sprite = sprite;
        this.favoriteGenre = Genre.HOUSE;
        this.name = "";
        this.angle = 0;
        this.target = new Point2D.Double(200,200);
        this.rotationSpeed = 0.1;
        this.speed = 2;
    }

    public void draw (Graphics2D g){
        g.drawImage(sprite, getTransform(), null);
    }

    private AffineTransform getTransform() {
        AffineTransform tx = new AffineTransform();
        tx.translate(position.getX() - this.sprite.getWidth()/2, position.getY() - this.sprite.getHeight()/2);
        tx.rotate(this.angle, this.sprite.getWidth()/2, this.sprite.getHeight()/2);

        return tx;
    }

    public void update(ArrayList<Customer> customers){
        double targetAngle = Math.atan2(this.target.getY() - this.position.getY(),
                this.target.getX() - this.position.getX());

        double angleDifference = this.angle - targetAngle;
        while(angleDifference < -Math.PI)
            angleDifference += 2 * Math.PI;
        while(angleDifference > Math.PI)
            angleDifference -= 2 * Math.PI;


        if(Math.abs(angleDifference) < this.rotationSpeed)
            this.angle = targetAngle;
        else if(angleDifference < 0)
            this.angle += this.rotationSpeed;
        else
            this.angle -= this.rotationSpeed;

        Point2D newPosition = new Point2D.Double(this.position.getX() + this.speed * Math.cos(this.angle),
                this.position.getY() + this.speed * Math.sin(this.angle));

        boolean collided = false;

        for(Customer other : customers) {
            if(other != this && newPosition.distance(other.position) < 64) {
                collided = true;
            }
        }


        if(!collided) {
            this.position = newPosition;
        } else {
            this.angle -= this.rotationSpeed*2;
        }
    }

    public void setTarget(Point2D target) {
        this.target = target;
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

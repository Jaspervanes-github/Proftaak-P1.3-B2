package Simulation;//import java.awt.image.BufferedImage;
//
//
//public class Tile {
//
//    private int width;
//    private int height;
//    private BufferedImage image;
//    private int gid;
//
//    public Tile(BufferedImage image, int gid) {
//        this.image = image;
//        this.gid = gid;
//        if (image != null) {
//            this.width = image.getWidth();
//            this.height = image.getHeight();
//        }
//    }
//
//    public int getWidth() {
//        return width;
//    }
//
//    public int getHeight() {
//        return height;
//    }
//
//    public BufferedImage getImage() {
//        return image;
//    }
//
//    public int getGid() {
//        return gid;
//    }
//}

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TiledLayer {
    private int width;
    private int height;
    private String name;
    private int[][] map;
    private double opacity;
    private boolean visible;

    public ArrayList<Point2D> getCollision() {
        return collision;
    }

    private ArrayList<Point2D> collision = new ArrayList<>();
    private ArrayList<JsonObject> targets = new ArrayList<>();

    public ArrayList<JsonObject> getTargets() {
        return targets;
    }

    public TiledLayer(JsonObject jsonData) {
        if (jsonData.getString("name").equals("Object Layer")) {
            setTiledLayer(jsonData);
        } else {
            setTargets(jsonData);
        }
    }

    public void setTiledLayer(JsonObject jsonData) {
        this.width = jsonData.getInt("width");
        this.height = jsonData.getInt("height");

        this.name = jsonData.getString("name");
        this.opacity = jsonData.getJsonNumber("opacity").doubleValue();
        this.visible = jsonData.getBoolean("visible");


        map = new int[height][width];
        int i = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int input = jsonData.getJsonArray("data").getInt(i);
                if (input == 284) {
                    collision.add(new Point2D.Double(x, y));
                }

                if (input < 1441) {
                    map[y][x] = input;
                } else {
                    map[y][x] = 1222;
                }
//                map[y][x] = jsonData.getJsonArray("data").getInt(i);
                //System.out.println(jsonData.getJsonArray("data").getInt(i));
                i++;
            }
        }

//        JsonObject target = jsonData.getJsonArray("layers").getJsonObject(0);
    }

    public void setTargets(JsonObject jsonData) {
        for (int j = 0; j < jsonData.size(); j++) {
            this.targets.add(jsonData.getJsonArray("objects").getJsonObject(j));
            System.out.println(this.targets);
        }
    }

    public void draw(Graphics2D g, ArrayList<BufferedImage> tiles) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (map[y][x] == 0)
                    continue;

                //               System.out.println(tiles.get(map[x][y]));

                g.drawImage(
                        tiles.get(map[y][x]),
                        AffineTransform.getTranslateInstance(x * 16, y * 16),
                        null);
            }
        }
    }


}
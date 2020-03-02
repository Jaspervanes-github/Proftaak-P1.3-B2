import javax.imageio.ImageIO;
import javax.json.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class TerrainMap {
    private int width;
    private int height;

    private int tileHeight;
    private int tileWidth;

    private ArrayList<BufferedImage> tiles = new ArrayList<>();

    private int[][] map;

    public TerrainMap(String fileName)
    {
        JsonReader reader = Json.createReader(getClass().getResourceAsStream(fileName));
        JsonObject root = reader.readObject();
        this.width = root.getInt("width");
        this.height = root.getInt("height");

        System.out.println(root);
        System.out.println(TerrainMap.class.getResource("/").getPath());

        //load the tilemap

        //TODO
        //Kopieer Johan's code, voor elke layer die je hebt herhaal code.

        JsonArray layers = root.getJsonArray("layers");
        sortedJSON(layers);
        JsonArray sortedLayers = layers;

        try {
            BufferedImage tilemap = ImageIO.read(getClass().getResourceAsStream(root.getJsonArray("tilesets").getJsonObject(0).getString("image")));
            System.out.println(tilemap);

            for(int i = 0; i < layers.size(); i++){

                tileHeight = layers.getJsonObject(i).getInt("height");
                tileWidth = layers.getJsonObject(i).getInt("width");

                for(int y = 0; y <= tilemap.getHeight(); y += tileHeight)
            {
                for(int x = 0; x <= tilemap.getWidth(); x+= tileWidth)
                {
                    System.out.println("x: " + x);
                    System.out.println("y: " + y);
                    System.out.println("tileWidth: " + tileWidth);
                    System.out.println("tileHeight: " + tileHeight);
                    System.out.println("tilemap Width: " + tilemap.getWidth());
                    System.out.println("tilemap Height: " + tilemap.getHeight());

                    tiles.add(tilemap.getSubimage(x, y, tileWidth, tileHeight));
                }
            }
            }

        } catch (IOException e){
            e.printStackTrace();
        }

        map = new int[height][width];
        for(int y = 0; y <= height; y++)
        {
            for(int x = 0; x <= width; x++)
            {
                map[y][x] = layers.getJsonObject(y).getJsonArray("data").getInt(x);
            }
        }

//        try {
//            BufferedImage tilemap = ImageIO.read(getClass().getResourceAsStream(root.getJsonObject("tilemap").getString("file")));
//
//            tileHeight = root.getJsonObject("tilemap").getJsonObject("tile").getInt("height");
//            tileWidth = root.getJsonObject("tilemap").getJsonObject("tile").getInt("width");
//
//            for(int y = 0; y < tilemap.getHeight(); y += tileHeight)
//            {
//                for(int x = 0; x < tilemap.getWidth(); x += tileWidth)
//                {
//                    tiles.add(tilemap.getSubimage(x, y, tileWidth, tileHeight));
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        map = new int[height][width];
//        for(int y = 0; y < height; y++)
//        {
//            for(int x = 0; x < width; x++)
//            {
//                map[y][x] = root.getJsonArray("map").getJsonArray(y).getInt(x);
//            }
//        }
    }

    //Sorteerd de gegeven JsonArray op het "id" veld.
    public ArrayList<JsonObject> sortedJSON(JsonArray jsonValues){

        List<JsonObject> values = new ArrayList<>();
        for (int i = 0; i < jsonValues.size(); i++){
            values.add(jsonValues.getJsonObject(i));
        }

        Collections.sort(values, new Comparator<JsonObject>() {

            @Override
            public int compare(JsonObject a, JsonObject b) {

                String valueA = new String();
                String valueB = new String();

                try{

                    int idA = a.getInt("id");
                    int idB = b.getInt("id");

                    valueA = String.valueOf(idA);
                    valueB = String.valueOf(idB);

                }catch (JsonException e){
                    e.printStackTrace();
                }

                return valueA.compareTo(valueB);
            }
        });

        ArrayList<JsonObject> sortedJson = new ArrayList<>();

        for (int i = 0; i < values.size(); i++){
            sortedJson.add(values.get(i));
        }

        return sortedJson;
    }

    void draw(Graphics2D g2d)
    {

        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                if(map[y][x] < 0)
                    continue;

                g2d.drawImage(
                        tiles.get(map[y][x]),
                        AffineTransform.getTranslateInstance(x*tileWidth, y*tileHeight),
                        null);
            }
        }


    }

}

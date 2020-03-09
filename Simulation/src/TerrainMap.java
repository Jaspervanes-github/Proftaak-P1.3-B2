//import javax.imageio.ImageIO;
//import javax.json.*;
//import java.awt.*;
//import java.awt.geom.AffineTransform;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.util.*;
//import java.util.List;
//
//
//public class TerrainMap {
//    private int width;
//    private int height;
//
//    private int tileHeight;
//    private int tileWidth;
//
//    //private ArrayList<BufferedImage> tiles = new ArrayList<>();
//    private HashMap<Integer, Tile> tiles = new HashMap<>();
//
//    private int[][] map;
//
//    public TerrainMap(String fileName) throws IOException {
//        JsonReader reader = Json.createReader(getClass().getResourceAsStream(fileName));
//        JsonObject root = reader.readObject();
//        this.width = root.getInt("width");
//        this.height = root.getInt("height");
//
//
//        JsonArray tileSets =  root.getJsonArray("tilesets");
//
//        for (int i = 0; i < tileSets.size(); i++) {
//            JsonObject tileSet = tileSets.getJsonObject(i);
//
//            String fileLocation = tileSet.getString("image");
//            int tileWidth = tileSet.getInt("tilewidth");
//            int tileHeigt = tileSet.getInt("tileheight");
//
//            BufferedImage tilemap = ImageIO.read(getClass().getResourceAsStream(fileLocation));
//
//
//            int Nwidth = tilemap.getWidth()/tileWidth;
//            int Nheight = tilemap.getHeight()/tileHeigt;
//            int tileCount = tileSet.getInt("tilecount");
//            int firstGid = tileSet.getInt("firstgid");
//
//            for (int y = 0; y < Nheight; y++) {
//                for (int x = 0; x < Nwidth; x++) {
//                    BufferedImage img = tilemap.getSubimage(x*tileWidth, y*tileHeigt, tileWidth, tileHeigt);
//                    int gid = firstGid + y*Nwidth+x;
//
//                    Tile tile = new Tile(img, gid);
//                    tiles.put(gid, tile);
//                }
//            }
//        }
//
//
//
//        JsonArray layers = root.getJsonArray("layers");
//        //sortedJSON(layers);
//        //JsonArray sortedLayers = layers;
//
//
//        map = new int[height][width];
//
//        for(int i = 0; i < layers.size(); i++){
//
//            JsonObject layer = layers.getJsonObject(i);
//
//            tileHeight = layers.getJsonObject(i).getInt("height");
//            tileWidth = layers.getJsonObject(i).getInt("width");
//
//            JsonArray data = layer.getJsonArray("data");
//            for(int y = 0; y < height; y++)
//            {
//                for(int x = 0; x < width; x++)
//                {
//                    if (data.getInt(y*height+x) > 0)
//                        map[y][x] = data.getInt(y*height+x);
//                }
//            }
//
//        }
//    }
//
//    //Sorteerd de gegeven JsonArray op het "id" veld.
//    public ArrayList<JsonObject> sortedJSON(JsonArray jsonValues){
//
//        List<JsonObject> values = new ArrayList<>();
//        for (int i = 0; i < jsonValues.size(); i++){
//            values.add(jsonValues.getJsonObject(i));
//        }
//
//        Collections.sort(values, new Comparator<JsonObject>() {
//
//            @Override
//            public int compare(JsonObject a, JsonObject b) {
//
//                String valueA = new String();
//                String valueB = new String();
//
//                try{
//
//                    int idA = a.getInt("id");
//                    int idB = b.getInt("id");
//
//                    valueA = String.valueOf(idA);
//                    valueB = String.valueOf(idB);
//
//                }catch (JsonException e){
//                    e.printStackTrace();
//                }
//
//                return valueA.compareTo(valueB);
//            }
//        });
//
//        ArrayList<JsonObject> sortedJson = new ArrayList<>();
//
//        for (int i = 0; i < values.size(); i++){
//            sortedJson.add(values.get(i));
//        }
//
//        return sortedJson;
//    }
//
//    void draw(Graphics2D g2d)
//    {
//
//        TerrainDemo terrainDemo = new TerrainDemo();
//
//        for(int y = 0; y < height; y++)
//        {
//            for(int x = 0; x < width; x++)
//            {
//                if(map[y][x] < 1)
//                    continue;
//
//                Tile t = tiles.get(map[y][x]);
//                if (t == null || t.getImage() == null) {
//                    x++;
//                    y++;
//                    continue;
//                }
//
//                //g2d.drawImage(t.getImage(), x*tileWidth, y*tileHeight, (int)terrainDemo.getCanvasWidth() , (int)terrainDemo.getCanvasHeight(), null);
//                //g2d.drawImage(t.getImage(), x*tileWidth, y*tileHeight, tileWidth, tileHeight, null);
//
//                g2d.drawImage(
//                        t.getImage(),
//                        AffineTransform.getTranslateInstance( x*15.6, y*15.6),
//                        null);
//            }
//        }
//
//    }
//
//
//}

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;



public class TerrainMap {
    private int width;
    private int height;

    private int tileHeight;
    private int tileWidth;

    private ArrayList<BufferedImage> tiles = new ArrayList<>();

    private ArrayList<TiledLayer> layers = new ArrayList<>();

    public TerrainMap(String fileName)
    {
        JsonReader reader = null;
        reader = Json.createReader(getClass().getResourceAsStream(fileName));
        JsonObject root = reader.readObject();

        this.width = root.getInt("width");
        this.height = root.getInt("height");

        JsonArray tilesets = root.getJsonArray("tilesets");

        for(int i = 0; i < tilesets.size(); i++) {
            //load the tilemap
            try {
                JsonObject tileset = tilesets.getJsonObject(i);

                BufferedImage tilemap = ImageIO.read(getClass().getResourceAsStream(tileset.getString("image")));

                tileHeight = tileset.getInt("tileheight");
                tileWidth = tileset.getInt("tilewidth");
                int gid = tileset.getInt("firstgid");
                int tileCount = tileset.getInt("tilecount");

                //er voor zorgen dat tiles gid+tileCount elementen groot is
                while(tiles.size() < gid + tileCount) {
                    tiles.add(null);
                }


                for (int y = 0; y < tilemap.getHeight(); y += tileHeight) {
                    for (int x = 0; x < tilemap.getWidth(); x += tileWidth) {
                        tiles.set(gid, tilemap.getSubimage(x, y, tileWidth, tileHeight));

                        gid++;
                       // System.out.println(gid);
                    }
                }
                System.out.println(this.tiles.size());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        JsonArray layers = root.getJsonArray("layers");


        for(int i = 0; i < layers.size(); i++) {
            JsonObject layerInfo = layers.getJsonObject(i);
            if(layerInfo.getString("type").equals("tilelayer")) {
                this.layers.add(new TiledLayer(layerInfo));
            }
        }
    }

    void draw(Graphics2D g2d)
    {

        for(TiledLayer layer : this.layers) {
            layer.draw(g2d, tiles);
        }


    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public ArrayList<BufferedImage> getTiles() {
        return tiles;
    }

    public ArrayList<TiledLayer> getLayers() {
        return layers;
    }
}


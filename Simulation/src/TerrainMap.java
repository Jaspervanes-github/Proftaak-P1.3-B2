import javax.imageio.ImageIO;
import javax.json.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;


public class TerrainMap {
    private int width;
    private int height;

    private int tileHeight;
    private int tileWidth;

    //private ArrayList<BufferedImage> tiles = new ArrayList<>();
    private HashMap<Integer, Tile> tiles = new HashMap<>();

    private int[][] map;

    public TerrainMap(String fileName) throws IOException {
        JsonReader reader = Json.createReader(getClass().getResourceAsStream(fileName));
        JsonObject root = reader.readObject();
        this.width = root.getInt("width");
        this.height = root.getInt("height");

        //System.out.println(root);
        //System.out.println(TerrainMap.class.getResource("/").getPath());

        //load the tilemap

        //TODO
        //Kopieer Johan's code, voor elke layer die je hebt herhaal code.


        JsonArray tileSets =  root.getJsonArray("tilesets");
        for (int i = 0; i < tileSets.size(); i++) {
            JsonObject tileSet = tileSets.getJsonObject(i);

            String fileLocation = tileSet.getString("image");
            int tileWidth = tileSet.getInt("tilewidth");
            int tileHeigt = tileSet.getInt("tileheight");


            BufferedImage tilemap = ImageIO.read(getClass().getResourceAsStream(fileLocation));


            int Nwidth = tilemap.getWidth()/tileWidth;
            int Nheight = tilemap.getHeight()/tileHeigt;

            int firstGid = tileSet.getInt("firstgid");

            for (int y = 0; y < Nheight; y++) {
                for (int x = 0; x < Nwidth; x++) {
                    BufferedImage img = tilemap.getSubimage(x*tileWidth, y*tileHeigt, tileWidth, tileHeigt);
                    int gid = firstGid + y*Nwidth+x;

                    Tile tile = new Tile(img, gid);
                    tiles.put(gid, tile);
                }
            }
        }



        JsonArray layers = root.getJsonArray("layers");
        sortedJSON(layers);
        JsonArray sortedLayers = layers;


        map = new int[height][width];

        for(int i = 0; i < layers.size(); i++){

            JsonObject layer = layers.getJsonObject(i);

            tileHeight = layers.getJsonObject(i).getInt("height");
            tileWidth = layers.getJsonObject(i).getInt("width");

            JsonArray data = layer.getJsonArray("data");
            for(int y = 0; y < height; y++)
            {
                for(int x = 0; x < width; x++)
                {
                    if (data.getInt(y*height+x) > 0)
                        map[y][x] = data.getInt(y*height+x);
                }
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
                if(map[y][x] < 1)
                    continue;

                Tile t = tiles.get(map[y][x]);
                if (t == null || t.getImage() == null) {
                    continue;
                }

                g2d.drawImage(t.getImage(), x*tileWidth, y*tileHeight, tileWidth, tileHeight, null);

                /*g2d.drawImage(
                        t.getImage(),
                        AffineTransform.getTranslateInstance(x*tileWidth, y*tileHeight),
                        null);*/
            }
        }


    }

}

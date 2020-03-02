import java.awt.image.BufferedImage;


public class Tile {

    private int width;
    private int height;
    private BufferedImage image;
    private int gid;

    public Tile(BufferedImage image, int gid) {
        this.image = image;
        this.gid = gid;
        if (image != null) {
            this.width = image.getWidth();
            this.height = image.getHeight();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getGid() {
        return gid;
    }
}

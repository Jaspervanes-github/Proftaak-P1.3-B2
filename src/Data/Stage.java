package Data;

public class Stage {

    private String genre;
    private String hostName;
    private String description;

    public Stage(String genre, String hostName, String description){
        this.genre = genre;
        this.hostName = hostName;
        this.description = description;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
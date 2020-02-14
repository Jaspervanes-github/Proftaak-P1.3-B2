package File_IO;

import Data.Performance;
import Data.Person.Artist;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class File_IO implements Serializable{

    public void writePerformanceFile(String filename, ObservableList<Performance> list) throws FileNotFoundException {

        ArrayList<Performance> performances;

        if(list instanceof ArrayList<?>){
            performances = (ArrayList<Performance>) list;
        } else {
            performances = new ArrayList<>(list);
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/File_IO/" + filename);
            ObjectOutputStream objectInputStream = new ObjectOutputStream(fileOutputStream);
            objectInputStream.writeObject(performances);
            fileOutputStream.close();
        } catch(IOException e){
            System.out.println("Exception in writeFile()");
            e.printStackTrace();
        }

    }

    public void writeArtistFile(String filename, ObservableList<Artist> list) throws FileNotFoundException {

        ArrayList<Artist> artists;

        if(list instanceof ArrayList<?>){
            artists = (ArrayList<Artist>) list;
        } else {
            artists = new ArrayList<>(list);
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/File_IO/" + filename);
            ObjectOutputStream objectInputStream = new ObjectOutputStream(fileOutputStream);
            objectInputStream.writeObject(artists);
            fileOutputStream.close();
        } catch(IOException e){
            System.out.println("Exception in writeFile()");
            e.printStackTrace();
        }
    }

    public ArrayList readFile(String filename) throws FileNotFoundException, EOFException {

        ArrayList List = new ArrayList();

        try {
            FileInputStream fileInputStream = new FileInputStream("src/File_IO/" + filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            if(filename.equals("Artists.txt")){
                List = (ArrayList<Artist>) objectInputStream.readObject();
            }else if (filename.equals("Performances.txt")) {
                List = (ArrayList<Performance>) objectInputStream.readObject();
            }
            objectInputStream.close();
        } catch (EOFException e){
            System.out.println("File is empty");
        } catch (FileNotFoundException e){
            System.out.println("File not found, generating a new one...");
        } catch (ClassNotFoundException e){
            System.out.println("Class not found in readFile()");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Exception in readFile()");
            e.printStackTrace();
        }

        return List;
    }
}

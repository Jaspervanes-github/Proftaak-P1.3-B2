package File_IO;

import Objects.Performance;
import Objects.Person.Artist;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class File_IO implements Serializable{

    public void writeFilePerformances(String filename, ObservableList<Performance> list) throws FileNotFoundException {

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
            System.out.println("Exception in writeFilePerformances()");
            e.printStackTrace();
        }

    }

    public ArrayList<Performance> readFilePerformances(String filename) throws FileNotFoundException, EOFException {

        ArrayList performances = new ArrayList();

        try {
            FileInputStream fileInputStream = new FileInputStream("src/File_IO/" + filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            performances = (ArrayList<Performance>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (EOFException e){
            System.out.println("File is empty");
        } catch (FileNotFoundException e){
            System.out.println("File not found, generating a new one...");
        } catch (ClassNotFoundException e){
            System.out.println("Class not found in readFilePerformances()");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Exception in readFilePerformances()");
            e.printStackTrace();
        }

        return performances;
    }

    public void writeFileArtist(String filename, ObservableList<Artist> list) throws FileNotFoundException {

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
            System.out.println("Exception in writeFilePerformances()");
            e.printStackTrace();
        }

    }

    public ArrayList<Artist> readFileArtist(String filename) throws FileNotFoundException, EOFException {

        ArrayList artists = new ArrayList();

        try {
            FileInputStream fileInputStream = new FileInputStream("src/File_IO/" + filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            artists = (ArrayList<Performance>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (EOFException e){
            System.out.println("File is empty");
        } catch (FileNotFoundException e){
            System.out.println("File not found, generating a new one...");
        } catch (ClassNotFoundException e){
            System.out.println("Class not found in readFileArtist()");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Exception in readFileArtist()");
            e.printStackTrace();
        }

        return artists;
    }
}

package File_IO;

import Data.Performance;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class File_IO implements Serializable{

    public void writeFile(String filename, ObservableList<Performance> list) throws FileNotFoundException {

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

    public ArrayList<Performance> readFile(String filename) throws FileNotFoundException, EOFException {

        ArrayList performances = new ArrayList();

        try {
            FileInputStream fileInputStream = new FileInputStream("src/File_IO/" + filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            performances = (ArrayList<Performance>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (EOFException e){
            System.out.println("File is empty");
        } catch (ClassNotFoundException e){
            System.out.println("Class not found in readFile()");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Exception in readFile()");
            e.printStackTrace();
        }

        return performances;
    }
}

package iostream.task3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FilmTask {
    public static List<Film> restoreFilmCollection(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filePath))) {
            return (ArrayList<Film>) ois.readObject();
        } catch (Exception ex) {
            return null;
        }
    }

    public static void saveFilmCollection(String filePath, List<Film> filmsList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filePath))) {
            oos.writeObject(filmsList);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

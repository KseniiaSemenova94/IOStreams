package iostream.task3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main implements Serializable {

    private static BufferedReader reader = null;
    private static Integer action = null;
    private static List<Film> filmsList = null;
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) throws IOException {
        filmsList = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("src/main/java/iostream/files/FilmCollection.txt")))
        {
            filmsList = (ArrayList<Film>)ois.readObject();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }





        System.out.println("Hello! This is your film collection.\n" +
                "Please select an action from the menu.");
        reader = new BufferedReader(new InputStreamReader(System.in));

        do {
            System.out.println(
                    "1. Show the whole collection.\n" +
                            "2. Add a new film to the collection.\n" +
                            "3. Edit existing film in collection.\n" +
                            "4. Remove film from collection.\n" +
                            "5. Close the program.\n");
            action = readAction();
            if (action == null || action < 1 || action > 5) {
                System.out.println("Некорректный номер действия. Ввведите, пожалуйста, число из списка.");
            } else {
                if (action == 1) {
                    showAllFilms(filmsList);
                }
                if (action == 2) {
                    addNewFilm(filmsList);
                }
                if (action == 3) {

                }
                if (action == 4) {

                }
            }
        } while (action == null || action != 5);
        reader.close();

        try(ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("src/main/java/iostream/files/FilmCollection.txt")))
        {
            oos.writeObject(filmsList);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    private static Integer readAction() throws IOException {
        Integer action;
        try {
            action = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            return null;
        }
        return action;
    }

    private static void addNewFilm(List<Film> filmsList) throws IOException {
        System.out.println("Enter film title.");
        String title = reader.readLine();
        System.out.println("Enter the names and surnames of the actors playing in this film.");
        String actors = reader.readLine();
        System.out.println();
        List<String> actorsList = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < actors.length(); i++) {
            if (actors.substring(i, i+1).equals(",")) {
                String newActors = actors.substring(start, i).trim();
                actorsList.add(newActors);
                start = i + 1;
            }
        }
        Film film = new Film(title, actorsList);
        filmsList.add(film);
    }

    private static void showAllFilms(List<Film> filmsList) {
        int counter = 0;

        for (Film film : filmsList) {
            counter++;
            System.out.print("Film №" + counter + ":  ");
            film.showTitle();
            System.out.print("The starring: ");
            film.showActors();
            System.out.println();
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println();
        }
    }
}
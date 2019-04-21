package iostream.task3;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main implements Serializable {

    private final static String FILE_PATH = "src/main/java/iostream/files/FilmCollection.txt";

    private static Integer action = null;
    private static List<Film> filmsList = null;
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        filmsList = FilmTask.restoreFilmCollection(FILE_PATH);

        System.out.println("Hello! This is your film collection.\n" +
                "Please select an action from the menu.");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            do {
                System.out.println(
                        "1. Show the whole collection.\n" +
                                "2. Add a new film to the collection.\n" +
                                "3. Remove film from collection.\n" +
                                "4. Close the program.\n");
                action = readAction(reader);
                if (action == null || action < 1 || action > 4) {
                    System.out.println("Некорректный номер действия. Ввведите, пожалуйста, число из списка.");
                } else {
                    if (action == 1) {
                        showAllFilms(filmsList);
                    }
                    if (action == 2) {
                        addNewFilm(filmsList, reader);
                    }
                    if (action == 3) {
                        System.out.println("Enter name of film");
                        String title = reader.readLine();
                        removeFilm(title);
                    }
                }
            } while (action == null || action != 4);

        } catch (IOException e) {
            e.printStackTrace();
        }

        FilmTask.saveFilmCollection(FILE_PATH, filmsList);
    }

    private static Integer readAction(BufferedReader reader) throws IOException {
        Integer action;
        try {
            action = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            return null;
        }
        return action;
    }

    private static void addNewFilm(List<Film> filmsList, BufferedReader reader) throws IOException {
        System.out.println("Enter film title.");
        String title = reader.readLine();
        System.out.println("Enter the names and surnames of the actors playing in this film.");
        String actors = reader.readLine();
        System.out.println();
        List<String> actorsList = new ArrayList<>(Arrays.asList(actors.split(",")));
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

    private static void removeFilm(String name) {
        Film filmToDelete = null;
        for (Film film : filmsList) {
            if (film.getTitle().equals(name)) {
                filmToDelete = film;
            }
        }
        if (filmToDelete == null) {
            System.out.println("There is no film with such name.");
        } else {
            filmsList.remove(filmToDelete);
            System.out.println("Film was successfully removed.");
            System.out.println();
        }
    }
}
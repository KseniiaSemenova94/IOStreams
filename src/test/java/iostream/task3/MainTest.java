package iostream.task3;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void createFilmCollectionTest() throws IOException {
        Date date = new Date();
        String path = "C:/Users/Ksenia/IdeaProjects/recent/IOStreams/src/main/java/iostream/files/" + date.getTime() + ".txt";
        File f = new File(path);
        f.getParentFile().mkdirs();
        f.createNewFile();
        List<String> actorList = new ArrayList<>();
        actorList.add("actor1");
        actorList.add("actor2");
        String filmName = "testFilm";
        Film film = new Film(filmName, actorList);
        List<Film> filmArrayList = new ArrayList<>();
        filmArrayList.add(film);
        FilmTask.saveFilmCollection(path, filmArrayList);
        List<Film> resultList = FilmTask.restoreFilmCollection(path);
        Film resultFilm = null;
        for (Film filmEl : resultList) {
            if (film.getTitle().equals(filmName)) {
                resultFilm = filmEl;
            }
        }
        assertNotNull(resultFilm);
        assertEquals(resultFilm.getTitle(), filmName);
        assertEquals("actor1", resultFilm.getActors().get(0));
        f.delete();
    }
}

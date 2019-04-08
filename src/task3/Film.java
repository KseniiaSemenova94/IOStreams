package task3;

import java.io.Serializable;
import java.util.List;

public class Film implements Serializable {
    private String title;

    private List<String> actors;

    private static final long serialVersionUID = 1L;

    Film (String title, List<String> actors) {
        this.title = title;
        this.actors = actors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getActors() {
        return actors;
    }

    public void showTitle() {
        System.out.println("The title: " + this.title);
    }

    public void showActors() {
        for (String actor : this.actors) {
            System.out.print(actor + "; ");
        }
    }

}
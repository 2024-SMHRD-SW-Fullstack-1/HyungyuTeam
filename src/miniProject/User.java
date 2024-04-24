package miniProject;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private int score;

    public User(String id, int score) {
        this.id = id;
        this.score = score;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}


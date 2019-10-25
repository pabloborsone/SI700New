package Internet;

import java.util.ArrayList;

public class InternetGame {
    private String biography;
    private String name;
    private ArrayList<String> wrongNames;

    String getBiography() {
        return biography;
    }
    void setBiography(String bio) {
        this.biography = bio;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    ArrayList<String> getwrongNames() {
        return wrongNames;
    }
    void setwrongNames(ArrayList<String> wrongNames) {
        this.wrongNames = wrongNames;
    }
}

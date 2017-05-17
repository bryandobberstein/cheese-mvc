package org.launchcode.cheesemvc.models;

/**
 * Created by max on 5/17/17.
 */
public class Cheese {

    private String name;
    private String description;
    private int cheeseId;
    private static int nextId = 1;

    public Cheese(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    public Cheese(){
        this.cheeseId = nextId;
        nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    @Override
    public String toString() {
        return "Cheese{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

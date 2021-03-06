package org.launchcode.cheesemvc.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by max on 5/17/17.
 */
@Entity
public class Cheese {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 1, message = "Please include a name.")
    private String name;

    @NotNull
    @Size(min = 1, message = "Please include a description.")
    private String description;

    @ManyToOne
    private Category category;

    public Cheese(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Cheese(){}

    public int getId() {
        return id;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

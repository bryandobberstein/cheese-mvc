package org.launchcode.cheesemvc.models;

/**
 * Created by max on 5/18/17.
 */
public enum CheeseType {

    HARD ("Hard"),
    SEMISOFT ("Semisoft"),
    SOFT ("Soft"),
    FAKE ("Fake");

    private final String name;

    CheeseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
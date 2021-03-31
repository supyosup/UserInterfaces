/**
 * The Journal class represents a Journal for a specific Module.
 * Entries in the Journal include Locations, Dates, and Descriptions of what the user saw.
 */

package com.example.bloomingwithbirdie;

import java.io.Serializable;
import java.util.ArrayList;

public class Journal implements Serializable {
    private String name;
    private ArrayList<int[]> dates;
    private ArrayList<String> descriptions;
    private ArrayList<String> locations;

    public Journal(String name) {
        this.name = name;
        dates = new ArrayList<>();
        descriptions = new ArrayList<>();
        locations = new ArrayList<>();
    }

    public void addEntry(int day, int month, int year, String description) {
        int[] dateEntry = new int[] {day, month, year};
        dates.add(dateEntry);

        if (!description.isEmpty()) {
            descriptions.add(description);
        }
    }

    /** Used in for the "Where I saw it" Journal entry**/
    public void addLocation(String location) {
        locations.add(location);
    }

    public ArrayList<String> getDescriptions() {
        return descriptions;
    }

    public ArrayList<String> getLocations() {
        return locations;
    }
}

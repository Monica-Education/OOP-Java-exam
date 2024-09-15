package classes;

import java.time.LocalDate;

public class Coin extends Items {
    private int diameter;
    private String metal;

    public Coin(int id, String location, int finderId, LocalDate foundDate, int assumedYear, Integer museumId, int diameter, String metal) {
        super(id, location, finderId, foundDate, assumedYear, museumId);
        this.diameter = diameter;
        this.metal = metal;
    }
    public int getDiameter() {
        return diameter;
    }

    public String getMetal() {
        return metal;
    }

    @Override
    public String toString() {
        return "Coin: ID: " + getId() + ", Location: " + getLocation() + ", Finder ID: " + getFinderId() + ", Found Date: " + getFoundDate() +
                ", Assumed Year: " + getAssumedYear() + ", Museum ID: " + getMuseumId() + ", Diameter: " + diameter + ", Metal: " + metal;
    }
}


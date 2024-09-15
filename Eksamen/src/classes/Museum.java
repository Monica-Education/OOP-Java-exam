package classes;

public class Museum {
    private int id;
    private String name;
    private String location;

    public Museum(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getLocation() {
        return location;
    }

    public String toString() {
        return "Museum: ID: " + id + ", Navn: " + name + ", Sted: " + location;
    }
}

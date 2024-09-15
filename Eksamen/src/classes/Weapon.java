package classes;

import java.time.LocalDate;

public class Weapon extends Items {
    private String type;
    private String material;
    private int weight;

    public Weapon(int id, String location, int finderId, LocalDate foundDate, int assumedYear, Integer museumId, String type, String material, int weight){
        super(id, location, finderId, foundDate, assumedYear, museumId);
        this.type = type;
        this.material = material;
        this.weight = weight;
    }
    public String getType(){
        return type;
    }
    public String getMaterial(){
        return material;
    }
    public int getWeight(){
        return weight;
    }

    @Override
    public String toString() {
        return "Smykke: ID: " + getId() + ", Location: " + getLocation() + ", Finder ID: " + getFinderId() + ", Found Date: " + getFoundDate() +
                ", Assumed Year: " + getAssumedYear() + ", Museum ID: " + getMuseumId() + ", Type: " + type + ", material: " + material + " weight: " + weight;
    }
}


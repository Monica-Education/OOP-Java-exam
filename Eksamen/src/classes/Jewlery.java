package classes;

import java.time.LocalDate;

public class Jewlery extends Items{
    private String type;
    private int value;
    private String fileName;

    public Jewlery(int id, String location, int finderId, LocalDate foundDate, int assumedYear, Integer museumId, String type, int value, String fileName){
        super(id, location, finderId, foundDate, assumedYear, museumId);
        this.type = type;
        this.value = value;
        this.fileName = fileName;
    }
    public String getType(){
        return type;
    }
    public int getValue(){
        return value;
    }
    public String getFileName(){
        return fileName;
    }

    @Override
    public String toString() {
        return "Jewlery: ID: " + getId() + ", Location: " + getLocation() + ", Finder ID: " + getFinderId() + ", Found Date: " + getFoundDate() +
                ", Assumed Year: " + getAssumedYear() + ", Museum ID: " + getMuseumId() + ", Type: " + type + ", value: " + value + " filename: " + fileName;
    }
}


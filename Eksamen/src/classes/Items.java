package classes;

import java.time.LocalDate;

public class Items {
    private int id;
    private String location;
    private int finderId;
    private LocalDate foundDate;
    private int assumedYear;
    private Integer museumId;

    public Items(int id, String location, int finderId, LocalDate foundDate, int assumedYear, Integer museumId) {
        this.id = id;
        this.location = location;
        this.finderId = finderId;
        this.foundDate = foundDate;
        this.assumedYear = assumedYear;
        this.museumId = museumId;
    }

    public int getId() {
        return id;
    }
    public String getLocation() {
        return location;
    }
    public int getFinderId() {
        return finderId;
    }
    public LocalDate getFoundDate() {
        return foundDate;
    }
    public int getAssumedYear() {
        return assumedYear;
    }
    public Integer getMuseumId() {
        return museumId;
    }
}

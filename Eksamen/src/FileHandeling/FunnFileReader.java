package FileHandeling;

import database.*;
import classes.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FunnFileReader {

    public static String filePath = "files/Funn.txt";

    ArrayList<Person> people = new ArrayList<>();
    ArrayList<Museum> museums = new ArrayList<>();
    ArrayList<Items> items = new ArrayList<>();

    public FunnFileReader(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        readFile(this.filePath);
    }

    public void readFile(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            switch (line) {
                case "Personer:" -> {
                    int number = Integer.parseInt(scanner.nextLine());
                    addPerson(scanner, number);
                }
                case "Museer:" -> {
                    int number = Integer.parseInt(scanner.nextLine());
                    addMuseum(scanner, number);
                }
                case "Funn:" -> {
                    addItems(scanner);
                }
            }
        }
    }

    public void addPerson(Scanner scanner, int number) {
        for (int i = 0; i < number; i++) {
            ArrayList<String> line = new ArrayList<>();
            for (int n = 0; n < 4; n++) {
                line.add(scanner.nextLine());
            }
            people.add(createPerson(line));
        }
    }

    public void addMuseum(Scanner scanner, int number) {
        for (int i = 0; i < number; i++) {
            ArrayList<String> line = new ArrayList<>();
            for (int n = 0; n < 3; n++) {
                line.add(scanner.nextLine());
            }
            museums.add(createMuseum(line));
        }
    }

    public void addItems(Scanner scanner) {
        while (scanner.hasNextLine()) {
            ArrayList<String> line = new ArrayList<>();

            String lines = scanner.nextLine();

            while (!lines.equals("-------")) {
                line.add(lines);
                lines = scanner.nextLine();
            }
            switch (line.get(6)) {
                case "Mynt" -> items.add(createCoin(line));
                case "Smykke" -> items.add(createJewelry(line));
                case "Våpen" -> items.add(createWeapon(line));
            }
        }
    }

    public Person createPerson(ArrayList<String> line) {
        int id = Integer.parseInt(line.get(0));
        String name = line.get(1);
        String phone = line.get(2);
        String email = line.get(3);
        return new Person(id, name, phone, email);
    }

    public Museum createMuseum(ArrayList<String> line) {
        int id = Integer.parseInt(line.get(0));
        String name = line.get(1);
        String location = line.get(2);
        return new Museum(id, name, location);
    }

    public Coin createCoin(ArrayList<String> lines) {
        int id = Integer.parseInt(lines.get(0));
        String location = lines.get(1);
        int finderId = Integer.parseInt(lines.get(2));
        LocalDate foundDate = FormatDate(lines.get(3));
        int assumedYear = Integer.parseInt(lines.get(4));

        String museumIdString = lines.get(5);
        Integer museumId = museumIdString.isEmpty() ? null : Integer.parseInt(museumIdString);

        int diameter = Integer.parseInt(lines.get(7));
        String metal = lines.get(8);

        return new Coin(id, location, finderId, foundDate, assumedYear, museumId, diameter, metal);
    }

    public Jewlery createJewelry(ArrayList<String> lines) {
        int id = Integer.parseInt(lines.get(0));
        String location = lines.get(1);
        int finderId = Integer.parseInt(lines.get(2));
        LocalDate foundDate = FormatDate(lines.get(3));
        int assumedYear = Integer.parseInt(lines.get(4));

        String museumIdString = lines.get(5);
        Integer museumId = museumIdString.isEmpty() ? null : Integer.parseInt(museumIdString);

        String type = lines.get(7);
        int value = Integer.parseInt(lines.get(8));
        String fileName = lines.get(9);

        return new Jewlery(id, location, finderId, foundDate, assumedYear, museumId, type, value, fileName);
    }

    public Weapon createWeapon(ArrayList<String> lines) {
        int id = Integer.parseInt(lines.get(0));
        String location = lines.get(1);
        int finderId = Integer.parseInt(lines.get(2));
        LocalDate foundDate = FormatDate(lines.get(3));
        int assumedYear = Integer.parseInt(lines.get(4));

        String museumIdString = lines.get(5);
        Integer museumId = museumIdString.isEmpty() ? null : Integer.parseInt(museumIdString);

        String type = lines.get(7);
        String material = lines.get(8);
        int weight = Integer.parseInt(lines.get(9));

        return new Weapon(id, location, finderId, foundDate, assumedYear, museumId, type, material, weight);
    }



    public LocalDate FormatDate(String dateString) {
        String[] dateParts = dateString.split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);
        return LocalDate.of(year, month, day);
    }

    public void printPeople() {
        System.out.println("Personer:");
        for (Person person : people) {
            System.out.println(person);
        }
    }

    public void printMuseums() {
        System.out.println("Museumer:");
        for (Museum museum : museums) {
            System.out.println(museum);
        }
    }

    public void printItems() {
        System.out.println("Funngjenstander:");
        for (Items item : items) {
            System.out.println(item);
        }
    }

    public void PrintEverything() {
        printPeople();
        printMuseums();
        printItems();
    }
    public void importToDatabase() {
        dataImport importer = new dataImport();
        Connection conn = null;

        try {
            conn = database.getConnection();
            for (Person person : people) {
                importer.insertPerson(conn, person);
            }
            for (Museum museum : museums) {
                importer.insertMuseum(conn, museum);
            }
            for (Items item : items) {
                if (item instanceof Coin) {
                    importer.insertCoin(conn, (Coin) item);
                } else if (item instanceof Jewlery) {
                    importer.insertJewelry(conn, (Jewlery) item);
                } else if (item instanceof Weapon) {
                    importer.insertWeapon(conn, (Weapon) item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}





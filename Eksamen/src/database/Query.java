package database;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;
import Interface.*;

public class Query{
    Connection conn = database.getConnection();

    public Query() throws SQLException {
    }

    //Henter informasjon om alle objektene
   public void allItems() throws SQLException {
        Statement statement = conn.createStatement();

        String weaponSql = "SELECT * FROM Vaapen";
        String jewlerySql = "SELECT * FROM Smykke";
        String coinSql = "SELECT * FROM Mynt";

        ResultSet weaponRs = statement.executeQuery(weaponSql);
        System.out.println("Våpen:");
        while (weaponRs.next()) {
            int ID = weaponRs.getInt("Id");
            String Location = weaponRs.getString("Funnsted");
            int FinderId = weaponRs.getInt("Finner_id");
            LocalDate FoundDate = weaponRs.getDate("Funntidspunkt").toLocalDate();
            int AssumedYear = weaponRs.getInt("Antatt_årstall");
            Integer MuseumId = weaponRs.getInt("Museum_id");
            String Type = weaponRs.getString("Type");
            String Material = weaponRs.getString("Materiale");
            int Weight = weaponRs.getInt("Vekt");
            System.out.printf("ID: %d, Funnsted: %s, Finner_id: %d, FinnerDag: %s, Anntatt_år: %d, MuseumId: %s, Type: %s, Materiale: %s, vekt: %d%n",
                    ID, Location, FinderId, FoundDate, AssumedYear, MuseumId, Type, Material, Weight);
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }

        ResultSet jewleryRs = statement.executeQuery(jewlerySql);
        System.out.println("Smykker:");
        while (jewleryRs.next()) {
            int ID = jewleryRs.getInt("Id");
            String Location = jewleryRs.getString("Funnsted");
            int FinderId = jewleryRs.getInt("Finner_id");
            LocalDate FoundDate = jewleryRs.getDate("Funntidspunkt").toLocalDate();
            int AssumedYear = jewleryRs.getInt("Antatt_årstall");
            Integer MuseumId = jewleryRs.getInt("Museum_id");
            String Type = jewleryRs.getString("Type");
            int Value = jewleryRs.getInt("Verdiestimat");
            String FileName = jewleryRs.getString("filnavn");
            System.out.printf("ID: %-10d Funnsted: %-20s Finner_id: %-10d Funntidspunkt: %-15s Antatt_årstall: %-10d Museum_id: %-10s Type: %-15s Verdiestimat: %-10d Filnavn: %-20s%n",
                    ID, Location, FinderId, FoundDate, AssumedYear, MuseumId, Type, Value, FileName);
        }
        ResultSet coinRs = statement.executeQuery(coinSql);
        System.out.println("Mynter:");
        while (coinRs.next()) {
            int ID = coinRs.getInt("Id");
            String Location = coinRs.getString("Funnsted");
            int FinderId = coinRs.getInt("Finner_id");
            LocalDate FoundDate = coinRs.getDate("Funntidspunkt").toLocalDate();
            int AssumedYear = coinRs.getInt("Antatt_årstall");
            int MuseumId = coinRs.getInt("Museum_id");
            int Diameter = coinRs.getInt("Diameter");
            String Metal = coinRs.getString("Metall");
            System.out.println("ID: " + ID + " Funnsted: " + Location + " FinderID: " + FinderId + " FoundDate: " + FoundDate + " MuseumID: " + MuseumId + " Diameter: " + Diameter);
        }

        weaponRs.close();
        jewleryRs.close();
        coinRs.close();

    }
    // Henter funngjennstander eldre en oppgitt årstall

    public void olderItems() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String weaponOld = "SELECT * FROM Vaapen WHERE Antatt_årstall < ?";
        String jewelryOld = "SELECT * FROM Smykke WHERE Antatt_årstall < ?";
        String coinOld = "SELECT * FROM Mynt WHERE Antatt_årstall < ?";

        System.out.println("Skriv inn ønsket årstall: ");
        int year = scanner.nextInt();

        if (year < 0 || year > 2024) {
            System.out.println("Ugyldig årstall.");
            return;
        }

        try (PreparedStatement weapon = conn.prepareStatement(weaponOld)) {
            weapon.setInt(1, year);
            ResultSet weaponRs = weapon.executeQuery();
            while (weaponRs.next()) {
                int id = weaponRs.getInt("Id");
                String Location = weaponRs.getString("Funnsted");
                int FinderId = weaponRs.getInt("Finner_id");
                LocalDate FoundDate = weaponRs.getDate("Funntidspunkt").toLocalDate();
                int AssumedYear = weaponRs.getInt("Antatt_årstall");
                Integer MuseumId = weaponRs.getInt("Museum_id");
                String Type = weaponRs.getString("Type");
                String Material = weaponRs.getString("Materiale");
                int Weight = weaponRs.getInt("Vekt");
                System.out.println("Våpen: " + "ID: " + id + " Funnsted: " + Location + " Finner_id: " + FinderId +
                        " Funntidspunkt: " + FoundDate + " Antatt_årstall: " + AssumedYear +
                        " Museum_id: " + MuseumId + " Type: " + Type + " Materiale: " + Material +
                        " Weight: " + Weight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement jewelry = conn.prepareStatement(jewelryOld)) {
            jewelry.setInt(1, year);
            ResultSet jewelryRs = jewelry.executeQuery();
            while (jewelryRs.next()) {
                int id = jewelryRs.getInt("Id");
                String Location = jewelryRs.getString("Funnsted");
                int FinderId = jewelryRs.getInt("Finner_id");
                LocalDate FoundDate = jewelryRs.getDate("Funntidspunkt").toLocalDate();
                int AssumedYear = jewelryRs.getInt("Antatt_årstall");
                Integer MuseumId = jewelryRs.getInt("Museum_id");
                String Type = jewelryRs.getString("Type");
                int Value = jewelryRs.getInt("Verdiestimat");
                String FileName = jewelryRs.getString("filnavn");
                System.out.println("Smykke: " + "ID: " + id + "Funnsted: " + Location + "Finner_id: + " + FinderId + " Funntidspunkt: " + FoundDate + " Antatt_årstall: " + AssumedYear + "Museum_id: " + MuseumId + " Type: " + Type + "Verdi: " + Value + " Filnavn: " + FileName);
            }
            try (PreparedStatement coin = conn.prepareStatement(coinOld)) {
                coin.setInt(1, year);
                ResultSet coinRs = coin.executeQuery();
                while (coinRs.next()) {
                    int id = coinRs.getInt("Id");
                    String Location = coinRs.getString("Funnsted");
                    int FinderId = coinRs.getInt("Finner_id");
                    LocalDate FoundDate = coinRs.getDate("Funntidspunkt").toLocalDate();
                    int AssumedYear = coinRs.getInt("Antatt_årstall");
                    int MuseumId = coinRs.getInt("Museum_id");
                    int Diameter = coinRs.getInt("Diameter");
                    String Metal = coinRs.getString("Metall");
                    System.out.println("Mynt: " + "ID: " + id + " Funnsted: " + Location + " FinderID: " + FinderId + " FoundDate: " + FoundDate + " MuseumID: " + MuseumId + " Diameter: " + Diameter);
                }
            }
        }
    }

  public  void counter() {
        try {
            // Tell antall våpen gjenstander totalt
            String weaponCountQuery = "SELECT COUNT(*) AS weapon_count FROM Vaapen";
            try (PreparedStatement weaponCountStmt = conn.prepareStatement(weaponCountQuery)) {
                ResultSet weaponCountRs = weaponCountStmt.executeQuery();
                if (weaponCountRs.next()) {
                    int weaponCount = weaponCountRs.getInt("weapon_count");
                    System.out.println("Totalt antall våpen gjenstander registrert: " + weaponCount);
                }
            }

            // Tell antall smykker totalt
            String jewelryCountQuery = "SELECT COUNT(*) AS jewelry_count FROM Smykke";
            try (PreparedStatement jewelryCountStmt = conn.prepareStatement(jewelryCountQuery)) {
                ResultSet jewelryCountRs = jewelryCountStmt.executeQuery();
                if (jewelryCountRs.next()) {
                    int jewelryCount = jewelryCountRs.getInt("jewelry_count");
                    System.out.println("Totalt antall smykker registrert: " + jewelryCount);
                }
            }

            // Tell antall mynter totalt
            String coinCountQuery = "SELECT COUNT(*) AS coin_count FROM Mynt";
            try (PreparedStatement coinCountStmt = conn.prepareStatement(coinCountQuery)) {
                ResultSet coinCountRs = coinCountStmt.executeQuery();
                if (coinCountRs.next()) {
                    int coinCount = coinCountRs.getInt("coin_count");
                    System.out.println("Totalt antall mynter registrert: " + coinCount);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
package database;
import classes.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class dataImport {

    // Legger til personer i databasen
    public void insertPerson(Connection conn, Person person) throws SQLException {
        String personSql = "INSERT INTO Person (Id, Navn, Tlf, Email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement personStmt = conn.prepareStatement(personSql)) {
            personStmt.setInt(1, person.getId());
            personStmt.setString(2, person.getName());
            personStmt.setString(3, person.getPhone());
            personStmt.setString(4, person.getEmail());
            personStmt.executeUpdate();
        }
    }

    // Legger til museum
    public void insertMuseum(Connection conn, Museum museum) throws SQLException {
        String museumSql = "INSERT INTO Museum(Id, Navn, Sted) VALUES (?, ?, ?)";
        try (PreparedStatement museumStmt = conn.prepareStatement(museumSql)) {
            museumStmt.setInt(1, museum.getId());
            museumStmt.setString(2, museum.getName());
            museumStmt.setString(3, museum.getLocation());
            museumStmt.executeUpdate();
        }
    }

    // Legger til mynt
    public void insertCoin(Connection conn, Coin coin) throws SQLException {
        String coinSql = "INSERT INTO Mynt (Id, Funnsted, Finner_id, Funntidspunkt, Antatt_årstall, Museum_id, Diameter, Metall) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement coinStmt = conn.prepareStatement(coinSql)) {
            coinStmt.setInt(1, coin.getId());
            coinStmt.setString(2, coin.getLocation());
            coinStmt.setInt(3, coin.getFinderId());
            coinStmt.setDate(4, java.sql.Date.valueOf(coin.getFoundDate()));
            coinStmt.setInt(5, coin.getAssumedYear());

            if (coin.getMuseumId() == null) {
                coinStmt.setNull(6, java.sql.Types.INTEGER);
            } else {
                coinStmt.setInt(6, coin.getMuseumId());
            }

            coinStmt.setInt(7, coin.getDiameter());
            coinStmt.setString(8, coin.getMetal());
            coinStmt.executeUpdate();
        }
    }

    // Legger til smykker
    public void insertJewelry(Connection conn, Jewlery jewelry) throws SQLException {
        String jewelrySql = "INSERT INTO Smykke(Id, Funnsted, Finner_id, Funntidspunkt, Antatt_årstall, Museum_id, Type, Verdiestimat, filnavn) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement jewelryStmt = conn.prepareStatement(jewelrySql)) {
            jewelryStmt.setInt(1, jewelry.getId());
            jewelryStmt.setString(2, jewelry.getLocation());
            jewelryStmt.setInt(3, jewelry.getFinderId());
            jewelryStmt.setDate(4, java.sql.Date.valueOf(jewelry.getFoundDate()));
            jewelryStmt.setInt(5, jewelry.getAssumedYear());

            if (jewelry.getMuseumId() == null) {
                jewelryStmt.setNull(6, java.sql.Types.INTEGER);
            } else {
                jewelryStmt.setInt(6, jewelry.getMuseumId());
            }

            jewelryStmt.setString(7, jewelry.getType());
            jewelryStmt.setInt(8, jewelry.getValue());
            jewelryStmt.setString(9, jewelry.getFileName());
            jewelryStmt.executeUpdate();
        }
    }

    // Legger til våpen
    public void insertWeapon(Connection conn, Weapon weapon) throws SQLException {
        String weaponSql = "INSERT INTO Vaapen(Id, Funnsted, Finner_id, Funntidspunkt, Antatt_årstall, Museum_id, Type, Materiale, Vekt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement weaponStmt = conn.prepareStatement(weaponSql)) {
            weaponStmt.setInt(1, weapon.getId());
            weaponStmt.setString(2, weapon.getLocation());
            weaponStmt.setInt(3, weapon.getFinderId());
            weaponStmt.setDate(4, java.sql.Date.valueOf(weapon.getFoundDate()));
            weaponStmt.setInt(5, weapon.getAssumedYear());

            if (weapon.getMuseumId() == null) {
                weaponStmt.setNull(6, java.sql.Types.INTEGER);
            } else {
                weaponStmt.setInt(6, weapon.getMuseumId());
            }

            weaponStmt.setString(7, weapon.getType());
            weaponStmt.setString(8, weapon.getMaterial());
            weaponStmt.setInt(9, weapon.getWeight());
            weaponStmt.executeUpdate();
        }
    }
}

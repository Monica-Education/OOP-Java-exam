package database;/*
Database tilkoblingen har tatt utgangspunkt i tilkoblingskoden fra forelesning 18.
https://github.com/kristiania/PGR112v24/blob/main/code/lectures/_18/DatabaseExample.java
*/
import com.mysql.cj.jdbc.Driver;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class database {
    private static Properties databaseProperties = new Properties();

    static {
        try {
            // Register JDBC driver
            DriverManager.registerDriver(new Driver());

            // leser database properties fra filen
            try (FileInputStream input = new FileInputStream("files/database.properties")) {
                databaseProperties.load(input);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to register JDBC driver", e);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database properties file", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        String host = databaseProperties.getProperty("host");
        String port = databaseProperties.getProperty("port");
        String database = databaseProperties.getProperty("database");
        String username = databaseProperties.getProperty("username");
        String password = databaseProperties.getProperty("password");

        return DriverManager.getConnection(
                String.format("jdbc:mysql://%s:%s/%s?allowPublicKeyRetrieval=true&useSSL=false",
                        host, port, database),
                username, password
        );
    }
}

import FileHandeling.FunnFileReader;
import Interface.Menu;
import database.*;
import Interactive.*;


import java.io.FileNotFoundException;
import java.sql.SQLException;

import static FileHandeling.FunnFileReader.filePath;

public class Main {
    public static void main(String[] args) {
        try {
            FunnFileReader reader = new FunnFileReader(filePath);
            reader.importToDatabase(); //Legger til data i databasen, må kommenteres ut om programmet skal kjøres flere ganger.
            //reader.PrintEverything(); //Array lister, dersom man ønsker å skrive ut det.

            Menu menu = new MenuOptions();
            menu.startMenu();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



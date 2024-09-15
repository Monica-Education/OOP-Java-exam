package Interactive;
import database.*;
import Interface.*;


import java.sql.SQLException;
import java.util.*;

//Klasse for håndtering av interaksjon mellom bruker og databasen.

public class MenuOptions implements Menu {

    void userMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("----");
            System.out.println("Velkommen! Vennligst velg fra menyvalgene: ");
            System.out.println("1. Se alle gjenstandene. ");
            System.out.println("2. Se alle gjenstander eldre enn året du velger.");
            System.out.println("3. Se hvor mange registrerte gjenstander vi har.");
            System.out.println("4. Avslutt. ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    Query query1 = new Query();
                    query1.allItems();
                    break;
                case 2:
                    Query query2 = new Query();
                    query2.olderItems();
                    break;
                case 3:
                    Query query3 = new Query();
                    query3.counter();
                    break;
                case 4:
                    System.out.println("Avslutter. Takk for besøket! ");
                    exit = true;
                    break;
                default:
                    System.out.println("Ikke et alternativt, velg noe annet!");
                    break;
            }
        }
    }

    @Override
    public void startMenu() throws SQLException {
        userMenu();
    }
}


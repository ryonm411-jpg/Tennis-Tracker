package ui;

import java.util.Scanner;

import model.MatchTracker;

//Tennis console application, Lets users add/view matches/stats
public class TennisApp {
    private Scanner input;
    private MatchTracker tracker;

//EFFECTS: runs the tennis app
    public void runApp() {
        input = new Scanner(System.in);
        tracker = new MatchTracker();

        boolean running = true;
        System.out.println("Welcome to Tennis Match Tracker!");

        while (running) {
            printMenu();
            int choice = readNumber("Choose an option: ");

            if (choice == 1) {
                addMatch();
            } else if (choice == 2) {
                showMatches();
            } else if (choice == 3) {
                showPlayerStats();
            } else if (choice == 4) {
                System.out.println("Goodbye!");
                running = false;
            } 
            }
        }
    }
    }

//EFFECTS: displays the menu options
private void displayMenu() {
    System.out.println("\nMain Menu:");
    System.out.println("1. Add a match");
    System.out.println("2. View all matches");
    System.out.println("3. View player stats");
    System.out.println("4. Quit");
    }



// EFFECTS: reads an integer
    private int readNumber(String msg) {
    }

// EFFECTS: reads a line of text
    private String readText(String msg) {
    }







 // EFFECTS: adds a new match to tracker
private void addMatch() {


}

    
}

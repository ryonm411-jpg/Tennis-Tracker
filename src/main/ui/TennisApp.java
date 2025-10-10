package ui;

import java.util.Scanner;

import model.MatchTracker;

//Tennis console application, Lets users add/view matches/stats
public class TennisApp {
    private Scanner input = new Scanner(System.in);
    private MatchTracker tracker = new MatchTracker();

//EFFECTS: runs the tennis app
    public void runApp() {
        System.out.println("Welcome to Tennis Match Tracker!");

        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("1. Add a match");
            System.out.println("2. Show all matches");
            System.out.println("3. Show player stats");
            System.out.println("4. Quit");

            System.out.print("Enter choice: ");
            int choice = readInt();

            if (choice == 1) {
                addMatch();
            } else if (choice == 2) {
                showMatches();
            } else if (choice == 3) {
                showStats();
            } else if (choice == 4) {
                System.out.println("Goodbye!");
                running = false;
            } else {
                System.out.println("Not a valid choice!");
            }
        }
    }
    


// show all matches
    private void showMatches() {
    }

// show player stats
    private void showStats() {
    }




// EFFECTS: reads an integer
// read an integer from user
    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Enter a number: ");
            }
        }
    } 

// EFFECTS: reads a line of text
    private String readText(String msg) {
    }







 // EFFECTS: adds a new match to tracker
private void addMatch() {


}

    
}

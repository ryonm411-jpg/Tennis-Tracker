package ui;

import java.util.Scanner;

import model.Match;
import model.MatchTracker;
import model.Player;

    //Tennis console application
    public class TennisApp {
    private Scanner input;
    private MatchTracker tracker;

    // EFFECTS: runs the tennis app
    public void runApp() {
        input = new Scanner(System.in);
        tracker = new MatchTracker();
        boolean isRunning = true;

        System.out.println("Welcome to Tennis Match Tracker!");

        while (isRunning) {
            displayMenu();
            int choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {
                addMatch();
            } 
            else if (choice == 2) {
                viewMatches();
            }
            else if (choice == 3) {
                addMultipleMatches();
            }
            else if (choice == 4) {
                viewMatchesForPlayer();
            }
            else if (choice == 5) {
                isRunning = false;
            } 
            else {
                System.out.println("Invalid choice!");
            }
        }

        System.out.println("Goodbye");
    }

    // EFFECTS: displays the main menu options
    private void displayMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("1. Add Match");
        System.out.println("2. View All Matches");
        System.out.println("3. Add Multiple Matches");
        System.out.println("4. View Matches for a Player");
        System.out.println("5. Quit");
        System.out.print("Enter choice: ");
    }

    // EFFECTS: adds a new match to tracker
    private void addMatch() {
        System.out.print("Enter Player 1 name: ");
        String p1Name = input.nextLine();
        System.out.print("Enter Player 2 name: ");
        String p2Name = input.nextLine();
        System.out.print("Enter date: ");
        String date = input.nextLine();
        System.out.print("Enter score: ");
        String score = input.nextLine();

        Player p1 = new Player(p1Name);
        Player p2 = new Player(p2Name);
        Match m = new Match(p1, p2, date, score);

        tracker.addMatch(m);
        System.out.println("Match added successfully");
    }

    // EFFECTS: displays all matches in the tracker
    private void viewMatches() {
        System.out.println("\nAll recorded matches:");
        for (Match m : tracker.getAllMatches()) {
            System.out.println(m.getDate() + ": " + m.getPlayer1().getName()
                    + " vs " + m.getPlayer2().getName() + " (" + m.getScore() + ")");
        }
    }

    
    //EFFECTS: adds multi matches
    private void addMultipleMatches() {
        boolean adding = true;
        while (adding) {
            addMatch(); 
            System.out.print("Add another match? (y/n): ");
            String choice = input.nextLine().toLowerCase();
            if (!choice.equals("y")) {
                adding = false;
            }
        }
    }

    
    //EFFEECTS: view the matches of a specific player
    private void viewMatchesForPlayer() {
        System.out.print("Enter player name: ");
        String name = input.nextLine();
        System.out.println("\nMatches for " + name + ":");
        for (Match m : tracker.getAllMatches()) {
            if (m.getPlayer1().getName().equalsIgnoreCase(name)
                    || m.getPlayer2().getName().equalsIgnoreCase(name)) {
                System.out.println(m.getDate() + ": " + m.getPlayer1().getName()
                        + " vs " + m.getPlayer2().getName()
                        + " (" + m.getScore() + ")");
            }
        }
    }

}

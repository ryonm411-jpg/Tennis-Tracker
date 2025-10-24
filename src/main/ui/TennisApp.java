package ui;

import model.Match;
import model.TennisData;
import model.MatchTracker;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.Scanner;

// Tennis console application
public class TennisApp {
    private static final String JSON_STORE = "./data/tennisData.json";
    private Scanner input;
    private TennisData tennisData;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    // EFFECTS: constructs the app and initializes JSON reader/writer
    public TennisApp() {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    // EFFECTS: runs the tennis app
    public void runApp() {
        input = new Scanner(System.in);
        tennisData = new TennisData();
        boolean isRunning = true;

        System.out.println("Welcome to Tennis Match Tracker!");
        System.out.print("Would you like to load your previous data? (y/n): ");
        String loadChoice = input.nextLine();

        if (loadChoice.equalsIgnoreCase("y")) {
            loadData(); 

        
        while (isRunning) {
            displayMenu();
            int choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {
                addMatch();
            } else if (choice == 2) {
                viewMatches();
            } else if (choice == 3) {
                addMultipleMatches();
            } else if (choice == 4) {
                viewMatchesForPlayer();
            } else if (choice == 5) {
                saveBeforeExit();
                isRunning = false;
            } else {
                System.out.println("Invalid choice!");
            }
        }

        System.out.println("Goodbye!");}
    }

    // EFFECTS: displays the main menu options
    private void displayMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("1. Add Match");
        System.out.println("2. View All Matches");
        System.out.println("3. Add Multiple Matches");
        System.out.println("4. View Matches for a Player");
        System.out.println("5. Save and Quit");
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

        Match m = new Match(p1Name, p2Name, date, score, 0, 0, 0);
        tennisData.getMatchTracker().addMatch(m);
        System.out.println("Match added successfully.");
    }

    // EFFECTS: displays all matches in the tracker
    private void viewMatches() {
        MatchTracker tracker = tennisData.getMatchTracker();

        if (tracker.getAllMatches().isEmpty()) {
            System.out.println("\nNo matches recorded yet.");
        } else {
            System.out.println("\nAll recorded matches:");
            for (Match m : tracker.getAllMatches()) {
                System.out.println(m.getDate() + ": " + m.getPlayer1Name()
                        + " vs " + m.getPlayer2Name() + " (" + m.getScore() + ")");
            }
        }
    }

    // EFFECTS: repeatedly adds matches until user stops
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

    // EFFECTS: view the matches of a specific player
    private void viewMatchesForPlayer() {
        System.out.print("Enter player name: ");
        String name = input.nextLine();
        System.out.println("\nMatches for " + name + ":");
        boolean found = false;

        MatchTracker tracker = tennisData.getMatchTracker();
        for (Match m : tracker.getAllMatches()) {
            if (m.getPlayer1Name().equalsIgnoreCase(name)
                    || m.getPlayer2Name().equalsIgnoreCase(name)) {
                System.out.println(m.getDate() + ": " + m.getPlayer1Name()
                        + " vs " + m.getPlayer2Name()
                        + " (" + m.getScore() + ")");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matches found for this player.");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads tennis data (players and matches) from JSON file
    private void loadData() {
        try {
            tennisData = jsonReader.read();
            System.out.println("Loaded data from " + JSON_STORE + ".");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: saves tennis data to JSON file if user chooses yes
    private void saveBeforeExit() {
        System.out.print("Would you like to save your matches before quitting? (y/n): ");
        String choice = input.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            try {
                jsonWriter.open();
                jsonWriter.write(tennisData);
                jsonWriter.close();
                System.out.println("Saved matches to " + JSON_STORE + ".");
            } catch (IOException e) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
    }
}

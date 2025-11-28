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
        initApp();
        askToLoadData();

        boolean isRunning = true;
        while (isRunning) {
            displayMenu();
            isRunning = processUserChoice();
        }

        System.out.println("Goodbye!");
    }

    // EFFECTS: initializes input scanner and empty tennis data
    private void initApp() {
        input = new Scanner(System.in);
        tennisData = new TennisData();
        System.out.println("Welcome to Tennis Match Tracker!");
    }

    // MODIFIES: this
    // EFFECTS: lets the user choose to load data
    private void askToLoadData() {
        System.out.print("Would you like to load your previous data? (y/n): ");
        String loadChoice = input.nextLine();

        if (loadChoice.equalsIgnoreCase("y")) {
            loadData();
        }
    }

    // EFFECTS: displays the main menu
    private void displayMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("1. Add Match");
        System.out.println("2. View All Matches");
        System.out.println("3. Add Multiple Matches");
        System.out.println("4. View Matches for a Player");
        System.out.println("5. Save and Quit");
        System.out.print("Enter choice: ");
    }

    // EFFECTS: processes the user's menu choice
    private boolean processUserChoice() {
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
            return false;
        } else {
            System.out.println("Invalid choice!");
        }

        return true;
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

        Match match = new Match(p1Name, p2Name, date, score, 0, 0, 0);
        tennisData.getMatchTracker().addMatch(match);
        System.out.println("Match added successfully.");
    }

    // EFFECTS: displays all recorded matches
    private void viewMatches() {
        MatchTracker tracker = tennisData.getMatchTracker();

        if (tracker.getAllMatches().isEmpty()) {
            System.out.println("\nNo matches recorded yet.");
        } else {
            System.out.println("\nAll recorded matches:");
            for (Match match : tracker.getAllMatches()) {
                System.out.println(match.getDate() + ": " + match.getPlayer1Name()
                        + " vs " + match.getPlayer2Name()
                        + " (" + match.getScore() + ")");
            }
        }
    }

    // EFFECTS: repeatedly adds matches until the user stops
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

    // EFFECTS: displays matches for a specific player
    private void viewMatchesForPlayer() {
        System.out.print("Enter player name: ");
        String name = input.nextLine();
        System.out.println("\nMatches for " + name + ":");

        boolean found = false;
        MatchTracker tracker = tennisData.getMatchTracker();

        for (Match match : tracker.getAllMatches()) {
            if (match.getPlayer1Name().equalsIgnoreCase(name)
                    || match.getPlayer2Name().equalsIgnoreCase(name)) {
                System.out.println(match.getDate() + ": " + match.getPlayer1Name()
                        + " vs " + match.getPlayer2Name()
                        + " (" + match.getScore() + ")");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matches found for this player.");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads tennis data from JSON file
    private void loadData() {
        try {
            tennisData = jsonReader.read();
            System.out.println("Loaded data from " + JSON_STORE + ".");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: saves tennis data to JSON file if chosen
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

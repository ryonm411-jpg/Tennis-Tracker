package model;

import org.json.JSONObject;


// Represents a tennis player
public class Player {
    private final String name;
    private int wins;
    private int losses;
    

    // EFFECTS: returns the JSON representation of the player
    public JSONObject toJson() {
        return null;
    }

    // REQUIRES: a name
    // EFFECTS: constructs a player with given name, zero wins/losses
    public Player(String name, int wins, int losses) {
        this.name = name;
        this.wins = wins;
        this.losses = losses;
    }

    // MODIFIES: this
    // EFFECTS: increments player win count by 1

    public void addWin() {
        wins++;

    }

    // MODIFIES: this
    // EFFECTS: increments player Loss count by 1

    public void addLoss() {
        losses++;

    }

    // EFFECTS: returns players win percentage
    public double getWinPercentage() {
        int totalGames = wins + losses;
        if (totalGames == 0) {
            return 0.0;
        }
        return (double) wins / totalGames;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

   

}

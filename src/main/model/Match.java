package model;

import org.json.JSONObject;

// Represents a tennis match
public class Match {
    // match basics
    private String player1;
    private String player2;
    private String date;
    private String score;
    private int aces;
    private int doubleFaults;
    private int unforcedErrors;

    // EFFECTS: constructs a Match with player1name, player2Nmae, date,
    // score, aces, doubleFaults, unforcedErrors.
    public Match(String player1Name, String player2Name, String date, String score,
            int aces, int doubleFaults, int unforcedErrors) {
        this.player1 = player1Name;
        this.player2 = player2Name;
        this.date = date;
        this.score = score;
        this.aces = aces;
        this.doubleFaults = doubleFaults;
        this.unforcedErrors = unforcedErrors;

    }
   
    //EFECTS: returns a new Match built from JSON
    public static Match fromJson(JSONObject json) {
        return null;
    }
    // EFFECTS: returns JSON version of match
    public JSONObject toJson() {
         return null; }




    public String getPlayer1Name() {
        return player1;
    }

    public String getPlayer2Name() {
        return player2;
    }

    public String getDate() {
        return date;
    }

    public String getScore() {
        return score;
    }

    public int getAces() {
        return aces;
    }

    public int getDoubleFaults() {
        return doubleFaults;
    }

    public int getUnforcedErrors() {
        return unforcedErrors;
    }

    // setters
    public void setAces(int aces) {
        this.aces = aces;
    }

    public void setDoubleFaults(int doubleFaults) {
        this.doubleFaults = doubleFaults;
    }

    public void setUnforcedErrors(int unforcedErrors) {
        this.unforcedErrors = unforcedErrors;
    }

}
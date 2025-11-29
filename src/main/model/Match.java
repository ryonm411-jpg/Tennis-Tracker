package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents a tennis match
public class Match implements Writable {
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

    // EFFECTS: returns JSON version of match
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("player1", player1);
        json.put("player2", player2);
        json.put("date", date);
        json.put("score", score);
        json.put("aces", aces);
        json.put("doubleFaults", doubleFaults);
        json.put("unforcedErrors", unforcedErrors);
        return json;
    }

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
    // MODIFIES: this, EventLog
    // EFFECTS: updates aces and logs the event
    public void setAces(int aces) {
        this.aces = aces;
        EventLog.getInstance().logEvent(new Event("Aces updated to " + aces
                + " for match: " + player1 + " vs " + player2));
    }

    // MODIFIES: this, EventLog
    // EFFECTS: updates doubleFaults and logs the event
    public void setDoubleFaults(int doubleFaults) {
        this.doubleFaults = doubleFaults;
        EventLog.getInstance().logEvent(new Event("Double faults updated to " + doubleFaults
                + " for match: " + player1 + " vs " + player2));
    }

    // MODIFIES: this, EventLog
    // EFFECTS: updates unforcedErrors and logs the event
    public void setUnforcedErrors(int unforcedErrors) {
        this.unforcedErrors = unforcedErrors;
        EventLog.getInstance().logEvent(new Event("Unforced errors updated to " + unforcedErrors
                + " for match: " + player1 + " vs " + player2));
    }

}
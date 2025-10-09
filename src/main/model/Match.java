package model;

public class Match {
    // match basics
    private Player player1;
    private Player player2;
    private String date;
    private String score;
    private int aces;
    private int doubleFaults;
    private int unforcedErrors;


// REQUIRES: player1 and p2
// EFFECTS: constructs a Match with given participants, date, 
// score, winner, and per-player stats.


    public Match(Player player1, Player player2, String date, String Score) {
        this.player1 = player1;
        this.player2 = player2;

        this.date = date;
        this.score = score;

        this.aces = 0;
        this.doubleFaults = 0;
        this.unforcedErrors = 0;

    }

    public Player getPlayer1() { 
        return player1; 
    }
    public Player getPlayer2() { 
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
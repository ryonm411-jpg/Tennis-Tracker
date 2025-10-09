package model;

public class Player {
    private final String name;
    private int wins;
    private int losses;
    
//    REQUIRES: a name
//    EFFECTS: constructs a player with given name, zero wins/losses
    public Player(String name) {
        this.name = name;
        this.wins = 0;
        this.losses = 0;
    }


//    MODIFIES: this
//    EFFECTS: increments player win count by 1   

    public void addWin(){
        wins++;

    }


//    MODIFIES: this
//    EFFECTS: increments player Loss count by 1   

    public void addLoss(){
        losses++;

    }

    //    EFFECTS: returns players win percentage
    public double getWinPercentage() {
        int totalGames = wins + losses;
        double winPer = wins / totalGames;
        if (totalGames == 0)
        return 0;
        return winPer;
    }
    
    public 
    
    
    
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

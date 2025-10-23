package model;

import java.util.List;

public class TennisData {
    private List<Player> players;
    private MatchTracker matchTracker;


    // EFFECTS: constructs tennis data with given players and matches
    public TennisData(List<Player> players, MatchTracker matchTracker) {
        this.players = players;
        this.matchTracker = matchTracker;

    }

    public List<Player> gerPlayers() {
        return players;
    }

    public MatchTracker getMatchTracker() {
        return matchTracker;
    }

}

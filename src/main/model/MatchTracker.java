package model;

import java.util.ArrayList;
import java.util.List;

public class MatchTracker {
    private List<Match> matches;

    // Effects: constructs an empty tracker of matches

    public MatchTracker() {
        matches = new ArrayList<>();
    }

    // MODIFIES: this
    // Effects: adds a match to matches

    public void addMatch(Match match) {
        matches.add(match);
    }

    // EFFECTS: return a list of all matches
    public List<Match> getAllMatches() {
        return matches;
    }

    // EFFECTS: return the num of recorded matches
    public int getMatchCount() {
        return matches.size();
    }

}

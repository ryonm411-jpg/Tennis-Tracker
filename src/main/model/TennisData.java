package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import model.persistence.Writable;

// Represents all tennis data of matches and players
public class TennisData implements Writable {
    private List<Player> players;
    private MatchTracker matchTracker;


    // EFFECTS: constructs tennis data with given players and matches
    public TennisData(List<Player> players, MatchTracker matchTracker) {
        this.players = players;
        this.matchTracker = matchTracker;

    }
    // EFFECTS: constructs tennisdata with empty players and matchtracker
    public TennisData() {
    this.players = new ArrayList<>();
    this.matchTracker = new MatchTracker();
}

    // MODIFIES: this
    // EFFECTS: adds player to this tennisdata
    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public MatchTracker getMatchTracker() {
        return matchTracker;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("players", playersToJson());
        json.put("matches", matchesToJson());
        return json;
    }

    // EFFECTS: returns players in this tennis data as a JSON array
    private JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : players) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns matches in this tennis data as a JSON array
    private JSONArray matchesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Match m : matchTracker.getAllMatches()) {
            jsonArray.put(m.toJson());
        }
        return jsonArray;
    }

}

package persistence;

import model.Match;
import model.Player;
import model.TennisData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that loads TennisData from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads TennisData from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TennisData read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTennisData(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses TennisData from JSON object and returns it
    private TennisData parseTennisData(JSONObject jsonObject) {
        TennisData td = new TennisData();
        if (jsonObject.has("players")) {
            addPlayers(td, jsonObject);
        }
        if (jsonObject.has("matches")) {
            addMatches(td, jsonObject);
        }
        return td;
    }

    // MODIFIES: td
    // EFFECTS: parses players from JSON object and adds them to TennisData
    private void addPlayers(TennisData td, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("players");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayer(td, nextPlayer);
        }
    }

    // MODIFIES: td
    // EFFECTS: parses single player JSONObject and adds Player to TennisData
    private void addPlayer(TennisData td, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int wins = jsonObject.getInt("wins");
        int losses = jsonObject.getInt("losses");
        td.addPlayer(new Player(name, wins, losses));
    }

    // MODIFIES: td
    // EFFECTS: parses matches from JSON object and adds them to TennisData’s
    // MatchTracker
    private void addMatches(TennisData td, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("matches");
        for (Object json : jsonArray) {
            JSONObject nextMatch = (JSONObject) json;
            addMatch(td, nextMatch);
        }
    }

    // MODIFIES: td
    // EFFECTS: parses single match JSONObject and adds Match to TennisData
    private void addMatch(TennisData td, JSONObject jsonObject) {
        String p1 = jsonObject.getString("player1");
        String p2 = jsonObject.getString("player2");
        String date = jsonObject.getString("date");
        String score = jsonObject.getString("score");
        int aces = jsonObject.getInt("aces");
        int doubleFaults = jsonObject.getInt("doubleFaults");
        int unforcedErrors = jsonObject.getInt("unforcedErrors");

        Match match = new Match(p1, p2, date, score, aces, doubleFaults, unforcedErrors);
        td.getMatchTracker().addMatch(match);
    }
}

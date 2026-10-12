package persistence;

import model.Match;
import model.Player;
import model.TennisData;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTennisData() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTennisData.json");
        try {
            TennisData td = reader.read();
            assertEquals(0, td.getPlayers().size());
            assertEquals(0, td.getMatchTracker().getMatchCount());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderMissingKeys() {
        JsonReader reader = new JsonReader("./data/testReaderMissingKeysTennisData.json");
        try {
            TennisData td = reader.read();
            assertEquals(0, td.getPlayers().size());
            assertEquals(0, td.getMatchTracker().getMatchCount());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTennisData() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTennisData.json");
        try {
            TennisData td = reader.read();

            List<Player> players = td.getPlayers();
            assertEquals(2, players.size());
            assertEquals("Ryon", players.get(0).getName());
            assertEquals(3, players.get(0).getWins());
            assertEquals(1, players.get(0).getLosses());
            assertEquals("Jeff", players.get(1).getName());
            assertEquals(2, players.get(1).getWins());
            assertEquals(2, players.get(1).getLosses());

            List<Match> matches = td.getMatchTracker().getAllMatches();
            assertEquals(1, matches.size());
            checkMatch("Ryon", "Jeff", "2025/10/01", "6-4, 6-3", 5, 2, 1, matches.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

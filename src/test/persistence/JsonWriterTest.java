package persistence;

import model.Match;
import model.MatchTracker;
import model.Player;
import model.TennisData;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
           
        }
    }

    @Test
    void testWriterEmptyTennisData() {
        try {
            TennisData td = new TennisData(); 
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTennisData.json");
            writer.open();
            writer.write(td);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTennisData.json");
            TennisData tdRead = reader.read();
            assertEquals(0, tdRead.getPlayers().size());
            assertEquals(0, tdRead.getMatchTracker().getMatchCount());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTennisData() {
        try {
            TennisData td = new TennisData();
            Player p1 = new Player("Ryon", 3, 1);
            Player p2 = new Player("Jeff", 2, 2);
            td.getPlayers().add(p1); 
            td.getPlayers().add(p2);

            MatchTracker tracker = td.getMatchTracker();
            tracker.addMatch(new Match("Ryon", "Jeff", "2025/10/01", "6-4, 6-3", 5, 2, 1));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTennisData.json");
            writer.open();
            writer.write(td);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTennisData.json");
            TennisData tdRead = reader.read();

    
            List<Player> players = tdRead.getPlayers();
            assertEquals(2, players.size());
            assertEquals("Ryon", players.get(0).getName());

            MatchTracker trackerRead = tdRead.getMatchTracker();
            assertEquals(1, trackerRead.getMatchCount());
            List<Match> matches = trackerRead.getAllMatches();
            checkMatch("Ryon", "Jeff", "2025/10/01", "6-4, 6-3", 5, 2, 1, matches.get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

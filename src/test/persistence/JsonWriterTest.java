package persistence;

import model.Match;

import model.Player;
import model.TennisData;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @TempDir
    Path tempDir;

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            assertTrue(true);
        }
    }

    @Test
    void testWriterEmptyTennisData() {
        try {
            String file = tempDir.resolve("testWriterEmptyTennisData.json").toString();
            TennisData td = new TennisData();
            JsonWriter writer = new JsonWriter(file);
            writer.open();
            writer.write(td);
            writer.close();

            JsonReader reader = new JsonReader(file);
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
            String file = tempDir.resolve("testWriterGeneralTennisData.json").toString();
            TennisData td = new TennisData();
            td.getPlayers().add(new Player("Ryon", 3, 1));
            td.getPlayers().add(new Player("Jeff", 2, 2));
            td.getMatchTracker().addMatch(
                    new Match("Ryon", "Jeff", "2025/10/01", "6-4, 6-3", 5, 2, 1));

            JsonWriter writer = new JsonWriter(file);
            writer.open();
            writer.write(td);
            writer.close();

            TennisData tdRead = new JsonReader(file).read();
            assertEquals(2, tdRead.getPlayers().size());
            assertEquals("Ryon", tdRead.getPlayers().get(0).getName());
            Match m = tdRead.getMatchTracker().getAllMatches().get(0);
            checkMatch("Ryon", "Jeff", "2025/10/01", "6-4, 6-3", 5, 2, 1, m);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

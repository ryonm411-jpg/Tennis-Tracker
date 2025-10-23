package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MatchTest {
    private Match match;

    @BeforeEach
    public void setUp() {
        match = new Match("Ryon", "Jeff", "2025/10/01", "6-4, 6-3", 0, 0, 0);

    }

    @Test
    public void testConstructor() {
        assertEquals("Ryon", match.getPlayer1Name());
        assertEquals("Jeff", match.getPlayer2Name());
        assertEquals("2025/10/01", match.getDate());
        assertEquals("6-4, 6-3", match.getScore());
        assertEquals(0, match.getAces());
        assertEquals(0, match.getDoubleFaults());
        assertEquals(0, match.getUnforcedErrors());
    }

    @Test
    public void testSetAces() {
        match.setAces(5);
        assertEquals(5, match.getAces());
    }

    @Test
    public void testSetDoubleFaults() {
        match.setDoubleFaults(2);
        assertEquals(2, match.getDoubleFaults());
    }

    @Test
    public void testSetUnforcedErrors() {
        match.setUnforcedErrors(2);
        assertEquals(2, match.getUnforcedErrors());
    }

    @Test
    void testFromJsonAndToJson() {
        JSONObject j = new JSONObject();
        j.put("player1", "Ryon");
        j.put("player2", "Jeff");
        j.put("date", "2025/10/01");
        j.put("score", "6-4, 6-3");
        j.put("aces", 5);
        j.put("doubleFaults", 2);
        j.put("unforcedErrors", 1);

        Match m = Match.fromJson(j);
        assertEquals("Ryon", m.getPlayer1Name());
        assertEquals("Jeff", m.getPlayer2Name());
        assertEquals("2025/10/01", m.getDate());
        assertEquals("6-4, 6-3", m.getScore());
        assertEquals(5, m.getAces());

        JSONObject out = m.toJson();
        assertEquals("Ryon", out.getString("player1"));
        assertEquals("Jeff", out.getString("player2"));
        assertEquals("2025/10/01", out.getString("date"));
        assertEquals("6-4, 6-3", out.getString("score"));
        assertEquals(5, out.getInt("aces"));
        assertEquals(2, out.getInt("doubleFaults"));
        assertEquals(1, out.getInt("unforcedErrors"));


    }

}
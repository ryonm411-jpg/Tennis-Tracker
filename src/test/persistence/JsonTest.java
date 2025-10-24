package persistence;

import model.Match;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkMatch(String p1, String p2, String date, String score,
            int aces, int doubleFaults, int unforcedErrors,
            Match m) {
        assertEquals(p1, m.getPlayer1Name());
        assertEquals(p2, m.getPlayer2Name());
        assertEquals(date, m.getDate());
        assertEquals(score, m.getScore());
        assertEquals(aces, m.getAces());
        assertEquals(doubleFaults, m.getDoubleFaults());
        assertEquals(unforcedErrors, m.getUnforcedErrors());
    }
}

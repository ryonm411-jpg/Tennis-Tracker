package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
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


}
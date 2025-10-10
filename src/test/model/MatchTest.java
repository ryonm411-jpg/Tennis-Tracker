package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MatchTest {
    private Player p1;
    private Player p2;
    private Match match;

    @BeforeEach
    public void setUp() {
        p1 = new Player("Ryon");
        p2 = new Player("Jeff");
        match = new Match(p1, p2, "2025/10/01", "6-4, 6-3");

    }

    @Test
    public void testConstructor() {
        assertEquals(p1, match.getPlayer1());
        assertEquals(p2, match.getPlayer2());
        assertEquals("2025/10/01", match.getDate());
        assertEquals("6-4, 6-3", match.getScore());
        assertEquals(0, match.getAces());
    }

    @Test
    public void testSetAces() {
        match.setAces(5);
        assertEquals(5, match.getAces());
    }

    @Test
    public void testSetDoubleFaults() {
        match.setDoubleFaults(2);
        ;
        assertEquals(2, match.getDoubleFaults());
    }

    @Test
    public void testSetUnforcedErrors() {
        match.setUnforcedErrors(2);
        assertEquals(2, match.getUnforcedErrors());
    }

}
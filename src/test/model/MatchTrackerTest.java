package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MatchTrackerTest {

    private MatchTracker tracker;
    private Match match;

    @BeforeEach
    public void setUp() {
        tracker = new MatchTracker();
        match = new Match("Ryon", "Jeff", "2025/10/02", "7-6, 6-4", 3, 1, 4);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, tracker.getMatchCount());
        assertTrue(tracker.getAllMatches().isEmpty());
    }

    @Test
    public void testAddMatch() {
        tracker.addMatch(match);
        assertEquals(1, tracker.getMatchCount());
        assertTrue(tracker.getAllMatches().contains(match));
    }
    
    @Test
    public void testMultipleMatchesAdded() {
        Match m2 = new Match("Roger", "Nadal", "2025/10/03", "6-2, 6-1", 2, 0, 1);
        tracker.addMatch(match);
        tracker.addMatch(m2);
        assertEquals(2, tracker.getMatchCount());
        assertTrue(tracker.getAllMatches().contains(match));
        assertTrue(tracker.getAllMatches().contains(m2));
    }

}

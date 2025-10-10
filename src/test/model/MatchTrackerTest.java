import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class MatchTrackerTest {

    private MatchTracker tracker;
    private Player p1;
    private Player p2;
    private Match match;


@BeforeEach
    public void setUp() {
        tracker = new MatchTracker();
        p1 = new Player("Ryon");
        p2 = new Player("Jeff");
        match = new Match(p1, p2, "2025/10/02", "7-6, 6-4");
}


@Test
public void testConstructor() {
    assertEquals(0, tracker.getMatchCount());
}


@Test
public void testAddMatch() {
    tracker.addMatch(match);
    assertEquals(1, tracker.getMatchCount());
    assertTrue(tracker.getAllMatches().contains(match));
    }

@Test
public void testEmptyTrackerInitially() {
    assertEquals(0, tracker.getMatchCount());
    assertTrue(tracker.getAllMatches().isEmpty());
    }

}


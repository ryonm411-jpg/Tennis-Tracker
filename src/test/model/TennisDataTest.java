package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TennisDataTest {

    private Player p;
    private Player p2;
    private Match m;
    private List<Player> players;
    MatchTracker tracker;
    private TennisData td;

    @BeforeEach
    public void setUp() {
        p = new Player("Ryon", 1, 0);
        p2 = new Player("Jeff", 0, 1);
        m = new Match("Ryon", "Jeff", "2025/10/01", "6-4, 6-3", 0, 0, 0);
        tracker = new MatchTracker();
        players = new ArrayList<>();
        td = new TennisData(players, tracker);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, td.getPlayers().size());
        assertEquals(0, td.getMatchTracker().getMatchCount());
        
    }


    @Test
public void testAddMatchAndPlayer() {
    tracker.addMatch(m);
    td.addPlayer(p);
    td.addPlayer(p2);

    assertEquals(2, td.getPlayers().size());
    assertEquals(p, td.getPlayers().get(0));
    assertEquals(p2, td.getPlayers().get(1));

    assertEquals(1, td.getMatchTracker().getMatchCount());
    assertEquals(m, td.getMatchTracker().getAllMatches().get(0));
}


}

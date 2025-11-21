package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TennisDataTest {

    private Player player1;
    private Player player2;
    private Match match;
    private List<Player> players;
    private MatchTracker tracker;
    private TennisData td;

    @BeforeEach
    public void setUp() {
        player1 = new Player("Ryon", 1, 0);
        player2 = new Player("Jeff", 0, 1);
        match = new Match("Ryon", "Jeff", "2025/10/01", "6-4, 6-3", 0, 0, 0);
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
        tracker.addMatch(match);
        td.addPlayer(player1);
        td.addPlayer(player2);

        assertEquals(2, td.getPlayers().size());
        assertEquals(player1, td.getPlayers().get(0));
        assertEquals(player2, td.getPlayers().get(1));

        assertEquals(1, td.getMatchTracker().getMatchCount());
        assertEquals(match, td.getMatchTracker().getAllMatches().get(0));
    }
}

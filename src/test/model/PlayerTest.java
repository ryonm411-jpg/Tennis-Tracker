package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player("Ryon", 0, 0);

    }

    @Test
    public void testConstructor() {
        assertEquals("Ryon", player.getName());
        assertEquals(0, player.getWins());
        assertEquals(0, player.getLosses());
        assertEquals(0.0, player.getWinPercentage());
    }

    @Test
    public void testAddWin() {
        player.addWin();
        assertEquals(1, player.getWins());
        assertEquals(0, player.getLosses());
        assertEquals(1.0, player.getWinPercentage());
    }

    @Test
    public void testAddMultipleWins() {
        player.addWin();
        player.addWin();
        assertEquals(2, player.getWins());
        assertEquals(0, player.getLosses());
        assertEquals(1.0, player.getWinPercentage());
    }

    @Test
    public void testAddLoss() {
        player.addLoss();
        assertEquals(1, player.getLosses());
        assertEquals(0, player.getWins());
        assertEquals(0.0, player.getWinPercentage());

    }

    @Test
    public void testMultipleLosses() {
        player.addLoss();
        player.addLoss();
        assertEquals(0, player.getWins());
        assertEquals(2, player.getLosses());
        assertEquals(0.0, player.getWinPercentage());
    }

    @Test
    public void testAddWinAndLoss() {
        player.addWin();
        player.addLoss();
        assertEquals(1, player.getWins());
        assertEquals(1, player.getLosses());
        assertEquals(0.5, player.getWinPercentage());
    }

    @Test
    void testToJson() {

        Player p = new Player("Ryon", 3, 1);

        JSONObject out = p.toJson();
        assertEquals("Ryon", out.getString("name"));
        assertEquals(3, out.getInt("wins"));
        assertEquals(1, out.getInt("losses"));
    }


}

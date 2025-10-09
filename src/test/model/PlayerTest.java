package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player player;
    
@BeforeEach
public void setUp() {
    player = new Player("Ryon");

    }

@Test
public void testConstructor() {
    assertEquals("Ryon", player.getLosses());
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

}

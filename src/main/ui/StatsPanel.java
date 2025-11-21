package ui;

import model.Match;
import model.TennisData;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Panel that displays a bar chart visualization of match statistics.
 */
public class StatsPanel extends JPanel {
    private TennisData tennisData;

    // REQUIRES: tennisData is not null
    // MODIFIES: this
    // EFFECTS: constructs a panel to display match statistics
    public StatsPanel(TennisData tennisData) {
        this.tennisData = tennisData;
        setBorder(new TitledBorder("Match Statistics"));
        setPreferredSize(new Dimension(800, 200));
    }

    // REQUIRES: none
    // MODIFIES: none
    // EFFECTS: paints the bar chart showing number of matches per player
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Map<String, Integer> playerMatchCount = new HashMap<>();
        
        for (Match m : tennisData.getMatchTracker().getAllMatches()) {
            String p1 = m.getPlayer1Name();
            String p2 = m.getPlayer2Name();

            if (playerMatchCount.containsKey(p1)) {
                int currentCount = playerMatchCount.get(p1);
                playerMatchCount.put(p1, currentCount + 1);
            } else {
                playerMatchCount.put(p1, 1);
            }

            
            if (playerMatchCount.containsKey(p2)) {
                int currentCount = playerMatchCount.get(p2);
                playerMatchCount.put(p2, currentCount + 1);
            } else {
                playerMatchCount.put(p2, 1);
            }
        }
        
        if (playerMatchCount.isEmpty()) {
            g.setColor(Color.BLACK);
            g.drawString("No matches recorded yet", 20, 50);
            return;
        }
        
        
        int maxCount = 0;
        for (Integer count : playerMatchCount.values()) {
            if (count > maxCount) {
                maxCount = count;
            }
        }
        
        if (maxCount == 0) {
            maxCount = 1;
        }
        
        int barWidth = 60;
        int spacing = 20;
        int startX = 30;
        int startY = getHeight() - 40;
        int maxBarHeight = getHeight() - 80;
        
        int xPos = startX;
        for (Map.Entry<String, Integer> entry : playerMatchCount.entrySet()) {
            String player = entry.getKey();
            int count = entry.getValue();
            double percentage = (double) count / maxCount;
            int barHeight = (int) (percentage * maxBarHeight);
            
            g.setColor(new Color(70, 130, 180));
            g.fillRect(xPos, startY - barHeight, barWidth, barHeight);
            
            g.setColor(Color.BLACK);
            g.drawRect(xPos, startY - barHeight, barWidth, barHeight);
            
            g.setFont(new Font("Arial", Font.PLAIN, 10));
            
            String label = player;
            if (player.length() > 8) {
                label = player.substring(0, 8);
            }
            
            g.drawString(label, xPos, startY + 15);
            g.drawString(String.valueOf(count), xPos + barWidth / 2 - 5, startY - barHeight - 5);
            
            xPos += barWidth + spacing;
        }
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("Matches per Player", 20, 20);
    }

    // REQUIRES: tennisData is not null
    // MODIFIES: this
    // EFFECTS: sets the tennis data
    public void setTennisData(TennisData tennisData) {
        this.tennisData = tennisData;
    }
}
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

    // EFFECTS: paints the bar chart showing number of matches per player
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Map<String, Integer> matchCounts = collectMatchCounts();

        if (matchCounts.isEmpty()) {
            drawNoMatchesMessage(g);
            return;
        }

        int maxCount = findMaxCount(matchCounts);

        drawBars(g, matchCounts, maxCount);
        drawTitle(g);
    }

    // EFFECTS: returns a map {playerName → number of matches played}
    private Map<String, Integer> collectMatchCounts() {
        Map<String, Integer> counts = new HashMap<>();

        for (Match match : tennisData.getMatchTracker().getAllMatches()) {
            incrementCount(counts, match.getPlayer1Name());
            incrementCount(counts, match.getPlayer2Name());
        }
        return counts;
    }

    // MODIFIES: counts
    // EFFECTS: increments the match count for a given player
    private void incrementCount(Map<String, Integer> counts, String player) {
        counts.put(player, counts.getOrDefault(player, 0) + 1);
    }

    // EFFECTS: draws "no matches" message
    private void drawNoMatchesMessage(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("No matches recorded yet", 20, 50);
    }

    // EFFECTS: returns the maximum match count
    private int findMaxCount(Map<String, Integer> playerMatchCounts) {
        int max = 1;
        for (Integer count : playerMatchCounts.values()) {
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    // EFFECTS: draws bars for each player
    private void drawBars(Graphics g, Map<String, Integer> matchCounts, int maxCount) {
        int barWidth = 60;
        int spacing = 20;
        int startX = 30;
        int baseline = getHeight() - 40;
        int maxBarHeight = getHeight() - 80;

        int currentX = startX;

        for (Map.Entry<String, Integer> entry : matchCounts.entrySet()) {
            String player = entry.getKey();
            int count = entry.getValue();

            int barHeight = computeBarHeight(count, maxCount, maxBarHeight);

            drawBar(g, currentX, baseline, barWidth, barHeight);
            drawBarLabels(g, player, count, currentX, baseline, barWidth, barHeight);

            currentX += barWidth + spacing;
        }
    }

    // EFFECTS: computes scaled bar height
    private int computeBarHeight(int count, int maxCount, int maxHeight) {
        double ratio = (double) count / maxCount;
        return (int) (ratio * maxHeight);
    }

    // EFFECTS: draws one colored bar
    private void drawBar(Graphics g, int x, int baseline, int width, int height) {
        g.setColor(new Color(70, 130, 180));
        g.fillRect(x, baseline - height, width, height);

        g.setColor(Color.BLACK);
        g.drawRect(x, baseline - height, width, height);
    }

    // EFFECTS: draws player label + count label
    private void drawBarLabels(Graphics g, String player, int count,
                               int x, int baseline, int width, int height) {
        g.setFont(new Font("Arial", Font.PLAIN, 10));
        g.setColor(Color.BLACK);

        String shortLabel = shortenLabel(player);
        g.drawString(shortLabel, x, baseline + 15);

        int countX = x + width / 2 - 5;
        int countY = baseline - height - 5;
        g.drawString(String.valueOf(count), countX, countY);
    }

    // EFFECTS: ensures label text is not too long
    private String shortenLabel(String name) {
        return name.length() > 8 ? name.substring(0, 8) : name;
    }

    // EFFECTS: draws the chart title
    private void drawTitle(Graphics g) {
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

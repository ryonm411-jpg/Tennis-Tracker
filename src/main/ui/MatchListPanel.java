package ui;

import model.Match;
import model.TennisData;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Panel that displays all matches in a scrollable list.
 */
public class MatchListPanel extends JPanel {
    private TennisData tennisData;
    private JList<String> matchList;
    private DefaultListModel<String> listModel;

    // REQUIRES: tennisData is not null
    // MODIFIES: this
    // EFFECTS: constructs a panel to display matches
    public MatchListPanel(TennisData tennisData) {
        this.tennisData = tennisData;
        setBorder(new TitledBorder("All Matches"));
        setLayout(new BorderLayout());
        
        listModel = new DefaultListModel<>();
        matchList = new JList<>(listModel);
        matchList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(matchList);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        
        add(scrollPane, BorderLayout.CENTER);
        refresh();
    }

    
    // MODIFIES: this
    // EFFECTS: updates the display with current matches
    public void refresh() {
        listModel.clear();
        for (Match m : tennisData.getMatchTracker().getAllMatches()) {
            String matchString = m.getDate() + ": " + m.getPlayer1Name()
                    + " vs " + m.getPlayer2Name() + " (" + m.getScore() + ")";
            listModel.addElement(matchString);
        }
    }

    // REQUIRES: tennisData is not null
    // MODIFIES: this
    // EFFECTS: sets the tennis data and refreshes display
    public void setTennisData(TennisData tennisData) {
        this.tennisData = tennisData;
    }
}




package ui;

import model.Match;
import model.TennisData;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Graphical user interface for the Tennis Match Tracker application.
 */
public class TennisGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/tennisData.json";
    private TennisData tennisData;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private MatchListPanel matchListPanel;
    private StatsPanel statsPanel;

    // REQUIRES: none
    // MODIFIES: this
    // EFFECTS: constructs the GUI and initializes components
    public TennisGUI() {
        super("Tennis Match Tracker");
        tennisData = new TennisData();
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        
        setupMenuBar();
        setupMainPanel();
        setupWindow();
        
        promptLoadOnStart();
    }

    // REQUIRES: none
    // MODIFIES: this
    // EFFECTS: sets up the menu bar with File menu
    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        
        JMenuItem loadItem = new JMenuItem("Load");
        loadItem.setActionCommand("load");
        loadItem.addActionListener(this);
        
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setActionCommand("save");
        saveItem.addActionListener(this);
        
        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.setActionCommand("quit");
        quitItem.addActionListener(this);
        
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(quitItem);
        
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    // REQUIRES: none
    // MODIFIES: this
    // EFFECTS: sets up the main panel with components
    private void setupMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        matchListPanel = new MatchListPanel(tennisData);
        statsPanel = new StatsPanel(tennisData);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addMatchBtn = new JButton("Add Match");
        addMatchBtn.setActionCommand("addMatch");
        addMatchBtn.addActionListener(this);
        
        JButton viewPlayerBtn = new JButton("View Matches for Player");
        viewPlayerBtn.setActionCommand("viewPlayer");
        viewPlayerBtn.addActionListener(this);
        
        buttonPanel.add(addMatchBtn);
        buttonPanel.add(viewPlayerBtn);
        
        mainPanel.add(matchListPanel, BorderLayout.CENTER);
        mainPanel.add(statsPanel, BorderLayout.SOUTH);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        
        add(mainPanel);
    }

    // REQUIRES: none
    // MODIFIES: this
    // EFFECTS: sets up window properties
    private void setupWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
    }

    // REQUIRES: none
    // MODIFIES: none
    // EFFECTS: prompts user to load data on application start
    private void promptLoadOnStart() {
        int option = JOptionPane.showConfirmDialog(this,
                "Would you like to load your previous data?",
                "Load Data",
                JOptionPane.YES_NO_OPTION);
        
        if (option == JOptionPane.YES_OPTION) {
            loadData();
        }
    }

    // REQUIRES: none
    // MODIFIES: none
    // EFFECTS: prompts user to save data before exiting
    private void promptSaveOnExit() {
        int option = JOptionPane.showConfirmDialog(this,
                "Would you like to save your matches before quitting?",
                "Save Data",
                JOptionPane.YES_NO_OPTION);
        
        if (option == JOptionPane.YES_OPTION) {
            saveData();
        }
        System.exit(0);
    }

    // REQUIRES: none
    // MODIFIES: this
    // EFFECTS: handles action events from buttons and menu items
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if (command.equals("addMatch")) {
            addMatch();
        } else if (command.equals("viewPlayer")) {
            viewMatchesForPlayer();
        } else if (command.equals("load")) {
            loadData();
        } else if (command.equals("save")) {
            saveData();
        } else if (command.equals("quit")) {
            promptSaveOnExit();
        }
    }

    // REQUIRES: none
    // MODIFIES: this
    // EFFECTS: opens dialog to add a new match
    private void addMatch() {
        final JDialog dialog = new JDialog(this, "Add Match", true);
        dialog.setLayout(new GridLayout(5, 2));
        
        final JTextField p1Field = new JTextField();
        final JTextField p2Field = new JTextField();
        final JTextField dateField = new JTextField();
        final JTextField scoreField = new JTextField();
        
        dialog.add(new JLabel("Player 1:"));
        dialog.add(p1Field);
        dialog.add(new JLabel("Player 2:"));
        dialog.add(p2Field);
        dialog.add(new JLabel("Date:"));
        dialog.add(dateField);
        dialog.add(new JLabel("Score:"));
        dialog.add(scoreField);
        
        JButton okBtn = new JButton("OK");
        JButton cancelBtn = new JButton("Cancel");
        
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String p1 = p1Field.getText();
                String p2 = p2Field.getText();
                String date = dateField.getText();
                String score = scoreField.getText();
                
                if (!p1.isEmpty() && !p2.isEmpty() && !date.isEmpty() && !score.isEmpty()) {
                    Match match = new Match(p1, p2, date, score, 0, 0, 0);
                    tennisData.getMatchTracker().addMatch(match);
                    matchListPanel.refresh();
                    statsPanel.repaint();
                    dialog.dispose();
                    JOptionPane.showMessageDialog(TennisGUI.this, "Match added successfully!");
                } else {
                    JOptionPane.showMessageDialog(dialog, "Please fill in all fields.");
                }
            }
        });
        
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        
        dialog.add(okBtn);
        dialog.add(cancelBtn);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // REQUIRES: none
    // MODIFIES: none
    // EFFECTS: shows dialog to filter matches by player name
    private void viewMatchesForPlayer() {
        String playerName = JOptionPane.showInputDialog(this, "Enter player name:");
        
        if (playerName != null && !playerName.isEmpty()) {
            StringBuilder matches = new StringBuilder();
            boolean found = false;
            
            for (Match m : tennisData.getMatchTracker().getAllMatches()) {
                if (m.getPlayer1Name().equalsIgnoreCase(playerName)
                        || m.getPlayer2Name().equalsIgnoreCase(playerName)) {
                    matches.append(m.getDate()).append(": ")
                           .append(m.getPlayer1Name()).append(" vs ")
                           .append(m.getPlayer2Name()).append(" (")
                           .append(m.getScore()).append(")\n");
                    found = true;
                }
            }
            
            if (found) {
                JTextArea textArea = new JTextArea(matches.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(400, 300));
                JOptionPane.showMessageDialog(this, scrollPane,
                        "Matches for " + playerName, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No matches found for " + playerName);
            }
        }
    }

    // REQUIRES: jsonReader is initialized
    // MODIFIES: this
    // EFFECTS: loads tennis data from JSON file
    private void loadData() {
        try {
            tennisData = jsonReader.read();
            matchListPanel.setTennisData(tennisData);
            matchListPanel.refresh();
            statsPanel.setTennisData(tennisData);
            statsPanel.repaint();
            JOptionPane.showMessageDialog(this, "Loaded data from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file: " + JSON_STORE);
        }
    }

    // REQUIRES: jsonWriter is initialized
    // MODIFIES: this
    // EFFECTS: saves tennis data to JSON file
    private void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(tennisData);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Saved matches to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to write to file: " + JSON_STORE);
        }
    }

    // REQUIRES: none
    // MODIFIES: none
    // EFFECTS: starts the GUI application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            
            public void run() {
                new TennisGUI();
            }
        });
    }
}
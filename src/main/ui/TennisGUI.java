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

    // MODIFIES: this
    // EFFECTS: sets up window properties
    private void setupWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

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

    // MODIFIES: this
    // EFFECTS: opens dialog to add a new match
    private void addMatch() {
        JDialog dialog = new JDialog(this, "Add Match", true);
        dialog.setLayout(new GridLayout(5, 2));

        JTextField p1 = new JTextField();
        JTextField p2 = new JTextField();
        JTextField date = new JTextField();
        JTextField score = new JTextField();

        addMatchFields(dialog, p1, p2, date, score);
        addMatchButtons(dialog, p1, p2, date, score);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }


    // REQUIRES: d is not null
    // MODIFIES: d
    // EFFECTS: adds input fields to the dialog
    private void addMatchFields(JDialog d, JTextField p1, JTextField p2, JTextField date, JTextField score) {
        d.add(new JLabel("Player 1:"));
        d.add(p1);
        d.add(new JLabel("Player 2:"));
        d.add(p2);
        d.add(new JLabel("Date:"));
        d.add(date);
        d.add(new JLabel("Score:"));
        d.add(score);
    }


    // REQUIRES: d is not null
// MODIFIES: d
// EFFECTS: adds OK and Cancel buttons with listeners to the dialog
private void addMatchButtons(JDialog d, JTextField p1, JTextField p2, JTextField date, JTextField score) {
    JButton ok = new JButton("OK");
    JButton cancel = new JButton("Cancel");

    ok.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleMatchAdd(d, p1, p2, date, score);
        }
    });

    cancel.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            d.dispose();
        }
    });

    d.add(ok);
    d.add(cancel);
}



    // REQUIRES: d is not null
    // MODIFIES: this, d
    // EFFECTS: validates fields, adds match to tennisData and updates UI if valid
    private void handleMatchAdd(JDialog d, JTextField p1, JTextField p2, JTextField date, JTextField score) {
        if (p1.getText().isEmpty() || p2.getText().isEmpty()
                || date.getText().isEmpty() || score.getText().isEmpty()) {
            JOptionPane.showMessageDialog(d, "Please fill in all fields.");
            return;
        }

        Match match = new Match(p1.getText(), p2.getText(), date.getText(), score.getText(), 0, 0, 0);
        tennisData.getMatchTracker().addMatch(match);
        matchListPanel.refresh();
        statsPanel.repaint();
        d.dispose();
        JOptionPane.showMessageDialog(this, "Match added successfully!");
    }

    // EFFECTS: shows dialog to filter matches by player name
    private void viewMatchesForPlayer() {
        String name = JOptionPane.showInputDialog(this, "Enter player name:");
        if (name == null || name.isEmpty()) {
            return;
        }

        String list = buildPlayerMatchList(name);
        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No matches found for " + name);
            return;
        }

        showPlayerMatches(name, list);
    }


    // REQUIRES: name is not null
    // EFFECTS: builds and returns formatted list of matches for the player
    private String buildPlayerMatchList(String name) {
    String result = "";
    for (Match m : tennisData.getMatchTracker().getAllMatches()) {
        if (m.getPlayer1Name().equalsIgnoreCase(name)
                || m.getPlayer2Name().equalsIgnoreCase(name)) {
            result += m.getDate() + ": "
                    + m.getPlayer1Name() + " vs "
                    + m.getPlayer2Name()
                    + " (" + m.getScore() + ")\n";
        }
    }
    return result;
}


    // REQUIRES: name and text are not null
    // MODIFIES: this
    // EFFECTS: shows the provided match text in a dialog
    private void showPlayerMatches(String name, String text) {
        JTextArea area = new JTextArea(text);
        area.setEditable(false);
        JScrollPane pane = new JScrollPane(area);
        pane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(this, pane,
                "Matches for " + name, JOptionPane.INFORMATION_MESSAGE);
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

    // EFFECTS: starts the GUI application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TennisGUI();
            }
        });
    }
}

package ui;

import model.Match;
import model.TennisData;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                promptSaveOnExit();
            }
        });
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

    
}


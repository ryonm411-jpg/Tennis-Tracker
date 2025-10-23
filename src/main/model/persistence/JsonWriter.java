package model.persistence;

import model.TennisData;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import org.json.JSONObject;


import java.io.*;

// Represents a writer that saves tennis data to JSON file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
    
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of TennisData to file
    public void write(TennisData data) {
        
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        
    }

   
}

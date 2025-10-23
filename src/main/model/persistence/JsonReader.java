package model.persistence;

import model.TennisData;
import java.io.IOException;

// Represents a reader that loads tennis data from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads JSON structure from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TennisData read() throws IOException {
        
        return null;
    }

}

    
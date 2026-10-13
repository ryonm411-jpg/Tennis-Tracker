# Tennis Match Tracker

[![CI](https://github.com/ryonm411-jpg/Tennis-Tracker/actions/workflows/ci.yml/badge.svg)](https://github.com/ryonm411-jpg/Tennis-Tracker/actions/workflows/ci.yml)

A Java desktop application for recording tennis matches and reviewing match history.

<!-- TODO: add screenshot, e.g. ![Tennis Match Tracker](docs/screenshot.png) -->

## Features

- **Record matches**: log the players, date and score of each match.
- **Browse history**: view every recorded match, or filter to the matches of a single player.
- **Visualise activity**: a bar chart shows how many matches each player has played.
- **Save and load**: persist all data to a JSON file and pick up where you left off.
- **Event log**: every change to the data is logged and printed when the app closes.

## Getting started

Requirements: JDK 21. Gradle and all dependencies are fetched automatically by the wrapper.

```sh
./gradlew run     # launch the app (use gradlew.bat on Windows cmd/PowerShell)
./gradlew test    # run the unit tests
./gradlew check   # run the tests and Checkstyle
```

## Design

The code is split into three packages:

| Package       | Responsibility |
|---------------|----------------|
| `model`       | Domain classes: `Match`, `Player`, `MatchTracker`, `TennisData`, plus the `EventLog` |
| `persistence` | JSON serialisation (`JsonReader`, `JsonWriter`, `Writable`) |
| `ui`          | Swing interface: `TennisGUI`, `MatchListPanel`, `StatsPanel` |

`EventLog` is a Singleton, so every model class writes to the same log. See the [UML class diagram](UML_Design_Diagram.pdf) for the full structure.

## Next steps

- **Observer pattern.** The UI panels currently refresh only when `TennisGUI` calls `refresh()` or `repaint()` on them, which couples the controller to every panel. Making the model the subject and the panels (and the event log) its observers would remove those manual calls and let new views be added without touching the controller.
- **Owner-centred model.** Record each match from one player's perspective with an explicit win/loss result, and derive win/loss records from match results instead of storing them separately.
- **Match stats.** The model stores aces, double faults and unforced errors, but the GUI does not yet let you enter them; add them to the add-match form.
- **Input validation.** Parse dates as `LocalDate` and validate score formats.

## Credits

Built as a personal project for UBC's CPSC 210 (Software Construction). `Event`, `EventLog`, `JsonReader`, `JsonWriter` and `Writable` are adapted from the CPSC 210 sample projects (AlarmSystem and JsonSerializationDemo).

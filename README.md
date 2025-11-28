  

# Tennis Match Tracker
_A personal prject for CPSC 210_

The **Tennis Match Tracker** is a desktop application that allows players to record and analyze their tennis matches.

Users will be able to log details of their tennis such as:
- Dates
- Scores
- Oponents
- Stats(e.g, aces, double daults, unforced errors)

## Intended Users
- Tennis players of all levels who want to keep track of their matches
- Coaches who want to monitor student performances
- Anyone interested in analyzing someones match history to identify strengths/weaknesses

## Why I chose this project
I am personally interested in **tennis** I think I would find it motivating to design a tool that I could actually use and benefit from.


## User Stories

- As a user, I want to be able to add a tennis match to my tracker and specify the players, date, score.
- As a user, I want to be able to view a list of all the matches that I have recorded
- As a user, I want to be able to add statistics to a match, such as aces, double faults, unforced errors, etc...
- As a user, I want to be able to be able to view player performaces, such as thier total wins/losses, thier win percentage.

## User Stories for Phase 2
- As a user, I want to be able to save all recorded tennis matches and player data to a JSON file, inorder to keep track of them after I close the application
- AS a user, I want to be able to load prev saved matches and player data from a JSON file so I can continue from where I left off.

## Phase 4: Task 2

Sample event log output when running the application:

```
Event Log:
====================
Fri Nov 28 01:08:41 PST 2025
Match added: m vs j

Fri Nov 28 01:08:41 PST 2025
Match added: Ryon vs Jeff

Fri Nov 28 01:08:41 PST 2025
Match added: joe vs schmoe

Fri Nov 28 01:08:41 PST 2025
Match added: k vs may

Fri Nov 28 01:08:41 PST 2025
Match added: Ryon vs schmoe

Fri Nov 28 01:08:41 PST 2025
Match added: Bily vs Hoe

```

The event log captures all significant model changes including:
- Adding matches to the match tracker
- Updating match statistics (aces, double faults, unforced errors)
- Adding players to the tennis data
- Recording wins and losses for players

All events are logged in the model package and printed to the console when the application is closed.

## Phase 4: Task 3

**Design Reflection:**

If I had more time, I’d refactor the UI to use the Observer design pattern. Right now, whenever something in the model changes—like adding a match or updating player stats—the UI panels (MatchListPanel and StatsPanel) only update if TennisGUI manually calls refresh() or repaint(). This means the GUI controller ends up tightly linked to the UI panels, which makes the code harder to maintain.

Using the Observer pattern, the data classes (TennisData, MatchTracker, maybe even Match) would act as subjects that notify their observers whenever something changes. The UI panels would register themselves as observers and update automatically whenever they get a notification. This removes the need for manual refresh calls, loosens the coupling between components, and makes it much easier to add new UI panels in the future. It would also fit naturally with the event-logging system already in place, since the same actions that produce events could also trigger observer updates.
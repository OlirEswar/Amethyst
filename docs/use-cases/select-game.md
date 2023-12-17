# Select Game

**Level**: User Goals

**Primary actor**: User

**Stakeholders and interests**:
- User: Wants to be able to play different games during the break period during different app 
sessions

**Pre-conditions**:
- The user is on the setting configuration screen

**Post-conditions**:
- The selected game will be displayed and run during each break period throughout the duration of
the pomodoro session
- If the user has previous data for the particular game it should be loaded during the break period

**Workflow**:
```puml
skin rose
title Select Game
'define swimlanes
|#papayawhip|User|
|#turquoise|System| 

|System|
start
:Display games;
|User|
:Selects a game for break;
|System|
:Loads game;
if (Does save data already exist?) then (yes)
    :Load save data;
else (no)
    :Generate random board for game;
endif
:Start break timer;
:Display game;
end
```

**Non-functional requirements**:
- Usability
    - Game name and general instructions should be displayed to make sure it is clear to the user
- Reliability
    - If the the current game data is unusable, the next most recent state should be loaded in, so
the user does not have to start over
- Performance
    - Previous game state data should be concise so that it can be loaded in quickly without delaying
the first break period of the session
- Supportability
    - Game names and instructions should be accessible in multiple languages so that international
users can still learn about the games before making an official selection
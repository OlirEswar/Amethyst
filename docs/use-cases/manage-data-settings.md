# Manage Data Settings
**Level**: User goals

**Primary actor**: User

**Stakeholders and interests**:

- User: Wants to be able to accumulate data about their productivity such as pomodoros completed
but also wants to be able to clear this data.

**Pre-conditions**:

- Load previous user data if they have already created preferences from previous sessions

**Post-conditions**:

- App can display user data from across multiple sessions
- App can clear and reset user data

**Workflow**:
```puml
skin rose
title Manage Data
'define swimlanes
|#papayawhip|User|
|#turquoise|System| 

|System|
start
:Display current user data (accumulated stats/game score);
|User|
if (wants to clear data) then (yes)
    switch (What type of data?) 
case (game data)
  :Press clear game data button;
case (stats data)
  :Press clear stats data button (stats and todos);
endswitch
|System|
    :clear respective locally data;
    end
else (no)
    |User|
    :complete pomodoro session;
    |System|
    :Store information from previous session;
end
```
**Non-functional requirements**:
- Usability
    - User should be able to easily navigate to menu that removes data
- Reliability
    - App must not crash even after storing lots of user data
    - May offload to non-local storage if data is too much
- Performance
  - Deleting data should be very quick and user should be able to quickly go back to the app
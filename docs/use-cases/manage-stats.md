# Manage Data Settings
**Level**: User goals

**Primary actor**: User

**Stakeholders and interests**:

- User: Wants to be able to accumulate data about their productivity such as pomodoros completed, to dos created, to dos
finished, etc. to keep them motivated

**Pre-conditions**:

- Load previous user data if they already have statistics from previous sessions

**Post-conditions**:

- App displays screen at end of session displaying summary of important data from session

**Workflow**:
```puml
skin rose
title Manage Stats
'define swimlanes
|#papayawhip|User|
|#turquoise|System| 

|System|
start
|User|
    switch (Perform action) 
case ()
  :Manage To Do;
case ()
  :End Period;
case ()
  :Play Match3 Game;
endswitch
|System|
    :record statistics;
|User|
    :End pomodoro session;
|System|
    :Display summary of user statistics;
end
```
**Non-functional requirements**:
- Usability
    - User should be able to easily navigate view different stats about their productivity
- Reliability
    - App must reliably and accurately display the different types of user data
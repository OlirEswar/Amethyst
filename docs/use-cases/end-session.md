# End Session

**Level**: User Goals

**Primary actor**: User

**Stakeholders and interests**:
- User: Wants a way to interrupt the current period after they are finished with their
pomodoro session.

**Pre-conditions**:
- The user is currently in a period and is not on the setting configuration screen

**Post-conditions**:
- The user ends the current period and the app is exited
- All important data such as preferred settings and game state data is saved

**Workflow**:
```puml
skin rose
title End Pomodoro Session

'define swimlanes
|#papayawhip|User|
|#turquoise|System|

|User|
start
    :Finished with Pomodoro session;
|System|
    :Stop timer;
    :Save remaining to-do list items for next session;
    if (in a game?) then (yes)
        :Save game state;
    else (no)
    endif
   :Return to setting configuration;
end
```
**Non-functional requirements**:
- Usability
    - May want to ask the user if they are sure they want to end the session before terminating the
app in case they trigger the process by accident
- Reliability
    - All data must be stored, so that it can be loaded the next time the user opens the application

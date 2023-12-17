# Send Time Notification

**Level**: User Goals

**Primary actor**: User

**Stakeholders and interests**:
- User: Wants a notification to interact with if the app is unfocused,
in order to properly budget time for activities.

**Pre-conditions**:
- The user is currently in the work period of the pomodoro session, and the work
period is longer than 5 minutes.

**Post-conditions**:
- The user will be notified when the work period is almost over.
- The user is presented an option to go back to the main app to keep
better track of time with the timer.

**Workflow**:
```puml
skin rose
title Send Time Notifications

'define swimlanes
|#turquoise|System|
|#papayawhip|User|

|System|
start
while (App is in focus?) is (yes)
    if (5 minutes left in timer?) then (yes)
        :Send User notification;
        |User|
        if (User clicks on notification?) then (yes)
            |System|
            :Return user to the app;
        else (no)
            |User|
            :Notification persists until it is dismissed;
            |System|
        endif
    else (no)
    endif
endwhile (no)
end
```

**Non-functional requirements**:
- Usability
    - Game must transition from level to level with animations easy on the eyes.
- Reliability
    - Game must not lose save state upon crash, if not current state, then previous state.
- Performance
    - Timer must be accurate within the second, synchronized with system clock.
    - Game cannot stutter or drop frames to ensure a smooth gameplay experience.
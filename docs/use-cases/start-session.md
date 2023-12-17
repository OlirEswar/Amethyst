# Start Session

**Level**: User Goals

**Primary actor**: User

**Stakeholders and interests**:
- User: Wants to be able to initiate the loop of work and break periods on their own

**Pre-conditions**:
- The user has entered in all of their preferred settings
- These settings are reasonable and can be applied to the application

**Post-conditions**:
- The first work period will be started with the user's preferred time and any other entered settings
- These settings will persist throughout the duration of the user's pomodoro session

**Workflow**:
```puml
skin rose
title Start Session

'define swimlanes
|#papayawhip|User|
|#turquoise|System|

|System|
start
    :Display Setting Configuration screen;
|User|
    :Enter preferred settings;
    :Submit settings;
|System|
    :Set up work/break period times;
    :Set up work/break period loop;
    :Start initial work period;
end
```
**Non-functional requirements**:
- Usability
    - Should have a clear UI element to trigger the start of the session
- Reliability
    - App must not crash with setting choices that are illogical or cause errors, but instead 
give the user a reasonable error message
    - Session should not start if any settings are unreasonable and cannot be applied to the session
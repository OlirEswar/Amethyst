# Manage Timer
**Level**: User goals

**Primary actor**: User

**Stakeholders and interests**:

- User: Wants to be able to pause and then unpause timer in case there are
any interruptions during their work or break period

**Pre-conditions**:

- User has filled out the settings configuration and is currently in a period

**Post-conditions**:

- Period timer stops counting down if paused
- Period timer resumes if unpaused
- Pause button UI is updated to reflect change in button action

**Workflow**:
- Manage to-dos (TODO: change this with swimlanes, it doesn't look that good)
```puml
skin rose
title Manage Timer
'define swimlanes
|#papayawhip|User|
|#turquoise|System| 

|User|
start
:Wants to change timer pause state;
while (timeRemaining?) is (yes);
:Press "pause"/"unpause" button;
|System|
switch (What modification should be made?) 
case (Timer is currently unpaused)
  :Stop timer from counting down;
  :Update "pause" button text to "unpause";
case (Timer is currently paused)
  |System|
  :Start new timer with current time remaining;
  :Update "unpause" button text to "pause";
endswitch
|System|
:Allow user to press button;
endwhile;
:Transition to next period;
end
```
**Non-functional requirements**:
- Usability
    - Button and button text should be large and easily readable
    - Button should have UI label updated each time it is pressed to reflect its new action
- Reliability
    - App must not crash if user presses button repeatedly 
    - App must accurately pause and unpause timer even if button UI fails to update
- Performance
  - Button must trigger pause or unpause action within the second, so that the user does
  not lose or gain time by pressing the button
- Supportability
  - Button text should support multiple languages to make it accessible to international users
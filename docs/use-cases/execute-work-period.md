# Execute Work Period

**Level**: User goals

**Primary actor**: User

**Stakeholders and interests**:
- User: Wants to be able to work on whatever they need to do
  in a predetermined amount of time, which is followed up by a break.
- Boss/Teacher of User: Wants the work assigned to be done in a
  timely manner.

**Pre-conditions**:
- The user has selected how long they want the time of working to be,
  with a default of 25 minutes, and if they want music/white noise, it
  will be played.

**Post-conditions**:
- The user will do a certain amount of work that the Boss/Teacher wants.
- The user is reminded when the work is about to end, either through
  a timer, or a notification.
- The break portion will be initiated, giving the user games to play to fill
  the break time.

**Workflow**:
```puml
skin rose
title Execute Work Period
'define swimlanes
|#papayawhip|User|
|#turquoise|System|

start
:Play user requested music or white noise;
while (Is there still work time?) is (yes)
if (Is screen focused?) then (yes)
    :Display time;
    |User|
    switch (What action to do?)
    case (Manage Timer)
      switch (Is timer paused?)
      case (yes)
      :Unpause timer;
      case (no)
      :pause timer;
      endswitch
    endswitch
    |System|
else (no)
    :Send Time Notifications;
endif
endwhile (no)
while (Is screen focused?) is (no)
    :Send reminder once, wait for ""User"";
endwhile (yes)
:Fade music or white noise;
:Prompt user with break, break time, and game choice;
stop 
```

**Non-functional requirements**:
- Usability
  - Timer must be seen from arms length if displayed.
  - Notification displayed must be clear to user and link back to the app.
- Performance
  - Must be accurate within the second, synchronized with system clock.
- Supportability
  - Must support 12-hour clock, 24-hour clock.
# Time Break Period

**Level**: User Goals

**Primary actor**: User

**Stakeholders and interests**:
- User: Wants to have timed and managed breaks between working sessions so that they can get 
refreshed without getting tired of working

**Pre-conditions**:
- The user has finished a work period and is entering a break period

**Post-conditions**:
- The user will be notified when the break period is almost over.
- The user will return to the work period screen, and the game display will be removed

**Workflow**:
```puml
skin rose
title Time Break Period
'define swimlanes
|#papayawhip|User|
|#turquoise|System|

|System|
start
while (Is there still break time?) is (yes)
   if (game selected) then (yes)
   else (no)
      :Select game;
      :Execute game;
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
   endif
   :Display break activity;
   if (At halfway point of break time) then (yes)
      :Display notification of half time elapsed;
   else (no)
   endif
endwhile (no)
:Notify user to prepare for work period;
|User|
:Return to work;
|System|
:Start __Time work period__;
end
```

**Non-functional requirements**:
- Usability
    - On-screen timer should not be distracting, but should be easily located
    - Notifications of remaining break time should get the user's attention
- Reliability
    - System should move to work period even if notifications fail to send or if timer UI does not
accurately reflect actual time remaining
- Performance
    - Timer must be accurate within the second, synchronized with system clock.
    - Timer must be constantly visible.
# Playing Match-3 Game

**Level**: User Goals

**Primary actor**: User

**Stakeholders and interests**:
- User: Wants to play the game in the allotted time as a break, rewarding them 
for doing the work they just previously did and mentally preparing them for the 
next pomodoro.

**Pre-conditions**:
- The user has gone through the work timer for the time allotted, and is currently 
focused on the screen. The user must have also selected the Match-3 Game to play during
this break.

**Post-conditions**:
- The user will play this game for a certain amount of time allotted by the break period 
that they had specified earlier. 
- The game will react to all changes by the user and generate new content upon request.
- The game's state will be saved for the next time that the user selects this game.
- The next pomodoro will start.

**Workflow**:
```puml
skin rose 
title Play Match-3 Game

'define swimlanes
|#papayawhip|User|
|#turquoise|System|

|System|
start
if (Previous data exists) then (yes)
    :Load data;
else (no)
    :Generate random board;
endif
:Set game state to board;
while (Is there still break time?) is (yes)
    :Display board;
    |User|
    :Make move on board;
    |System|
    :Swap gems;
    :Make match-3 move;
    if (Score reaches level-up threshold) then (yes)
        :Level up, play animation;
    else (no)
    endif
|System|
endwhile (no)
:Save game data;
if (All Pomodoros finished?) then (no)
    :Start new Pomodoro;
    stop
else (yes)
    :Show congratulations screen;
    stop
```

**Non-functional requirements**:
- Usability
    - Game must transition from level to level with animations easy on the eyes.
- Reliability
    - Game must not lose save state upon crash, if not current state, then previous state.
- Performance
    - Timer must be accurate within the second, synchronized with system clock.
    - Game cannot stutter or drop frames to ensure a smooth gameplay experience.
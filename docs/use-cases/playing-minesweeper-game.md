# Playing Minesweeper Game

**Level**: User Goals

**Primary actor**: User

**Stakeholders and interests**:
- User: Wants to play the game in the allotted time as a break, rewarding them
  for doing the work they just previously did and mentally preparing them for the
  next pomodoro.

**Pre-conditions**:
- The user has gone through the work timer for the time allotted, and is currently
  focused on the screen. The user must have also selected the Minesweeper Game to play during
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
title Play Minesweeper Game

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
while (Is there break time?) is (yes)
    :Display board;
    |User|
    :Select tile;
    |System|
    if (Is tile a mine?) then (yes)
        :Display game loss;
        :Reset board;
    else (no)
        :Clear tile and tiles adjacent;
        while (Empty tiles adjacent to recently cleared?) is (yes)
            :Clear tile and tiles adjacent;
        endwhile (no)
        :Update board and numbers under tiles;
        if (Are all non-mine tiles cleared?) then (yes)
            :Game won;
            :Reset board;
        else (no)
        endif
    endif
endwhile (no)
:Save game state;
if (All Pomodoros finished?) then (no)
    :Start new Pomodoro;
    stop
else (yes)
    :Show congratulations screen;
    stop
```

**Non-functional requirements**:
- Usability
    - Game must transition from board to board with animations easy on the eyes.
- Reliability
    - Game must not lose save state upon crash, if not current state, then previous state.
- Performance
    - Timer must be accurate within the second, synchronized with system clock.
    - Game cannot stutter or drop frames to ensure a smooth gameplay experience.
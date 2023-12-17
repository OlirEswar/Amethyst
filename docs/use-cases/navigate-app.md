# Navigate App

**Level**: User Goals

**Primary actor**: User

**Stakeholders and interests**:
- User: Wants to be able to perform different types of actions while they are in a period
- User: Should have quick access to various actions on one screen

**Pre-conditions**:
- The user is currently in the work or break period of the pomodoro session

**Post-conditions**:
- The user will have successfully performed an action that changes the state of the application
- This may include: a paused or unpaused timer, a newly added or deleted to-do item, or a game move

**Workflow**:
```puml
skin rose
title Navigate App

'define swimlanes
|#papayawhip|User|
|#turquoise|System|

|System|
start
    :Start application;
    :Display Setting Configuration screen;
|User|
    :Enter preferred settings;
    :Submit settings and start session;
|System|
    :Start period;
    :Display to-do list;
    if (Is break period?) then (yes)
      :Start game;
    else (no)
    endif
|User|
    switch (What action to do?) 
    case (Manage to-do list)
      :Add, delete, edit to-do item;
    case (Play game)
    if (Is break period?) then (yes)
      :Make game move;
    else (no)
    endif
    endswitch
    :Save remaining to-do list items for next session;
|System|
    :Start next period;
end
```
**Non-functional requirements**:
- Usability
    - UI elements triggering each action should have enough space between them so that user can
navigate the contents of a screen easily
    - UI elements should be clearly labeled so that the user knows which elements trigger which 
actions
- Reliability
    - System should be able to handle and execute many actions in quick succession
- Performance
    - System should have little delay between user action and appropriate UI changes
- Supportability
    - Users should have multiple ways of interacting with on-screen elements including tapping 
and swiping. For example, a user should be able to tap or swipe on the game board to make a move
and they should have the option to tap a delete button or swipe a to-do item to delete it.
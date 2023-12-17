# Load Data

**Level**: Subfunction

**Primary actor**: User

**Stakeholders and interests**:
- User: Wants to be able to resume their game progress between periods and potentially across 
pomodoro sessions as well

**Pre-conditions**:
- The user has started a break period
- The user has stored game data like board arrangement and score

**Post-conditions**:
- The user will be able to resume their game as it was after the break period ends

**Workflow**:
```puml
skin rose
title Load - System only

start
if (Does save data exist?) then (yes)
    :Set game state to current data;
else (no)
    :Create random game state;
endif
:Display game state;
end
```
**Non-functional requirements**:
- Reliability
    - Game state must be loaded at the start of each break period.
- Performance
    - Delay between ending period and loading game data should be minimized.
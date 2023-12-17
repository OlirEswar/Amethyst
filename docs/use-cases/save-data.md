# Save Data

**Level**: Subfunction

**Primary actor**: User

**Stakeholders and interests**:
- User: Wants to be able to resume their game progress between periods and potentially across
  pomodoro sessions as well, meaning they must be able to save important data

**Pre-conditions**:
- The system is currently transitioning from a break period to a work period
- The user has made moves and created information that can be saved

**Post-conditions**:
- The system will store the game's state so that it can be reloaded
- The user can access this game state in future break periods

**Workflow**:
```puml
skin rose
title Save - System only

start
:Record current game state;
if (Is there save data?) then (yes)
    :Overwrite with new data;
else (no)
    :Save state as data;
endif
:Start work period;
end
```
- Reliability
  - Game state must be saved at the end of each break period.
- Performance
  - Delay between starting work period and saving game data should be minimized.
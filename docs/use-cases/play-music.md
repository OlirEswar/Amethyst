# Play Music

**Level**: User Goals

**Primary actor**: User

**Stakeholders and interests**:
- User: Wants to be able to listen to music within the app while they are working or playing a game

**Pre-conditions**:
- The user has selected a pre-loaded song from the application or has fetched music from a 3rd party
source

**Post-conditions**:
- Music will be played throughout the pomodoro session

**Workflow**:
```puml
skin rose
title Playing Music

'define swimlanes
|#papayawhip|User|
|#turquoise|System|

|System|
start
if (What music did the user choose?) then (3rd party)
    :Fetch 3rd party API music;
else (White noise)
    :Load white noise from memory;
endif
while (Is there still work time?) is (yes)
    :Play selected music;
    |User|
    if (Presses fast-forward/rewind) then (yes)
        :Switch to next/previous track;
    else (no)
    endif
endwhile (no)
|System|
:Fade music;
:Begin break period;
end
```
**Non-functional requirements**:
- Usability
    - Music must be relatively quiet to allow the user to focus on their actual work
- Reliability
    - Backup tracks should be played if the music from 3rd party sources suddenly becomes unavailable
  so that the noise level remains consistent
- Interfaces
    - Application must interface with 3rd party music providers and utilize methods such as APIs
to access their tracks
- Legal
    - Music should be acquired through trusted sources to ensure they are being played legally
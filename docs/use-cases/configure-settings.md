# Configure Settings

**Level**: User goals

**Primary actor**: User

**Stakeholders and interests**:

- User: Wants to be able to customize different app settings such as the amount of
  time allocated for working, the time allocated for taking breaks, what games to play,
  and what music to play.

**Pre-conditions**:

- Load previous user settings if they have already created preferences from previous sessions

**Post-conditions**:

- App applies settings to upcoming work/break sessions
- App saves the settings to be automatically applied for future app uses

**Workflow**:
```puml
skin rose
title Configure Settings
'define swimlanes
|#papayawhip|User|
|#turquoise|System|

start
|System|
if (""User"" has previously used settings?) then (yes)
    :Autofill settings options with previously used values;
else (no)
endif
|User|
while (User presses save?) is (no)
  :Present ""User"" with list of settings options;
  |User|
  :Enter in preferred options;
  |System|
  if (Any unreasonable settings? (negative time, too long break time, etc.)) then (yes)
    :Display error message;  
  else (no)
  endif
endwhile(yes)
|System|
:Start work pomodoro phase with specified settings;
stop 
```
**Non-functional requirements**:
- Usability
  - Form input fields for entering settings must be spaced out, with relatively large fon
  so that it is easy for users to read and change values
  - User should be able to continually change individual settings until they are happy with
  their configuration and ready to save (should not be tedious to change different settings)
- Reliability
  - App must not crash when user enters unreasonable time settings and must not try
  to apply these settings to the pomodoro timer.
  - App should reliably load in previous settings if they are there
- Performance
  - Timer settings should not take long to save, so that the user can easily transition to 
  starting the timer
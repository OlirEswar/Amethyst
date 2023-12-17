# Pomodoro Game - Vision Document

## Introduction
We are striving towards a simple yet effective implementation 
of the Pomodoro timer, which allows users to space out work and break 
periods to properly regulate chunks of work. This app would be able to 
assist users in focusing during break works, with a time indicator and 
supplemental white noise or user-selected music. This app also addresses the 
small break periods between work with a small, easily playable game in 
order to properly reward them.

## Business Position
This app is able to help manage and regulate work times in methods that 
other apps fall short.
- It rewards the user with a game, structuring break time instead of 
the user not knowing what to do in this short period of time.
  - This ensures that users are ready to go back to working productively instead
  of feeling unsatisfied with their break
- It integrates break and work periods seamlessly, whilst also giving 
users the option to lengthen the work period in order to not break their 
workflow.
- It can play music and display calming backgrounds during the work period,
promoting the user's focus on their work.

## Key Functionality
- Times work periods and break periods accurately.
- Allows customizable work period ambience in the form of background noise.
- Provides the user with a rewarding game during the break period.
- Automatically save game state between break periods.
- Automatic Pomodoro settings per session.
- Tracking of to-dos over the course of the work session.

## Stakeholder descriptions:
### Market Demographics
This product is primarily for students and people in the workforce who
need to get work completed for themselves, their bosses, professors, etc.

### Stakeholder Summaries
- **User**: management of time, completion of work, proper rewarding
of work
- **Boss/Teacher of User**: work is done in a timely manner

## System context diagram:
```puml
top to bottom direction
skin rose
actor User as user
rectangle "Pomodoro Game" {
    user -- (Manage to-dos)
    user -- (Execute work period)
    user -- (Execute break period)
}
```
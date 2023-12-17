# CMPU-203 S23 - Team 2D

## Name
Pomodoro Game

## Functionality Added in Iteration 2 and 3
The app was converted from a text-based UI to a GUI, allowing the user to interact
with the app in real time and get feedback on the screen, rather than through a
command line. From this, functionality was expanded to include the logic surrounding
the break period of the pomodoro cycle, along with the game logic itself. The game logic
consists of a board of different colored gems that users can swap to create matches, earning
points and increasing their score. Along with this, both the work and break periods have on-screen
timers that update every second in order to properly give the user feedback on how much time
is remaining for their work period. Users have the ability to pause the work/break period
timers in order to conduct other tasks.

## Description
The Pomodoro Game is a time management application where a user may input times in order
to manage their time effectively, doing work in specified work periods, and relaxing/rewarding
themselves in specified break periods. Specifically, the break periods consist of games that
the user may play in these short time frames in order to give themselves a greater reward,
motivating them to continue on working and giving them something to look forward to after
finishing their blocks of work.

## Usage
To use this app, make sure that the Gradle build is synced in Android Studio, before running
the app. You will be presented with the set times panel, where you can choose how long your
work and break periods are. After clicking the "CONFIRM" button, the work period will start.
The timer will start ticking down, where the user must do work in this time. The user may also
pause the timer, in case they have to do other things. Once the timer is up, the break period
will automatically start, displaying the match-3 game's gem board. Users click on one space on
the board, followed by another click on another button to swap the positions of the gems
represented on each button. If a match is made, points are given to the user, and if
enough points are accumulated, the level increases. Users may pause this break timer as well.
Once the break period timer is up, the work period starts again, and the cycle repeats.

### Debug Break Period
Since the minimum time to put in the work period is 1 minute, and since you might want to
test the break period's functionality without waiting a minute, do the following steps.
1. Input the desired work period time.
2. Input "1337", followed by how long you'd like the break period to be.
   Example: Input "1337**10**" for a **10**-minute-long break period.
3. Hit "CONFIRM". The break period should be enabled, and a snackbar will alert
   to you that you've used a debug method to start in the break period.

## Authors
Oliravan Eswaramoorthy, Alexander Teabo

## Project Status
Android app prototype
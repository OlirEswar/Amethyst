# CMPU-203 S23 - Team 2D

## Name
Amethyst - A Focus Timer
(Pomodoro Game)

## Functionality Added in Iteration 4
The app's main usage loop of setting time -> work period -> break period was modified, adding 
fragments between every period with varying use cases. The Menu fragment was added to display 
app's title and prompt the user to start with the setting time periods. After a work/break 
period is finished, a subsequent fragment was added to let the user know the period has ended, 
and that clicking the button will send them to the next period. Various backgrounds and UI 
updates were added to improve user's ease of use, along with making everything look more 
aesthetically pleasing.

The break period game, Match-3, had some significant improvements. The gems were swapped out to 
graphical elements, responsive to selections and had a defined chessboard-pattern grid to improve 
visual clarity. The user is now restricted to only making gem swaps that are adjacent, and a combo
system along with visual elements to describe it were added, rewarding the user doing streaks of 
valid moves with augmented points. Persistent data was added, allowing for the user to continue on
their game in a pomodoro cycle, and between games. A tutorial button was added for new users to take 
a look at, and learn how the game is played.

Finally, to-dos were added in the work period, along with statistics that track the amount of to-dos
done, break periods, work periods, and time spent in each period. You can add to-dos, and either remove
them, or confirm that you've finished them!

## Description
The Pomodoro Game is a time management application where a user may input times in order 
to manage their time effectively, doing work in specified work periods, and relaxing/rewarding 
themselves in specified break periods. Specifically, the break periods consist of games that 
the user may play in these short time frames in order to give themselves a greater reward, 
motivating them to continue on working and giving them something to look forward to after 
finishing their blocks of work. 

## Usage
To use this app, make sure that the Gradle build is synced in Android Studio, before running 
the app. You will be greeted by the menu screen, allowing you to start your session. Afterwards, 
you will be presented with the set times panel, where you can choose how long your 
work and break periods are. After clicking the "CONFIRM" button, the work period will start.

The timer will start ticking down, where the user must do work in this time. The user may also
pause the timer, in case they have to do other things. Once the timer is up, a screen will be 
presented to you, asking for confirmation to begin the break period. Upon confirming, you will 
be taken to the break period, by displaying the match-3 game's gem board. The game will continue 
on from where you past left off from it, or create a new game if no save data is detected.

For to-dos, enter the task you want to do and add. If you'd like to remove it, there is a remove 
button, but usually you will finish the task, and thus complete it with the completion button. 
These will be saved if you click the end button in the work/break period, along with giving you
statistics on how many you did this session. If you'd like to see your cumulative stats, along with
the ability to wipe game data/stats data, click on the settings button in the main menu.

For the break period's match-3 game, users will click on one space on the board, followed by
another click on another button to swap the positions of the gems represented on each button. 
The buttons must be adjacent, otherwise the first gem will be deselected. If a match is made, 
points are given to the user and the combo increases, and if enough points are accumulated, 
the level increases. If a match is not made, the user will lose combo progress, and might 
even go down a combo level. Users may pause this break timer as well. Once the break period timer
is up, the work period starts again, and the cycle repeats.

### Debug Break Period 
Since the minimum time to put in the work period is 1 minute, and since you might want to 
test the break period's functionality without waiting a minute, do the following steps.
1. Input the desired work period time.
2. Input "1337", followed by how long you'd like the break period to be. 
Example: Input "1337**10**" for a **10**-minute-long break period.
3. Hit "CONFIRM". The break period should be enabled, and a snackbar will alert
to you that you've used a debug method to start in the break period.

## Notable bugs/compromises

### Screen resizing glitch
When typing in values into the add to do edit text view, the screen will naturally resize, and 
will subsequently break the layout for a bit. Closing the keyboard fixes everything. Not exactly
sure why it does this, with the ConstraintLayout and all.

### Loading to-dos on entry
When loading into the work period with existing to dos, the ScrollView will not present the to do 
items. However, upon adding a to-do item (or just clicking add to re-render), they will pop up.

### Timer in View, not Controller/Model -> View
This was a hard one, but the problem is that it'll heavily increase the complexity of the code with
not much seen additions to flexibility. We've tried to implement a solution that properly utilizes
MVC, but it would not work at all and would require major additions to code (Emitter on the model
to emit onTick events, Listener triggering the onFinish -> Emitter triggering its own onFinish, 
plus the loads of code required to have one specific timer modified for every work/break period).

### Full testing
The simple justification for not doing complete testing of the work period/break period time
values in statistics or the settings... it will take loads of time. We'll have to sleep the 
clock for 60 seconds at a time to progress through the minimum timer value for each screen.
Just, not worth it, so we tested everything else we could. Another issue with the full 
break period testing is that (once again, lack of documentation), we can't really extract 
the image resource of a ViewInteraction in order to re-implement a potential match algorithm,
and even then, it's still not guaranteed that there will be a potential match on the screen.
So we just tested the structure.

## Authors 
Oliravan Eswaramoorthy, Alexander Teabo

## Project Status
Android app prototype
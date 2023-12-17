# CMPU-203 S23 - Team 2D

## Name
Pomodoro Game

## Description (Current Functionality)
The current prototype gives the user a text interface to interact with a pomodoro timer
application. The prototype allows users to enter their preferred duration for work and
break periods and then automatically switches between the two periods when the proper amount
of time has elapsed. During each period, the user has access to a set of commands to check
the time remaining the period, to print the type of period they are in, and to quit out of
the application. In this prototype, we assume that the user only enters time durations for
two types of periods, work and break periods. In the future, more types of periods, such as
'long breaks', may be added, making the settings configuration process more involved. In 
addition, we assume that the user only configures the time of each period, where in the
future they may also control settings like music played and game played. We also limit,
the timer from changing the period until after the user checks the time and time has run out.
In the next iteration, this changing should run in real-time with app notifications. 
Currently, the user cannot play any games in the application during the break period.
Finally, we limit the user to only entering in positive integers as minute durations. In 
the future, we may include durations that are fractions of a minute.

## Usage
The current text prototype has a main() method in the TextUI class. Once this method is
run, the user is met with a welcome message. To start configuring the settings, the user
inputs any string and presses enter. They then enter a positive integer for the work and
break periods. To confirm that the time is usable for the session, the user must enter the
string "y" and press enter. If it is not usable, the user should enter the string "n" in
the command line and press enter. Other inputs will be invalid. After this a work period
will start. The user can check the time remaining in the period by typing the string "c" 
in the command line and hitting enter. They can check what type of period they are in
by typing in the string "p" and hitting enter. Finally, the user can type in the string "q"
at any time to quit out of the application. The user should also note that the application
will only switch to the next period once the user enters the "c" command and all the time
in the period or more has elapsed.

## Authors and acknowledgment
Oliravan Eswaramoorthy, Alexander Teabo

## Project status
Text prototype
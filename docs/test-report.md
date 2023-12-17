# Pomodoro Game - Test Report

## Methodology
In this iteration, the application was tested by entering in different types of inputs
whenever user input was available. Unit tests were not used. Please see the transcript
below for the different inputs and their results in different circumstances.

## Inputs and Transcript
The user is required to enter an input after the welcome message. This can be any string
including the empty string.

**Prompt:** Welcome to the Pomodoro Game. In this text based prototype you will have timer moving between work and 
break periods where you can play games. Press any key and hit enter to configure the timer.
**Input:** ok
**Output:** Please enter the length (in minutes) of the work period:

**Prompt:** Welcome to the Pomodoro Game. In this text based prototype you will have timer moving between work and
break periods where you can play games. Press any key and hit enter to configure the timer.
**Input:** 77
**Output:** Please enter the length (in minutes) of the work period:

**Prompt:** Welcome to the Pomodoro Game. In this text based prototype you will have timer moving between work and
break periods where you can play games. Press any key and hit enter to configure the timer.
**Input:** 
**Output:** Please enter the length (in minutes) of the work period:

Each of these first inputs has the expected result. Regardless of what the user inputs at the first prompt, the
program should move onto the next phase and ask the user for their desired work period length. In each example, even
the empty string, the program continues as expected.

**Prompt:** Please enter the length (in minutes) of the work period:
**Input:** 15
**Output:** Please enter the length (in minutes) of the break period:

In this prompt, the program continues as expected when the user enters a valid length for the work period. Since the
user enters 15 in this case, a positive integer, a valid work period object can be created. As a result the program
can move on to ask the user for their desired break period time.

**Prompt:** Please enter the length (in minutes) of the work period:
**Input:** c
**Output:** Please enter an integer!
Please enter the length (in minutes) of the work period:

In this prompt, the program behaves as expected when the user enters a non-integer value. In this case, the user enters
"c", which would not be a valid duration for a period. As a result, the program explains the user what their error was
and prompts the user with the same question again to ensure the information is received.

**Prompt:** Please enter the length (in minutes) of the work period:
**Input:** -8
**Output:** Please enter a number greater than 0!
Please enter the length (in minutes) of the work period:

In this prompt, the program behaves as expected when the user enters a value less than or equal to 0. In this case, 
the user enters "-8", which would not be a valid duration for a period as there cannot be a negative duration. 
As a result, the program explains the user what their error was and prompts the user with the same question again to 
ensure the information is received.

**Prompt:** Please enter the length (in minutes) of the work period:
**Input:** 0
**Output:** Please enter a number greater than 0!
Please enter the length (in minutes) of the work period:

In this prompt, the program behaves as expected when the user enters a value less than or equal to 0. In this case,
the user enters "0", which would not be a valid duration for a period as the period would have no actual duration,
defeating the point of the period-based pomodoro timer. As a result, the program explains the user what their error 
was and prompts the user with the same question again to ensure the information is received.

**Prompt:** Please enter the length (in minutes) of the work period:
**Input:** 0
**Output:** Please enter a number greater than 0!
Please enter the length (in minutes) of the work period:
**Input:** b
**Output:** Please enter an integer!
Please enter the length (in minutes) of the work period:
**Input:** -8
**Output:** Please enter a number greater than 0!
Please enter the length (in minutes) of the work period:

In this prompt, the program behaves as expected even if a user enters in invalid inputs multiple times. In this case,
the program continues to ask the user for the work period time until they provide a valid result.

**Prompt:** Please enter the length (in minutes) of the break period:
**Input:** 5
**Output:** Chosen settings: Work Period: 15 minutes | Break Period: 5 minutes
Are these settings ok? (y/n)

In this prompt, the program continues as expected when the user enters a valid length for the work period. Since the
user enters 5 in this case, a positive integer, a valid break period object can be created. As a result the program
can move on to ask the user to confirm their choices.

**Prompt:** Please enter the length (in minutes) of the break period:
**Input:** ok
**Output:** Please enter an integer!
Please enter the length (in minutes) of the break period:

In this prompt, the program behaves as expected when the user enters a non-integer value. In this case, the user enters
"ok", which would not be a valid duration for a period. As a result, the program explains the user what their error was
and prompts the user with the same question again to ensure the information is received.

**Prompt:** Please enter the length (in minutes) of the break period:
**Input:** -6
**Output:** Please enter a number greater than 0!
Please enter the length (in minutes) of the break period:

In this prompt, the program behaves as expected when the user enters a value less than or equal to 0. In this case,
the user enters "-6", which would not be a valid duration for a period as there cannot be a negative duration.
As a result, the program explains the user what their error was and prompts the user with the same question again to
ensure the information is received.

**Prompt:** Please enter the length (in minutes) of the break period:
**Input:** 0
**Output:** Please enter a number greater than 0!
Please enter the length (in minutes) of the break period:

In this prompt, the program behaves as expected when the user enters a value less than or equal to 0. In this case,
the user enters "0", which would not be a valid duration for a period as the period would have no actual duration,
defeating the point of the period-based pomodoro timer. As a result, the program explains the user what their error
was and prompts the user with the same question again to ensure the information is received.

**Prompt:** Please enter the length (in minutes) of the break period:
**Input:** ok
**Output:** Please enter an integer!
Please enter the length (in minutes) of the break period:
**Input:** -6
**Output:** Please enter a number greater than 0!
Please enter the length (in minutes) of the break period:
**Input:** 2.5
Please enter an integer!
Please enter the length (in minutes) of the break period:

In this prompt, the program behaves as expected even if a user enters in invalid inputs multiple times. In this case,
the program continues to ask the user for the work period time until they provide a valid result.

**Prompt:** Are these settings ok? (y/n)
**Input:** n
**Output:** Please enter the length (in minutes) of the work period:

In this prompt, the program behaves as expected if the user isn't satisfied with their chosen settings. If the user
enters "n" to signal they are not happy with their settings, the program loops back and asks the user for their
desired work period time, so that they can restart the configuration process.

**Prompt:** Are these settings ok? (y/n)
**Input:** string
**Output:** Invalid choice. Please enter 'y' or 'n'
Are these settings ok? (y/n)

In this prompt, the program behaves as expected if a user enters a value that is not "y" or "n". In this case, the
program provides an error message and prompts the user with the same confirmation message again.

**Prompt:** Are these settings ok? (y/n)
**Input:** y
**Output:**
Starting session! Get to work!
Press and enter 'p' to get the period type, 'c' to check the time remaining, or 'q' to quit
Enter a command (p/c) or 'q' to quit

In this prompt, the program behaves as expected if a user enters in "y" when asked to confirm their settings. In this
case, the user indicates that they are happy with their settings allowing the program to move onto the next phase and
give the user the corresponding instructions.

**Prompt:** Press and enter 'p' to get the period type, 'c' to check the time remaining, or 'q' to quit
Enter a command (p/c) or 'q' to quit
**Input:** c
**Output:** Work Period: 13:7 remaining

In this prompt, the program behaves as expected when a user enters a possible command. In this case, the user enters
the "c" command indicating that they want to check the amount of time remaining in the period. The program responds 
accordingly, giving the user the time remaining in the current period in the format of "minutes:seconds".

**Prompt:** Enter a command (p/c) or 'q' to quit
**Input:** p
**Output:** Work

In this prompt, the program behaves as expected when a user enters a possible command. In this case, the user enters
the "p" command indicating that they want to check which type of period they are currently in. The program responds
accordingly, giving the user the type of the current period, in this prototype, either "Work" or "Break".

**Prompt:** Enter a command (p/c) or 'q' to quit
**Input:** 4.5
**Output:** Invalid command. Please enter 'p', 'c', or 'q'
Enter a command (p/c) or 'q' to quit

In this prompt, the program behaves as expected when a user enters a command that is not listed. In this case, the
user enters "4.5" which is not a valid command. The program responds by giving an error message and listing the valid
commands, followed by prompting the user to enter a valid command again.

**Prompt:** Enter a command (p/c) or 'q' to quit
**Input:** q
**Output:** Goodbye! Hope you had a productive Pomodoro session!

In this prompt, the program behaves as expected when a user enters the quit command, "q". In this case, the user
enters "q" and the program responds by giving a goodbye message and then exiting.

**Prompt:** Enter a command (p/c) or 'q' to quit
**Input:** c
**Output:** Work time is up!
Starting Break period!
Enter a command (p/c) or 'q' to quit

In this prompt, the program behaves as expected when the user checks the time remaining after it has all elapsed. The
program alerts the user that the time has finished for the period, and moves the user to the next period, giving them
the possible commands.

**Prompt:** Press and enter 'p' to get the period type, 'c' to check the time remaining, or 'q' to quit
Enter a command (p/c) or 'q' to quit
**Input:** c
**Output:** Break Period: 0:59 remaining

In this prompt, the program behaves as expected when a user enters a possible command. In this case, the user enters
the "c" command in the break period, indicating that they want to check the amount of time remaining in the period. The 
program responds accordingly, giving the user the time remaining in the current period in the format of 
"minutes:seconds".

**Prompt:** Enter a command (p/c) or 'q' to quit
**Input:** p
**Output:** Break

In this prompt, the program behaves as expected when a user enters a possible command. In this case, the user enters
the "p" command during the break period, indicating that they want to check which type of period they are currently in. 
The program responds accordingly, giving the user the type of the current period, in this prototype, either "Work" or 
"Break".

**Prompt:** Enter a command (p/c) or 'q' to quit
**Input:** yes
**Output:** Invalid command. Please enter 'p', 'c', or 'q'
Enter a command (p/c) or 'q' to quit

In this prompt, the program behaves as expected when a user enters a command that is not listed in the game period. 
In this case, the user enters "yes" which is not a valid command. The program responds by giving an error message and 
listing the valid commands, followed by prompting the user to enter a valid command again.

**Prompt:** Enter a command (p/c) or 'q' to quit
**Input:** q
**Output:** Goodbye! Hope you had a productive Pomodoro session!

In this prompt, the program behaves as expected when a user enters the quit command, "q", in the break period. In this 
case, the user enters "q" and the program responds by giving a goodbye message and then exiting.

**Prompt:** Enter a command (p/c) or 'q' to quit
**Input:** c
**Output:** Break time is up!
Starting Work period!
Enter a command (p/c) or 'q' to quit

In this prompt, the program behaves as expected when the user checks the time remaining after all the time in the
break period has all elapsed. The program alerts the user that the time has finished for the period, and moves the user 
to the next period, giving them the possible commands.



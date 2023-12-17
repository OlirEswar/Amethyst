# Manage To-dos
**Level**: User goals

**Primary actor**: User

**Stakeholders and interests**:

- User: Wants to be able to list different tasks that they want to
accomplish during their pomodoro session

**Pre-conditions**:

- Load previous user to-dos if they have already created preferences from previous sessions

**Post-conditions**:

- App lists to-dos in work sessions, below timer
- App saves the to-dos to be automatically applied for future app uses

**Workflow**:
- Manage to-dos (TODO: change this with swimlanes, it doesn't look that good)
```puml
skin rose
title Manage To-Dos
'define swimlanes
|#papayawhip|User|
|#turquoise|System| 

|System|
start
:Display current to-do items;
|User|
while(Have to-do list items to modify?) is (yes);
switch (What modification should be made?) 
case (Create new to-do)
  :Input desired name and deadline;
  :Add to-do to list;
case (Modify existing to-do)
  |User|
  :Select to-do from list;
  :Change desired fields;
case (Delete existing to-do)
  |User|
  :Select to-do from list;
  :Delete to-do item from list;
endswitch
|System|
:Modify to-do item selected in list;
:Display current to-dos;
endwhile (no)
:(Complete To-do List);
end
```
**Non-functional requirements**:
- Usability
    - Form input fields for entering settings must be spaced out, with relatively large fon
      so that it is easy for users to read and change values
    - User should be able to create, update, delete, and read to-dos at
  any point during the work period, so that their list stays up to date.
- Reliability
    - App must not crash when user enters in long to-dos, and can limit users to a certain number of characters
    - App should reliably load in previous to-dos if they are there
# Making Match3 Move
**Level**: User goals

**Primary actor**: User

**Stakeholders and interests**:

- User: Wants to switch two gems on the Match3 game board in order to 
create matches, earning points for themselves.

**Pre-conditions**:

- The current game is the Match3 game, and the game is not paused or out of focus.

**Post-conditions**:

- The swap is conducted, the combo is updated, and if needed, the points are updated.
- If needed, the board generates more gems to fill up the space cleared 
by the match.

**Workflow**:
```puml
skin rose
title Making Match-3 move

'define swimlanes
|#papayawhip|User|
|#turquoise|System|

|System|
start
while (Two valid gems selected) is (no)
|User|
:User taps a gem on the board;
|System|
:Display currently selected gem;
|User|
:User selects another gem;
|System|
if (Selected and swap gem adjacent?) then (yes)
    :Two valid gems selected;
else (no)
    :Deselect both gems;
endif
endwhile (yes)
|System|
:Display swap animation between gems;
:Swap gems in board;
if (Swap makes match?) then (yes)
    :Clear matched gems;
    :Gems above fall;
    :New gems generated from top;
    :Gems settle on board;
    while (Any match created?) is (yes)
        :Remove matched gems;
        :Clear matches gems;
        :Gems above fall;
        :New gems generated from top;
        :Gems settle on board;
    endwhile (no)
    :Add points to score;
    :Increment combo progress;
    if (Combo progress > Combo cap?) then (yes)
        :Increment combo level, reset progress;
        :Update combo cap;
    else (no)
    endif
else (no)
    :Unswap gems;
    :Decrement combo progress;
    if (Combo progress < 0) then (yes)
        :Decrement combo level, reset progress;
        :Update combo cap;
    else (no)
        :Reset progress;
    endif
endif
end
```
**Non-functional requirements**:
- Usability
    - The swapping mechanism must be easy to understand, with animations to show 
  users the consequences of what happened.
- Performance
    - Game mustn't stutter during the swap.
    - All gems must fall uniformly, and have expected behavior.
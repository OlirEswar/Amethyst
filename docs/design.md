# Design

## Design Class Diagram
## Controller Package
```puml
title Controller Package

class ControllerActivity {
    ~ centralView: CentralView
    ~ game: Match3Game
    ~ facade: IPersistenceFacade
    ~ currentWorkTime: int
    ~ currentBreakTime: int
    ~ sessionStats: Stats
    ~ toDoList: ToDoList
    ~ totalStats: Stats
    --
    # onCreate(savedInstanceState: Bundle)
    + onSelectStart()
    + onSelectSettings()
    + resetGameData(view: ISettingsView)
    + resetStatsData(view: ISettingsView)
    + returnToMenu()
    + onSubmitSettings(workTime: int, breakTime: int)
    + debugJumpToBreak(workTime: int, breakTime: int)
    + selectGem(x: int, y: int, view: IBreakPeriodView)
    + startWorkPeriod()
    + endWorkPeriod()
    + startBreakPeriod()
    + endBreakPeriod()
    + addToDoItem(string: String, view: IWorkPeriodView)
    + deleteToDoItem(index: int, view: IWorkPeriodView)
    + finishToDoItem(index: int, view: IWorkPeriodView)
    + endSession()
    + onFinishedViewing()
}
```

## Persistence Package
```puml
title Persistence Package

class LocalStorageFacade {
    - dir : File
    - {static} FILE_NAME : String
    --
    + saveGame(game: Match3Game)
    + retrieveGame() : Match3Game
}

interface IPersistenceFacade {
    --
    + saveGame(game: Match3Game)
    + retrieveGame() : Match3Game
}
IPersistenceFacade <|.. LocalStorageFacade  
```

## Model Package
```puml
title Model Package

class Match3Game {
    ~ level : int
    ~ score : int
    ~ scoreCap : int
    ~ progessToCap : int
    ~ comboLevel : int
    ~ comboProgress : int
    - {static} COMBO_CAPS : int[]
    ~ board : Gem[][]
    ~ selectedGem : Coordinate
    ~ swapGem : Coordinate
    --
    + selectGem()
    - swapGems(): boolean
    - calculateMatches(): ArrayList<Match>
    - removeGems(ArrayList<Match>)
    - doGemFall()
    - updateScore()
    - calculateCombo()
}

class Match {
    - slots : List<Coordinate>
    - color : GemColor
    --
    + addAndCheck(): boolean
    + getScore(): int
    + toString(): String
}

class Gem {
    - color : GemColor
    - {static} GEM_COLORS : GemColor[]  
    --
    + {STATIC} getRandomGem(): Gem
    + clone(): Gem
    + getColor(): GemColor
    + toString(): String
    + equals(): boolean
}

enum GemColor {
    RED
    ORANGE
    YELLOW
    GREEN
    BLUE
    PURPLE
    WHITE 
    NONE
}

class Coordinate {
    + x: int
    + y: int
    --
    + reset(): void
    + isValid() : boolean
    + isAdjacent(Coordinate) : boolean
    + equals() : boolean
    + toString(): String
}

class ToDoList {
    ~ toDoLinkedList: LinkedList<String>
    --
    + create(toDo: String): void
    + get(index: int): String
    + delete(index: int)
    + size(): int
}

class Stats {
    + completedToDos: int
    + deletedToDos: int
    + remainingToDos: int
    + completedWorkPeriods: int
    + completedBreakPeriods: int
    + totalWorkTime: int
    + totalBreakTime: int
    --
    + onFinishBreak(minutes: int)
    + onFinishWork(minutes: int)
    + onAddToDo()
    + onDeleteToDo()
    + onFinishToDo()
    + concatenate(other: Stats)
}

Gem .> GemColor
Match3Game .> Gem
Match3Game .> Coordinate
```

## View Package
```puml
title View Package
    left to right direction
    
    interface ICentralView {
        --
        + getRootView(): View
        + displayFragment(fragment: Fragment, reversible: boolean, name: String)
    }
    
    class CentralView {
        ~ fm: FragmentManager
        ~ binding: ActivityMainBuilding
        --
        + getRootView(): View
        + displayFragment(fragment: Fragment, reversible: boolean, name: String)
    }
    
    interface ISetTimeView {
        
    }
    
    interface ISetTimeView.Listener {
        --
        + onSubmitSettings(workTime: int, breakTime: int)
        + debugJumpToBreak(workTime: int, breakTime: int)
    }
    
    class SetTimeFragment {
        ~ binding: FragmentSetTimeBinding
        ~ listener: Listener
        --
        + onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View
        + onViewCreated(view: View, savedInstanceState: Bundle)
    }
    
    interface IBreakPeriodView {
       --
       + update(game: Match3Game)
    }
    
    interface IBreakPeriodView.Listener {
       --
       + selectGem(x: int, y: int, view: IBreakPeriodView)
       + endBreakPeriod()
       ~ endSession()
    }
    
    class BreakPeriodFragment {
       ~ binding: FragmentBreakPeriodBinding
       ~ listener: Listener
       ~ game: Match3Game
       ~ boolean: isTutorialVisible
       - minutes: int
       - currentMillis: long
       - isPaused: boolean
       - timer: CountDownTimer 
       --
       + onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View
       + onViewCreated(view: View, savedInstanceState: Bundle) 
       + update(game: Match3Game)
       - {static} setButtonImage(button: ImageButton, color: GemColor)
       - {static} setSelectedButtonImage(button: ImageButton, color: GemColor) 
       - drawScoreUI()
       - void createTimer(millis: long)
    }
    
    interface IWorkPeriodView {
        + update(toDoList: ToDoList)
    }
    
    interface IWorkPeriodView.Listener {
        --
        + endWorkPeriod()
        + endSession()
        + addToDoItem(toDoItem: String, view: IWorkPeriodView)
        + deleteToDoItem(index: int, view: IWorkPeriodView)
        + finishToDoItem(index: int, view: IWorkPeriodView)
    }
    
    class WorkPeriodFragment {
        ~ binding: FragmentBreakPeriodBinding
        ~ listener: Listener
        - minutes: int
        - currentMillis: long
        - isPaused: boolean
        - timer: CountDownTimer 
        - toDoList: ToDoList
        --
        + onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View
        + onViewCreated(view: View, savedInstanceState: Bundle)
        + update(toDoList: ToDoList)
        - renderToDos()
        - void createTimer(millis: long)
    }
    
    interface IMenuView {
        
    }
    
    interface IMenuView.Listener {
        --
        + onSelectStart()
        + onSelectSettings()
    }
    
    class MenuFragment {
        - binding: FragmentMenuBinding
        - listener: Listener
        --
        + onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View
        + onViewCreated(view: View, savedInstanceState: Bundle)
    }
    
    interface IBeforePeriodView {
        
    }
    
    interface IBeforePeriodView.Listener {
        --
        + startBreakPeriod()
        + startWorkPeriod()
    }
    
    class BeforePeriodFragment {
        - binding: FragmentBeforePeriodBinding
        - listener: Listener
        - isBeforeBreak: boolean
        --
        + onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View
        + onViewCreated(view: View, savedInstanceState: Bundle)
    }
    
    interface ISettingsView {
        + renderStats(stats: Stats)
    }
    
    interface ISettingsView.Listener {
        + resetGameData(view: ISettingsView)
        + resetStatsData(view: ISettingsView)
        + returnToMenu()
    }
    
    class SettingsFragment {
        - binding: FragmentSettingsBinding
        - listener: Listener
        - stats: Stats
        --
        + onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View
        + onViewCreated(view: View, savedInstanceState: Bundle)
        + renderStats(stats: Stats) 
    }
    
    interface IStatsView {
        
    }
    
    interface IStatsView.Listener {
        + onFinishedViewing()
    }
    
    class StatsFragment {
        - binding: FragmentStatsBinding
        - listener: Listener
        ~ stats: Stats
        --
        + onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View
        + onViewCreated(view: View, savedInstanceState: Bundle)
    }
    
    ICentralView <|.. CentralView
    ISetTimeView -down- ISetTimeView.Listener
    ISetTimeView.Listener <|.. SetTimeFragment
    IBreakPeriodView -down- IBreakPeriodView.Listener
    IBreakPeriodView.Listener <|.. BreakPeriodFragment
    IWorkPeriodView -down- IWorkPeriodView.Listener
    IWorkPeriodView.Listener <|.. WorkPeriodFragment
    IMenuView -down- IMenuView.Listener
    IMenuView.Listener <|.. MenuFragment
    IBeforePeriodView -down- IBeforePeriodView.Listener
    IBeforePeriodView.Listener <|.. BeforePeriodFragment
    ISettingsView -down- ISettingsView.Listener
    ISettingsView.Listener <|.. SettingsFragment
    IStatsView -down- IStatsView.Listener
    IStatsView.Listener <|.. StatsFragment
```

## Sequence Diagrams
### Configure Settings (Android UI)
```puml
title Configure Settings
hide footbox
actor User as user
participant "CentralView" as CV
participant "ControllerActivity" as ctrl
participant "SetTimeFragment" as STF
participant "WorkPeriodFragment" as WPF

loop !confirmed

ctrl -->> STF**: new SetTimeFragment(this)
ctrl -> CV: centralView.displayFragment(new SetTimeFragment(this), true, "setTime")
CV -> user: Allow user to enter work/break period length
user -> CV: Submit settings. 
loop invalid settings input
CV -> user: Show error message
user -> CV: Resubmit settings
end
CV -> STF: this.fm, this.binding
STF -> ctrl: listener.onSubmitSettings(workTime, breakTime)
ctrl -> ctrl: onSubmitSettings(workTime, breakTime)
ctrl -->> WPF**: new WorkPeriodFragment(this, currentWorkTime)
ctrl -> CV: centralView.displayFragment(new WorkPeriodFragment(this, currentWorkTime), false, "workPeriod")
```

### Execute Work Period (Android UI)
```puml
title Execute Work Period
hide footbox
actor User as user
participant "CentralView" as CV
participant "ControllerActivity" as ctrl
participant "WorkPeriodFragment" as WPF
participant "BeforePeriodFragment" as BPF
participant "SessionStats" as SS
participant "LocalStorageFacade" as LSF

ctrl -->> WPF**: new WorkPeriodFragment(this, currentWorkTime)
WPF -> WPF: createTimer(millis)
WPF -> WPF: timer.start()
ctrl -> CV: centralView.displayFragment(new WorkPeriodFragment(this, currentWorkTime), false, "workPeriod")
CV -> user: Display time remaining
CV -> user: Display pause button
alt user presses button
    user -> CV: Press button
    CV -> WPF: this.fm, this.binding
    alt !isPaused
        WPF -> WPF: timer.cancel()
        CV -> user: Update pause button text
    else isPaused
        WPF -> WPF: createTimer(millis)
        WPF -> WPF: timer.start()
        CV -> user: Update pause button text
    end
end
alt user adds todo
    user -> CV: Type to do item into field
    user -> CV: Press enter button
    CV -> WPF: this.fm, this.binding
    alt input.equals("")
        WPF -> WPF: Snackbar.make()
        WPF -> CV: sb.show()
        CV -> user: Show error message
    end
    WPF -> ctrl: listener.addToDoItem(input, this)
    ctrl -> WPF: view.update(toDoList)
    ctrl -> SS: sessionStats.onAddTodo()
    WPF -> WPF: renderToDos()
end
alt user removes todo
    user -> CV: Press remove button
    CV -> WPF: this.fm, this.binding
    WPF -> ctrl: listener.deleteToDoItem(finalI, this)
    ctrl -> WPF: view.update(toDoList)
    ctrl -> SS: sessionStats.onDeleteToDo()
    WPF -> WPF: renderToDos()
end
alt user completes todo
    user -> CV: Press remove button
    CV -> WPF: this.fm, this.binding
    WPF -> ctrl: listener.finishToDoItem(finalI, this)
    ctrl -> WPF: view.update(toDoList)
    ctrl -> SS: sessionStats.onFinishToDo()
    WPF -> WPF: renderToDos()
end
WPF -> ctrl: listener.endWorkPeriod()
ctrl -> ctrl: endWorkPeriod()
ctrl -->> BPF**: new BeforePeriodFragment(this, false)
ctrl -> SS: new Stats()
ctrl ->> LSF**: facade.saveToDos(toDoList)
ctrl -> CV: centralView.displayFragment(new BeforePeriodFragment(this, false), false, "beforeWork")
```
*NOTE: Time Break Period works in the same way except
BreakPeriodFragments are created in place of WorkPeriodFragments and vice versa.
The listener also uses the endBreakPeriod() method instead of endWorkPeriod() to end the period in
the break period scenario

### Manage Data
```puml
title Manage Data
hide footbox
actor User as user
participant "CentralView" as CV
participant "ControllerActivity" as ctrl
participant "SettingsFragment" as SF
participant "MenuFragment" as MF
participant "LocalStorageFacade" as LSF

user -> CV: Click settings button
CV -> MF: this.fm, this.binding
MF -> ctrl: listener.onSelectSettings()
ctrl -->> SF**: new SettingsFragment(this, totalStats)
ctrl -> CV: displayFragment(new SettingsFragment(this, totalStats))
CV -> user: Display settings and statistics
alt user clicks wipe game data
user -> CV: Click button
CV -> SF: this.fm, this.binding
SF -> ctrl: listener.resetGameData(this)
ctrl -> ctrl: this.game = new Match3Game()
ctrl -> LSF: facade.saveGame(game)
ctrl -> SF: view.renderStats()
SF -> CV: update stats
CV -> user: display updated stats
end
alt user clicks wipe stats data
user -> CV: Click button
CV -> SF: this.fm, this.binding
SF -> ctrl: listener.resetStatsData(this)
ctrl -> ctrl: this.totalStats = new Stats()
ctrl -> ctrl: this.toDoList = new ToDoList()
ctrl -> LSF: facade.saveStats(totalStats)
ctrl -> LSF: facade.saveToDos(toDoList)
ctrl -> SF: view.renderStats()
SF -> CV: update stats
CV -> user: display updated stats
end
user -> CV: Click return to menu button
CV -> SF: this.fm, this.binding
SF -> ctrl: listener.returnToMenu()
ctrl -->> MF: new MenuFragment(this)
ctrl -> CV: displayFragment(new MenuFragment(this))
CV -> user: Display main menu screen
```

### Start Match-3 Game
```puml
title Start Match-3 Game
hide footbox
actor User as user
participant "CentralView" as CV
participant "Controller Activity" as ctrl
participant "Match3Game" as M3G
participant "BreakPeriodFragment" as BPF

ctrl -->> M3G**: new Match3Game()
ctrl -->> BPF**: new BreakPeriodFragment(this, game, currentBreakTime)
loop x <= 8
    loop y <= 8
    BPF -> BPF: setButtonColor(gemButton, board[x][y].getColor());
    BPF -> BPF: currentRow.addView(gemButton);
    end
end
ctrl -> CV: centralView.displayFragment(new BreakPeriodFragment(this, game, currentBreakTime), false, "breakPeriod")
CV -> user: Display board to user
```

### Make Match-3 Move
```puml
title Make Match-3 Move
hide footbox
actor User as user
participant "CentralView" as CV
participant "ControllerActivity" as ctrl
participant "Match3Game" as M3G
participant "BreakPeriodFragment" as BPF

loop
CV -> user: Display current game board
user -> BPF: Make move on game board
BPF -> ctrl: onClick(view)
ctrl -> M3G: game.selectGem(x, y)
alt selectedGem.isValid && selectedGem.isAdjacent(swapGem)
    M3G -> M3G: swapGems()
end
ctrl -> CV: view.update(game)
CV -> user: Display updated board
end
```
package edu.vassar.cmpu203.pomodorogame.controller;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import edu.vassar.cmpu203.pomodorogame.model.match3.Match3Game;
import edu.vassar.cmpu203.pomodorogame.model.stats.Stats;
import edu.vassar.cmpu203.pomodorogame.model.todo.ToDoList;
import edu.vassar.cmpu203.pomodorogame.persistence.IPersistenceFacade;
import edu.vassar.cmpu203.pomodorogame.persistence.LocalStorageFacade;
import edu.vassar.cmpu203.pomodorogame.view.BeforePeriodFragment;
import edu.vassar.cmpu203.pomodorogame.view.BreakPeriodFragment;
import edu.vassar.cmpu203.pomodorogame.view.CentralView;
import edu.vassar.cmpu203.pomodorogame.view.IBeforePeriodView;
import edu.vassar.cmpu203.pomodorogame.view.IBreakPeriodView;
import edu.vassar.cmpu203.pomodorogame.view.IMenuView;
import edu.vassar.cmpu203.pomodorogame.view.ISetTimeView;
import edu.vassar.cmpu203.pomodorogame.view.ISettingsView;
import edu.vassar.cmpu203.pomodorogame.view.IStatsView;
import edu.vassar.cmpu203.pomodorogame.view.IWorkPeriodView;
import edu.vassar.cmpu203.pomodorogame.view.MenuFragment;
import edu.vassar.cmpu203.pomodorogame.view.SetTimeFragment;
import edu.vassar.cmpu203.pomodorogame.view.SettingsFragment;
import edu.vassar.cmpu203.pomodorogame.view.StatsFragment;
import edu.vassar.cmpu203.pomodorogame.view.WorkPeriodFragment;

/** The controller for the application. */
public class ControllerActivity extends AppCompatActivity implements
        ISetTimeView.Listener, IWorkPeriodView.Listener, IBreakPeriodView.Listener,
        IMenuView.Listener, IBeforePeriodView.Listener, IStatsView.Listener,
        ISettingsView.Listener {

    /** The main view, which allows for the swapping of fragments to display. */
    CentralView centralView;
    /** The current game. */
    Match3Game game;
    /** The persistence subsystem. */
    IPersistenceFacade facade;
    /** The current time, in minutes, to be used for work periods. */
    int currentWorkTime;
    /** The current time, in minutes, to be used for break periods. */
    int currentBreakTime;

    Stats sessionStats;

    ToDoList toDoList;

    Stats totalStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.centralView = new CentralView(this);
        this.facade = new LocalStorageFacade(this.getFilesDir());

        this.game = facade.retrieveGame();
        if (game == null) {
            game = new Match3Game();
            facade.saveGame(game);
        }
        this.totalStats = facade.retrieveStats();
        if (totalStats == null) {
            totalStats = new Stats();
            facade.saveStats(totalStats);
        }
        this.toDoList = facade.retrieveToDos();
        if (toDoList == null) {
            toDoList = new ToDoList();
            facade.saveToDos(toDoList);
        }

        this.currentWorkTime = 0;
        this.currentBreakTime = 0;

        this.sessionStats = new Stats();

        setContentView(centralView.getRootView());
        this.getSupportActionBar().hide();

        centralView.displayFragment(
                new MenuFragment(this),
                true,
                "menu"
        );
    }

    /* Menu listener */
    @Override
    public void onSelectStart() {
        this.sessionStats = new Stats();
        centralView.displayFragment(
                new SetTimeFragment(this),
                true,
                "setTime"
        );
    }

    @Override
    public void onSelectSettings() {
        centralView.displayFragment(
                new SettingsFragment(this, totalStats),
                true,
                "settings"
        );
    }

    /* Settings listener */
    @Override
    public void resetGameData(ISettingsView view) {
        this.game = new Match3Game();
        facade.saveGame(game);
        view.renderStats(totalStats);
    }

    @Override
    public void resetStatsData(ISettingsView view) {
        this.totalStats = new Stats();
        this.toDoList = new ToDoList();

        facade.saveStats(totalStats);
        facade.saveToDos(toDoList);

        view.renderStats(totalStats);
    }

    @Override
    public void returnToMenu() {
        centralView.displayFragment(
                new MenuFragment(this),
                false,
                "menu"
        );
    }

    /* Set time listener */
    @Override
    public void onSubmitSettings(int workTime, int breakTime) {
        this.currentWorkTime = workTime;
        this.currentBreakTime = breakTime;
        toDoList = facade.retrieveToDos();
        centralView.displayFragment(
                new WorkPeriodFragment(this, currentWorkTime, toDoList),
                false,
                "workPeriod"
        );
    }

    @Override
    public void debugJumpToBreak(int workTime, int breakTime) {
        this.currentWorkTime = workTime;
        this.currentBreakTime = breakTime;
        centralView.displayFragment(
                new BreakPeriodFragment(this, game, currentBreakTime),
                false,
                "breakPeriod"
        );
    }

    /* Before Period listener */
    @Override
    public void startWorkPeriod() {
        toDoList = facade.retrieveToDos();
        centralView.displayFragment(
                new WorkPeriodFragment(this, currentWorkTime, toDoList),
                false,
                "workPeriod"
        );
    }

    @Override
    public void startBreakPeriod() {
        centralView.displayFragment(
                new BreakPeriodFragment(this, game, currentBreakTime),
                false,
                "breakPeriod"
        );
    }

    /* Work period listener */
    @Override
    public void addToDoItem(String string, IWorkPeriodView view) {
        toDoList.create(string);
        view.update(toDoList);
        sessionStats.onAddToDo();
    }

    @Override
    public void deleteToDoItem(int index, IWorkPeriodView view) {
        toDoList.delete(index);
        view.update(toDoList);
        sessionStats.onDeleteToDo();
    }

    @Override
    public void finishToDoItem(int index, IWorkPeriodView view) {
        toDoList.delete(index);
        view.update(toDoList);
        sessionStats.onFinishToDo();
    }

    @Override
    public void endWorkPeriod() {
        sessionStats.onFinishWork(currentBreakTime);
        centralView.displayFragment(
                new BeforePeriodFragment(this, true),
                false,
                "beforeBreak"
        );
        facade.saveToDos(toDoList);
    }

    @Override
    public void endSession() {
        totalStats.concatenate(sessionStats);
        centralView.displayFragment(
                new StatsFragment(this, sessionStats),
                true,
                "statsScreen"
        );
        sessionStats = new Stats();
        facade.saveToDos(toDoList);
    }

    /* Stats listener */
    @Override
    public void onFinishedViewing() {
        centralView.displayFragment(
                new MenuFragment(this),
                true,
                "menu"
        );
    }

    /* Break period listener */
    @Override
    public void selectGem(int x, int y, IBreakPeriodView view) {
        game.selectGem(x, y);
        view.update(game);
    }

    @Override
    public void endBreakPeriod() {
        sessionStats.onFinishBreak(currentBreakTime);
        facade.saveGame(game);

        centralView.displayFragment(
                new BeforePeriodFragment(this, false),
                false,
                "beforeWork"
        );
    }
}
package edu.vassar.cmpu203.pomodorogame.model.stats;

import java.io.Serializable;

/**
 * A pseudo-Bundle class for storing statistics of the user's pomodoro sessions.
 */
public class Stats implements Serializable {
    public int completedToDos;
    public int deletedToDos;
    public int remainingToDos;
    public int completedWorkPeriods;
    public int completedBreakPeriods;
    public int totalWorkTime;
    public int totalBreakTime;

    /** Default constructor: instantiates a Stats object with no stats. */
    public Stats() {
        this.completedToDos = 0;
        this.deletedToDos = 0;
        this.remainingToDos = 0;
        this.completedWorkPeriods = 0;
        this.completedBreakPeriods = 0;
        this.totalWorkTime = 0;
        this.totalBreakTime = 0;
    }

    /**
     * Helper function for adding a Break Period + its time.
     * @param minutes the time of the break period
     */
    public void onFinishBreak(int minutes) {
        if (minutes > 0) {
            this.completedBreakPeriods++;
            this.totalBreakTime += minutes;
        }
    }

    /**
     * Helper function for adding a work period + its time.
     * @param minutes the time of the work period
     */
    public void onFinishWork(int minutes) {
        if (minutes > 0) {
            this.completedWorkPeriods++;
            this.totalWorkTime += minutes;
        }
    }

    /** Helper function for adding a To do item. */
    public void onAddToDo() {
        this.remainingToDos++;
    }

    /** Helper function for deleting a To do item. */
    public void onDeleteToDo() {
        if (remainingToDos > 0) {
            this.deletedToDos++;
            this.remainingToDos--;
        }
    }

    /** Helper function for finishing a To do item. */
    public void onFinishToDo() {
        if (remainingToDos > 0) {
            this.completedToDos++;
            this.remainingToDos--;
        }
    }

    /**
     * Adds another stats object to the current one.
     * @param other the other object to have been added with
     */
    public void concatenate(Stats other) {
        this.completedToDos += other.completedToDos;
        this.deletedToDos += other.deletedToDos;
        this.remainingToDos += other.remainingToDos;
        this.completedWorkPeriods += other.completedWorkPeriods;
        this.completedBreakPeriods += other.completedBreakPeriods;
        this.totalWorkTime += other.totalWorkTime;
        this.totalBreakTime += other.totalBreakTime;
    }
}

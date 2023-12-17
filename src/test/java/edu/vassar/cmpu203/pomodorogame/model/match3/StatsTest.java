package edu.vassar.cmpu203.pomodorogame.model.match3;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.vassar.cmpu203.pomodorogame.model.stats.Stats;

public class StatsTest {
    /**Create stats object to be tested**/
    Stats stats;

    @Test
    void testOnFinishBreak() {
        stats = new Stats();

        /**Make sure finishing adds total time and increments break period*/
        stats.onFinishBreak(5);
        assertTrue(stats.totalBreakTime == 5);
        assertTrue(stats.completedBreakPeriods == 1);

        stats.onFinishBreak(20);
        assertTrue(stats.totalBreakTime == 25);
        assertTrue(stats.completedBreakPeriods == 2);

        /**Make sure negative numbers and zeros have no effect*/
        stats.onFinishBreak(-5);
        assertTrue(stats.totalBreakTime == 25);
        assertTrue(stats.completedBreakPeriods == 2);

        stats.onFinishBreak(0);
        assertTrue(stats.totalBreakTime == 25);
        assertTrue(stats.completedBreakPeriods == 2);

        /**Make sure method does not affect any other stats*/
        assertTrue(stats.completedToDos == 0);
        assertTrue(stats.deletedToDos == 0);
        assertTrue(stats.remainingToDos == 0);
        assertTrue(stats.completedWorkPeriods == 0);
        assertTrue(stats.totalWorkTime == 0);
    }

    @Test
    void testOnFinishWork() {
        stats = new Stats();

        /**Make sure finishing adds total time and increments work period*/
        stats.onFinishWork(5);
        assertTrue(stats.totalWorkTime == 5);
        assertTrue(stats.completedWorkPeriods == 1);

        stats.onFinishWork(20);
        assertTrue(stats.totalWorkTime == 25);
        assertTrue(stats.completedWorkPeriods == 2);

        /**Make sure negative numbers and zeros have no effect*/
        stats.onFinishWork(-5);
        assertTrue(stats.totalWorkTime == 25);
        assertTrue(stats.completedWorkPeriods == 2);

        stats.onFinishWork(0);
        assertTrue(stats.totalWorkTime == 25);
        assertTrue(stats.completedWorkPeriods == 2);

        /**Make sure method does not affect any other stats*/
        assertTrue(stats.completedToDos == 0);
        assertTrue(stats.deletedToDos == 0);
        assertTrue(stats.remainingToDos == 0);
        assertTrue(stats.completedBreakPeriods == 0);
        assertTrue(stats.totalBreakTime == 0);
    }

    @Test
    void testOnAddToDo() {
        stats = new Stats();

        /**Test if method increments remaining to dos*/
        stats.onAddToDo();
        assertTrue(stats.remainingToDos == 1);

        stats = new Stats();

        /**Test many increments*/
        for (int i = 0; i < 10; i++) {
            stats.onAddToDo();
            assertTrue(stats.remainingToDos == i + 1);
            /**Make sure method doesn't affect other stats*/
            assertTrue(stats.completedToDos == 0);
            assertTrue(stats.deletedToDos == 0);
            assertTrue(stats.completedBreakPeriods == 0);
            assertTrue(stats.totalBreakTime == 0);
            assertTrue(stats.completedWorkPeriods == 0);
            assertTrue(stats.totalWorkTime == 0);
        }
    }

    @Test
    void testOnDeleteToDo() {
        stats = new Stats();
        stats.onAddToDo();
        stats.onAddToDo();
        stats.onAddToDo();

        /**Test if method decrements remaining to dos and increments deleted to dos*/
        stats.onDeleteToDo();
        assertTrue(stats.remainingToDos == 2);
        assertTrue(stats.deletedToDos == 1);

        /**Make sure method does not affect other statistics*/
        assertTrue(stats.completedToDos == 0);
        assertTrue(stats.completedBreakPeriods == 0);
        assertTrue(stats.totalBreakTime == 0);
        assertTrue(stats.completedWorkPeriods == 0);
        assertTrue(stats.totalWorkTime == 0);

        stats.onDeleteToDo();
        assertTrue(stats.remainingToDos == 1);
        assertTrue(stats.deletedToDos == 2);

        stats.onDeleteToDo();
        assertTrue(stats.remainingToDos == 0);
        assertTrue(stats.deletedToDos == 3);

        /**Test if method skips actions if remaining to dos is 0*/
        stats.onDeleteToDo();
        assertTrue(stats.remainingToDos == 0);
        assertTrue(stats.deletedToDos == 3);
    }

    @Test
    void testOnFinishToDo() {
        stats = new Stats();
        stats.onAddToDo();
        stats.onAddToDo();
        stats.onAddToDo();

        /**Test if method decrements remaining to dos and increments completed to dos*/
        stats.onFinishToDo();
        assertTrue(stats.remainingToDos == 2);
        assertTrue(stats.completedToDos == 1);

        /**Make sure method does not affect other statistics*/
        assertTrue(stats.deletedToDos == 0);
        assertTrue(stats.completedBreakPeriods == 0);
        assertTrue(stats.totalBreakTime == 0);
        assertTrue(stats.completedWorkPeriods == 0);
        assertTrue(stats.totalWorkTime == 0);

        stats.onFinishToDo();
        assertTrue(stats.remainingToDos == 1);
        assertTrue(stats.completedToDos == 2);

        stats.onFinishToDo();
        assertTrue(stats.remainingToDos == 0);
        assertTrue(stats.completedToDos == 3);

        /**Test if method skips actions if remaining to dos is 0*/
        stats.onFinishToDo();
        assertTrue(stats.remainingToDos == 0);
        assertTrue(stats.completedToDos == 3);
    }

    @Test
    void testConcatenate() {
        /**initialize other set of stats*/
        Stats otherStats = new Stats();
        otherStats.onFinishBreak(5);
        otherStats.onFinishBreak(10);
        otherStats.onFinishWork(12);
        otherStats.onFinishWork(20);
        otherStats.onAddToDo();
        otherStats.onAddToDo();
        otherStats.onAddToDo();
        otherStats.onAddToDo();
        otherStats.onAddToDo();
        otherStats.onFinishToDo();
        otherStats.onFinishToDo();
        otherStats.onDeleteToDo();

        stats = new Stats();
        otherStats.onFinishBreak(10);
        otherStats.onFinishWork(15);
        otherStats.onAddToDo();
        otherStats.onAddToDo();
        otherStats.onAddToDo();
        otherStats.onAddToDo();
        otherStats.onFinishToDo();
        otherStats.onDeleteToDo();
        otherStats.onDeleteToDo();

        stats.concatenate(otherStats);

        assertTrue(stats.completedToDos == 3);
        assertTrue(stats.deletedToDos == 3);
        assertTrue(stats.remainingToDos == 3);
        assertTrue(stats.completedWorkPeriods == 3);
        assertTrue(stats.completedBreakPeriods == 3);
        assertTrue(stats.totalWorkTime == 47);
        assertTrue(stats.totalBreakTime == 25);
    }
}

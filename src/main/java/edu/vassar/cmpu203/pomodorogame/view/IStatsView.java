package edu.vassar.cmpu203.pomodorogame.view;

/**
 * Interface that defines functions to be used by the Stats Screen.
 */
public interface IStatsView {
    /**
     * The listener that defines and calls methods to the model, through the controller.
     */
    interface Listener {
        /** Signals the end of the pomodoro session and sends the user back to the initial menu */
        void onFinishedViewing();
    }
}

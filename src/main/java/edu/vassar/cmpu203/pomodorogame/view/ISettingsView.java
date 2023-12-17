package edu.vassar.cmpu203.pomodorogame.view;

import edu.vassar.cmpu203.pomodorogame.model.stats.Stats;

/**
 * Interface that defines the functions to be used by the Settings view
 */
public interface ISettingsView {

    /**
     * Interface that defines the methods called by the view, to be passed
     * to the controller and thus the model.
     */
    interface Listener {
        /** Wipes the game data, replacing it with a new game state. */
        void resetGameData(ISettingsView view);
        /** Wipes the statistics data, replacing it with a new statistics state. */
        void resetStatsData(ISettingsView view);
        /** Returns the app state to the main menu. */
        void returnToMenu();
    }

    /** Displays all time stats. */
    void renderStats(Stats stats);
}

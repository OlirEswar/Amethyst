package edu.vassar.cmpu203.pomodorogame.view;

import edu.vassar.cmpu203.pomodorogame.model.match3.Match3Game;

/**
 * Interface that defines the methods for the BreakPeriod view.
 */
public interface IBreakPeriodView {
    /**
     * Interface that defines the methods called by the view, to be passed
     * to the controller and thus the model.
     */
    interface Listener {
        /**
         * To be used for the gem buttons, selects the gem in the
         * specified coordinates.
         * @param x the x coordinate of the gem selected
         * @param y the y coordinate of the gem selected
         * @param view the view where the <code>Match3Game</code> resides
         */
        void selectGem(int x, int y, IBreakPeriodView view);

        /** Ends the break period and initiates transition to the next work period. */
        void endBreakPeriod();
        /** Ends the break period and initiates transition to the stats screen. */
        void endSession();
    }

    /**
     * Updates the view based on the current state of the game.
     * @param game the game object to update
     */
    void update(Match3Game game);
}

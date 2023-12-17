package edu.vassar.cmpu203.pomodorogame.view;

/**
 * Interface that defines the methods of the MenuView
 */
public interface IMenuView {

    /**
     * Interface that defines the methods called by the view, to be passed
     * to the controller and thus the model.
     */
    interface Listener {

        /**
         * Begins the SetTime fragment.
         */
        void onSelectStart();

        /**
         * Begins the Settings fragment.
         */
        void onSelectSettings();
    }
}

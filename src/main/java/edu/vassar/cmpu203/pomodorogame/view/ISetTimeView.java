package edu.vassar.cmpu203.pomodorogame.view;

/**
 * Interface that defines the functions to be used by the SetTime view
 */
public interface ISetTimeView {

    /**
     * Interface that defines the methods called by the view, to be passed
     * to the controller and thus the model.
     */
    interface Listener {

        /**
         * Submits settings to the controller for use in constructing
         * work and break periods.
         * @param workTime the length of the work period, in minutes
         * @param breakTime the length of the break period, in minutes
         */
        void onSubmitSettings(int workTime, int breakTime);

        /**
         * Submits settings to the controller and immediately jumps to
         * the break period, for testing purposes.
         * @param workTime the length of the work period, in minutes
         * @param breakTime the length of the break period, in minutes.
         */
        void debugJumpToBreak(int workTime, int breakTime);
    }
}

package edu.vassar.cmpu203.pomodorogame.view;

/**
 * Interface that defines the methods for the BeforePeriod view
 */
public interface IBeforePeriodView {
    /**
     * Interface that defines the methods called by the view, to be passed
     * to the controller and thus the model.
     */
    interface Listener {
        /** Initiates the transition to the Break Period. */
        void startBreakPeriod();
        /** Initiates the transition to the Work Period. */
        void startWorkPeriod();
    }
}
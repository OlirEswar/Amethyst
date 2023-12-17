package edu.vassar.cmpu203.pomodorogame;

import android.os.SystemClock;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import edu.vassar.cmpu203.pomodorogame.controller.ControllerActivity;

/**
 * Testing class for the WorkPeriodFragment.
 */
public class WorkPeriodInstTest {
    /** Instance of the controller. */
    @org.junit.Rule
    public ActivityScenarioRule<ControllerActivity> activityRule
            = new ActivityScenarioRule<>(ControllerActivity.class);

    /** The main testing function, testing if the timer
     * works properly. */
    @org.junit.Test
    public void testWorkPeriod() {
        Espresso.onView(ViewMatchers.withId(R.id.startButton))
                .perform(ViewActions.click());

        ViewInteraction pauseButton = displayWorkPeriod();
        SystemClock.sleep(1000);

        ViewInteraction timerText =
                Espresso.onView(ViewMatchers.withId(R.id.timerText));

        // are we in the work period?
        timerText.check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        pauseButton.check(ViewAssertions.matches(ViewMatchers.withText(R.string.pause_button)));
        // once initialized, is the timer counting down properly?
        // only "24:5" as devices/testing computers may differ in speed!
        timerText.check(ViewAssertions.matches(ViewMatchers.withSubstring("24:5")));

        // click the pause button! see if the text changes
        pauseButton.perform(ViewActions.click());
        pauseButton.check(ViewAssertions.matches(ViewMatchers.withText(R.string.unpause_button)));

        // make sure that the time doesn't change
        timerText.check(ViewAssertions.matches(ViewMatchers.withSubstring("24:5")));

        // click the pause button again! see if the text changes
        pauseButton.perform(ViewActions.click());
        pauseButton.check(ViewAssertions.matches(ViewMatchers.withText(R.string.pause_button)));

        // now the time should change..
        SystemClock.sleep(10000);
        timerText.check(ViewAssertions.matches(ViewMatchers.withSubstring("24:4")));
    }

    /**
     * Helper function that will automatically push us to the Work Period
     * with a 25 minute timer.
     *
     * @return the pause button, to interact with
     */
    ViewInteraction displayWorkPeriod() {
        ViewInteraction workTimeInput =
                Espresso.onView(ViewMatchers.withId(R.id.workPeriodInput));
        ViewInteraction breakTimeInput =
                Espresso.onView(ViewMatchers.withId(R.id.breakPeriodInput));
        ViewInteraction confirmButton =
                Espresso.onView(ViewMatchers.withId(R.id.confirmButton));

        workTimeInput.perform(ViewActions.typeText("25"));
        Espresso.closeSoftKeyboard();

        breakTimeInput.perform(ViewActions.typeText("5"));
        Espresso.closeSoftKeyboard();

        confirmButton.perform(ViewActions.click());

        return Espresso.onView(ViewMatchers.withId(R.id.pauseButton));
    }
}

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
 * Testing class for the SetTimeFragment.
 */
public class SetTimeInstTest {
    /** Instance of the controller. */
    @org.junit.Rule
    public ActivityScenarioRule<ControllerActivity> activityRule
            = new ActivityScenarioRule<>(ControllerActivity.class);

    /**
     * Tests the SetTimeFragment logic, specifically making sure that
     * the errors are thrown when the time = 0.
     */
    @org.junit.Test
    public void testSetTime() {
        Espresso.onView(ViewMatchers.withId(R.id.startButton))
                .perform(ViewActions.click());

        ViewInteraction defaultWorkLabel =
                Espresso.onView(ViewMatchers.withId(R.id.workPeriodLabel));
        defaultWorkLabel.check(ViewAssertions.matches(
                ViewMatchers.withText(R.string.work_period_label)
        ));
        ViewInteraction breakPeriodLabel =
                Espresso.onView(ViewMatchers.withId(R.id.breakPeriodLabel));
        breakPeriodLabel.check(ViewAssertions.matches(
                ViewMatchers.withText(R.string.break_period_label)
        ));

        ViewInteraction workTimeInput =
                Espresso.onView(ViewMatchers.withId(R.id.workPeriodInput));
        ViewInteraction breakTimeInput =
                Espresso.onView(ViewMatchers.withId(R.id.breakPeriodInput));
        ViewInteraction confirmButton =
                Espresso.onView(ViewMatchers.withId(R.id.confirmButton));

        workTimeInput.perform(ViewActions.typeText("0"));
        Espresso.closeSoftKeyboard();

        breakTimeInput.perform(ViewActions.typeText("0"));
        Espresso.closeSoftKeyboard();

        confirmButton.perform(ViewActions.click());

        SystemClock.sleep(1000);
        // are we in the same screen? alternative way w/out checking Snackbar
        defaultWorkLabel.check(ViewAssertions
                .matches(ViewMatchers.isDisplayed()));

        breakTimeInput.perform(ViewActions.typeText("3"));
        Espresso.closeSoftKeyboard();

        confirmButton.perform(ViewActions.click());

        SystemClock.sleep(1000);
        // are we in the same screen? pt. 2 both must be non-zero
        defaultWorkLabel.check(ViewAssertions
                .matches(ViewMatchers.isDisplayed()));

        workTimeInput.perform(ViewActions.typeText("3"));
        Espresso.closeSoftKeyboard();

        confirmButton.perform(ViewActions.click());

        SystemClock.sleep(3000);
        // did we move on?
        Espresso.onView(ViewMatchers.withId(R.id.timerText)) // only present in WorkPeriod
                .check(ViewAssertions.matches(ViewMatchers
                        .isDisplayed()));
    }
}

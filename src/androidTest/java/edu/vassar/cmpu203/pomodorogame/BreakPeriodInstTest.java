package edu.vassar.cmpu203.pomodorogame;

import android.os.SystemClock;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.PositionAssertions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.Matchers;

import edu.vassar.cmpu203.pomodorogame.controller.ControllerActivity;
import edu.vassar.cmpu203.pomodorogame.model.utils.Coordinate;

/**
 * Testing class for the BreakPeriodFragment.
 */
public class BreakPeriodInstTest {
    /** Instance of the controller. */
    @org.junit.Rule
    public ActivityScenarioRule<ControllerActivity> activityRule
            = new ActivityScenarioRule<>(ControllerActivity.class);

    /**
     * Tests the break period, through the button tags.
     * NOTE: This doesn't test the timer as it's
     * already tested in WorkPeriodInstTest.
     */
    @org.junit.Test
    public void testBreakPeriod() {
        displayBreakPeriod();
        SystemClock.sleep(1000);

        // check the grid is created properly
        ViewInteraction zeroZero =
                getButtonByCoordinate(new Coordinate(0, 0));

        zeroZero.check(PositionAssertions
                .isCompletelyAbove(ViewMatchers
                        .withTagValue(Matchers
                                .equalTo(new Coordinate(0, 1)))));

        zeroZero.check(PositionAssertions
                .isCompletelyLeftOf(ViewMatchers
                        .withTagValue(Matchers
                                .equalTo(new Coordinate(1, 0)))));

        ViewInteraction invalidCoordinate =
                getButtonByCoordinate(new Coordinate());

        invalidCoordinate.check(ViewAssertions.doesNotExist());

        // make sure tags aren't switched around, just gems
        ViewInteraction zeroOne =
                getButtonByCoordinate(new Coordinate(0, 1));

        zeroZero.perform(ViewActions.click());
        zeroOne.perform(ViewActions.click());

        zeroZero.check(PositionAssertions
                .isCompletelyAbove(ViewMatchers
                        .withTagValue(Matchers
                                .equalTo(new Coordinate(0, 1)))));
    }

    /**
     * Helper function that will automatically push us to the Break Period
     * with a 5 minute timer.
     */
    void displayBreakPeriod() {
        Espresso.onView(ViewMatchers.withId(R.id.startButton))
                .perform(ViewActions.click());

        ViewInteraction workTimeInput =
                Espresso.onView(ViewMatchers.withId(R.id.workPeriodInput));
        ViewInteraction breakTimeInput =
                Espresso.onView(ViewMatchers.withId(R.id.breakPeriodInput));
        ViewInteraction confirmButton =
                Espresso.onView(ViewMatchers.withId(R.id.confirmButton));

        workTimeInput.perform(ViewActions.typeText("25"));
        Espresso.closeSoftKeyboard();

        breakTimeInput.perform(ViewActions.typeText("13375"));
        Espresso.closeSoftKeyboard();

        confirmButton.perform(ViewActions.click());
    }

    /**
     * Helper function that will give us a ViewInteraction of a coordinate on the board.
     * @param c the specified coordinate of the button
     * @return a ViewInteraction to work with
     */
    ViewInteraction getButtonByCoordinate(Coordinate c) {
        return Espresso.onView(ViewMatchers
                .withTagValue(Matchers
                        .equalTo(c)));
    }
}

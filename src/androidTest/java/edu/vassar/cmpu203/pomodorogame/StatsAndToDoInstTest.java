package edu.vassar.cmpu203.pomodorogame;

import android.view.KeyEvent;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.Matchers;

import edu.vassar.cmpu203.pomodorogame.controller.ControllerActivity;

/**
 * Testing class for user statistics and to-do functionality.
 */
public class StatsAndToDoInstTest {
    /** Instance of the controller. */
    @org.junit.Rule
    public ActivityScenarioRule<ControllerActivity> activityRule
            = new ActivityScenarioRule<>(ControllerActivity.class);

    @org.junit.Test
    public void testStatsAndToDo() {
        Espresso.onView(ViewMatchers.withId(R.id.settingsButton))
                .perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.wipeStatsButton))
                .perform(ViewActions.click());

        // are all the current stats none? yes, ALL of them.
        ViewInteraction[] vis = getStatVIs();
        for (ViewInteraction vi : vis) {
            vi.check(ViewAssertions.matches(ViewMatchers.withSubstring("0")));
        }

        Espresso.onView(ViewMatchers.withId(R.id.menuButton))
                .perform(ViewActions.click());


        displayWorkPeriod();

        ViewInteraction addButton =
                Espresso.onView(ViewMatchers.withId(R.id.submitToDoButton));
        ViewInteraction toDoField =
                Espresso.onView(ViewMatchers.withId(R.id.editToDo));

        // fail case
        addButton.perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withTagValue(Matchers.equalTo(0)))
                .check(ViewAssertions.doesNotExist());

        // do these appear on the board?
        String first = "swimmy";
        toDoField.perform(ViewActions.typeText(first));
        Espresso.closeSoftKeyboard();
        addButton.perform(ViewActions.click());

        // NB: we set the matcher = 0 b/c the latest item is the first
        ViewInteraction firstToDo =
                Espresso.onView(ViewMatchers.withTagValue(Matchers.equalTo(0)));
        firstToDo.check(ViewAssertions.matches(ViewMatchers.withText(first)));

        String second = "gakster";
        clearField(first);
        toDoField.perform(ViewActions.typeText(second));
        Espresso.closeSoftKeyboard();
        addButton.perform(ViewActions.click());

        ViewInteraction secondToDo =
                Espresso.onView(ViewMatchers.withTagValue(Matchers.equalTo(0)));
        secondToDo.check(ViewAssertions.matches(ViewMatchers.withText(second)));

        String third = "plimpus";
        clearField(second);
        toDoField.perform(ViewActions.typeText(third));
        Espresso.closeSoftKeyboard();
        addButton.perform(ViewActions.click());

        ViewInteraction thirdToDo =
                Espresso.onView(ViewMatchers.withTagValue(Matchers.equalTo(0)));
        thirdToDo.check(ViewAssertions.matches(ViewMatchers.withText(third)));

        ViewInteraction finishFirst =
                Espresso.onView(ViewMatchers.withTagValue(Matchers.equalTo("Finish2")));
        finishFirst.perform(ViewActions.click());
        ViewInteraction deleteSecond =
                Espresso.onView(ViewMatchers.withTagValue(Matchers.equalTo("Delete1")));
        deleteSecond.perform(ViewActions.click());

        // check the last two inputted no longer exist!
        Espresso.onView(ViewMatchers.withTagValue(Matchers.equalTo(1)))
                .check(ViewAssertions.doesNotExist());
        Espresso.onView(ViewMatchers.withTagValue(Matchers.equalTo("Delete1")))
                .check(ViewAssertions.doesNotExist());
        Espresso.onView(ViewMatchers.withTagValue(Matchers.equalTo("Finish1")))
                .check(ViewAssertions.doesNotExist());
        Espresso.onView(ViewMatchers.withTagValue(Matchers.equalTo(2)))
                .check(ViewAssertions.doesNotExist());
        Espresso.onView(ViewMatchers.withTagValue(Matchers.equalTo("Delete2")))
                .check(ViewAssertions.doesNotExist());
        Espresso.onView(ViewMatchers.withTagValue(Matchers.equalTo("Finish2")))
                .check(ViewAssertions.doesNotExist());

        // finish up!
        Espresso.onView(ViewMatchers.withId(R.id.endButtonWork))
                .perform(ViewActions.click());

        // make sure stats are saved here
        ViewInteraction[] sessionVIs = getSessionVIs();
        int i = 0;
        for (; i < 3; i++) {
            sessionVIs[i].check(ViewAssertions.matches(ViewMatchers.withText("1")));
        }
        for (; i < sessionVIs.length; i++) {
            sessionVIs[i].check(ViewAssertions.matches(ViewMatchers.withSubstring("0")));
        }

        // exit settings and enter stats again
        Espresso.onView(ViewMatchers.withId(R.id.finishButton))
                .perform(ViewActions.click());


        // make sure cumulative settings are saved!
        Espresso.onView(ViewMatchers.withId(R.id.settingsButton))
                .perform(ViewActions.click());
        ViewInteraction[] after = getStatVIs();
        i = 0;
        for (; i < 3; i++) {
            after[i].check(ViewAssertions.matches(ViewMatchers.withSubstring("1")));
        }
        for (; i < sessionVIs.length; i++) {
            after[i].check(ViewAssertions.matches(ViewMatchers.withSubstring("0")));
        }
    }

    /**
     * Helper function that will display the work period with a 25 minute
     * timer, and also clear stats data.
     */
    void displayWorkPeriod() {
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

        breakTimeInput.perform(ViewActions.typeText("5"));
        Espresso.closeSoftKeyboard();

        confirmButton.perform(ViewActions.click());
    }

    /**
     * Helper function that returns all of the ViewInteractions of the
     * total statistics.
     */
    ViewInteraction[] getStatVIs() {
        return new ViewInteraction[] {
                Espresso.onView(ViewMatchers.withId(R.id.completeToDoText)),
                Espresso.onView(ViewMatchers.withId(R.id.deleteToDoText)),
                Espresso.onView(ViewMatchers.withId(R.id.remainToDoText)),
                Espresso.onView(ViewMatchers.withId(R.id.completeBPText)),
                Espresso.onView(ViewMatchers.withId(R.id.timeBPText)),
                Espresso.onView(ViewMatchers.withId(R.id.completeWPText)),
                Espresso.onView(ViewMatchers.withId(R.id.timeWPText))
        };
    }

    /**
     * Helper function that returns all the ViewInteractions of the
     * session statistics. First 3 are the to do values.
     */
    ViewInteraction[] getSessionVIs() {
        return new ViewInteraction[] {
                Espresso.onView(ViewMatchers.withId(R.id.remainingToDosValue)),
                Espresso.onView(ViewMatchers.withId(R.id.completedToDosValue)),
                Espresso.onView(ViewMatchers.withId(R.id.deletedToDosValue)),
                Espresso.onView(ViewMatchers.withId(R.id.totalBreakTimeValue)),
                Espresso.onView(ViewMatchers.withId(R.id.totalWorkTimeValue)),
                Espresso.onView(ViewMatchers.withId(R.id.numberOfBreakPeriods)),
                Espresso.onView(ViewMatchers.withId(R.id.numberOfWorkPeriods))
        };
    }

    /**
     * Helper function that clears the EditText field.
     */
    void clearField(String s) {
        for (char ignored : s.toCharArray())
            Espresso.onView(ViewMatchers.withId(R.id.editToDo))
                    .perform(ViewActions.pressKey(KeyEvent.KEYCODE_DEL));
    }
}

package edu.gatech.seclass.jobcompare6300.ui;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import edu.gatech.seclass.jobcompare6300.R;

@RunWith(AndroidJUnit4.class)
public class AdjustActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAdjustSettings() {

        // Main Initial User, so must start it first
        onView(withId(R.id.adjSettingsBtn)).perform(click());

        // Replace text in input fields instead of adding to existing text
        onView(withId(R.id.salaryText)).perform(clearText(), replaceText("1"), closeSoftKeyboard());
        onView(withId(R.id.bonusText)).perform(clearText(), replaceText("2"), closeSoftKeyboard());
        onView(withId(R.id.optionText)).perform(clearText(), replaceText("3"), closeSoftKeyboard());
        onView(withId(R.id.hbpText)).perform(clearText(), replaceText("1"), closeSoftKeyboard());
        onView(withId(R.id.holidayText)).perform(clearText(), replaceText("2"), closeSoftKeyboard());
        onView(withId(R.id.stipendText)).perform(clearText(), replaceText("3"), closeSoftKeyboard());

        onView(withId(R.id.saveBtn)).perform(click());
        onView(withText("Yes")).perform(click());
        // Add assertion to verify navigation
        onView(withId(R.id.currentBtn)).check(matches(isDisplayed()));

        // Main Initial User, so must start it first
        onView(withId(R.id.adjSettingsBtn)).perform(click());
        // Click Cancel Button
        onView(withId(R.id.saveBtn)).perform(click());
        onView(withText("No")).perform(click());
        // Add assertion to verify navigation
        onView(withId(R.id.saveBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void testCancelAdjustSettings() {

        // Main Initial User, so must start it first
        onView(withId(R.id.adjSettingsBtn)).perform(click());
        // Click Cancel Button
        onView(withId(R.id.cancelBtn)).perform(click());
        onView(withText("Yes")).perform(click());
        // Add assertion to verify navigation
        onView(withId(R.id.currentBtn)).check(matches(isDisplayed()));

        // Main Initial User, so must start it first
        onView(withId(R.id.adjSettingsBtn)).perform(click());
        // Click Cancel Button
        onView(withId(R.id.cancelBtn)).perform(click());
        onView(withText("No")).perform(click());
        // Add assertion to verify navigation
        onView(withId(R.id.cancelBtn)).check(matches(isDisplayed()));

    }

}


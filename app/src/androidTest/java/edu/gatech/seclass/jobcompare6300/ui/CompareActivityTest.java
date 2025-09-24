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
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.is;

import edu.gatech.seclass.jobcompare6300.R;

@RunWith(AndroidJUnit4.class)
public class CompareActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testCompareButtonPopulatesTable() {

        // Main Initial User, so must start it first
        onView(withId(R.id.offerBtn)).perform(click());

        onView(withId(R.id.titleText)).perform(clearText(), replaceText("Junior Developer"), closeSoftKeyboard());
        onView(withId(R.id.companyText)).perform(clearText(), replaceText("Tech Innovations Inc."), closeSoftKeyboard());
        onView(withId(R.id.cityText)).perform(clearText(), replaceText("Atlanta"), closeSoftKeyboard());
        onView(withId(R.id.stateText)).perform(clearText(), replaceText("GA"), closeSoftKeyboard());
        onView(withId(R.id.costText)).perform(clearText(), replaceText("90"), closeSoftKeyboard());
        onView(withId(R.id.salaryText)).perform(clearText(), replaceText("60000"), closeSoftKeyboard());
        onView(withId(R.id.bonusText)).perform(clearText(), replaceText("3000"), closeSoftKeyboard());
        onView(withId(R.id.optionText)).perform(clearText(), replaceText("200"), closeSoftKeyboard());
        onView(withId(R.id.hbpText)).perform(clearText(), replaceText("0.1"), closeSoftKeyboard());
        onView(withId(R.id.holidayText)).perform(clearText(), replaceText("10"), closeSoftKeyboard());
        onView(withId(R.id.stipendText)).perform(clearText(), replaceText("50"), closeSoftKeyboard());

        onView(withId(R.id.addBtn)).perform(click());
        onView(withText("Yes")).perform(click());
        // Add assertion to verify navigation
        onView(withId(R.id.addMoreBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.backToMainBtn)).perform(click());

        // Main Initial User, so must start it first
        onView(withId(R.id.offerBtn)).perform(click());

        onView(withId(R.id.titleText)).perform(clearText(), replaceText("Senior Developer"), closeSoftKeyboard());
        onView(withId(R.id.companyText)).perform(clearText(), replaceText("Tech Innovations Inc."), closeSoftKeyboard());
        onView(withId(R.id.cityText)).perform(clearText(), replaceText("Atlanta"), closeSoftKeyboard());
        onView(withId(R.id.stateText)).perform(clearText(), replaceText("GA"), closeSoftKeyboard());
        onView(withId(R.id.costText)).perform(clearText(), replaceText("120"), closeSoftKeyboard());
        onView(withId(R.id.salaryText)).perform(clearText(), replaceText("100000"), closeSoftKeyboard());
        onView(withId(R.id.bonusText)).perform(clearText(), replaceText("5000"), closeSoftKeyboard());
        onView(withId(R.id.optionText)).perform(clearText(), replaceText("200"), closeSoftKeyboard());
        onView(withId(R.id.hbpText)).perform(clearText(), replaceText("0.1"), closeSoftKeyboard());
        onView(withId(R.id.holidayText)).perform(clearText(), replaceText("15"), closeSoftKeyboard());
        onView(withId(R.id.stipendText)).perform(clearText(), replaceText("50"), closeSoftKeyboard());

        onView(withId(R.id.addBtn)).perform(click());
        onView(withText("Yes")).perform(click());
        // Add assertion to verify navigation
        onView(withId(R.id.addMoreBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.backToMainBtn)).perform(click());

        // Navigate to CompareActivity
        onView(withId(R.id.compareBtn)).perform(click());
        // Simulate clicking on two checkboxes based on their position
        // For demonstration, it clicks on the first and second checkboxes assuming they are accessible
        onView(withTagValue(is((Object)("CheckBox_0")))).perform(click());;
        onView(withTagValue(is((Object)("CheckBox_1")))).perform(click());

        onView(withId(R.id.compareBtn)).perform(click());
        onView(withText("Yes")).perform(click());

        // Add assertion to verify navigation
        onView(withId(R.id.backToMainBtn)).perform(click());
        onView(withId(R.id.currentBtn)).check(matches(isDisplayed()));

        onView(withId(R.id.compareBtn)).perform(click());
        onView(withId(R.id.compareBtn)).perform(click());
        onView(withText("No")).perform(click());
        onView(withId(R.id.compareBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void testCancelButtonReturnsToPreviousScreen() {
        // Main Initial User, so must start it first
        onView(withId(R.id.compareBtn)).perform(click());
        // Click Cancel Button
        onView(withId(R.id.cancelBtn)).perform(click());
        onView(withText("Yes")).perform(click());
        // Add assertion to verify navigation
        onView(withId(R.id.currentBtn)).check(matches(isDisplayed()));

        // Main Initial User, so must start it first
        onView(withId(R.id.compareBtn)).perform(click());
        // Click Cancel Button
        onView(withId(R.id.cancelBtn)).perform(click());
        onView(withText("No")).perform(click());
        // Add assertion to verify navigation
        onView(withId(R.id.compareBtn)).check(matches(isDisplayed()));
    }
}

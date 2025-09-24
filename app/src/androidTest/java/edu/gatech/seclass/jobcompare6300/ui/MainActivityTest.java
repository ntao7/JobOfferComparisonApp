package edu.gatech.seclass.jobcompare6300.ui;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;

import edu.gatech.seclass.jobcompare6300.R;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void navigateToCurrentJobDetails() {
        onView(withId(R.id.currentBtn)).perform(click());
        // Add assertion to verify navigation
        onView(withId(R.id.saveBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void navigateToJobOffers() {
        onView(withId(R.id.offerBtn)).perform(click());
        // Add assertion to verify navigation
        onView(withId(R.id.addBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void navigateToAdjustComparisonSettings() {
        onView(withId(R.id.adjSettingsBtn)).perform(click());
        // Add assertion to verify navigation
        onView(withId(R.id.saveBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void navigateToCompareJobOffers() {
        onView(withId(R.id.compareBtn)).perform(click());
        // Add assertion to verify navigation
        onView(withId(R.id.compareBtn)).check(matches(isDisplayed()));
    }
}


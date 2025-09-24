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
public class JobOfferActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAddJobOfferDetails() {
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

        // Main Inits User, so must start it first
        onView(withId(R.id.addMoreBtn)).perform(click());

        // Assuming IDs for EditText fields and "Add" button in your activity_job_offer.xml
        onView(withId(R.id.titleText)).perform(clearText(), replaceText("Lead UI/UX Designer"), closeSoftKeyboard());
        onView(withId(R.id.companyText)).perform(clearText(), replaceText("Creative Solutions"), closeSoftKeyboard());
        onView(withId(R.id.cityText)).perform(clearText(), replaceText("Columbus"), closeSoftKeyboard());
        onView(withId(R.id.stateText)).perform(clearText(), replaceText("OH"), closeSoftKeyboard());
        onView(withId(R.id.costText)).perform(clearText(), replaceText("130"), closeSoftKeyboard());
        onView(withId(R.id.salaryText)).perform(clearText(), replaceText("120000"), closeSoftKeyboard());
        onView(withId(R.id.bonusText)).perform(clearText(), replaceText("10000"), closeSoftKeyboard());
        onView(withId(R.id.optionText)).perform(clearText(), replaceText("300"), closeSoftKeyboard());
        onView(withId(R.id.hbpText)).perform(clearText(), replaceText("0.2"), closeSoftKeyboard());
        onView(withId(R.id.holidayText)).perform(clearText(), replaceText("20"), closeSoftKeyboard());
        onView(withId(R.id.stipendText)).perform(clearText(), replaceText("70"), closeSoftKeyboard());

        onView(withId(R.id.addBtn)).perform(click());
        onView(withText("No")).perform(click());
        // Add assertion to verify navigation
        onView(withId(R.id.addBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void testCancelAddingJobOfferDetails() {
        // Main Initial User, so must start it first
        onView(withId(R.id.offerBtn)).perform(click());
        onView(withId(R.id.cancelBtn)).perform(click());
        onView(withText("Yes")).perform(click());
        // Add assertion to verify navigation
        onView(withId(R.id.currentBtn)).check(matches(isDisplayed()));

        // Main Initial User, so must start it first
        onView(withId(R.id.offerBtn)).perform(click());
        onView(withId(R.id.cancelBtn)).perform(click());
        onView(withText("No")).perform(click());
        // Add assertion to verify navigation
        onView(withId(R.id.cancelBtn)).check(matches(isDisplayed()));
    }

}


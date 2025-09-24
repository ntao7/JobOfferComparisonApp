package edu.gatech.seclass.jobcompare6300.ui;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import static org.hamcrest.Matchers.is;

import edu.gatech.seclass.jobcompare6300.R;

@RunWith(AndroidJUnit4.class)
public class CompareTableActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testBackToMainMenu() {
        // Main Initial User, so must start it first
        onView(withId(R.id.currentBtn)).perform(click());

        onView(withId(R.id.titleText)).perform(clearText(), replaceText("Software Engineer"), closeSoftKeyboard());
        onView(withId(R.id.companyText)).perform(clearText(), replaceText("Tech Company"), closeSoftKeyboard());
        onView(withId(R.id.cityText)).perform(clearText(), replaceText("New York"), closeSoftKeyboard());
        onView(withId(R.id.stateText)).perform(clearText(), replaceText("NY"), closeSoftKeyboard());
        onView(withId(R.id.costText)).perform(clearText(), replaceText("100"), closeSoftKeyboard());
        onView(withId(R.id.salaryText)).perform(clearText(), replaceText("70000"), closeSoftKeyboard());
        onView(withId(R.id.bonusText)).perform(clearText(), replaceText("5000"), closeSoftKeyboard());
        onView(withId(R.id.optionText)).perform(clearText(), replaceText("50"), closeSoftKeyboard());
        onView(withId(R.id.hbpText)).perform(clearText(), replaceText("0.1"), closeSoftKeyboard());
        onView(withId(R.id.holidayText)).perform(clearText(), replaceText("20"), closeSoftKeyboard());
        onView(withId(R.id.stipendText)).perform(clearText(), replaceText("50"), closeSoftKeyboard());

        onView(withId(R.id.saveBtn)).perform(click());
        onView(withText("Yes")).perform(click());
        // Add assertion to verify navigation
        onView(withId(R.id.currentBtn)).check(matches(isDisplayed()));

        // Main Initial User, so must start it first
        onView(withId(R.id.offerBtn)).perform(click());

        // Assuming IDs for EditText fields and "Add" button in your activity_job_offer.xml
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
        onView(withId(R.id.compareBtn)).perform(click());
        // Add assertion to verify navigation
        onView(withId(R.id.backToMainBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.backToMainBtn)).perform(click());
        // Add assertion to verify navigation
        onView(withId(R.id.currentBtn)).check(matches(isDisplayed()));

    }

    @Test  // This button is not show
    public void testCompareOthers() {
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

        // Main Initial User, so must start it first
        onView(withId(R.id.offerBtn)).perform(click());

        onView(withId(R.id.titleText)).perform(clearText(), replaceText("Lead Mobile Developer"), closeSoftKeyboard());
        onView(withId(R.id.companyText)).perform(clearText(), replaceText("Future Tech Solutions LLC"), closeSoftKeyboard());
        onView(withId(R.id.cityText)).perform(clearText(), replaceText("San Jose"), closeSoftKeyboard());
        onView(withId(R.id.stateText)).perform(clearText(), replaceText("CA"), closeSoftKeyboard());
        onView(withId(R.id.costText)).perform(clearText(), replaceText("150"), closeSoftKeyboard());
        onView(withId(R.id.salaryText)).perform(clearText(), replaceText("210000"), closeSoftKeyboard());
        onView(withId(R.id.bonusText)).perform(clearText(), replaceText("10000"), closeSoftKeyboard());
        onView(withId(R.id.optionText)).perform(clearText(), replaceText("300"), closeSoftKeyboard());
        onView(withId(R.id.hbpText)).perform(clearText(), replaceText("0.12"), closeSoftKeyboard());
        onView(withId(R.id.holidayText)).perform(clearText(), replaceText("20"), closeSoftKeyboard());
        onView(withId(R.id.stipendText)).perform(clearText(), replaceText("70"), closeSoftKeyboard());

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
        onView(withId(R.id.compareBtn)).perform(click());

        // Navigate to CompareActivity - click compare others button
        onView(withId(R.id.compareBtn)).check(matches(isDisplayed()));

        // For demonstration, it clicks on the second and third checkboxes assuming they are accessible
        onView(withTagValue(is((Object)("CheckBox_1")))).perform(click());;
        onView(withTagValue(is((Object)("CheckBox_2")))).perform(click());

        onView(withId(R.id.compareBtn)).perform(click());
        onView(withText("Yes")).perform(click());

        // Add assertion to verify navigation
        onView(withId(R.id.compareBtn)).perform(click());
    }
}


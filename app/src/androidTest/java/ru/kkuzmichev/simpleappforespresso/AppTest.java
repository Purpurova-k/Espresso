package ru.kkuzmichev.simpleappforespresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.core.AllOf.allOf;

import android.content.Intent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AppTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkMainText() {
        ViewInteraction mainText = onView(
                withId(R.id.text_home)
        );
        mainText.check(
                matches(
                        withText("This is home fragment")
                )
        );
    }


    @Test
    public void intentTest() {
        ViewInteraction settingsButton = onView(withContentDescription("More options"));
        ViewInteraction settingsTextView = onView(withId(R.id.title));

        settingsButton.check(matches(isDisplayed()));
        settingsButton.perform(click());

        Intents.init();
        settingsTextView.perform(click());
        intended(allOf(
                hasData("https://google.com"),
                hasAction(Intent.ACTION_VIEW)
        ));
        Intents.release();
    }
}

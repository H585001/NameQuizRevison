package com.example.namequizapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import androidx.lifecycle.ViewModelProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.example.namequizapp.viewmodels.QuizActivityViewModel;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.inject.Inject;

@RunWith(AndroidJUnit4.class)
public class QuizActivityTest {

    @Inject
    private QuizActivityViewModel viewModel;

    @Before
    public void setup() {
        activityScenarioRule.getScenario().onActivity(activity -> {
           viewModel = new ViewModelProvider(activity).get(QuizActivityViewModel.class);
        });
    }

    @Rule
    public ActivityScenarioRule<QuizActivity> activityScenarioRule = new ActivityScenarioRule<>(QuizActivity.class);


    @Test
    public void test_increaseScore() {
        viewModel.initQuestion();
        int correct = viewModel.getCorrectAnswer();
        switch (correct) {
            case 0:
                onView(withId(R.id.option1)).perform(click());
                break;
            case 1:
                onView(withId(R.id.option2)).perform(click());
                break;
            case 2:
                onView(withId(R.id.option3)).perform(click());
                break;
        }
        onView(withText("1 / 1")).check(matches(isDisplayed()));
    }
}

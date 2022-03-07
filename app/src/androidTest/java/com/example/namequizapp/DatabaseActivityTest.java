package com.example.namequizapp;

import static android.app.Activity.RESULT_OK;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.junit.Assert.assertTrue;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;

import androidx.room.Database;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.namequizapp.database.PersonDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DatabaseActivityTest {

    @Rule
    public ActivityScenarioRule<DatabaseActivity> activityScenarioRule = new ActivityScenarioRule<>(DatabaseActivity.class);

    @Before
    public void setup() {
        Intents.init();
    }
    @After
    public void after() {
        Intents.release();
    }

    private Instrumentation.ActivityResult galleryPickStub() {
        Resources resources = ApplicationProvider.getApplicationContext().getResources();
        Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(R.drawable.ic_launcher_background) + "/" +
                resources.getResourceTypeName(R.drawable.ic_launcher_background) + "/" +
                resources.getResourceEntryName(R.drawable.ic_launcher_background)
        );
        Intent resultIntent = new Intent();
        resultIntent.setData(imageUri);
        return new Instrumentation.ActivityResult(RESULT_OK, resultIntent);
    }

    @Test
    public void test_addUser() {

        PersonDatabase db = PersonDatabase.getDbInstance(ApplicationProvider.getApplicationContext());
        intending(hasAction(Intent.ACTION_OPEN_DOCUMENT)).respondWith(galleryPickStub());
        onView(withId(R.id.addStudent)).perform(click());
        onView(withId(R.id.addImage)).perform(click());
        onView(withId(R.id.male)).perform(click());
        onView(withId(R.id.studentText)).perform(typeText("Mark Zuckerberg"));
        onView(withId(R.id.addPersonButton)).perform(click());

        assertTrue(db.personDao().getPersonByName("Mark Zuckerberg") != null);
    }

}

package com.amespressotestactivities

import android.content.Intent
import androidx.core.os.bundleOf
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amespressotestactivities.simple.EndActivity
import com.amespressotestactivities.simple.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EndActivityTests {

    companion object {
        // Name Constant
        private const val NAME = "Antoine"
    }


    private var intent =
        Intent(ApplicationProvider.getApplicationContext(), EndActivity::class.java).putExtras(
            bundleOf("name" to NAME)
        )

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule<EndActivity>(intent)

    @Before
    fun setupTest() {
        Intents.init()
    }


    @After
    fun cleanUp() {
        Intents.release()
    }


    // Check if name from previous activity is displayed : hello [name]!
    @Test
    fun nameDisplayedTest() {
        Espresso.onView(ViewMatchers.withId(R.id.helloName))
            .check(matches(withText("Hello $NAME!")))
    }
}
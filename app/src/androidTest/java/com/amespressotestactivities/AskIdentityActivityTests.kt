package com.amespressotestactivities

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.amespressotestactivities.simple.AskIdentityActivity
import com.amespressotestactivities.simple.EndActivity
import com.amespressotestactivities.simple.R
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AskIdentityActivityTests {

    companion object {
        private const val NAME = "Antoine"
    }

    // IntentsTestRule to check start of a new Activity
    @get:Rule
    val intentsTestRule = ActivityScenarioRule(AskIdentityActivity::class.java)


    @Before
    fun setupTest() {
        Intents.init()
    }

    @After
    fun cleanUp() {
        Intents.release()
    }


    // Check if name editText is empty and button is not enabled at start
    @Test
    fun emptyNameAtStartTest() {
        Espresso.onView(ViewMatchers.withId(R.id.enterNameEditText)).check(matches(ViewMatchers.withText("")))
        checkContinueButtonEnabled(false)
    }

    // Check if button is not enabled if name editText is empty
    @Test
    fun emptyNameTest() {
        fillName("")
        checkContinueButtonEnabled(false)
    }

    // Check if button is not enabled if name editText contains only spaces
    @Test
    fun spacesNameTest() {
        fillName(" ")
        checkContinueButtonEnabled(false)
    }

    // Check if button is enabled if name editText contains correct name
    @Test
    fun correctNameTest() {
        fillName(NAME)
        checkContinueButtonEnabled(true)
    }

    // Check if button is enabled if name editText contains correct name after containing only spaces
    @Test
    fun correctNameAfterSpacesNameTest() {
        fillName(" ")
        fillName(NAME)
        checkContinueButtonEnabled(true)
    }

    // Check if button is not enabled if name editText is empty after containing correct name
    @Test
    fun emptyNameAfterCorrectNameTest() {
        fillName(NAME)
        fillName("")
        checkContinueButtonEnabled(false)
    }

    // Check if button is not enabled if name editText contains only spaces after containing correct name
    @Test
    fun spacesNameAfterCorrectNameTest() {
        fillName(NAME)
        fillName(" ")
        checkContinueButtonEnabled(false)
    }

    // Check start of EndActivity after click on Continue button
    @Test
    fun navigationToEndActivityTest() {
        fillName(NAME)
        Espresso.onView(ViewMatchers.withId( R.id.continueButton)).perform(click())
        Intents.intended(IntentMatchers.hasComponent(EndActivity::class.java.name))
    }

    // Fill Name edittext with text
    private fun fillName(text: String) {
        Espresso.onView(ViewMatchers.withId(R.id.enterNameEditText)).perform(clearText(), typeText(text), pressImeActionButton())
    }

    // Check if continue Button is enabled or not
    private fun checkContinueButtonEnabled(enabled: Boolean) {
        Espresso.onView(ViewMatchers.withId( R.id.continueButton)).check(matches(if (enabled) isEnabled() else not(isEnabled())))
    }
}
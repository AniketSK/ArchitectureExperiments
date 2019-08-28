package com.aniketkadam.temperatureapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun loading_is_shown_when_the_app_launches() {
        onView(withContentDescription(R.string.loading_content_description)).check(
            matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun on_success_after_loading_the_temperature_is_shown() {
        onView(withText("Mumbai")).check(matches(isDisplayed()))
    }
}
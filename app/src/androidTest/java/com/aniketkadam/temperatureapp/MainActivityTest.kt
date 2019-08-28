package com.aniketkadam.temperatureapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import io.appflate.restmock.RESTMockServer
import io.appflate.restmock.utils.RequestMatchers.pathEndsWithIgnoringQueryParams
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java, true, false)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        RESTMockServer.reset()
    }

    @Test
    fun loading_is_shown_when_the_app_launches() {
        activityTestRule.launchActivity(null)
        onView(withContentDescription(R.string.loading_content_description)).check(
            matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun on_success_after_loading_the_city_is_shown() {
        RESTMockServer.whenGET(pathEndsWithIgnoringQueryParams("ip.json"))
            .thenReturnFile(200, "mocks/api_response_for_ip_location.json")
        RESTMockServer.whenGET(pathEndsWithIgnoringQueryParams("current.json"))
            .thenReturnFile(200, "mocks/api_response_for_current.json")

        activityTestRule.launchActivity(null)
        onView(withText("Mumbai")).check(matches(isDisplayed()))
    }

    @Test
    fun on_success_after_loading_the_temperature_is_shown() {
        RESTMockServer.whenGET(pathEndsWithIgnoringQueryParams("ip.json"))
            .thenReturnFile(200, "mocks/api_response_for_ip_location.json")
        RESTMockServer.whenGET(pathEndsWithIgnoringQueryParams("current.json"))
            .thenReturnFile(200, "mocks/api_response_for_current.json")

        activityTestRule.launchActivity(null)
        onView(withText(" 30°")).check(matches(isDisplayed()))
    }

    @Test
    fun on_error_the_error_screen_is_shown() {
        RESTMockServer.whenGET(pathEndsWithIgnoringQueryParams("ip.json"))
            .thenReturnEmpty(400)

        activityTestRule.launchActivity(null)
        onView(withId(R.id.errorText)).check(matches(isDisplayed()))
    }
}
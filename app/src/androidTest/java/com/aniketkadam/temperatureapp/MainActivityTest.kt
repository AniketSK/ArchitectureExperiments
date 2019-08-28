package com.aniketkadam.temperatureapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.jakewharton.espresso.OkHttp3IdlingResource
import io.appflate.restmock.RESTMockServer
import io.appflate.restmock.utils.RequestMatchers.pathEndsWithIgnoringQueryParams
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java, true, false)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private var httpIdlingResource: OkHttp3IdlingResource? = null

    @Before
    fun setup() {
        RESTMockServer.reset()
        httpIdlingResource =
            (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as TestApplication).appInjector.provideOkHttp()
                .let { (OkHttp3IdlingResource.create("OkHttp", it)) }
        IdlingRegistry.getInstance().register(httpIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(httpIdlingResource)
    }

    @Test
    fun loading_is_shown_when_the_app_launches() {

        RESTMockServer.whenGET(pathEndsWithIgnoringQueryParams("ip.json"))
            .delay(TimeUnit.SECONDS, 5)
            .thenReturnFile(200, "mocks/api_response_for_ip_location.json")

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
        onView(withText("30°")).check(matches(isDisplayed()))
    }

    @Test
    fun on_error_the_error_screen_is_shown() {
        RESTMockServer.whenGET(pathEndsWithIgnoringQueryParams("ip.json"))
            .thenReturnEmpty(400)

        activityTestRule.launchActivity(null)
        onView(withId(R.id.errorText)).check(matches(isDisplayed()))
    }


    @Test
    fun when_retry_is_clicked_it_goes_back_to_loading() {
        RESTMockServer.whenGET(pathEndsWithIgnoringQueryParams("ip.json"))
            .thenReturnEmpty(400)
        activityTestRule.launchActivity(null)
        onView(withId(R.id.errorText)).check(matches(isDisplayed()))
        RESTMockServer.reset()

        IdlingRegistry.getInstance().unregister(httpIdlingResource)
        RESTMockServer.whenGET(pathEndsWithIgnoringQueryParams("ip.json"))
            .delay(TimeUnit.SECONDS, 2000)
            .thenReturnEmpty(400)

        onView(withId(R.id.retryButton)).perform(ViewActions.click())
        onView(withContentDescription(R.string.loading_content_description)).check(
            matches(
                isDisplayed()
            )
        )
    }
}
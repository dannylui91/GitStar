package com.example.danny.gitstar

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.matcher.ViewMatchers.Visibility.GONE
import android.support.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.danny.gitstar.data.GithubService
import com.example.danny.gitstar.di.component.DaggerTestComponent
import com.example.danny.gitstar.di.module.ApiModuleMock
import com.example.danny.gitstar.ui.search.SearchActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import rx.Observable
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class SearchActivityTest {

    @Rule
    @JvmField
    val rule: ActivityTestRule<SearchActivity> = ActivityTestRule(SearchActivity::class.java, false, false)

    @Inject
    lateinit var githubService: GithubService

    @Before
    fun setup() {
        val app = App.get(context = InstrumentationRegistry.getTargetContext())
        val testComponent = DaggerTestComponent.builder()
                .apiModule(ApiModuleMock())
                .build()
        testComponent.inject(this)
        app.setInjector(testComponent)
    }

    @Test
    fun testListSuccess() {
        val repoList = TestDataFactory.makeRepoList(5)
        `when`(githubService.getRepos(anyString())).thenReturn(Observable.just(repoList))
        rule.launchActivity(Intent())

        typeBlah()
        onView(withId(R.id.repoRv)).check(matches(withEffectiveVisibility(VISIBLE)))
        onView(withId(R.id.errorLayout)).check(matches(withEffectiveVisibility(GONE)))
        onView(withId(R.id.repoRv)).check(matches(Matchers.withListSize(3))) // List should have only 3 items
    }

    @Test
    fun testListEmpty() {
        val repoList = TestDataFactory.makeRepoList(0)
        `when`(githubService.getRepos(anyString())).thenReturn(Observable.just(repoList))
        rule.launchActivity(Intent())

        typeBlah()
        onView(withId(R.id.repoRv)).check(matches(withEffectiveVisibility(GONE)))
        onView(withId(R.id.errorLayout)).check(matches(withEffectiveVisibility(VISIBLE)))
        onView(withText(R.string.empty_text)).check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun testListError() {
        `when`(githubService.getRepos(anyString())).thenReturn(Observable.error(Exception()))
        rule.launchActivity(Intent())

        typeBlah()
        onView(withId(R.id.repoRv)).check(matches(withEffectiveVisibility(GONE)))
        onView(withId(R.id.errorLayout)).check(matches(withEffectiveVisibility(VISIBLE)))
        onView(withText(R.string.error_text)).check(matches(isCompletelyDisplayed()))
    }

    private fun typeBlah() {
        onView(withId(R.id.queryEt)).perform(click())
        onView(withId(R.id.queryEt)).perform(typeText("Blah"))
        onView(withId(R.id.queryEt)).perform(pressImeActionButton())
    }
}
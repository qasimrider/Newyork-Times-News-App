package com.nytimes.newsapp.news.ui

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nytimes.newsapp.dtos.news.DummyNews
import com.nytimes.newsapp.news.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class NewsDetailFragmentTest{

    @Test
    fun test_isNewsDetailsVisible(){
        val news = DummyNews.PEOPLE_KILLED
        val bundle = bundleOf("news" to news)

        val scenario = launchFragmentInContainer<NewsDetailFragment>(fragmentArgs = bundle)
        onView(withId(R.id.newsTitle)).check(matches(withText(news.title)))
//        scenario.moveToState(Lifecycle.State.RESUMED)
    }
}
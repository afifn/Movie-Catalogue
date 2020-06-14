package com.kuycoding.jetpack.ui.tvshow.detail;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.kuycoding.jetpack.R;
import com.kuycoding.jetpack.data.source.local.entity.TvEntity;
import com.kuycoding.jetpack.utils.EspressoIdlingResource;
import com.kuycoding.jetpack.utils.FakeDataDummy;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class DetailTvShowActivityTest {
    private TvEntity dummyEntity = FakeDataDummy.generateDummyTvShow().get(0);

    @Rule
    public ActivityTestRule<DetailTvShowActivity> activityRule = new ActivityTestRule<DetailTvShowActivity>(DetailTvShowActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent results = new Intent(context, DetailTvShowActivity.class);
            results.putExtra(DetailTvShowActivity.EXTRA_TV, dummyEntity.getId());
            return results;
        }
    };

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadTv() {
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()));
    }
}
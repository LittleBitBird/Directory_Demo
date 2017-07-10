package com.example.guanhuawu.directory_demo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Created by guanhua.wu on 2017/7/10.
 */
@RunWith(AndroidJUnit4.class)
public class DirectoryTest {


    @Rule
    public ActivityTestRule<Add_Contacts> rule = new ActivityTestRule<>(Add_Contacts.class);

    @Test
    public void search_Contacts() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.example.guanhuawu.directory_demo", appContext.getPackageName());
        onView(withId(R.id.Back))
        .check(matches(withText("取消")));
    }


}
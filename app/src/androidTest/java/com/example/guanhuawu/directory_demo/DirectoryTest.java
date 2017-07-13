package com.example.guanhuawu.directory_demo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.guanhuawu.directory_demo.adpater.DataBaseHelper;
import com.example.guanhuawu.directory_demo.dao.ContactPersonDao;
import com.example.guanhuawu.directory_demo.helper.MapContactHelper;
import com.example.guanhuawu.directory_demo.persist.ContactPerson;
import com.j256.ormlite.dao.Dao;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    static DataBaseHelper dataBaseHelper;
    static Context appContext;
    static ContactPersonDao contactPersonDao;
    static List<ContactPerson> personList = new ArrayList<>();
    static MapContactHelper mapDemo;
    static Dao Basedao;

    @BeforeClass
    public static void beforeclass() throws SQLException {
        appContext = InstrumentationRegistry.getTargetContext();
        dataBaseHelper = new DataBaseHelper(appContext);
        contactPersonDao = new ContactPersonDao(appContext);
        personList = contactPersonDao.getContactDaoOpen().queryForAll();
        mapDemo = new MapContactHelper();
//        Basedao = contactPersonDao.getDao();
    }


    @Rule
    public ActivityTestRule<AddContacts> rule = new ActivityTestRule<>(AddContacts.class);

    @Test
    public void searchContacts() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.example.guanhuawu.directory_demo", appContext.getPackageName());
        onView(withId(R.id.tvBack))
                .check(matches(withText("取消")));
    }

    @Test
    public void testMap() {
    }

    @Test
    public void testconvertFromListToMap() {
        Map<String, List> contactMap = mapDemo.convertFromListToMap(personList);
        String[] Mapkey = mapDemo.MAP_KEY;
        for (int i = 0; i < Mapkey.length; i++) {
            if (contactMap.containsKey(Mapkey[i])) {
                List<ContactPerson> personList = contactMap.get(Mapkey[i]);
                for (ContactPerson person:personList
                     ) {
                    Log.e("query", person.getSurName());
                }
            }
        }
    }

    @Test
    public void testgetPersonNumber() {
        Map<String, List> contactMap = mapDemo.convertFromListToMap(personList);
        Log.e("num", mapDemo.getPersonNumber(contactMap) + "");
    }

    @Test
    public void testfindPersonByPosition() {
        Map<String, List> contactMap = mapDemo.convertFromListToMap(personList);
        for(int i = 0 ; i < mapDemo.getPersonNumber(contactMap);i++)
        Log.e("tag", "testfindPersonByPosition: "+mapDemo.findPersonByPosition(contactMap,i).getSurName());
//        List<ContactPerson> personList = contactMap.get("w");
//        personList = mapDemo.sortPersonList(personList);
//        for (ContactPerson person : personList
//                ) {
//            Log.e("sort", person.getSurName());
//        }
    }

}
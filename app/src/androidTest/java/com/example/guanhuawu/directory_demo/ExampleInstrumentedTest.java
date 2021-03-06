package com.example.guanhuawu.directory_demo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.guanhuawu.directory_demo.dao.ContactPersonDao;
import com.example.guanhuawu.directory_demo.helper.Concert;
import com.example.guanhuawu.directory_demo.persist.ContactPerson;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Log.e("test","UseAppContext");
        Context appContext = InstrumentationRegistry.getTargetContext();

        ContactPersonDao dao = new ContactPersonDao(appContext);
        List<ContactPerson> personList = dao.getOrderBySurname();
        String keyWords="d";
        for (ContactPerson person:personList
             ) {
            String name = person.getSurName()+person.getFirstName();
            name = name.substring(1);
            String namePinYin = Concert.getPingYin(name);
            if(namePinYin.startsWith(keyWords))
            Log.e("PinYin",namePinYin);
        }
        System.out.println(personList.size());

//        String s = "^[a]\\w+";
//        Log.e("matches",":"+"asdasd".matches(s));

        assertEquals("com.example.guanhuawu.directory_demo", appContext.getPackageName());
    }
}

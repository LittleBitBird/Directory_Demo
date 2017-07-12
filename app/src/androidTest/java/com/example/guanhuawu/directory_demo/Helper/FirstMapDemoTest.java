package com.example.guanhuawu.directory_demo.Helper;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.example.guanhuawu.directory_demo.DAO.ContactPersonDao;
import com.example.guanhuawu.directory_demo.persist.ContactPerson;

import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guanhua.wu on 2017/7/12.
 */
public class FirstMapDemoTest {
    static Context appContext;
    static ContactPersonDao contactPersonDao;
    static List<ContactPerson> personList = new ArrayList<>();
    static FirstMapDemo mapDemo;

    @BeforeClass
    public static void beforeclass() throws SQLException {
        appContext = InstrumentationRegistry.getTargetContext();
        contactPersonDao = new ContactPersonDao(appContext);
        personList = contactPersonDao.getContactDaoOpen().queryForAll();
        mapDemo = new FirstMapDemo();
    }

    @Test
    public void findPersonByPosition() throws Exception {

    }

    @Test
    public void getPersonNumber() throws Exception {

    }

    @Test
    public void convertFromListToMap() throws Exception {

    }

    @Test
    public void sortPersonList() throws Exception {

    }

    @Test
    public void getIndexofKey() throws Exception {

    }

}
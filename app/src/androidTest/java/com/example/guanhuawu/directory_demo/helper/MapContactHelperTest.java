package com.example.guanhuawu.directory_demo.helper;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import com.example.guanhuawu.directory_demo.adpater.DataBaseHelper;
import com.example.guanhuawu.directory_demo.dao.ContactPersonTestDao;
import com.example.guanhuawu.directory_demo.persist.ContactPerson;
import com.example.guanhuawu.directory_demo.persist.ContactPersonTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by guanhua.wu on 2017/7/12.
 */
public class MapContactHelperTest {
    static Context appContext;
    static ContactPersonTestDao personTestDao;
    static List<ContactPerson> originalPersonList = new ArrayList<>();
    static List<ContactPersonTest> personTestList = new ArrayList<>();
    static MapContactHelper mapDemo;

    @BeforeClass
    public static void beforeclass() throws SQLException {
        appContext = InstrumentationRegistry.getTargetContext();
        personTestDao = new ContactPersonTestDao(DataBaseHelper.getHelper(appContext).getConnectionSource(),ContactPersonTest.class);
        personTestList = personTestDao.queryForAll();
        originalPersonList = convertToPersonContact(personTestList);
        mapDemo = new MapContactHelper();
    }

    @After
    public void after() throws SQLException {
        personTestDao.delete(personTestDao.queryForAll());

        for (ContactPersonTest person : personTestList
                ) {
            personTestDao.create(person);
        }
    }

    @Test
    public void findPersonByPosition() throws Exception {
        Map<String, List> mapContact = MapContactHelper.convertFromListToMap(originalPersonList);
        List<ContactPerson> persons = new ArrayList<>();


        for (int i = 0; i < MapContactHelper.getPersonNumber(mapContact); i++) {
            Log.e("map", "" + i);
            Log.e("map", "" + MapContactHelper.findPersonByPosition(mapContact, i).getSurName());
            persons.add(MapContactHelper.findPersonByPosition(mapContact, i));
        }

        Assert.assertEquals("阿萨", persons.get(0).getSurName());
        Assert.assertEquals("阿斯顿张", persons.get(1).getSurName());
        Assert.assertEquals("陈柳青", persons.get(2).getSurName());
        Assert.assertEquals("戴慧", persons.get(3).getSurName());
        Assert.assertEquals("辅导员", persons.get(4).getSurName());
        Assert.assertEquals("姑姑", persons.get(5).getSurName());
        Assert.assertEquals("郭新宇", persons.get(6).getSurName());
        Assert.assertEquals("哈皮", persons.get(7).getSurName());
        Assert.assertEquals("何云", persons.get(8).getSurName());
        Assert.assertEquals("红莲", persons.get(9).getSurName());
    }

    @Test
    public void getPersonNumber() throws Exception {
        Map<String, List> mapContact = MapContactHelper.convertFromListToMap(originalPersonList);

        Assert.assertEquals(21, MapContactHelper.getPersonNumber(mapContact));
    }

    @Test
    public void convertFromListToMap() throws Exception {
        Map<String, List> mapContact = MapContactHelper.convertFromListToMap(originalPersonList);

        Assert.assertNotNull(mapContact);

        for (List<ContactPerson> persons : mapContact.values()
                ) {
            for (ContactPerson person : persons
                    ) {
                Log.e("map", Concert.getPingYin(person.getSurName()) + "" + person.getSurName());
            }
        }
    }

    @Test
    public void sortPersonList() throws Exception {
        MapContactHelper.sortPersonList(originalPersonList);

        Assert.assertEquals("阿萨", originalPersonList.get(0).getSurName());
        Assert.assertEquals("阿斯顿张", originalPersonList.get(1).getSurName());
        Assert.assertEquals("陈柳青", originalPersonList.get(2).getSurName());
        Assert.assertEquals("戴慧", originalPersonList.get(3).getSurName());
        Assert.assertEquals("辅导员", originalPersonList.get(4).getSurName());
        Assert.assertEquals("姑姑", originalPersonList.get(5).getSurName());
        Assert.assertEquals("郭新宇", originalPersonList.get(6).getSurName());
    }

    @Test
    public void getIndexofKey() throws Exception {
        String indexOfKey = "a";
        Map<String, List> mapContact = MapContactHelper.convertFromListToMap(originalPersonList);
        for (int i = 0; i < MapContactHelper.getPersonNumber(mapContact); i++) {
            Log.e("map", "" + i);
            Log.e("map", "" + MapContactHelper.findPersonByPosition(mapContact, i).getSurName());
        }

        Assert.assertEquals(0, MapContactHelper.getIndexofKey("a", mapContact));
        Assert.assertEquals(2, MapContactHelper.getIndexofKey("c", mapContact));
        Assert.assertEquals(3, MapContactHelper.getIndexofKey("d", mapContact));
        Assert.assertEquals(4, MapContactHelper.getIndexofKey("f", mapContact));
        Assert.assertEquals(5, MapContactHelper.getIndexofKey("g", mapContact));
        Assert.assertEquals(7, MapContactHelper.getIndexofKey("h", mapContact));
        Assert.assertEquals(10, MapContactHelper.getIndexofKey("l", mapContact));
        Assert.assertEquals(12, MapContactHelper.getIndexofKey("q", mapContact));
        Assert.assertEquals(13, MapContactHelper.getIndexofKey("s", mapContact));
        Assert.assertEquals(14, MapContactHelper.getIndexofKey("w", mapContact));
        Assert.assertEquals(18, MapContactHelper.getIndexofKey("x", mapContact));
    }

    public static List<ContactPerson> convertToPersonContact(List<ContactPersonTest> personTestList){
        List<ContactPerson> persons = new ArrayList<>();
        for (ContactPersonTest personTest:personTestList) {
            ContactPerson person = new ContactPerson();

            person.setSurName(personTest.getSurName());
            person.setFirstName(personTest.getFirstName());
            person.setTelephoneNumber(personTest.getTelephoneNumber());
            person.setPhoneNumber(personTest.getPhoneNumber());
            person.setAddress(personTest.getAddress());
            person.setCompanyName(personTest.getCompanyName());
            person.setContactPersonId(personTest.getContactPersonId());
            person.setEmail(personTest.getEmail());
            person.setContactPersonId(personTest.getContactPersonId());
            persons.add(person);
        }
        return persons;
    }

}
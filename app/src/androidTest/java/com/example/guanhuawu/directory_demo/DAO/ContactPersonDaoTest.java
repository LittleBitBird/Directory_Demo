package com.example.guanhuawu.directory_demo.DAO;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.guanhuawu.directory_demo.Adpater.DataBaseHelper;
import com.example.guanhuawu.directory_demo.persist.ContactPerson;
import com.j256.ormlite.dao.Dao;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.SQLException;

/**
 * Created by guanhua.wu on 2017/7/10.
 */
@RunWith(AndroidJUnit4.class)
public class ContactPersonDaoTest {
    static Context appContext;
    static DataBaseHelper dataBaseHelper;
    static ContactPersonDao contactPersonDao;
    static Dao Basedao;

    @BeforeClass
    public static void beforeclass() throws SQLException {
        appContext = InstrumentationRegistry.getTargetContext();
        dataBaseHelper = new DataBaseHelper(appContext);
        contactPersonDao = new ContactPersonDao(appContext);
//        Basedao = contactPersonDao.getDao();
    }

    @Test
    public void add() throws Exception {
        ContactPerson person = new ContactPerson();
        person.setAddress("袭来");
        person.setEmail("123@12.com");
        person.setFirst_name("guanhua");
        person.setPhone_Number("17802592330");
        person.setRemarks("备注");
        person.setSurname("qqq");
        person.setTelePhone_Number("88562309");
//        DataBaseHelper baseHelper = new DataBaseHelper(appContext);
        ContactPersonDao contactPersonDao1 = new ContactPersonDao(appContext);
        contactPersonDao1.getContactDaoOpen().queryForAll();
//        Basedao.create(person);
    }

    @Test
    public void getOrderBy_Surname() throws Exception {

    }

    @Test
    public void getOrderBy_Id() throws Exception {
        contactPersonDao.getOrderBy_Id(30);
    }

    @Test
    public void delete_ById() throws Exception {
        contactPersonDao.deleteById(38);
        contactPersonDao.deleteById(39);
        contactPersonDao.deleteById(40);
        contactPersonDao.deleteById(41);
        contactPersonDao.deleteById(42);
        contactPersonDao.deleteById(43);

    }

    @Test
    public void update_Contact() throws Exception {

    }

}
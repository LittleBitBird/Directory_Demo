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
        person.setRemarks("备注");
        DataBaseHelper baseHelper = new DataBaseHelper(appContext);
        baseHelper.getWritableDatabase();
//        baseHelper.getWritableDatabase().rawQuery("select from ",null);
        baseHelper.getWritableDatabase().close();
        baseHelper.close();
//        Basedao.create(person);
    }

    @Test
    public void getOrderBy_Surname() throws Exception {
        contactPersonDao.getContactDaoOpen().executeRaw("insert into Contact_Person(contact_person_id,surname,first_name,company_name,phone_number,telephone_number,email,address,remark)" +
                "select contact_person_id,sur_name,first_name,company_name,phone_number,telephone_number,email,address,remark from Contact_personOld");


    }

    @Test
    public void getOrderBy_Id() throws Exception {

    }

    @Test
    public void delete_ById() throws Exception {

    }

    @Test
    public void update_Contact() throws Exception {

    }

    @Test
    public void deleteTable() throws Exception {
        contactPersonDao.getContactDaoOpen().executeRaw("drop table if exists Contact_person");
        contactPersonDao.getContactDaoOpen().executeRaw("drop table if exists Contact_personOld2");
    }

}
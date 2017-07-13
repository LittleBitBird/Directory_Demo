package com.example.guanhuawu.directory_demo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.guanhuawu.directory_demo.dao.ContactPersonDao;
import com.example.guanhuawu.directory_demo.persist.ContactPerson;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.SQLException;

/**
 * Created by guanhua.wu on 2017/7/10.
 */
@RunWith(AndroidJUnit4.class)
public class UpdateAndDeleteContactsTest {
    @Test
    public void getIntentFromDirectory() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        ContactPersonDao dao ;
        ContactPerson contactPerson = null;
        dao = new ContactPersonDao(appContext);
        try {
            contactPerson = dao.getContactDaoOpen().queryForId(32);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(contactPerson.getFirstName().length()!=0){
            Log.e("test","First_name is not null");
        }
        if(contactPerson.getSurName().length()!=0){
            Log.e("test","Surname is not null");
        }
        if(contactPerson.getAddress().length()!=0){
            Log.e("test","Address is not null");
        }
        if(contactPerson.getCompanyName().length()!=0){
            Log.e("test","Company_Name is not null");
        }
        if(contactPerson.getEmail().length()!=0){
            Log.e("test","Email is not null");
        }
        Assert.assertNotNull(contactPerson);
    }

}
package com.example.guanhuawu.directory_demo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.guanhuawu.directory_demo.DAO.ContactPersonDao;
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
    public void getIntent_from_Directory() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        ContactPersonDao dao ;
        ContactPerson contact_person = null;
        dao = new ContactPersonDao(appContext);
        try {
            contact_person = dao.getOrderBy_Id(20);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(contact_person.getFirst_name().length()!=0){
            Log.e("test","First_name is not null");
        }
        if(contact_person.getSurname().length()!=0){
            Log.e("test","Surname is not null");
        }
        if(contact_person.getAddress().length()!=0){
            Log.e("test","Address is not null");
        }
        if(contact_person.getCompany_Name().length()!=0){
            Log.e("test","Company_Name is not null");
        }
        if(contact_person.getEmail().length()!=0){
            Log.e("test","Email is not null");
        }
        Assert.assertNotNull(contact_person);
    }

}
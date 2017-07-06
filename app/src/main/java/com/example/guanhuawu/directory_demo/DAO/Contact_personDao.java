package com.example.guanhuawu.directory_demo.DAO;

import android.content.Context;

import com.example.guanhuawu.directory_demo.Adpater.DataBaseHelper;
import com.example.guanhuawu.directory_demo.persist.Contact_person;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by guanhua.wu on 2017/7/6.
 */

public class Contact_personDao {

    private Context context;
    private Dao<Contact_person,Integer> ContactDaoOpen;
    private DataBaseHelper helper;

    public Contact_personDao(Context context){
        this.context = context;
        helper = new DataBaseHelper(context);
    }

    public Dao<Contact_person, Integer> getContactDaoOpen() throws SQLException {
        return helper.getDao(Contact_person.class);
    }

    public  void  add(Contact_person contact_person) throws SQLException {
        getContactDaoOpen().create(contact_person);
    }





}

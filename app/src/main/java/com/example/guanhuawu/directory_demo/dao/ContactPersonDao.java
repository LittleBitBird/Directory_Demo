package com.example.guanhuawu.directory_demo.dao;

import android.content.Context;

import com.example.guanhuawu.directory_demo.adpater.DataBaseHelper;
import com.example.guanhuawu.directory_demo.persist.ContactPerson;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by guanhua.wu on 2017/7/6.
 */

public class ContactPersonDao extends BaseDaoImpl<ContactPerson, Integer> {


    private Dao<ContactPerson, Integer> ContactDaoOpen;
    private DataBaseHelper helper;

    public ContactPersonDao(Context context) throws SQLException {
        super(DataBaseHelper.getHelper(context.getApplicationContext()).getConnectionSource(), ContactPerson.class);
        helper = DataBaseHelper.getHelper(context);
        getContactDaoOpen();
    }


//    public ContactPersonDao(ConnectionSource connectionSource, Context context) throws SQLException {
//        this.context = context;
//        helper = new DataBaseHelper(context);
//        try {
//            ContactDaoOpen = helper.getDao(ContactPerson.class);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public Dao<ContactPerson, Integer> getContactDaoOpen() {
        try {
            ContactDaoOpen = helper.getDao(ContactPerson.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ContactDaoOpen;
    }

    public List<ContactPerson> getOrderBySurname() throws SQLException {
        List<ContactPerson> personList = ContactDaoOpen.queryBuilder().
                orderBy("Surname", true).query();
        return personList;
    }

    public ContactPerson getOrderById(Integer Id) throws SQLException {
        ContactPerson person = ContactDaoOpen.
                queryBuilder().
                where().
                idEq(Id).query().get(0);
        return person;
    }


    public void DeleteAll() throws SQLException {
        List<ContactPerson> personList = ContactDaoOpen.queryBuilder().query();
        ContactDaoOpen.delete(personList);
    }

    public void close() {
        helper.close();
        helper = null;
        ContactDaoOpen = null;
    }

}

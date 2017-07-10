package com.example.guanhuawu.directory_demo.DAO;

import android.content.Context;

import com.example.guanhuawu.directory_demo.Adpater.DataBaseHelper;
import com.example.guanhuawu.directory_demo.persist.Contact_person;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by guanhua.wu on 2017/7/6.
 */

public class Contact_personDao {

    private Context context;
    private Dao<Contact_person, Integer> ContactDaoOpen;
    private DataBaseHelper helper;

    public Contact_personDao(Context context) {
        this.context = context;
        helper = new DataBaseHelper(context);
        try {
            ContactDaoOpen = helper.getDao(Contact_person.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void add(Contact_person contact_person) throws SQLException {
        ContactDaoOpen.create(contact_person);
    }


    public List<Contact_person> getOrderBy_Surname() throws SQLException {
        List<Contact_person> contact_persons = ContactDaoOpen.queryBuilder().
                orderBy("Surname", true).query();
        return contact_persons;
    }

    public Contact_person getOrderBy_Id(Integer Id) throws SQLException {
        Contact_person person = ContactDaoOpen.
                queryBuilder().
                where().
                idEq(Id).query().get(0);
        return person;
    }

    public void Delete_ById(Integer id) {
        try {
            ContactDaoOpen.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update_Contact(Contact_person person){
        try {
            ContactDaoOpen.update(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteAll() throws SQLException {
        List<Contact_person> contact_persons = ContactDaoOpen.queryBuilder().query();
        ContactDaoOpen.delete(contact_persons);
    }

    public void close() {
        helper.close();
    }

}

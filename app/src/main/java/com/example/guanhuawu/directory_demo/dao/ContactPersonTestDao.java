package com.example.guanhuawu.directory_demo.dao;

import com.example.guanhuawu.directory_demo.persist.ContactPersonTest;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * Created by guanhua.wu on 2017/7/12.
 */

public class ContactPersonTestDao extends BaseDaoImpl<ContactPersonTest,Integer> {

    public ContactPersonTestDao(ConnectionSource connectionSource, Class<ContactPersonTest> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }
}

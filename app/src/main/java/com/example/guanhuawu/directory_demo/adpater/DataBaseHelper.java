package com.example.guanhuawu.directory_demo.adpater;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.guanhuawu.directory_demo.persist.ContactPerson;
import com.example.guanhuawu.directory_demo.persist.ContactPersonTest;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guanhua.wu on 2017/7/5.
 */

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TABLE_NAME = "sqlite-test.db";

    public DataBaseHelper(Context context) {
        super(context.getApplicationContext(), TABLE_NAME, null, 22);
    }

    private Map<String, Dao> daos = new HashMap<String, Dao>();

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, ContactPerson.class);
            TableUtils.createTable(connectionSource, ContactPersonTest.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            Log.e("veision", "老版本：" + i + "新版本" + i1);
//            Contact_person_id,Surname,first_name,Company_Name,Phone_Number,TelePhone_Number,Email,address,Remarks
            getContactDao().executeRaw("ALTER TABLE Contact_Person RENAME TO Contact_personOld");
            TableUtils.createTable(connectionSource, ContactPerson.class);
            getContactDao().executeRaw("insert into Contact_Person(contact_person_id,surname,first_name,company_name,phone_number,telephone_number,email,address,remark)" +
                                                            "select contact_person_id,sur_name,first_name,company_name,phone_number,telephone_number,email,address,remark from Contact_personOld");
            getContactDao().executeRaw("drop table if exists Contact_personOld");
            Log.e("update", "Success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static DataBaseHelper instance;

    public static synchronized DataBaseHelper getHelper(Context context) {
        if (instance == null) {
            synchronized (DataBaseHelper.class) {
                if (instance == null)
                    instance = new DataBaseHelper(context);
            }
        }
        return instance;
    }

    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();
        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }
        if (dao == null) {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }


    @Override
    public void close() {
        super.close();
        instance = null;
        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }

    public Dao getContactDao() throws SQLException {
        return getDao(ContactPerson.class);
    }

}

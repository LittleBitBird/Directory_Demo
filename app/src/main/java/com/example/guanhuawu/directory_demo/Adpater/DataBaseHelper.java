package com.example.guanhuawu.directory_demo.Adpater;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.guanhuawu.directory_demo.persist.Contact_person;
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
        super(context, TABLE_NAME, null, 1);
    }

    private Map<String, Dao> daos = new HashMap<String, Dao>();

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Contact_person.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, Contact_person.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static DataBaseHelper instance;

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
        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }

}

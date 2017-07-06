package com.example.guanhuawu.directory_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.guanhuawu.directory_demo.Adpater.Dierctory_ListView_adapter;
import com.example.guanhuawu.directory_demo.DAO.Contact_personDao;
import com.example.guanhuawu.directory_demo.persist.Contact_person;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Directory extends AppCompatActivity {

    @BindView(R.id.list_View)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        ButterKnife.bind(this);
        List list = new ArrayList();
        list.add("123");list.add("123");list.add("123");list.add("123");list.add("123");
        Dierctory_ListView_adapter view_adapter = new Dierctory_ListView_adapter(this,list);
        listView.setAdapter(view_adapter);
        try {
            create();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void create() throws SQLException {
        Contact_personDao contact_personDao = new Contact_personDao(this);
        Contact_person person = new Contact_person();
        person.setAddress("袭来");

        person.setEmail("123@12.com");
        person.setFirst_name("guanhua");
        person.setPhone_Number("17802592330");
        person.setRemarks("备注");
        person.setSurname("wu");
        person.setTelePhone_Number("88562309");
        contact_personDao.add(person);
    }

}

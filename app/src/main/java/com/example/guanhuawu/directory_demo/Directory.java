package com.example.guanhuawu.directory_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.guanhuawu.directory_demo.Adpater.Dierctory_ListView_adapter;
import com.example.guanhuawu.directory_demo.DAO.Contact_personDao;
import com.example.guanhuawu.directory_demo.Helper.Concert;
import com.example.guanhuawu.directory_demo.persist.Contact_person;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnTextChanged;

public class Directory extends AppCompatActivity {

    @BindView(R.id.list_View)
    ListView listView;

    @BindView(R.id.Add_contacts)
    TextView Add_contacts;

    @BindView(R.id.Search_Contacts)
    EditText Search_Contacts;

    @BindView(R.id.a)
    TextView A;
    @BindView(R.id.b)
    TextView B;
    @BindView(R.id.c)
    TextView C;
    @BindView(R.id.d)
    TextView D;
    @BindView(R.id.e)
    TextView E;
    @BindView(R.id.f)
    TextView F;
    @BindView(R.id.g)
    TextView G;
    @BindView(R.id.h)
    TextView H;
    @BindView(R.id.i)
    TextView I;
    @BindView(R.id.j)
    TextView J;
    @BindView(R.id.k)
    TextView K;
    @BindView(R.id.l)
    TextView L;
    @BindView(R.id.m)
    TextView M;
    @BindView(R.id.n)
    TextView N;
    @BindView(R.id.o)
    TextView O;
    @BindView(R.id.p)
    TextView P;
    @BindView(R.id.q)
    TextView Q;
    @BindView(R.id.r)
    TextView mR;
    @BindView(R.id.s)
    TextView S;
    @BindView(R.id.t)
    TextView T;
    @BindView(R.id.u)
    TextView U;
    @BindView(R.id.v)
    TextView V;
    @BindView(R.id.w)
    TextView W;
    @BindView(R.id.x)
    TextView X;
    @BindView(R.id.y)
    TextView Y;
    @BindView(R.id.z)
    TextView Z;
    @BindView(R.id.Main_Index)
    TextView MainIndex;
    Contact_personDao dao;
    List<Contact_person> personList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        ButterKnife.bind(this);
        dao = new Contact_personDao(this);
        try {
//            dao.DeleteAll();
            personList = dao.getOrderBy_Surname();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Bind_Adapter();
        Bind();
    }

    public void Bind_Adapter() {
        Dierctory_ListView_adapter view_adapter = new Dierctory_ListView_adapter(this, personList);
        listView.setAdapter(view_adapter);
    }

    @OnTextChanged(R.id.Search_Contacts)
    public void Search_Contacts() {
        List<Contact_person> persons = new ArrayList<Contact_person>();
        if (Search_Contacts.length() > 0) {//搜索框里面有值，下一步
//            String key_words = Search_Contacts.getText().toString().replaceAll(" ", "");
            String key_words = "w";
            key_words = Concert.getPingYin(key_words);
            Log.e("search", key_words);
            for (Contact_person person : personList
                    ) {
                String name = person.getSurname() + person.getFirst_name();
                name = name.substring(1);
                String namePinYin = Concert.getPingYin(name);
                if (namePinYin.startsWith(key_words)) {
                    persons.add(person);
                    Log.e("PinYin", namePinYin + " " + persons.size());
                }
            }
            personList = persons;
            Bind_Adapter();
        } else {//搜索框里面没有值
            Log.e("search", "null");
            try {
                personList = dao.getOrderBy_Surname();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Bind_Adapter();
        }
    }

    @OnItemClick(R.id.list_View)
    public void onItemClick(int position, View view) {
        TextView view1 = view.findViewById(R.id.Member);
        Log.e("123", position + " " + view1.getText() + " " + personList.get(position).getSurname());
    }

    public void Bind() {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                try {
                    MainIndex.setText(personList.get(i).getSurname().substring(0, 1).toUpperCase());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @OnClick({R.id.a, R.id.b, R.id.c, R.id.d, R.id.e, R.id.f,
            R.id.g, R.id.h, R.id.i, R.id.j, R.id.k, R.id.l,
            R.id.m, R.id.n, R.id.o, R.id.p, R.id.q, R.id.r,
            R.id.s, R.id.t, R.id.u, R.id.v, R.id.w, R.id.x,
            R.id.y, R.id.z})
    public void MuliteClick(View view) {
        TextView getTex = (TextView) view;
        String FirstChar = getTex.getText().toString().toLowerCase();
        for (Contact_person person : personList) {
            if (person.getSurname().substring(0, 1).equals(FirstChar)) {
                Log.e("Fist", person.getSurname() + " " + personList.indexOf(person));
                listView.setSelection(personList.indexOf(person));
                break;
            }
        }
    }


    @OnClick(R.id.Add_contacts)
    public void Add_contacts() {
        Intent intent = new Intent(this, Add_Contacts.class);
        startActivity(intent);
    }

    public void create() throws SQLException {
        Contact_personDao contact_personDao = new Contact_personDao(this);
        Contact_person person = new Contact_person();
        person.setAddress("袭来");
        person.setEmail("123@12.com");
        person.setFirst_name("guanhua");
        person.setPhone_Number("17802592330");
        person.setRemarks("备注");
        person.setSurname("cu");
        person.setTelePhone_Number("88562309");
        contact_personDao.add(person);
    }

}

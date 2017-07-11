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

import com.example.guanhuawu.directory_demo.Adpater.DierctoryListViewAdapter;
import com.example.guanhuawu.directory_demo.DAO.ContactPersonDao;
import com.example.guanhuawu.directory_demo.Helper.Concert;
import com.example.guanhuawu.directory_demo.persist.ContactPerson;

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
    ContactPersonDao dao;
    List<ContactPerson> personList = null;
    DierctoryListViewAdapter view_adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        ButterKnife.bind(this);
        try {
            dao = new ContactPersonDao(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        view_adapter = new DierctoryListViewAdapter(this, personList);
        listView.setAdapter(view_adapter);
    }

    @OnTextChanged(R.id.Search_Contacts)
    public void Search_Contacts() {
        List<ContactPerson> persons = new ArrayList<ContactPerson>();
        if (Search_Contacts.length() > 0) {//搜索框里面有值，下一步
            String key_words = Search_Contacts.getText().toString().replaceAll(" ", "");
            key_words = Concert.getPingYin(key_words);
            Log.e("search", key_words);
            for (ContactPerson person : personList
                    ) {
                String name = person.getSurname() + person.getFirst_name();
                name = name.substring(1);
                String namePinYin = Concert.getPingYin(name);
                if (namePinYin.startsWith(key_words)) {
                    persons.add(person);
                    Log.e("PinYin", namePinYin + " " + persons.size());
                }
            }
            personList.clear();
            personList.addAll(persons);
            view_adapter.notifyDataSetChanged();
        } else {//搜索框里面没有值
            Log.e("search", "null");
            try {
                persons = dao.getOrderBy_Surname();
                personList.clear();
                personList.addAll(persons);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            view_adapter.notifyDataSetChanged();
        }
    }

    @OnItemClick(R.id.list_View)
    public void onItemClick(int position, View view) {
        TextView view1 = view.findViewById(R.id.Member);
        Log.e("123", position + " " + view1.getText() + " " + personList.get(position).getSurname());
        Intent intent = new Intent(this,ShowDetailMessage.class);
        intent.putExtra("id",personList.get(position).getContact_person_id());
        dao.close();
        startActivity(intent);
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
        for (ContactPerson person : personList) {
            if (person.getSurname().substring(0, 1).equals(FirstChar)) {
                Log.e("Fist", person.getSurname() + " " + personList.indexOf(person));
                listView.setSelection(personList.indexOf(person));
                break;
            }
        }
    }


    @OnClick(R.id.Add_contacts)
    public void Add_contacts() {
        Intent intent = new Intent(this, AddContacts.class);
        dao.close();
        startActivity(intent);
    }

    public void create() throws SQLException {
        ContactPersonDao contact_personDao = new ContactPersonDao(this);
        ContactPerson person = new ContactPerson();
        person.setAddress("袭来");
        person.setEmail("123@12.com");
        person.setFirst_name("guanhua");
        person.setPhone_Number("17802592330");
        person.setRemarks("备注");
        person.setSurname("cu");
        person.setTelePhone_Number("88562309");
    }

}

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

import com.example.guanhuawu.directory_demo.adpater.DierctoryListViewAdapter;
import com.example.guanhuawu.directory_demo.dao.ContactPersonDao;
import com.example.guanhuawu.directory_demo.helper.Concert;
import com.example.guanhuawu.directory_demo.helper.MapContactHelper;
import com.example.guanhuawu.directory_demo.persist.ContactPerson;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnTextChanged;

public class Directory extends AppCompatActivity {

    @BindView(R.id.lvListView)
    ListView listView;

    @BindView(R.id.tvAddContacts)
    TextView AddContacts;

    @BindView(R.id.edtSearchContacts)
    EditText SearchContacts;

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
    @BindView(R.id.tvMainIndex)
    TextView MainIndex;
    ContactPersonDao dao;
    List<ContactPerson> personList = null;
    DierctoryListViewAdapter viewAdapter;
    Map<String, List> contactMap = new HashMap<>();
    Map<String, List> contactMapSave = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        ButterKnife.bind(this);
        bindData();
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.e("tag", "start onStart~~~");
//        bindData();
    }

    public void bindData(){
        try {
            dao = new ContactPersonDao(this);
            personList = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        bindAdapter();
        Bind();
    }

    public void bindAdapter() {
        contactMap = MapContactHelper.convertFromListToMap(personList);
        contactMapSave.putAll(contactMap);
        viewAdapter = new DierctoryListViewAdapter(this, contactMap);
        listView.setAdapter(viewAdapter);
    }

    @OnTextChanged(R.id.edtSearchContacts)
    public void searchContacts() {
        List<ContactPerson> persons = new ArrayList<ContactPerson>();
        if (SearchContacts.length() > 0) {//搜索框里面有值，下一步

            String keyWords = SearchContacts.getText().toString().replaceAll(" ", "");
            keyWords = Concert.getPingYin(keyWords);
            Log.e("search", keyWords);
            List<ContactPerson> list;
            List<ContactPerson> listSave = new ArrayList<>();
            list = contactMapSave.get(keyWords.substring(0, 1));
            if (list != null)
                for (ContactPerson person : list
                        ) {
                    if (Concert.getPingYin(person.getSurName()).startsWith(keyWords)) {
                        listSave.add(person);
                    }
                }
            contactMap.clear();
            contactMap.put(keyWords.substring(0, 1), listSave);
            viewAdapter.notifyDataSetChanged();
        } else {//搜索框里面没有值
            Log.e("search", "null");
            Map<String, List> map = MapContactHelper.convertFromListToMap(personList);
            contactMap.clear();
            contactMap.putAll(map);
            viewAdapter.notifyDataSetChanged();
        }
    }

    @OnItemClick(R.id.lvListView)
    public void onItemClick(int position, View view) {
        TextView view1 = view.findViewById(R.id.tvMember);
//        Log.e("123", position + " " + view1.getText() + " " + personList.get(position).getSurName());
        Intent intent = new Intent(this, ShowDetailMessage.class);
        intent.putExtra("id", MapContactHelper.findPersonByPosition(contactMap, position).getContactPersonId());
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
                    ContactPerson person = MapContactHelper.findPersonByPosition(contactMap, i);
                    if (person != null)
                        MainIndex.setText(Concert.getPingYin(person.getSurName()).substring(0, 1).toUpperCase());
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
        int index = MapContactHelper.getIndexofKey(FirstChar, contactMap);
        listView.setSelection(index);
    }


    @OnClick(R.id.tvAddContacts)
    public void addContacts() {
        Intent intent = new Intent(this, AddContacts.class);
        startActivity(intent);
    }

    public void create() throws SQLException {
        ContactPersonDao contactPersonDao = new ContactPersonDao(this);
        ContactPerson person = new ContactPerson();
        person.setAddress("袭来");
        person.setEmail("123@12.com");
        person.setFirstName("guanhua");
        person.setPhoneNumber("17802592330");
        person.setRemarks("备注");
        person.setSurName("cu");
        person.setTelephoneNumber("88562309");
    }

}

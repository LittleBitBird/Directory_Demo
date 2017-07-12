package com.example.guanhuawu.directory_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.guanhuawu.directory_demo.DAO.ContactPersonDao;
import com.example.guanhuawu.directory_demo.persist.ContactPerson;

import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowDetailMessage extends AppCompatActivity {
    Integer contactPersonId;
    ContactPersonDao dao;
    ContactPerson contactPerson;
    @BindView(R.id.tvBack)
    TextView tvBack;
    @BindView(R.id.tvContactName)
    TextView tvContactName;
    @BindView(R.id.tvEditMessage)
    TextView tvEditMessage;
    @BindView(R.id.tvSurName)
    TextView tvSurName;
    @BindView(R.id.tvFirstName)
    TextView tvFirstName;
    @BindView(R.id.tvCompanyName)
    TextView tvCompanyName;
    @BindView(R.id.tvTelePhoneNumber)
    TextView tvTelePhoneNumber;
    @BindView(R.id.tvPhoneNumber)
    TextView tvPhoneNumber;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvRemarks)
    TextView tvRemarks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__detail__message);
        ButterKnife.bind(this);
        getIntentFromDirectory();
        setTextValues();
    }

    public void getIntentFromDirectory() {
        Intent intent = getIntent();
        contactPersonId = intent.getIntExtra("id", 0);
        Log.e("12", "getIntent_from_Directory: " + contactPersonId, null);
        try {
            dao = new ContactPersonDao(this);
            contactPerson = dao.getContactDaoOpen().queryForId(contactPersonId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tvContactName.setText(contactPerson.getSurName() + contactPerson.getFirstName());
    }

    public void setTextValues() {
        tvAddress.setText(contactPerson.getAddress());
        tvCompanyName.setText(contactPerson.getCompanyName());
        tvEmail.setText(contactPerson.getEmail());
        tvFirstName.setText(contactPerson.getFirstName());
        tvPhoneNumber.setText(contactPerson.getPhoneNumber());
        tvRemarks.setText(contactPerson.getRemarks());
        tvSurName.setText(contactPerson.getSurName());
        tvTelePhoneNumber.setText(contactPerson.getTelephoneNumber());
    }



    public void backClick() {
        dao.close();
        this.finish();
    }

    public void editMessageClick() {
        Intent intent = new Intent(this, UpdateAndDeleteContacts.class);
        intent.putExtra("id", contactPerson.getContactPersonId());
        startActivity(intent);
    }

    @OnClick({R.id.tvBack, R.id.tvEditMessage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvBack:
                backClick();
                break;
            case R.id.tvEditMessage:
                editMessageClick();
                break;
        }
    }
}

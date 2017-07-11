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
    Integer Contact_person_id;
    ContactPersonDao dao;
    ContactPerson contact_person;
    @BindView(R.id.Back)
    TextView Back;
    @BindView(R.id.Contact_Name)
    TextView ContactName;
    @BindView(R.id.EditMessage)
    TextView EditMessage;
    @BindView(R.id.txtSurname)
    TextView txtSurname;
    @BindView(R.id.txtFirst_name)
    TextView txtFirstName;
    @BindView(R.id.txtCompany_Name)
    TextView txtCompanyName;
    @BindView(R.id.txtTelePhone_Number)
    TextView txtTelePhoneNumber;
    @BindView(R.id.txtPhone_Number)
    TextView txtPhoneNumber;
    @BindView(R.id.txtEmail)
    TextView txtEmail;
    @BindView(R.id.txtAddress)
    TextView txtAddress;
    @BindView(R.id.txtRemarks)
    TextView txtRemarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__detail__message);
        ButterKnife.bind(this);
        getIntent_from_Directory();
        setText_Values();
    }

    public void getIntent_from_Directory() {
        Intent intent = getIntent();
        Contact_person_id = intent.getIntExtra("id", 0);
        Log.e("12", "getIntent_from_Directory: " + Contact_person_id, null);
        try {
            dao = new ContactPersonDao(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            contact_person = dao.getContactDaoOpen().queryForId(Contact_person_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContactName.setText(contact_person.getSurname().substring(1) + contact_person.getFirst_name());
    }

    public void setText_Values() {
        txtAddress.setText(contact_person.getAddress());
        txtCompanyName.setText(contact_person.getCompany_Name());
        txtEmail.setText(contact_person.getEmail());
        txtFirstName.setText(contact_person.getFirst_name());
        txtPhoneNumber.setText(contact_person.getPhone_Number());
        txtRemarks.setText(contact_person.getRemarks());
        txtSurname.setText(contact_person.getSurname().substring(1));
        txtTelePhoneNumber.setText(contact_person.getTelePhone_Number());
    }

    @OnClick({R.id.Back, R.id.EditMessage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Back:
                Back_Click();
                break;
            case R.id.EditMessage:
                EditMessage_Click();
                break;
        }
    }

    public void Back_Click() {
        Intent intent = new Intent(this, Directory.class);
        dao.close();
        startActivity(intent);
    }

    public void EditMessage_Click() {
        Intent intent = new Intent(this, UpdateAndDeleteContacts.class);
        intent.putExtra("id", contact_person.getContact_person_id());
        dao.close();
        startActivity(intent);
    }
}

package com.example.guanhuawu.directory_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guanhuawu.directory_demo.DAO.ContactPersonDao;
import com.example.guanhuawu.directory_demo.persist.ContactPerson;

import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateAndDeleteContacts extends AppCompatActivity {
    Integer contactPersonId;
    ContactPersonDao dao;
    ContactPerson contactPerson;

    @BindView(R.id.edtSurname)
    EditText edtSurname;
    @BindView(R.id.edtFirst_name)
    EditText edtFirstName;
    @BindView(R.id.edtCompany_Name)
    EditText edtCompanyName;
    @BindView(R.id.edtTelePhone_Number)
    EditText edtTelePhoneNumber;
    @BindView(R.id.edtPhone_Number)
    EditText edtPhoneNumber;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtAddress)
    EditText edtAddress;
    @BindView(R.id.edtRemarks)
    EditText edtRemarks;
    @BindView(R.id.tvDeleteContactPerson)
    TextView DeleteContactPerson;
    @BindView(R.id.tvBack)
    TextView tvBack;
    @BindView(R.id.tvContactName)
    TextView tvContactName;
    @BindView(R.id.tvSave)
    TextView tvSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and__delete);
        ButterKnife.bind(this);
        getIntentFromDirectory();
        setEditValues();
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

    public void setEditValues() {
        edtAddress.setText(contactPerson.getAddress());
        edtCompanyName.setText(contactPerson.getCompanyName());
        edtEmail.setText(contactPerson.getEmail());
        edtFirstName.setText(contactPerson.getFirstName());
        edtPhoneNumber.setText(contactPerson.getPhoneNumber());
        edtRemarks.setText(contactPerson.getRemarks());
        edtSurname.setText(contactPerson.getSurName());
        edtTelePhoneNumber.setText(contactPerson.getTelephoneNumber());
    }

    @OnClick(R.id.tvBack)
    public void onViewClickedBack() {
        dao.close();
        this.finish();
    }

    @OnClick(R.id.tvDeleteContactPerson)
    public void onViewClickedDelete() {
        try {
            dao.getContactDaoOpen().deleteById(contactPerson.getContactPersonId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dao.close();
        Toast toast = Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        this.finish();
    }

    @OnClick(R.id.tvSave)
    public void updateContact() {
        contactPerson.setCompanyName(edtCompanyName.getText().toString());
        contactPerson.setRemarks(edtRemarks.getText().toString());
        contactPerson.setPhoneNumber(edtPhoneNumber.getText().toString());
        contactPerson.setEmail(edtEmail.getText().toString());
        contactPerson.setTelephoneNumber(edtTelePhoneNumber.getText().toString());
        contactPerson.setAddress(edtAddress.getText().toString());
        contactPerson.setFirstName(edtFirstName.getText().toString());
        contactPerson.setSurName(edtSurname.getText().toString());

        try {
            dao.getContactDaoOpen().update(contactPerson);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Toast toast = Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        dao.close();
        this.finish();
    }
}

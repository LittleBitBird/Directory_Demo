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
import com.example.guanhuawu.directory_demo.Helper.Concert;
import com.example.guanhuawu.directory_demo.persist.ContactPerson;

import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateAndDeleteContacts extends AppCompatActivity {
    Integer Contact_person_id;
    ContactPersonDao dao;
    ContactPerson contact_person;

    @BindView(R.id.Back)
    TextView Back;
    @BindView(R.id.Contact_Name)
    TextView ContactName;
    @BindView(R.id.Save)
    TextView Save;
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
    @BindView(R.id.Delete_Contact_Person)
    TextView DeleteContactPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and__delete);
        ButterKnife.bind(this);
        getIntent_from_Directory();
        setEdit_Values();
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
            contact_person = dao.getOrderBy_Id(Contact_person_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContactName.setText(contact_person.getSurname().substring(1) + contact_person.getFirst_name());
    }

    public void setEdit_Values() {
        edtAddress.setText(contact_person.getAddress());
        edtCompanyName.setText(contact_person.getCompany_Name());
        edtEmail.setText(contact_person.getEmail());
        edtFirstName.setText(contact_person.getFirst_name());
        edtPhoneNumber.setText(contact_person.getPhone_Number());
        edtRemarks.setText(contact_person.getRemarks());
        edtSurname.setText(contact_person.getSurname().substring(1));
        edtTelePhoneNumber.setText(contact_person.getTelePhone_Number());
    }

    @OnClick(R.id.Back)
    public void onViewClicked_Back() {
        Intent intent = new Intent(this, Directory.class);
        dao.close();
        startActivity(intent);
    }

    @OnClick(R.id.Delete_Contact_Person)
    public void onViewClicked_Delete() {
        dao.Delete_ById(contact_person.getContact_person_id());
        dao.close();
        Toast toast = Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        Intent intent = new Intent(this, Directory.class);
        startActivity(intent);
    }

    @OnClick(R.id.Save)
    public void update_Contact() {
        contact_person.setCompany_Name(edtCompanyName.getText().toString());
        contact_person.setRemarks(edtRemarks.getText().toString());
        contact_person.setPhone_Number(edtPhoneNumber.getText().toString());
        contact_person.setEmail(edtEmail.getText().toString());
        contact_person.setTelePhone_Number(edtTelePhoneNumber.getText().toString());
        contact_person.setAddress(edtAddress.getText().toString());
        contact_person.setFirst_name(edtFirstName.getText().toString());
        char First = Concert.getPingYin(edtSurname.getText().toString()).charAt(0);
        contact_person.setSurname(First + edtSurname.getText().toString());
        dao.update_Contact(contact_person);
        Toast toast = Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        Intent intent = new Intent(this, Directory.class);
        dao.close();
        startActivity(intent);
    }
}

package com.example.guanhuawu.directory_demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guanhuawu.directory_demo.dao.ContactPersonDao;
import com.example.guanhuawu.directory_demo.persist.ContactPerson;

import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class AddContacts extends AppCompatActivity {

    @BindView(R.id.tvBack)
    TextView mBack;

    @BindView(R.id.tvSave)
    TextView mAddContacts;

    @BindView(R.id.edtSurname)
    EditText medtSurname;

    @BindView(R.id.edtFirst_name)
    EditText medtFirstName;

    @BindView(R.id.edtCompany_Name)
    EditText medtCompanyName;

    @BindView(R.id.edtTelePhone_Number)
    EditText medtTelePhoneNumber;

    @BindView(R.id.edtPhone_Number)
    EditText medtPhoneNumber;

    @BindView(R.id.edtEmail)
    EditText medtEmail;

    @BindView(R.id.edtAddress)
    EditText medtAddress;

    @BindView(R.id.edtRemarks)
    EditText medtRemarks;

    ContactPersonDao dao;
    List<ContactPerson> personList = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__contacts);

        ButterKnife.bind(this);
        createNewDao();
    }

    public void createNewDao(){
        try {
            dao = new ContactPersonDao(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @OnTextChanged(R.id.edtSurname)
    protected void handleTextChange() {
        if (medtSurname.length() > 0) {//长度大于0，不为空值
            Log.e("value", "不为空" + medtSurname.length());
            mAddContacts.setTextColor(Color.parseColor("#77aaee"));
        } else {//为空值
            Log.e("value", "为空" + medtSurname.length());
            mAddContacts.setTextColor(getResources().getColor(R.color.darker_gray));
        }
    }

    @OnClick(R.id.tvBack)
    public void Back() {
        dao.close();
        this.finish();
    }

    @OnClick(R.id.tvSave)
    public void onViewClicked() {
        Log.e("color", "1");
        String s = mAddContacts.getCurrentTextColor() + "";
        if (s.equals("-5592406")) {
            //颜色为灰色，不可用
//            Log.e("color", mAdd_contacts.getCurrentTextColor() + "相同不可用");
        } else {
            //颜色不为灰色可用
            Log.e("color", "2");
            ContactPerson person = new ContactPerson();
            person.setAddress(medtAddress.getText().toString());
            person.setEmail(medtEmail.getText().toString());
            person.setFirstName(medtFirstName.getText().toString());
            person.setCompanyName(medtCompanyName.getText().toString());
            person.setPhoneNumber(medtPhoneNumber.getText().toString());
            person.setRemarks(medtRemarks.getText().toString());
            person.setSurName(medtSurname.getText().toString());
            person.setTelephoneNumber(medtTelePhoneNumber.getText().toString());

            saveContact(person);
            dao.close();
            Toast toast = Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            this.finish();
        }
//        Log.e("color", mAdd_contacts.getCurrentTextColor() + "不同可用");
    }

    public void saveContact(ContactPerson person){
        try {
            dao.getContactDaoOpen().create(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


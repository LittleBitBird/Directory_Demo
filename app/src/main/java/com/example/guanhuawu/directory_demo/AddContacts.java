package com.example.guanhuawu.directory_demo;

import android.content.Intent;
import android.graphics.Color;
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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class AddContacts extends AppCompatActivity {

    @BindView(R.id.Back)
    TextView mBack;

    @BindView(R.id.Save)
    TextView mAdd_contacts;

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
            mAdd_contacts.setTextColor(Color.parseColor("#77aaee"));
        } else {//为空值
            Log.e("value", "为空" + medtSurname.length());
            mAdd_contacts.setTextColor(getResources().getColor(R.color.darker_gray));
        }
    }

    @OnClick(R.id.Back)
    public void Back() {
        Intent intent = new Intent(this, Directory.class);
        startActivity(intent);
    }

    @OnClick(R.id.Save)
    public void onViewClicked() {
        Log.e("color", "1");
        String s = mAdd_contacts.getCurrentTextColor() + "";
        if (s.equals("-5592406")) {
            //颜色为灰色，不可用
//            Log.e("color", mAdd_contacts.getCurrentTextColor() + "相同不可用");
        } else {
            //颜色不为灰色可用
            Log.e("color", "2");
            ContactPerson person = new ContactPerson();
            person.setAddress(medtAddress.getText().toString());
            person.setEmail(medtEmail.getText().toString());
            person.setFirst_name(medtFirstName.getText().toString());
            person.setCompany_Name(medtCompanyName.getText().toString());
            person.setPhone_Number(medtPhoneNumber.getText().toString());
            person.setRemarks(medtRemarks.getText().toString());
            char First = Concert.getPingYin(medtSurname.getText().toString()).charAt(0);
            person.setSurname(First+medtSurname.getText().toString());
            person.setTelePhone_Number(medtTelePhoneNumber.getText().toString());

            try {
                dao.getContactDaoOpen().create(person);
                dao.close();
                Toast toast = Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                Intent intent = new Intent(this, Directory.class);
                startActivity(intent);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//        Log.e("color", mAdd_contacts.getCurrentTextColor() + "不同可用");
    }

}


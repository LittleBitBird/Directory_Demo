package com.example.guanhuawu.directory_demo.persist;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by guanhua.wu on 2017/7/6.
 */
@DatabaseTable(tableName = "Contact_person")
public class ContactPerson {

    @DatabaseField(generatedId = true)
    private Integer Contact_person_id;

    @DatabaseField
    private String Surname;

    @DatabaseField
    private String first_name;

    @DatabaseField
    private String Company_Name;

    @DatabaseField
    private String Phone_Number;

    @DatabaseField
    private String TelePhone_Number;

    @DatabaseField
    private String Email;

    @DatabaseField(columnName = "address")
    private String Address;

    @DatabaseField
    private String Remarks;

    public ContactPerson() {
    }

    public ContactPerson(Integer contact_person_id, String surname, String first_name, String company_Name, String phone_Number, String telePhone_Number, String email, String address, String remarks) {
        Contact_person_id = contact_person_id;
        Surname = surname;
        this.first_name = first_name;
        Company_Name = company_Name;
        Phone_Number = phone_Number;
        TelePhone_Number = telePhone_Number;
        Email = email;
        Address = address;
        Remarks = remarks;
    }

    public Integer getContact_person_id() {
        return Contact_person_id;
    }

    public void setContact_person_id(Integer contact_person_id) {
        Contact_person_id = contact_person_id;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getCompany_Name() {
        return Company_Name;
    }

    public void setCompany_Name(String company_Name) {
        Company_Name = company_Name;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        Phone_Number = phone_Number;
    }

    public String getTelePhone_Number() {
        return TelePhone_Number;
    }


    public void setTelePhone_Number(String telePhone_Number) {
        TelePhone_Number = telePhone_Number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    @Override
    public String toString() {
        return "ContactPerson{" +
                "Contact_person_id=" + Contact_person_id +
                ", Surname='" + Surname + '\'' +
                ", first_name='" + first_name + '\'' +
                ", Company_Name='" + Company_Name + '\'' +
                ", Phone_Number='" + Phone_Number + '\'' +
                ", TelePhone_Number='" + TelePhone_Number + '\'' +
                ", Email='" + Email + '\'' +
                ", Address='" + Address + '\'' +
                ", Remarks='" + Remarks + '\'' +
                '}';
    }
}

package com.example.guanhuawu.directory_demo.persist;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by guanhua.wu on 2017/7/6.
 */
@DatabaseTable(tableName = "Contact_Person")
public class ContactPerson {
//    contact_person_id,surname,first_name,company_name,phone_number,telephone_number,email,address,remark
    @DatabaseField(generatedId = true, columnName = "contact_person_id")
    private Integer contactPersonId;

    @DatabaseField(columnName = "surname")
    private String surName;

    @DatabaseField(columnName = "first_name")
    private String firstName;

    @DatabaseField(columnName = "company_name")
    private String companyName;

    @DatabaseField(columnName = "phone_number")
    private String phoneNumber;

    @DatabaseField(columnName = "telephone_number")
    private String telephoneNumber;

    @DatabaseField(columnName = "email")
    private String email;

    @DatabaseField(columnName = "address")
    private String address;

    @DatabaseField(columnName = "remark")
    private String remarks;

    public ContactPerson() {
    }

    public ContactPerson(Integer contactPersonId, String surName, String firstName, String companyName, String phoneNumber, String telephoneNumber, String email, String address, String remarks) {
        this.contactPersonId = contactPersonId;
        this.surName = surName;
        this.firstName = firstName;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
        this.remarks = remarks;
    }

    public Integer getContactPersonId() {
        return contactPersonId;
    }

    public void setContactPersonId(Integer contactPersonId) {
        contactPersonId = contactPersonId;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}

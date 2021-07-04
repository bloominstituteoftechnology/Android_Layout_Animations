package com.example.lambda_school_loaner_47.contactsapp;

import java.util.ArrayList;

public class ContactsRepo {

    private ArrayList<Contacts> contacts;

    public ArrayList<Contacts> getContacts() {
        contacts = new ArrayList<>();
/*
        contacts = ContactsDao.getContacts();
*/
        return contacts;
    }
}

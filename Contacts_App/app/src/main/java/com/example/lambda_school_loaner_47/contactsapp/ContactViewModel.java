package com.example.lambda_school_loaner_47.contactsapp;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

public class ContactViewModel extends ViewModel {

    private ArrayList<Contacts> contacts;
    private ContactsRepo repo;

    public ArrayList<Contacts> loadContacts(){
        contacts = new ArrayList<>();
        repo     = new ContactsRepo();
        contacts = repo.getContacts();

        return contacts;
    }
}

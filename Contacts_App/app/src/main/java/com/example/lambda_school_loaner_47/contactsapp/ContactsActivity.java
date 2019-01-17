package com.example.lambda_school_loaner_47.contactsapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {

    RecyclerView view;
    LinearLayoutManager manager;
    ArrayList<Contacts> list;
    ContactsAdapter adapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        context = this;
        view    = findViewById(R.id.recycleView);
        list    = new ArrayList<>();



        findViewById(R.id.btnShowContacts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter = new ContactsAdapter(list);
                view.setHasFixedSize(true);
                manager = new LinearLayoutManager(context);
                view.setLayoutManager(manager);
                view.setAdapter(adapter);

                ContactsDao.getContacts(new ContactsDao.ObjectCallback<ArrayList<Contacts>>() {
                    @Override
                    public void returnContacts(ArrayList<Contacts> object) {
                        for (final Contacts contact:object) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    list.add(contact);
                                    adapter.notifyDataSetChanged();
                                }
                            });
/*
                            adapter.notifyDataSetChanged();
*/
                        }
                    }
                });
            }
        });
    }
}

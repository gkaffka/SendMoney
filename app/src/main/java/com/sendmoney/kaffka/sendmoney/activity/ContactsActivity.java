package com.sendmoney.kaffka.sendmoney.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.sendmoney.kaffka.sendmoney.R;
import com.sendmoney.kaffka.sendmoney.adapter.ContactsAdapter;
import com.sendmoney.kaffka.sendmoney.models.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaffka on 04/02/2017.
 */

public class ContactsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Contact> contactList;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        initContactsList();
        initRecyclerView();
        initToolbar();
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.contactList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ContactsAdapter(contactList));
    }

    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.contacts);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initContactsList() {
        contactList = new ArrayList<>();
        contactList.add(new Contact("Morpheus Pimpão", "1", null, 0f));
        contactList.add(new Contact("Trinity Cançada", "2", null, 0f));
        contactList.add(new Contact("The Oracle", "3", null, 0f));
        contactList.add(new Contact("Agent Smith", "4", null, 0f));
        contactList.add(new Contact("Mr. Anderson", "5", null, 0f));
        contactList.add(new Contact("Agent Jones", "6", null, 0f));
        contactList.add(new Contact("Cypher Safadão", "7", null, 0f));
        contactList.add(new Contact("Spoon Boy", "8", null, 0f));
        contactList.add(new Contact("Tank Sumido", "9", null, 0f));
        contactList.add(new Contact("Dozer Danado", "10", null, 0f));
        contactList.add(new Contact("The One", "11", null, 0f));
        contactList.add(new Contact("The Two", "12", null, 0f));
        contactList.add(new Contact("The The", "13", null, 0f));
        contactList.add(new Contact("Dujour Dujour", "14", null, 0f));
        contactList.add(new Contact("Mouse Mickey", "15", null, 0f));
        contactList.add(new Contact("Finn Thehumnan", "16", null, 0f));
        contactList.add(new Contact("Jake Thedog", "17", null, 0f));
        contactList.add(new Contact("Mr. Bombastic", "18", null, 0f));
    }
}

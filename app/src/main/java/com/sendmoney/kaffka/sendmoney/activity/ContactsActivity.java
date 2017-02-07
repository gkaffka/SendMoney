package com.sendmoney.kaffka.sendmoney.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.sendmoney.kaffka.sendmoney.R;
import com.sendmoney.kaffka.sendmoney.SendMoneyApplication;
import com.sendmoney.kaffka.sendmoney.adapter.ContactsAdapter;
import com.sendmoney.kaffka.sendmoney.models.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaffka on 04/02/2017.
 */

public class ContactsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        initRecyclerView();
        initToolbar();
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.contactList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ContactsAdapter(SendMoneyApplication.getInstance().getContacts()));
    }

    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.contacts);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}

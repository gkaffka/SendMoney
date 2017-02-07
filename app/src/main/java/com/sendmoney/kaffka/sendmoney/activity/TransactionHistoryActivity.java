package com.sendmoney.kaffka.sendmoney.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.sendmoney.kaffka.sendmoney.R;
import com.sendmoney.kaffka.sendmoney.adapter.TransfersAdapter;
import com.sendmoney.kaffka.sendmoney.models.Transfer;
import com.sendmoney.kaffka.sendmoney.net.callbacks.GetTransfersCallback;
import com.sendmoney.kaffka.sendmoney.net.jobs.GetTransfersJob;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by kaffka on 04/02/2017.
 */

public class TransactionHistoryActivity extends AppCompatActivity implements GetTransfersCallback {

    private RecyclerView recyclerView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        initRecyclerView();
        initToolbar();
        initService();
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.transferList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.history);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onGetTransfersSuccess(List<Transfer> transfers) {
        Collections.reverse(transfers);
        recyclerView.setAdapter(new TransfersAdapter(transfers));
    }

    @Override
    public void onGetTransfersError() {

    }

    private void initService() {
        new GetTransfersJob(this, this).getTransfers();
    }
}

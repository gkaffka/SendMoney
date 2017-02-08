package com.sendmoney.kaffka.sendmoney.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.sendmoney.kaffka.sendmoney.R;
import com.sendmoney.kaffka.sendmoney.adapter.TransfersAdapter;
import com.sendmoney.kaffka.sendmoney.models.Transfer;
import com.sendmoney.kaffka.sendmoney.net.callbacks.GetTransfersCallback;
import com.sendmoney.kaffka.sendmoney.net.jobs.GetTransfersJob;

import java.util.Collections;
import java.util.List;

/**
 * Created by kaffka on 04/02/2017.
 */

public class TransactionHistoryActivity extends AppCompatActivity implements GetTransfersCallback {

    private RecyclerView recyclerView;
    private TextView status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        initViews();
        initToolbar();
        initService();
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.transferList);
        status = (TextView) findViewById(R.id.text_loading);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        status.setText(R.string.error);
    }

    private void initService() {
        new GetTransfersJob(this, this).getTransfers();
    }
}

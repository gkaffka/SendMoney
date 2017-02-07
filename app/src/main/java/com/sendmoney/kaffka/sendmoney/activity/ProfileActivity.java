package com.sendmoney.kaffka.sendmoney.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sendmoney.kaffka.sendmoney.Preferences;
import com.sendmoney.kaffka.sendmoney.R;
import com.sendmoney.kaffka.sendmoney.databinding.ActivityProfileBinding;
import com.sendmoney.kaffka.sendmoney.net.callbacks.GenerateTokenCallback;
import com.sendmoney.kaffka.sendmoney.net.jobs.GenerateTokenJob;
import com.sendmoney.kaffka.sendmoney.viewmodels.ActivityProfileViewModel;

public class ProfileActivity extends AppCompatActivity implements GenerateTokenCallback {

    private ActivityProfileBinding activityProfileBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        activityProfileBinding.setViewmodel(new ActivityProfileViewModel(this));
        initTokenService();
    }

    private void initTokenService(){
        new GenerateTokenJob(this).generateToken(getString(R.string.user_name), getString(R.string.user_email));
    }

    @Override
    public void onTokenGenerateSuccess(String token) {
        new Preferences(this).setToken(token);
        activityProfileBinding.textLoading.setVisibility(View.GONE);
        activityProfileBinding.buttonsLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onTokenGenerateError() {
        activityProfileBinding.textLoading.setText(R.string.error);
    }
}

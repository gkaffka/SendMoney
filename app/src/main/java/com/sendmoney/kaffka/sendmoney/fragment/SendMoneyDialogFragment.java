package com.sendmoney.kaffka.sendmoney.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sendmoney.kaffka.sendmoney.R;
import com.sendmoney.kaffka.sendmoney.databinding.SendMoneyDialogBinding;
import com.sendmoney.kaffka.sendmoney.models.Contact;
import com.sendmoney.kaffka.sendmoney.net.callbacks.SendMoneyCallback;
import com.sendmoney.kaffka.sendmoney.net.jobs.SendMoneyJob;
import com.sendmoney.kaffka.sendmoney.viewmodels.SendMoneyDialogViewModel;

/**
 * Created by kaffka on 06/02/2017.
 */

public class SendMoneyDialogFragment extends DialogFragment implements SendMoneyCallback {

    private SendMoneyDialogBinding sendMoneyDialogBindin;
    private Contact contact;

    public static SendMoneyDialogFragment newInstance(Contact contact) {
        SendMoneyDialogFragment f = new SendMoneyDialogFragment();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("name", contact.getName());
        args.putLong("id", contact.getId());
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_DeviceDefault_Dialog);
        initContact();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sendMoneyDialogBindin = DataBindingUtil.inflate(inflater, R.layout.send_money_dialog, container, false);
        sendMoneyDialogBindin.setViewmodel(new SendMoneyDialogViewModel(contact));
        View v = sendMoneyDialogBindin.getRoot();
        sendMoneyDialogBindin.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SendMoneyJob(SendMoneyDialogFragment.this, v.getContext()).sendMoney(contact.getId(), Double.parseDouble(sendMoneyDialogBindin.edittextValue.getText().toString()));
                sendMoneyDialogBindin.textLoading.setVisibility(View.VISIBLE);
            }
        });
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    private void initContact() {
        contact = new Contact(getArguments().getString("name"), getArguments().getLong("id"));
    }

    @Override
    public void onSendMoneySuccess() {
        sendMoneyDialogBindin.textLoading.setText(R.string.money_sent_success);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getDialog().dismiss();
            }
        },1000);

    }

    @Override
    public void onSendMoneyError() {
        sendMoneyDialogBindin.textLoading.setText(R.string.error);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getDialog().dismiss();
            }
        },1000);
    }
}

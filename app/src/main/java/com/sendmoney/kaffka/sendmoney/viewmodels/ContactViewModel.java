package com.sendmoney.kaffka.sendmoney.viewmodels;

import android.app.Activity;


import android.content.Context;
import android.databinding.BaseObservable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sendmoney.kaffka.sendmoney.fragment.SendMoneyDialogFragment;
import com.sendmoney.kaffka.sendmoney.models.Contact;

/**
 * Created by kaffka on 05/02/2017.
 */

public class ContactViewModel extends BaseObservable {

    private Contact contact;

    public ContactViewModel(Contact contact) {
        this.contact = contact;
    }

    public String getContactInitials() {
        String name[] = contact.getName().split(" ");
        return name[0].substring(0, 1) + name[1].substring(0, 1);
    }

    public int getContactInitialsColor() {
        int[] colors = new int[]{
                0xff00ff2b, 0xffaa0000, 0xff0000aa, 0xffffb54c,
                0xffffcc33, 0xffe64a45, 0xff2ecc71, 0xff2eccc4};
        return colors[Math.abs(contact.getName().hashCode()) % colors.length];
    }

    public String getContactName() {
        return contact.getName();
    }

    public View.OnClickListener getOpenSendMoneyDialog() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog((AppCompatActivity) v.getContext());
            }
        };
    }

    void showDialog(AppCompatActivity context) {
        FragmentTransaction ft = context.getSupportFragmentManager().beginTransaction();
        Fragment prev = context.getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DialogFragment newFragment = SendMoneyDialogFragment.newInstance(contact);
        newFragment.show(ft, "dialog");
    }
}

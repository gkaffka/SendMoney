package com.sendmoney.kaffka.sendmoney.viewmodels;

import android.databinding.BaseObservable;
import android.view.View;

import com.sendmoney.kaffka.sendmoney.models.Contact;

/**
 * Created by kaffka on 05/02/2017.
 */

public class SendMoneyDialogViewModel extends BaseObservable {

    private Contact contact;

    public SendMoneyDialogViewModel(Contact contact) {
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
}

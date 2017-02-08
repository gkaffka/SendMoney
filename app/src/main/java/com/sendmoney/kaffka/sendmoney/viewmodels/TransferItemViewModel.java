package com.sendmoney.kaffka.sendmoney.viewmodels;

import android.databinding.BaseObservable;

import com.sendmoney.kaffka.sendmoney.models.Contact;
import com.sendmoney.kaffka.sendmoney.models.Transfer;

import java.text.SimpleDateFormat;

/**
 * Created by kaffka on 05/02/2017.
 */

public class TransferItemViewModel extends BaseObservable {

    private final Contact contact;
    private final Transfer transfer;

    public TransferItemViewModel(Contact contact, Transfer transfer) {
        this.contact = contact;
        this.transfer = transfer;
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

    public String getDate() {
        try {
            SimpleDateFormat dateFromServer = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.ss");
            SimpleDateFormat dateFormatted = new SimpleDateFormat("dd 'de' MMM");
            return dateFormatted.format(dateFromServer.parse(transfer.getData()));
        } catch (Exception e) {
            return "";
        }
    }

    public String getValue() {
        return "R$ " + String.valueOf((int) transfer.getValue());
    }
}

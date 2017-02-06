package com.sendmoney.kaffka.sendmoney.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sendmoney.kaffka.sendmoney.R;
import com.sendmoney.kaffka.sendmoney.databinding.ContactItemBinding;
import com.sendmoney.kaffka.sendmoney.models.Contact;
import com.sendmoney.kaffka.sendmoney.viewmodels.ContactViewModel;

import java.util.List;

/**
 * Created by kaffka on 04/02/2017.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {

    private List<Contact> contactList;
    private Context context;

    public ContactsAdapter(List<Contact> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.contactItemBinding.setViewmodel(new ContactViewModel(contactList.get(position), context));
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        public final ContactItemBinding contactItemBinding;

        public ContactViewHolder(View v) {
            super(v);
            contactItemBinding = ContactItemBinding.bind(v);
        }
    }
}

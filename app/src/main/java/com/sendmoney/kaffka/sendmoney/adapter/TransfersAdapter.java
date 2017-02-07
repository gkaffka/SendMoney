package com.sendmoney.kaffka.sendmoney.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sendmoney.kaffka.sendmoney.R;
import com.sendmoney.kaffka.sendmoney.SendMoneyApplication;
import com.sendmoney.kaffka.sendmoney.databinding.TransferItemBinding;
import com.sendmoney.kaffka.sendmoney.models.Contact;
import com.sendmoney.kaffka.sendmoney.models.Transfer;
import com.sendmoney.kaffka.sendmoney.viewmodels.TransferItemViewModel;

import java.util.List;

/**
 * Created by kaffka on 04/02/2017.
 */

public class TransfersAdapter extends RecyclerView.Adapter<TransfersAdapter.TransferViewHolder> {

    private List<Transfer> transferList;

    public TransfersAdapter(List<Transfer> transferList) {
        this.transferList = transferList;
    }

    @Override
    public TransfersAdapter.TransferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TransfersAdapter.TransferViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.transfer_item, parent, false));
    }

    @Override
    public void onBindViewHolder(TransfersAdapter.TransferViewHolder holder, int position) {
        holder.transferItemBinding.setViewmodel(new TransferItemViewModel(getContactById(transferList.get(position).getClientId()), transferList.get(position)));
    }

    @Override
    public int getItemCount() {
        return transferList.size();
    }

    public static class TransferViewHolder extends RecyclerView.ViewHolder {

        public final TransferItemBinding transferItemBinding;

        public TransferViewHolder(View v) {
            super(v);
            transferItemBinding = TransferItemBinding.bind(v);
        }
    }

    private Contact getContactById(long id) {
        for (Contact contact : SendMoneyApplication.getInstance().getContacts())
            if (contact.getId() == id) return contact;

        return null;
    }
}


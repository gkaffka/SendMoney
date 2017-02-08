package com.sendmoney.kaffka.sendmoney.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sendmoney.kaffka.sendmoney.R;
import com.sendmoney.kaffka.sendmoney.SendMoneyApplication;
import com.sendmoney.kaffka.sendmoney.databinding.TransferItemBinding;
import com.sendmoney.kaffka.sendmoney.models.Contact;
import com.sendmoney.kaffka.sendmoney.models.Transfer;
import com.sendmoney.kaffka.sendmoney.viewmodels.TransferItemViewModel;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by kaffka on 04/02/2017.
 */

public class TransfersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Transfer> transferList;
    private static final int VIEW_TYPE_GRAPH = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    public TransfersAdapter(List<Transfer> transferList) {
        this.transferList = transferList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;

        switch (viewType) {
            case VIEW_TYPE_ITEM:
                viewHolder = new TransferViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.transfer_item, parent, false));
                break;
            case VIEW_TYPE_GRAPH:
                viewHolder = new TransferGraphViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.graph_view, parent, false));
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TransferViewHolder)
            ((TransferViewHolder) holder).transferItemBinding.setViewmodel(new TransferItemViewModel(getContactById(transferList.get(position - 1).getClientId()), transferList.get(position - 1)));
        else
            generateGraph((TransferGraphViewHolder) holder);
    }

    @Override
    public int getItemCount() {
        return transferList.size() + 1;
    }

    public static class TransferViewHolder extends RecyclerView.ViewHolder {

        public final TransferItemBinding transferItemBinding;

        public TransferViewHolder(View v) {
            super(v);
            transferItemBinding = TransferItemBinding.bind(v);
        }
    }

    public static class TransferGraphViewHolder extends RecyclerView.ViewHolder {

        public final LinearLayout graph;

        public TransferGraphViewHolder(View v) {
            super(v);
            graph = (LinearLayout) v.findViewById(R.id.graph_view);
        }
    }

    private Contact getContactById(long id) {
        for (Contact contact : SendMoneyApplication.getInstance().getContacts())
            if (contact.getId() == id) return contact;
        return null;
    }

    private int getItemType(int position) {
        if (position == 0) return VIEW_TYPE_GRAPH;
        else return VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemViewType(int position) {
        return getItemType(position);
    }

    private void generateGraph(TransferGraphViewHolder holder) {
        double biggest = 0;
        holder.graph.removeAllViews();
        SendMoneyApplication.getInstance().resetContactsTotals();
        for (Transfer c : transferList) {
            Contact contact = getContactById(c.getClientId());
            double currentValue = contact.getTotal() + c.getValue();
            contact.setTotal(currentValue);
            biggest = biggest > currentValue ? biggest : currentValue;
        }
        Collections.sort(SendMoneyApplication.getInstance().getContacts(), new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                return (int) o2.getTotal() - (int) o1.getTotal();
            }
        });
        for (Contact c : SendMoneyApplication.getInstance().getContacts()) {
            if (c.getTotal() > 0)
                holder.graph.addView(initGraphItem(holder.graph.getContext(), c, c.getTotal(), biggest));
        }
    }

    private View initGraphItem(Context context, Contact contact, double total, double biggest) {
        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout lin = (LinearLayout) inflater.inflate(R.layout.graph_item_view, null, false);
        TextView contactInitial = (TextView) lin.findViewById(R.id.textContactInitial);
        TextView contactBar = (TextView) lin.findViewById(R.id.textGraphBar);
        TextView contactValue = (TextView) lin.findViewById(R.id.tetxContactTotal);
        String totalFormatted = total > 1000 ? String.valueOf(total).substring(0, 3) + "k" : (int) total + "";
        contactValue.setText(totalFormatted);
        contactInitial.setText(getContactInitials(contact.getName()));
        contactInitial.setTextColor(getContactInitialsColor(contact.getName()));
        double reference = 16 / biggest;
        contactBar.setText(getGraphBar((int) (total == 0 ? 1 : total * reference)));
        return lin;
    }

    private String getContactInitials(String nome) {
        String name[] = nome.split(" ");
        return name[0].substring(0, 1) + name[1].substring(0, 1);
    }

    private int getContactInitialsColor(String name) {
        int[] colors = new int[]{
                0xff00ff2b, 0xffaa0000, 0xff0000aa, 0xffffb54c,
                0xffffcc33, 0xffe64a45, 0xff2ecc71, 0xff2eccc4};
        return colors[Math.abs(name.hashCode()) % colors.length];
    }

    private String getGraphBar(int value) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < value; i++) {
            builder.append(i % 3 == 0 ? "$" : "@");
            builder.append("\n");
        }
        return builder.toString();
    }

}


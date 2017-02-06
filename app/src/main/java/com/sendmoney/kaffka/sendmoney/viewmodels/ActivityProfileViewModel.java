package com.sendmoney.kaffka.sendmoney.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sendmoney.kaffka.sendmoney.R;
import com.sendmoney.kaffka.sendmoney.activity.ContactsActivity;
import com.sendmoney.kaffka.sendmoney.activity.TransactionHistoryActivity;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by kaffka on 05/02/2017.
 */

public class ActivityProfileViewModel extends BaseObservable {

    private Context context;

    public ActivityProfileViewModel(Context context) {
        this.context = context;
    }

    public String getAvatarUrl() {
        return context.getResources().getString(R.string.user_avatar_url);
    }

    public String getUserEmail(){
        return context.getResources().getString(R.string.user_email);
    }

    public String getUserName(){
        return context.getResources().getString(R.string.user_name);
    }

    public View.OnClickListener getSendMoneyToContactClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ContactsActivity.class));
            }
        };
    }

    public View.OnClickListener getOpenTransactionHistoryClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, TransactionHistoryActivity.class));
            }
        };
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_person_placeholder)
                .bitmapTransform(new CropCircleTransformation(view.getContext()))
                .into(view);
    }
}

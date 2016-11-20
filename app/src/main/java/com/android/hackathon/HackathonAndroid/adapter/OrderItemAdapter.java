package com.android.hackathon.HackathonAndroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.android.hackathon.HackathonAndroid.R;
import com.android.hackathon.HackathonAndroid.modle.ShoppingCard;


import java.util.ArrayList;


import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zzbpc on 2016/11/20.
 */

public class OrderItemAdapter extends RecyclerView.Adapter {
    private ArrayList<ShoppingCard> list;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderdetail_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ShoppingCard shoppingCard = list.get(position);
        ListViewHolder listViewHolder = (ListViewHolder) holder;
        listViewHolder.title.setText("订单号为:"+ shoppingCard.getCardId());
        String content = position+". ";
        content +=shoppingCard.getProductName()+shoppingCard.getProductNumber()+"件*"+shoppingCard.getProductPrice();


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public ArrayList<ShoppingCard> getList() {
        return list;
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.detail)
        TextView detail;
        @Bind(R.id.order_title)
        TextView title;

        public ListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

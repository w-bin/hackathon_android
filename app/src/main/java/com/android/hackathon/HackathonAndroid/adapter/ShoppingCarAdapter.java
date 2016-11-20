package com.android.hackathon.HackathonAndroid.adapter;

import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.hackathon.HackathonAndroid.R;
import com.android.hackathon.HackathonAndroid.modle.ShoppingCard;
import com.android.hackathon.HackathonAndroid.ui.activity.ShoppingCars;
import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zzbpc on 2016/11/20.
 */

public class ShoppingCarAdapter extends RecyclerView.Adapter implements Serializable {
    private List<ShoppingCard> list;

    public interface OnItemClickListener {
        public void onClickAdd();

        public void onClickSub();
    }

    ;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoppingcar_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final ShoppingCard shoppingCard = list.get(position);
        ListViewHolder listViewHolder = (ListViewHolder) holder;
        Glide.with(holder.itemView.getContext()).load(shoppingCard.getImgUrl().get(0)).into(listViewHolder.imageView);
        listViewHolder.productNametv.setText(shoppingCard.getProductName());
        listViewHolder.productDestv.setText(shoppingCard.getProductDescribe());
        listViewHolder.productPricetv.setText(shoppingCard.getProductPrice() + "");
        listViewHolder.checkBox.setChecked(shoppingCard.isChoosed());
        listViewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoppingCard.setChoosed(!shoppingCard.isChoosed());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void addAll(ArrayList<ShoppingCard> lists) {
        if (list == null)
            list = new ArrayList<ShoppingCard>();
        list.addAll(lists);
        this.notifyDataSetChanged();
    }

    public ArrayList<ShoppingCard> getChoosed(){
        ArrayList<ShoppingCard> lists = new ArrayList<ShoppingCard>();
        for(ShoppingCard shoppingCard : list){
            if(shoppingCard.isChoosed()) {
                shoppingCard.setBuyNumber(1);
                lists.add(shoppingCard);
            }
        }
        return lists;
    }

    public void addItem(ShoppingCard item) {
        if (list == null)
            list = new ArrayList<ShoppingCard>();
        list.add(item);
        this.notifyDataSetChanged();
    }

    public void clear() {
        if (list == null)
            list = new ArrayList<ShoppingCard>();
        list.clear();
        this.notifyDataSetChanged();
    }

    public void choose_all(boolean ischoosed) {
        if (list == null)
            list = new ArrayList<ShoppingCard>();
        for (ShoppingCard shoppingCard : list) {
            shoppingCard.setChoosed(ischoosed);
        }
        this.notifyDataSetChanged();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img)
        ImageView imageView;
        @Bind(R.id.producttv)
        TextView productNametv;
        @Bind(R.id.productDes)
        TextView productDestv;
        @Bind(R.id.pricetv)
        TextView productPricetv;
        @Bind(R.id.isChecked)
        CheckBox checkBox;
        public ListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

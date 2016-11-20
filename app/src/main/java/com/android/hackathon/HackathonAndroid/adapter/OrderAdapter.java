package com.android.hackathon.HackathonAndroid.adapter;

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
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zzbpc on 2016/11/20.
 */

public class OrderAdapter extends RecyclerView.Adapter {
    private ArrayList<ShoppingCard> list;
    private int money;

    public int getMoney() {
        return money;
    }



    public interface OnItemClickListener {
        public void onClickAdd(int price);

        public void onClickSub(int price );
    }

    ;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final ShoppingCard shoppingCard = list.get(position);
        final ListViewHolder listViewHolder = (ListViewHolder) holder;
        Glide.with(holder.itemView.getContext()).load(shoppingCard.getImgUrl().get(0)).into(listViewHolder.imageView);
        listViewHolder.productNametv.setText(shoppingCard.getProductName());
        listViewHolder.productDestv.setText(shoppingCard.getProductDescribe());
        listViewHolder.productPricetv.setText(shoppingCard.getProductPrice() + "");
        listViewHolder.editText.setText(shoppingCard.getBuyNumber() + "");
        listViewHolder.remaintv.setText("库存还剩下 :"+shoppingCard.getProductNumber()+"件");
        listViewHolder.textView.setText("总价 : "+ shoppingCard.getBuyNumber()+" * "+ shoppingCard.getProductPrice()+" = "+shoppingCard.getBuyNumber()*shoppingCard.getProductPrice());
        listViewHolder.increasebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(shoppingCard.getBuyNumber()<shoppingCard.getProductNumber()) {
                    shoppingCard.setBuyNumber(shoppingCard.getBuyNumber() + 1);
                    notifyDataSetChanged();
                    onItemClickListener.onClickAdd(shoppingCard.getProductPrice());
                }
            }
        });
        listViewHolder.decreasebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(shoppingCard.getBuyNumber()>1) {
                    shoppingCard.setBuyNumber(shoppingCard.getBuyNumber() - 1);
                    notifyDataSetChanged();
                    onItemClickListener.onClickSub(shoppingCard.getProductPrice());
                }
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
        for(ShoppingCard shoppingCard : lists){
            money+=shoppingCard.getProductPrice();
        }
        this.notifyDataSetChanged();
    }

    public ArrayList<ShoppingCard> getChoosed(){
        ArrayList<ShoppingCard> lists = new ArrayList<ShoppingCard>();
        for(ShoppingCard shoppingCard : list){
            if(shoppingCard.isChoosed()) {
                shoppingCard.setBuyNumber(shoppingCard.getSelectedNumber());
                lists.add(shoppingCard);
            }
        }
        return lists;
    }




    public  ArrayList<ShoppingCard> getList(){
        return list;
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
        @Bind(R.id.number)
        EditText editText;
        @Bind(R.id.increasebtn)
        Button increasebtn;
        @Bind(R.id.decreasebtn)
        Button decreasebtn;
        @Bind(R.id.sum_of_one)
        TextView textView;
        @Bind(R.id.productRemain)
        TextView remaintv;
        public ListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

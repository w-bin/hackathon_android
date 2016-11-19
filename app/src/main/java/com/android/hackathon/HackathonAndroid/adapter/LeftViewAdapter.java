package com.android.hackathon.HackathonAndroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.hackathon.HackathonAndroid.R;
import com.android.hackathon.HackathonAndroid.modle.Product;
import com.android.hackathon.HackathonAndroid.modle.ProductKind;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zzbpc on 2016/11/19.
 */

public class LeftViewAdapter extends RecyclerView.Adapter {
    private  List<ProductKind> list ;
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(  ProductKind kind);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.left_list_item,parent,false);
        return new LeftViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ProductKind kind =list.get(position);
        ((LeftViewHolder)holder).textView.setText(kind.getKindName());
        ((LeftViewHolder)holder).textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(kind);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null?0:list.size();
    }

    public void addItem(ProductKind kind){
        if(list==null)
            list=new ArrayList<ProductKind>();
        list.add(kind);
        this.notifyDataSetChanged();
    }

    public void addAll(ArrayList<ProductKind> kinds){
        if(list==null)
            list=new ArrayList<ProductKind>();
        list.addAll(kinds);
        this.notifyDataSetChanged();
    }



    class LeftViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.left_list_kind)
        TextView textView;

        public LeftViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

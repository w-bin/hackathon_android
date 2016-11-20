package com.android.hackathon.HackathonAndroid.adapter;

import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.hackathon.HackathonAndroid.R;
import com.android.hackathon.HackathonAndroid.modle.Product;
import com.android.hackathon.HackathonAndroid.modle.ProductKind;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.w3c.dom.Text;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zzbpc on 2016/11/19.
 */

public class RightViewAdapter extends RecyclerView.Adapter {

    private List<Product> list;
    private static final int HEAD = 0;
    private static final int CONTENT = 1;
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(Product product);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType == HEAD){
            view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.righ_list_head,parent,false);
            return new HeadViewHolder(view);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.right_list_item,parent,false);
            return new RightViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Product product = list.get(position);
        if (holder instanceof HeadViewHolder) {
            ((HeadViewHolder) holder).banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
            List<String> titleList = new ArrayList<>();
            List<String> imgList = new ArrayList<>();

            ((HeadViewHolder) holder).banner.setBannerTitleList(titleList);
            ((HeadViewHolder) holder).banner.setImages(imgList);
        }else{
            Glide.with(holder.itemView.getContext()).load(product.getImgUrl().get(0)).into(((RightViewHolder)holder).image);
            ((RightViewHolder) holder).producttv.setText(product.getName());
            ((RightViewHolder) holder).pricetv.setText(product.getPrice());
            ((RightViewHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(product);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list==null? 0:list.size();
    }

    @Override
    public int getItemViewType(int position) {
//        if(position==0)
//            return HEAD;
       return CONTENT;
    }

    public void clearList (){
        if(list==null)
            list = new ArrayList<Product>();
        list.clear();
    }
    public  void addItem(Product product){
        if(list==null)
            list = new ArrayList<Product>();
        list.add(product);
        this.notifyDataSetChanged();
    }

    public void addAll(ArrayList<Product> products){
        if(list==null)
            list = new ArrayList<Product>();
        list.addAll(products);
        this.notifyDataSetChanged();
    }

    class RightViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.img)
        ImageView image;
        @Bind(R.id.producttv)
        TextView  producttv;
        @Bind(R.id.pricetv)
        TextView pricetv;
        @Bind(R.id.card_view)
        CardView cardView;
        public RightViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class HeadViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.banner)
        Banner banner;
        public HeadViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

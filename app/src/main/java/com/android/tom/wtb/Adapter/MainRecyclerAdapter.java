package com.android.tom.wtb.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.tom.wtb.Model.BillDetail;
import com.android.tom.wtb.R;
import com.android.tom.wtb.Utils.DateUtil;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;


import java.util.List;

/**
 * Created by tom on 14/12/11.
 */
public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> implements StickyRecyclerHeadersAdapter<MainRecyclerAdapter.HeaderViewHolder> {

    private List<BillDetail> mData;

    public MainRecyclerAdapter(List<BillDetail> list) {
        this.mData = list;
    }

    @Override
    public long getHeaderId(int i) {
        BillDetail detail = mData.get(i);
        return DateUtil.timeWith(detail.getDate());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {
        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
        View view = View.inflate(viewGroup.getContext(), R.layout.item_main_header, null);
        // 创建一个ViewHolder
        HeaderViewHolder holder = new HeaderViewHolder(view);
        return holder;
    }

    @Override
    public void onBindHeaderViewHolder(HeaderViewHolder viewHolder, int i) {
        viewHolder.tvDate.setText(mData.get(i).getDate());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
        View view = View.inflate(parent.getContext(), R.layout.item_main_cell, null);
        // 创建一个ViewHolder
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvPrice.setText(mData.get(position).getPrice()+"");
        holder.tvTitle.setText(mData.get(position).getTitle());
        holder.itemView.setTag(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvPrice;
        public View itemView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            itemView.setOnClickListener(itemClickListener);
            tvTitle = (TextView) itemView.findViewById(R.id.title);
            tvPrice = (TextView) itemView.findViewById(R.id.price);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDate;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            tvDate = (TextView) itemView.findViewById(R.id.date);
        }
    }

    private View.OnClickListener itemClickListener;

    public void setItemClickListener(View.OnClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
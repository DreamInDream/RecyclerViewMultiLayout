package com.example.maqiang8.recyclerviewmultilayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Map;

public class ChannelRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Map<String, Object>> channelList;
    public ChannelRVAdapter(Context context, List<Map<String, Object>> channelList){
        this.context=context;
        this.channelList=channelList;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((ChannelSubHolder)holder).tv_channel.setText(channelList.get(position).get("title").toString());
        Glide.with(context).load(channelList.get(position).get("pic")).into(((ChannelSubHolder)holder).iv);
        ((ChannelSubHolder)holder).ll_channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"频道"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_channel_subitem,null);
        return new ChannelSubHolder(view);
    }

    @Override
    public int getItemCount() {
        return channelList.size();
    }

    public static class ChannelSubHolder extends RecyclerView.ViewHolder {
         ImageView iv;
         TextView tv_channel;
         LinearLayout ll_channel;
        public ChannelSubHolder(View itemView) {
            super(itemView);
            iv =  itemView.findViewById(R.id.iv);
            tv_channel=itemView.findViewById(R.id.tv_channel);
            ll_channel=itemView.findViewById(R.id.ll_channel);

        }
    }
}

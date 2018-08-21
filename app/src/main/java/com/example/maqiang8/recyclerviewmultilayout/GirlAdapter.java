package com.example.maqiang8.recyclerviewmultilayout;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GirlAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Integer> girlList;
    public GirlAdapter(Context context, List<Integer> girlList){
        this.context=context;
        this.girlList=girlList;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((GirlSubHolder)holder).iv.setImageResource(girlList.get(position));
        ((GirlSubHolder)holder).tv_girl.setText("美女"+position);
        ((GirlSubHolder)holder).ll_girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"美女"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_girl_subitem,null);
        return new GirlSubHolder(view);
    }

    @Override
    public int getItemCount() {
        if (girlList.size()>=6) {
            return 6;
        }
        else {
            return girlList.size();
        }
    }
    public static class GirlSubHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv_girl;
        LinearLayout ll_girl;
        public GirlSubHolder(View itemView) {
            super(itemView);
            iv =  itemView.findViewById(R.id.iv);
            tv_girl=itemView.findViewById(R.id.tv_girl);
            ll_girl=itemView.findViewById(R.id.ll_girl);
            int b=0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return 6;
                }
            });
        }
    }
}

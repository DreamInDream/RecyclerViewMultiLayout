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

import java.util.List;

public class ManAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Integer> manList;
    public ManAdapter(Context context, List<Integer> girlList){
        this.context=context;
        this.manList=girlList;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((ManSubHolder)holder).iv.setImageResource(manList.get(position));
        ((ManSubHolder)holder).tv_man.setText("帅哥"+position);
        ((ManSubHolder)holder).ll_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"帅哥"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_man_subitem,null);
        return new ManSubHolder(view);
    }

    @Override
    public int getItemCount() {
        if (manList.size()>=6) {
            return 6;
        }
        else {
            return manList.size();
        }
    }
    public static class ManSubHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv_man;
        LinearLayout ll_man;
        public ManSubHolder(View itemView) {
            super(itemView);
            iv =  itemView.findViewById(R.id.iv);
            tv_man=itemView.findViewById(R.id.tv_man);
            ll_man=itemView.findViewById(R.id.ll_man);
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
                    return 4;
                }
            });
        }
    }
}

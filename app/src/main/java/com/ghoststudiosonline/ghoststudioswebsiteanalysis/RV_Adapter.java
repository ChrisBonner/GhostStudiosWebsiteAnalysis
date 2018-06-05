package com.ghoststudiosonline.ghoststudioswebsiteanalysis;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RV_Adapter extends RecyclerView.Adapter<RV_Adapter.ViewHolder> {

    private ArrayList<String> mBl_item = new ArrayList<>();
    private Context mContext;

    public RV_Adapter(Context context, ArrayList<String> bl_item) {
        mContext = context;
        mBl_item = bl_item;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d("myTag", "onBindViewHolder: called.");

        holder.bl_item.setText(mBl_item.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("myTag", "Clicked on: " + mBl_item.get(position));
                Toast.makeText(mContext,  mBl_item.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBl_item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView bl_item;
        RelativeLayout parentLayout;


        public ViewHolder(View itemView) {
            super(itemView);

            bl_item = itemView.findViewById(R.id.blacklist_item_text);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

}

package org.imfine.fas.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import org.imfine.fas.R;
import org.imfine.fas.data.Accident;
import org.imfine.fas.data.Oldman;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder>{

    Context context;
    ArrayList<Accident> datas2 = new ArrayList<>();

    public RecordAdapter(Context context, ArrayList<Accident> datas2) {
        this.context = context;
        this.datas2 = datas2;
    }

    @NonNull
    @Override
    public RecordAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_oldman_record,
                parent,false);
        return new RecordAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.ViewHolder holder, int position) {
        final Accident data2 = datas2.get(position);

        Glide.with(context).load("https://firebasestorage.googleapis.com/v0/b/fas-imfine.appspot.com/o/userImage%2F"+"-Lo40k9AiDa3tTMU_PZh"+"?alt=media")
                .into(holder.profileImg);
        holder.name.setText(data2.getName());
        holder.time.setText(data2.getTime());
        holder.value.setText(data2.getValue());


    }

    @Override
    public int getItemCount() {
        return datas2==null? 0:datas2.size();
        //return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout root;
        ImageView profileImg;
        TextView name;
        TextView time;
        TextView value;
        TextView age;
        TextView location;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            root = itemView.findViewById(R.id.oldman_record_ll_root);
            profileImg = itemView.findViewById(R.id.oldman_record_iv_profile);
            name = itemView.findViewById(R.id.oldman_record_tv_name);
            time = itemView.findViewById(R.id.oldman_record_tv_time);
            value = itemView.findViewById(R.id.oldman_record_tv_value);
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


        }
    }


}
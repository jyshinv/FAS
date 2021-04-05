package org.imfine.fas.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import org.imfine.fas.DouriCCTV2Activity;
import org.imfine.fas.DouriChecking2Activity;
import org.imfine.fas.DouriCheckingActivity;
import org.imfine.fas.R;
import org.imfine.fas.data.Oldman;

import java.util.ArrayList;

public class CCTVAdapter extends RecyclerView.Adapter<CCTVAdapter.ViewHolder> {

    Context context;
    ArrayList<Oldman> datas = new ArrayList<>(); //초기화 꼭 해줘야함 (명시적으로)

    public CCTVAdapter(Context context, ArrayList<Oldman> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public CCTVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_oldman2,
                parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CCTVAdapter.ViewHolder holder, int position) {
        final Oldman data = datas.get(position);
        Glide.with(context).load("https://firebasestorage.googleapis.com/v0/b/fas-imfine.appspot.com/o/userImage%2F"+data.getId()+"?alt=media")
                .into(holder.profileImg);
        holder.name.setText(data.getName() + " 님의 CCTV");

    }

    @Override
    public int getItemCount() {
        return datas==null? 0:datas.size();
        //return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout root;
        ImageView profileImg;
        TextView name;
        TextView age;
        TextView location;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            root = itemView.findViewById(R.id.oldman2_item_ll_root);
            profileImg = itemView.findViewById(R.id.oldman2_items_iv_profile);
            name = itemView.findViewById(R.id.oldman2_items_tv_name);

            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, ""+datas.get(getAdapterPosition()).getId(), Toast.LENGTH_SHORT).show();

                    //원래코드 : 클릭하면 자세한 정보로 넘어감
                    //Intent intent = new Intent(context, DouriCCTV2Activity.class);
                    //intent.putExtra("uid",datas.get(getAdapterPosition()).getId());
                    //context.startActivity(intent);

                    //임시코드
                    //임시코드
                    Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://192.168.35.72:8080/?action=stream"));
                    context.startActivity(intent);


                }
            });


        }
    }
}

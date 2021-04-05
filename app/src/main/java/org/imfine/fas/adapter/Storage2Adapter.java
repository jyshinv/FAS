//package org.imfine.fas.adapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//
//import org.imfine.fas.DouriCCTV2Activity;
//import org.imfine.fas.DouriStorage2Activity;
//import org.imfine.fas.DouriChecking2Activity;
//import org.imfine.fas.DouriCheckingActivity;
//import org.imfine.fas.R;
//import org.imfine.fas.data.Storage;
//
//import java.util.ArrayList;
//
//public class Storage2Adapter extends RecyclerView.Adapter<Storage2Adapter.ViewHolder> {
//
//    Context context;
//    ArrayList<Storage> datas = new ArrayList<>(); //초기화 꼭 해줘야함 (명시적으로)
//
//    public Storage2Adapter(Context context, ArrayList<Storage> datas) {
//        this.context = context;
//        this.datas = datas;
//    }
//
//    @NonNull
//    @Override
//    public Storage2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_oldman_storage,
//                parent,false);
//        return new ViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull Storage2Adapter.ViewHolder holder, int position) {
//        final Storage data = datas.get(position);
//        holder.id.setText(data.getVideo());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return datas==null? 0:datas.size();
//        //return 10;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        LinearLayout root;
//        TextView id;
//
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            root = itemView.findViewById(R.id.oldman_storage_ll_root);
//            id = itemView.findViewById(R.id.oldman_storage_tv);
//
//            root.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//                }
//            });
//
//
//        }
//    }
//}

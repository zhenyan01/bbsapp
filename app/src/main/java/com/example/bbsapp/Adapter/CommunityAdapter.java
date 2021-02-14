package com.example.bbsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bbsapp.Bean.Community;
import com.example.bbsapp.Bean.User;
import com.example.bbsapp.R;
import com.example.bbsapp.activity.CommunityIndex;
import com.example.bbsapp.activity.Receive;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class CommunityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Community> communityList;

    private final int P_TYPE = 0;
    private final int F_TYPE = 1;

    private int maxCommunityCount = 5;
    private boolean isFoot = true;

    public CommunityAdapter(Context context, List<Community> communityList){
        this.context = context;
        this.communityList = communityList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View communityView = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_item, parent, false);
        View footView = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer, parent, false);
        if(viewType == F_TYPE){
            return new CommunityAdapter.RecyclerViewHolder(footView, F_TYPE);
        }else{
            return new CommunityAdapter.RecyclerViewHolder(communityView, P_TYPE);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(isFoot && getItemViewType(position) == F_TYPE){
            final CommunityAdapter.RecyclerViewHolder recyclerViewHolder = (CommunityAdapter.RecyclerViewHolder) holder;
            recyclerViewHolder.footer.setText("Loading...");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    maxCommunityCount += 8;
                    notifyDataSetChanged();
                }
            }, 2000);
        }else{
            final CommunityAdapter.RecyclerViewHolder recyclerViewHolder = (CommunityAdapter.RecyclerViewHolder) holder;
            Community community = communityList.get(position);
            recyclerViewHolder.c_name.setText(community.getName());
            recyclerViewHolder.c_info.setText(community.getInfo());
            recyclerViewHolder.c_owner.setText(community.getOwnerNickname());

            recyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CommunityIndex.class);
                    intent.putExtra("id", community.getObjectId());
                    intent.putExtra("c_name", community.getName());
                    intent.putExtra("c_owner", community.getOwner().getObjectId());
                    intent.putExtra("c_info", community.getInfo());
                    context.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(position == maxCommunityCount - 1){
            return F_TYPE;
        }
        return P_TYPE;
    }

    @Override
    public int getItemCount() {
        if(communityList.size() < maxCommunityCount){
            return communityList.size();
        }
        return maxCommunityCount;
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView c_owner, c_name, c_info;
        public TextView footer;

        public RecyclerViewHolder(View view, int viewType) {
            super(view);
            if(viewType  == P_TYPE){
                c_owner = view.findViewById(R.id.c_owner);
                c_info  = view.findViewById(R.id.info);
                c_name = view.findViewById(R.id.community_name);
            }else{
                footer = view.findViewById(R.id.footer);
            }
        }
    }
}

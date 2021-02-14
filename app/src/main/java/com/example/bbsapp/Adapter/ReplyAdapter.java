package com.example.bbsapp.Adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bbsapp.Bean.Post;
import com.example.bbsapp.Bean.Reply;
import com.example.bbsapp.R;

import java.util.List;

public class ReplyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<Reply> replyList;

    private final int N_TYPE = 0;
    private final int F_TYPE = 1;

    private int maxPostCount = 10;
    private boolean isFoot = true;

    public ReplyAdapter(Context context, List<Reply> replyList){
        this.context = context;
        this.replyList = replyList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View replyView = LayoutInflater.from(parent.getContext()).inflate(R.layout.reply_item, parent, false);
        View footView = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer, parent, false);
        if(viewType == F_TYPE){
            return new ReplyAdapter.RecyclerViewHolder(footView, F_TYPE);
        }else{
            return new ReplyAdapter.RecyclerViewHolder(replyView, N_TYPE);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(isFoot && getItemViewType(position) == F_TYPE){
            final ReplyAdapter.RecyclerViewHolder recyclerViewHolder = (ReplyAdapter.RecyclerViewHolder) holder;
            recyclerViewHolder.footer.setText("Loading...");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    maxPostCount += 8;
                    notifyDataSetChanged();
                }
            }, 2000);
        }else{
            final ReplyAdapter.RecyclerViewHolder recyclerViewHolder = (ReplyAdapter.RecyclerViewHolder) holder;
            Reply reply = replyList.get(position);
            recyclerViewHolder.nickname.setText(reply.getNickname());
            recyclerViewHolder.content.setText(reply.getContent());
            recyclerViewHolder.time.setText(reply.getCreatedAt());
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(position == maxPostCount - 1){
            return F_TYPE;
        }
        return N_TYPE;
    }

    @Override
    public int getItemCount() {
        if(replyList.size() < maxPostCount){
            return replyList.size();
        }
        return maxPostCount;
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView nickname, time, content;
        public TextView footer;

        public RecyclerViewHolder(View view, int viewType) {
            super(view);
            if(viewType  == N_TYPE){
                nickname = view.findViewById(R.id.reply_user_nickname);
                content = view.findViewById(R.id.reply_content);
                time = view.findViewById(R.id.reply_time);
            }else{
                footer = view.findViewById(R.id.footer);
            }
        }
    }
}

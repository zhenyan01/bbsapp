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

import com.example.bbsapp.Bean.Post;
import com.example.bbsapp.Bean.User;
import com.example.bbsapp.R;
import com.example.bbsapp.activity.Login;
import com.example.bbsapp.activity.Receive;

import java.util.List;
import java.util.logging.LogRecord;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Post> postList;

    private final int P_TYPE = 0;
    private final int F_TYPE = 1;

    private int maxPostCount = 10;
    private boolean isFoot = true;

    public HomeAdapter(Context context, List<Post> postList){
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View postView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        View footView = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer, parent, false);
        if(viewType == F_TYPE){
            return new RecyclerViewHolder(footView, F_TYPE);
        }else{
            return new RecyclerViewHolder(postView, P_TYPE);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(isFoot && getItemViewType(position) == F_TYPE){
            final RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;
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
            final RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;
            Post post = postList.get(position);
            recyclerViewHolder.nickname.setText(post.getNickname());
            recyclerViewHolder.content.setText(post.getContent());
            recyclerViewHolder.time.setText(post.getCreatedAt());
            recyclerViewHolder.title.setText(post.getTitle());


            recyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //int position = recyclerViewHolder.getAdapterPosition();
                    if(User.getCurrentUser(User.class) != null){
                        Intent intent = new Intent(context, Receive.class);
                        intent.putExtra("id", post.getObjectId());
                        intent.putExtra("username", post.getName());
                        intent.putExtra("content", post.getContent());
                        intent.putExtra("time", post.getCreatedAt());
                        intent.putExtra("nickname", post.getNickname());
                        context.startActivity(intent);
                    }else{
                        Toast.makeText(context, "fetch user data failed", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, Login.class));
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == maxPostCount - 1){
            return F_TYPE;
        }
        return P_TYPE;
    }

    @Override
    public int getItemCount() {
        if(postList.size() < maxPostCount){
            return postList.size();
        }
        return maxPostCount;
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView nickname, content, time, title;
        public TextView footer;

        public RecyclerViewHolder(View view, int viewType) {
            super(view);
            if(viewType  == P_TYPE){
                nickname = view.findViewById(R.id.pi_author_nickname);
                content = view.findViewById(R.id.pi_post_content);
                title = view.findViewById(R.id.pi_post_title);
                time = view.findViewById(R.id.pi_post_time);
            }else{
                footer = view.findViewById(R.id.footer);
            }
        }
    }
}

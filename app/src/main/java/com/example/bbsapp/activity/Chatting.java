package com.example.bbsapp.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.bbsapp.databinding.ActivityChattingBinding;
import com.example.bbsapp.databinding.ActivityChattingBinding;
import com.getstream.sdk.chat.ChatUI;

import org.jetbrains.annotations.Nullable;

import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.api.models.FilterObject;
import io.getstream.chat.android.client.models.Filters;
import io.getstream.chat.android.client.models.User;
import io.getstream.chat.android.livedata.ChatDomain;
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModel;
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModelBinding;
import io.getstream.chat.android.ui.channel.list.viewmodel.factory.ChannelListViewModelFactory;

import static java.util.Collections.singletonList;

public final class Chatting extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ActivityChattingBinding binding = ActivityChattingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ChatClient client = new ChatClient.Builder("b67pax5b2wdq", getApplicationContext()).build();
        new ChatDomain.Builder(client, getApplicationContext()).build();
        new ChatUI.Builder(getApplicationContext()).build();


        User user = new User();
        user.setId("tutorial-droid");
        user.getExtraData().put("name", "Android User");
        user.getExtraData().put("image", "https://bit.ly/2TIt8NR");

        client.connectUser(
                user,
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoidHV0b3JpYWwtZHJvaWQifQ.NhEr0hP9W9nwqV7ZkdShxvi02C5PR7SJE7Cs4y7kyqg"
        ).enqueue();

        FilterObject filter = Filters.and(
                Filters.eq("type", "messaging"),
                Filters.in("members", singletonList(user.getId()))
        );

        ChannelListViewModelFactory factory = new ChannelListViewModelFactory(
                filter,
                ChannelListViewModel.DEFAULT_SORT
        );

        ChannelListViewModel channelsViewModel =
                new ViewModelProvider(this, factory).get(ChannelListViewModel.class);
        
        ChannelListViewModelBinding.bind(channelsViewModel, binding.channelListView, this);
        binding.channelListView.setChannelItemClickListener(channel -> {
            //start channel activity
            startActivity(ChannelActivity.newIntent(this, channel));
        });
    }
}
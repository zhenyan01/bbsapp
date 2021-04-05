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

        // Step 0 - inflate binding
        ActivityChattingBinding binding = ActivityChattingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Step 1 - Set up the client for API calls, the domain for offline storage
        //          and the UI components
        // 4/5
        ChatClient client = new ChatClient.Builder("jvqcjmfy2rta", getApplicationContext()).build();
        new ChatDomain.Builder(client, getApplicationContext()).build();
        new ChatUI.Builder(getApplicationContext()).build();

        // Step 2 - Authenticate and connect the user
        User user = new User();
        user.setId("tutorial-droid");
        user.getExtraData().put("name", "Android User");
        user.getExtraData().put("image", "https://bit.ly/2TIt8NR");

        client.connectUser(
                user,
                "jczg2uu3h595k5vjqsfxp96sx434m3svfm5frbpcxh5u6c36asz4sn9rucbva52v"
        ).enqueue();

        // Step 3 - Set the channel list filter and order
        // This can be read as requiring only channels whose "type" is "messaging" AND
        // whose "members" include our "user.id"
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

        // Step 4 - Connect the ChannelListViewModel to the ChannelListView, loose
        //          coupling makes it easy to customize
        ChannelListViewModelBinding.bind(channelsViewModel, binding.channelListView, this);
        binding.channelListView.setChannelItemClickListener(channel -> {
            //start channel activity
            startActivity(ChannelActivity.newIntent(this, channel));
        });
    }
}
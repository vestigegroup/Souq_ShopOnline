package com.marwaeltayeb.souq.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.marwaeltayeb.souq.R;
import com.marwaeltayeb.souq.ViewModel.NewsFeedViewModel;
import com.marwaeltayeb.souq.adapter.NewsFeedAdapter;
import com.marwaeltayeb.souq.databinding.ActivityNewsFeedBinding;

public class NewsFeedActivity extends AppCompatActivity {

    private static final String TAG = "NewsFeedActivity";
    private ActivityNewsFeedBinding binding;
    private NewsFeedViewModel newsFeedViewModel;
    private NewsFeedAdapter newsFeedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_feed);

        newsFeedViewModel = ViewModelProviders.of(this).get(NewsFeedViewModel.class);

        setUpRecyclerView();

        getPosters();
    }

    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.newsFeedList.setLayoutManager(layoutManager);
        binding.newsFeedList.setHasFixedSize(true);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        binding.newsFeedList.addItemDecoration(dividerItemDecoration);
    }

    private void getPosters() {
        newsFeedViewModel.getPosters().observe(this, NewsFeedResponse -> {
            newsFeedAdapter = new NewsFeedAdapter(getApplicationContext(), NewsFeedResponse.getPosters());
            binding.newsFeedList.setAdapter(newsFeedAdapter);
            newsFeedAdapter.notifyDataSetChanged();
        });
    }

}

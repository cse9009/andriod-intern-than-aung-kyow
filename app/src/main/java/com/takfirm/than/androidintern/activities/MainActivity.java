package com.takfirm.than.androidintern.activities;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.takfirm.than.androidintern.R;
import com.takfirm.than.androidintern.adapters.Divider;
import com.takfirm.than.androidintern.adapters.UserRecyclerAdapter;
import com.takfirm.than.androidintern.api.ApiClient;
import com.takfirm.than.androidintern.api.ApiInterface;
import com.takfirm.than.androidintern.models.Search;
import com.takfirm.than.androidintern.models.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private ApiInterface mApiInterface;
    private List<User> mUsers = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private UserRecyclerAdapter mRecyclerAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        loadAllUsers();
    }

    private void init() {
        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        mSwipeRefreshLayout = findViewById(R.id.home_swipe_refresh_layout);
        mRecyclerView = findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setNestedScrollingEnabled(true);
        mRecyclerView.addItemDecoration(new Divider(MainActivity.this,LinearLayoutManager.VERTICAL));

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

    }

    private void loadAllUsers(){
        mSwipeRefreshLayout.setRefreshing(true);

        Call<Search> call = mApiInterface.getSearchResults();
        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                if(response.isSuccessful() && response.body().getUsers() != null){
                    if(mUsers.size() > 0){
                        mUsers.clear();
                    }
                    mUsers = response.body().getUsers();

                    mRecyclerAdapter = new UserRecyclerAdapter(mUsers,MainActivity.this);
                    mRecyclerView.setAdapter(mRecyclerAdapter);
                    mRecyclerAdapter.notifyDataSetChanged();

                    mRecyclerAdapter.setOnItemClickListener(new UserRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            User user = mUsers.get(position);
                            Intent details_intent = new Intent(MainActivity.this, DetailsActivity.class);
                            details_intent.putExtra("name",user.getName());
                            details_intent.putExtra("id",user.getId());
                            details_intent.putExtra("image",user.getImage());
                            details_intent.putExtra("user",user.getUser());
                            details_intent.putExtra("who",user.getWho());

                            startActivity(details_intent);
                            overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);

                        }
                    });

                    mSwipeRefreshLayout.setRefreshing(false);
                }else{
                    mSwipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(MainActivity.this, "No Result", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this, "Network failure, please try again.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onRefresh() {
        loadAllUsers();
    }
}

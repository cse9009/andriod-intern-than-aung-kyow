package com.takfirm.than.androidintern.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.takfirm.than.androidintern.R;

public class DetailsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ImageView mUserImage;
    private TextView mUserTv, mWhoTv, mIdTv;
    private ProgressBar mImageLoadProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String id = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("name");
        String user = getIntent().getStringExtra("user");
        String who = getIntent().getStringExtra("who");
        String image = getIntent().getStringExtra("image");

        getSupportActionBar().setTitle(name);

        init();

        //loading data
        mUserTv.setText(user);
        mWhoTv.setText(who);
        mIdTv.setText(id);

        Glide.with(this)
                .load(image)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        mImageLoadProgressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        mImageLoadProgressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(mUserImage);
    }

    private void init() {
        mImageLoadProgressBar = findViewById(R.id.details_progress_load_photo);
        mUserImage = findViewById(R.id.user_image);
        mUserTv = findViewById(R.id.user_id_tv);
        mWhoTv = findViewById(R.id.user_designation_tv);
        mIdTv = findViewById(R.id.user_key_tv);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
        overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);
    }
}

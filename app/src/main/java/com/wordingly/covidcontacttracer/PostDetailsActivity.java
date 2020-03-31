package com.wordingly.covidcontacttracer;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wordingly.covidcontacttracer.utils.Utils;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


public class PostDetailsActivity extends FragmentActivity {
    private static final String TAG = PostDetailsActivity.class.getSimpleName();


    public TextView postTitleTextView;


    public TextView postBodyTextView;


    public ImageView postMediaImageView;


    public ImageView shareImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_post_details);
        //setup Views
        setupViews();
        getWindow().setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.prelogin_background, null));

        Intent i = getIntent();
        final String postTitle = i.getStringExtra("postTitle");
        final String postBody = i.getStringExtra("postBody");
        final String postMediaUrl = i.getStringExtra("postMediaUrl");
        final String postWebUrl = i.getStringExtra("postWebUrl");

        postTitleTextView.setText(postTitle);
        postBodyTextView.setText(Utils.fromHtml(postBody));

        shareImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, postTitle);
                i.putExtra(Intent.EXTRA_TEXT, postWebUrl);
                startActivity(Intent.createChooser(i, "Share Post"));
            }
        });

        Glide.with(this)
                .load(postMediaUrl)
                .transition(withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .centerCrop()
                .into(postMediaImageView);

    }

    private void setupViews() {
        postBodyTextView = findViewById(R.id.post_body_text_view);
        postMediaImageView = findViewById(R.id.post_media_image_view);
        postTitleTextView = findViewById(R.id.post_title_text_view);
        shareImageView = findViewById(R.id.share_image_view);
    }

}

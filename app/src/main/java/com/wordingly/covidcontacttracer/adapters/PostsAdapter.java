package com.wordingly.covidcontacttracer.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wordingly.covidcontacttracer.PostDetailsActivity;
import com.wordingly.covidcontacttracer.R;
import com.wordingly.covidcontacttracer.WebPageActivity;
import com.wordingly.covidcontacttracer.objects.Posts;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class PostsAdapter extends RealmRecyclerViewAdapter<Posts, PostsAdapter.PostViewHolder> {

    private final Context context;
    private final String TAG = PostsAdapter.class.getSimpleName();

    public PostsAdapter(Context context, OrderedRealmCollection<Posts> data) {
        super(data, true);
        this.context = context;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PostViewHolder holder, int position) {

        final Posts post = getData().get(position);

        if (post != null) {
            holder.postTitleTextView.setText(post.getTitle());

            if (post.getDuration().equals("")) {
                holder.postDurationTextView.setVisibility(View.GONE);
            } else {
                holder.postDurationTextView.setText(post.getDuration());
            }

            if (post.getSubtitle().equals("")) {
                holder.postSubtitleTextView.setVisibility(View.GONE);
            } else {
                holder.postSubtitleTextView.setText(post.getSubtitle());
            }

            holder.shareArticleLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, post.getTitle());
                    if (post.getPost_type().equals(Posts.POST_TYPE_YOUTUBE_VIDEO)) {
                        i.putExtra(Intent.EXTRA_TEXT, "https://www.youtube.com/watch?v=" + post.getMedia_url());
                    } else {
                        i.putExtra(Intent.EXTRA_TEXT, post.getWeb_url());
                    }
                    context.startActivity(Intent.createChooser(i, "Share Post"));
                }
            });

            if (!post.getMedia_url().equals("")) {
                // Only default and web links have image media
                String mediaUrl = "";
                if (post.getPost_type().equals(Posts.POST_TYPE_DEFAULT) || post.getPost_type().equals(Posts.POST_TYPE_WEB_LINK)) {
                    mediaUrl = post.getMedia_url();
                } else if (post.getPost_type().equals(Posts.POST_TYPE_YOUTUBE_VIDEO)) {
                    mediaUrl = "https://img.youtube.com/vi/" + post.getMedia_url() + "/0.jpg";
                }
                Glide.with(context)
                        .load(mediaUrl)
                        .transition(withCrossFade())
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .centerCrop()
                        .into(holder.postMediaImageView);
            } else {
                holder.postMediaImageView.setVisibility(View.GONE);
            }

            if (post.getPost_type().equals(Posts.POST_TYPE_DEFAULT)) {
                holder.postLinearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), PostDetailsActivity.class);
                        intent.putExtra("postTitle", post.getTitle());
                        intent.putExtra("postBody", post.getBody());
                        intent.putExtra("postMediaUrl", post.getMedia_url());
                        intent.putExtra("postWebUrl", post.getWeb_url());
                        intent.putExtra("postBody", post.getBody());
                        context.startActivity(intent);
                    }
                });
            } else if (post.getPost_type().equals(Posts.POST_TYPE_WEB_LINK)) {
                holder.postLinearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, WebPageActivity.class);
                        intent.putExtra("url", post.getWeb_url());
                        context.startActivity(intent);
                    }
                });
            } else if (post.getPost_type().equals(Posts.POST_TYPE_YOUTUBE_VIDEO)) {
                holder.postLinearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, WebPageActivity.class);
                        intent.putExtra("url", "https://www.youtube.com/watch?v=" + post.getMedia_url());
                        context.startActivity(intent);
                    }
                });
            }
        }

    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout postLinearLayout;
        public TextView postTitleTextView;
        public TextView postSubtitleTextView;
        public TextView postDurationTextView;
        public ImageView postMediaImageView;
        public LinearLayout shareArticleLinearLayout;
        public FrameLayout postMediaFrameLayout;

        public PostViewHolder(View itemView) {
            super(itemView);

            postLinearLayout = itemView.findViewById(R.id.post_linear_layout);
            postTitleTextView = itemView.findViewById(R.id.post_title_text_view);
            postDurationTextView = itemView.findViewById(R.id.post_duration_text_view);
            postSubtitleTextView = itemView.findViewById(R.id.post_subtitle_text_view);
            postMediaImageView = itemView.findViewById(R.id.post_media_image_view);
            shareArticleLinearLayout = itemView.findViewById(R.id.shareArticleLinearLayout);
            postMediaFrameLayout = itemView.findViewById(R.id.post_media_frame_layout);
        }
    }
}
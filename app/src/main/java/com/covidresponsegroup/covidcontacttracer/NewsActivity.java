package com.covidresponsegroup.covidcontacttracer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.covidresponsegroup.covidcontacttracer.adapters.PostsAdapter;
import com.covidresponsegroup.covidcontacttracer.objects.Posts;
import com.covidresponsegroup.covidcontacttracer.utils.Utils;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.Sort;

public class NewsActivity extends AppCompatActivity {
    Realm realm;
    private RecyclerView postsRecyclerView;
    private LinearLayout postsEmptyLinearLayout;
    private PostsAdapter postsAdapter;
    private NestedScrollView postsNestedScrollView;
    private LinearLayout writeForUsLinearLayout;
    private LinearLayout sendFeedbackLinearLayout;
    private static final String SCREEN_NAME = "screen_posts_activity";

    public NewsActivity() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        this.realm = Realm.getDefaultInstance();


        postsRecyclerView = (RecyclerView) findViewById(R.id.posts_recycler_view);
        postsEmptyLinearLayout = (LinearLayout) findViewById(R.id.posts_empty_linear_layout);
        postsNestedScrollView = (NestedScrollView) findViewById(R.id.postsNestedScrollView);

        writeForUsLinearLayout = (LinearLayout) findViewById(R.id.writeForUsLinearLayout);
        sendFeedbackLinearLayout = (LinearLayout) findViewById(R.id.sendFeedbackLinearLayout);

        class EmailOnClickListener implements View.OnClickListener {
            private String subject;
            private String message;

            private EmailOnClickListener(String subject, String message) {
                this.subject = subject;
                this.message = message;
            }

            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"abhinavtripathi01@gmail.com"});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(emailIntent, "Send mail"));
            }
        }

        writeForUsLinearLayout.setOnClickListener(new EmailOnClickListener("Write for BuildMyVocab", "Tell us something about yourself"));
        sendFeedbackLinearLayout.setOnClickListener(new EmailOnClickListener("BuildMyVocab Feedback", "Please share your valuable feedback"));

        postsNestedScrollView.fullScroll(ScrollView.FOCUS_UP);
        postsRecyclerView.setFocusable(false);
        // Uncomment this if app is crashing in kitkat : )
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
        postsRecyclerView.setNestedScrollingEnabled(false);
//        }

        if (Utils.isNetworkAvailable()) {
            // TODO: Rate limit this network call, once per day
            //networkCalls.posts();
        }

        initView();
    }

    private void initView() {
        if (realm.where(Posts.class).count() == 0) {
            postsEmptyLinearLayout.setVisibility(View.VISIBLE);
            postsRecyclerView.setVisibility(View.GONE);
        } else {
            postsEmptyLinearLayout.setVisibility(View.GONE);
            postsRecyclerView.setVisibility(View.VISIBLE);
            setupRecycler();
        }
    }

    private boolean appInstalled(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    private void setupRecycler() {
        String fieldNames[] = {"is_pinned", "created_at"};
        Sort sortOrders[] = {Sort.DESCENDING, Sort.DESCENDING};
        OrderedRealmCollection<Posts> data = realm.where(Posts.class).findAllAsync().sort(fieldNames,sortOrders);
        postsAdapter = new PostsAdapter(this, data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        postsRecyclerView.setLayoutManager(linearLayoutManager);
        postsRecyclerView.setAdapter(postsAdapter);
        postsRecyclerView.setHasFixedSize(true);
    }



    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Contact Tracer App");
        actionBar.setSubtitle("Profile");
    }
}

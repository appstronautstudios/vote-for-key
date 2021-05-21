package com.appstronautstudios.voteforkeydemo;

import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.appstronautstudios.voteforkey.VoteForKey;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String[] keys = {"course_1","course_2","course_3","course_4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up back button
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setTitle("Vote For Key demo");

        View resetAllBtn = findViewById(R.id.reset_all);
        resetAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (String key : keys) {
                    VoteForKey.removeVoteForKey(MainActivity.this, key);
                }
                configure();
            }
        });

        configure();
    }

    private void configure() {
    ListView listView = findViewById(R.id.listview);
        listView.setAdapter(new ListAdapter() {
            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEnabled(int i) {
                return false;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public int getCount() {
                return keys.length;
            }

            @Override
            public Object getItem(int i) {
                return keys[i];
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = getLayoutInflater().inflate(R.layout.list_item_course, null, false);
                }

                ImageView like = view.findViewById(R.id.like);
                ImageView dislike = view.findViewById(R.id.dislike);

                String key = (String) getItem(i);
                String vote = VoteForKey.getVoteForKey(MainActivity.this, key);
                if (vote != null) {
                    if (vote.equalsIgnoreCase("y")) {
                        like.setBackground(new ColorDrawable(ContextCompat.getColor(MainActivity.this, R.color.colorAccent)));
                        dislike.setBackground(null);
                    } else {
                        like.setBackground(null);
                        dislike.setBackground(new ColorDrawable(ContextCompat.getColor(MainActivity.this, R.color.colorAccent)));
                    }
                }

                like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        VoteForKey.setVoteForKey(MainActivity.this, key, "y");
                        configure();
                    }
                });

                dislike.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        VoteForKey.setVoteForKey(MainActivity.this, key, "n");
                        configure();
                    }
                });

                return view;
            }

            @Override
            public int getItemViewType(int i) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        });
    }
}

package com.qlf.qlf2019;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qlf.qlf2019.Utils.Contants;
import com.squareup.picasso.Picasso;

public class SpeakerDetail extends AppCompatActivity {
    private static final String TAG = "SpeakerDetail";
    private ImageView imageViewForSpeaker;
    private TextView detail;
    private String detailDesc, url, name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle data = getIntent().getExtras();
        name = data.getString(Contants.NAME);
        detailDesc = data.getString(Contants.DETAIL);
        url = data.getString(Contants.URL);
        imageViewForSpeaker = findViewById(R.id.speakerImagedetail);
        detail = findViewById(R.id.detail);
        getSupportActionBar().setTitle(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        detail.setText(detailDesc);
        Picasso.get().load(url).fit().into(imageViewForSpeaker);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sorry, no contact found yet!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

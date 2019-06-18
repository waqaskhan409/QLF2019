package com.qlf.qlf2019.Utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qlf.qlf2019.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SponsorsAdapter extends RecyclerView.Adapter<SponsorsAdapter.SponsorsHOlder> {
    private static final String TAG = "SponsorsAdapter";
    ArrayList<String> urls;

    public SponsorsAdapter(ArrayList<String> urls) {
        this.urls = urls;
    }

    @NonNull
    @Override
    public SponsorsHOlder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_for_sponsors, viewGroup, false);
        return new SponsorsHOlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SponsorsHOlder sponsorsHOlder, int i) {
        Log.d(TAG, "onBindViewHolder: " + urls.size());
        Log.d(TAG, "onBindViewHolder: " + urls);
        Picasso.get().load(urls.get(i)).fit().into(sponsorsHOlder.imageView);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + urls.size());
        return urls.size();
    }

    class SponsorsHOlder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public SponsorsHOlder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.sponsorsImage);
        }
    }
}

package com.qlf.qlf2019.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qlf.qlf2019.R;
import com.qlf.qlf2019.SpeakerDetail;
import com.qlf.qlf2019.models.ProgramAndGuests;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.support.constraint.Constraints.TAG;

public class SpeakerAdapter extends RecyclerView.Adapter<SpeakerAdapter.SpeakerHolder> {
    private static final String TAG = "SpeakerAdapter";
    private ProgressBar progressBar;
    private ArrayList<ProgramAndGuests> model;

    public SpeakerAdapter(ProgressBar progressBar, ArrayList<ProgramAndGuests> model) {
        this.progressBar = progressBar;
        this.model = model;
    }
    private boolean connection(SpeakerHolder holder){
        ConnectivityManager connectivityManager = (ConnectivityManager)holder.itemView.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
             return true;
        }
        else
             return false;
    }
    @NonNull
    @Override
    public SpeakerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_for_speakers,viewGroup, false);
        return new SpeakerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SpeakerHolder holder, final int position) {
        progressBar.setVisibility(View.GONE);
        Log.d(TAG, "onBindViewHolder: " + model.get(position).getImage());
        Picasso.get().setLoggingEnabled(true);

//        Picasso.get().load("https://vignette.wikia.nocookie.net/jamesbond/images/d/dc/James_Bond_%28Pierce_Brosnan%29_-_Profile.jpg/revision/latest?cb=20130506224906").placeholder(R.drawable.qlf_logo_small).into(holder.speakerImage);
        Picasso.get().load(model.get(position).getImage()).fit().into(holder.speakerImage);
        if (!connection(holder)){
//            Picasso.get().setLoggingEnabled(true);
//            Picasso.get().load(model.get(position).getImage()).fit().networkPolicy(NetworkPolicy.OFFLINE).into(holder.speakerImage);
//            Glide.with(holder.itemView.getContext()).load("http://goo.gl/gEgYUd").fitCenter().into(holder.speakerImage);
        }else {
//            Glide.with(holder.itemView.getContext()).load("http://goo.gl/gEgYUd").fitCenter().into(holder.speakerImage);
        }

        holder.speakerName.setText(model.get(position).getName());
        holder.speakerPosition.setText(model.get(position).getPosition());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), SpeakerDetail.class);
                intent.putExtra(Contants.DETAIL, model.get(position).getDescription());
                intent.putExtra(Contants.NAME, model.get(position).getName());
                intent.putExtra(Contants.URL, model.get(position).getImage());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    class SpeakerHolder extends RecyclerView.ViewHolder{
        private TextView speakerName, speakerPosition;
        private CircleImageView speakerImage;

        public SpeakerHolder(@NonNull View itemView) {
            super(itemView);
            speakerImage = itemView.findViewById(R.id.speakerImage);
            speakerName = itemView.findViewById(R.id.speakerName);
            speakerPosition = itemView.findViewById(R.id.speakerPosition);

        }
    }
}

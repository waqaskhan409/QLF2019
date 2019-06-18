package com.qlf.qlf2019.Utils;

import android.annotation.TargetApi;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qlf.qlf2019.R;
import com.qlf.qlf2019.models.ProgramAndGuests;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ProgramHolder> {
    private static final String TAG = "ProgramAdapter";
    private ArrayList<ProgramAndGuests> model;

    public ProgramAdapter(ArrayList<ProgramAndGuests> model) {
        this.model = model;
    }

    @NonNull
    @Override
    public ProgramHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_for_program, viewGroup, false);
        return new ProgramHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ProgramHolder holder, int position) {
        holder.textFortime.setText(model.get(position).getTime());
        holder.textForSpeakers.setHint("");
        if(model.size() != 0 && !model.get(position).getModeratedby().equals("")){
            holder.textForSpeakers.setText("By "+ model.get(position).getModeratedby()); }
        holder.textForVenue.setText("Venue : " + model.get(position).getVenue());
        holder.textForTitle.setText(model.get(position).getTitle());
        if(model.get(position).getVenue().equals("Pink Hall")){
            Drawable mDraw = holder.itemView.getContext().getDrawable(R.drawable.rounded_corner_for_image);
            mDraw.setColorFilter(holder.itemView.getContext().getColor(R.color.bright_pink), PorterDuff.Mode.MULTIPLY);
            holder.startLine.setBackground(mDraw);
        }else if(model.get(position).getVenue().equals("Cpec Hall")){
            Drawable mDraw = holder.itemView.getContext().getDrawable(R.drawable.rounded_corner_for_image);
            mDraw.setColorFilter(holder.itemView.getContext().getColor(R.color.bright_pink), PorterDuff.Mode.MULTIPLY);
            holder.startLine.setBackground(mDraw);
        }else if(model.get(position).getVenue().equals("Orange Hall")){
            Drawable mDraw = holder.itemView.getContext().getDrawable(R.drawable.rounded_corner_for_image);
            mDraw.setColorFilter(holder.itemView.getContext().getColor(R.color.orange), PorterDuff.Mode.MULTIPLY);
            holder.startLine.setBackground(mDraw);
        }else if(model.get(position).getVenue().equals("EXPO")){
            Drawable mDraw = holder.itemView.getContext().getDrawable(R.drawable.rounded_corner_for_image);
            mDraw.setColorFilter(holder.itemView.getContext().getColor(R.color.azure), PorterDuff.Mode.MULTIPLY);
            holder.startLine.setBackground(mDraw);
        }else if(model.get(position).getVenue().equals("Green Hall")){
            Drawable mDraw = holder.itemView.getContext().getDrawable(R.drawable.rounded_corner_for_image);
            mDraw.setColorFilter(holder.itemView.getContext().getColor(R.color.green), PorterDuff.Mode.MULTIPLY);
            holder.startLine.setBackground(mDraw);
        }else if(model.get(position).getVenue().equals("PURPLE HALL") || model.get(position).getVenue().equals("Purple Hall")){
            Drawable mDraw = holder.itemView.getContext().getDrawable(R.drawable.rounded_corner_for_image);
            mDraw.setColorFilter(holder.itemView.getContext().getColor(R.color.purple), PorterDuff.Mode.MULTIPLY);
            holder.startLine.setBackground(mDraw);
        }else if(model.get(position).getVenue().equals("ART GALLERY") || model.get(position).getVenue().equals("Art Gallery")){
            Drawable mDraw = holder.itemView.getContext().getDrawable(R.drawable.rounded_corner_for_image);
            mDraw.setColorFilter(holder.itemView.getContext().getColor(R.color.yellow), PorterDuff.Mode.MULTIPLY);
            holder.startLine.setBackground(mDraw);
        }else if(model.get(position).getVenue().equals("QUETTA CLUB")) {
            Drawable mDraw = holder.itemView.getContext().getDrawable(R.drawable.rounded_corner_for_image);
            mDraw.setColorFilter(holder.itemView.getContext().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.MULTIPLY);
            holder.startLine.setBackground(mDraw);
        }
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    class ProgramHolder extends RecyclerView.ViewHolder{
        private ImageView startLine;
        private CircleImageView imageFormoderator;
        private TextView textForTitle, textForSpeakers, textFortime, textForVenue ;
        public ProgramHolder(@NonNull View itemView) {
            super(itemView);
            startLine = itemView.findViewById(R.id.startLine);
//            imageFormoderator = itemView.findViewById(R.id.moderator);
            textForSpeakers = itemView.findViewById(R.id.speakers);
            textForVenue = itemView.findViewById(R.id.venue);
            textFortime = itemView.findViewById(R.id.time);
            textForTitle = itemView.findViewById(R.id.title);
        }
    }
}

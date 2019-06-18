package com.qlf.qlf2019;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.qlf.qlf2019.Utils.Contants;
import com.qlf.qlf2019.Utils.SpeakerAdapter;
import com.qlf.qlf2019.models.ProgramAndGuests;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Speakers extends Fragment {
    private static final String TAG = "Speakers";

    private RecyclerView mRecyclerForSpeaker;
    private SpeakerAdapter adapter;

    private DatabaseReference databaseReference ;
    private ProgressBar progressBar;
    private ArrayList<ProgramAndGuests> guestss = new ArrayList<>();




    public Speakers() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_speakers, container, false);
        setupWindowAnimations();
        mRecyclerForSpeaker = view.findViewById(R.id.recyclerForSpeaker);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Speakers");
        progressBar = view.findViewById(R.id.progress_circular);

        LinearLayoutManager lm = new LinearLayoutManager(getActivity());


        mRecyclerForSpeaker.setLayoutManager(lm);
        mRecyclerForSpeaker.setHasFixedSize(true);

        databaseReference = FirebaseDatabase.getInstance().getReference().child(Contants.GUESTS);
        databaseReference.keepSynced(true);
        getingJson();
        updateList();

        return view;
    }
    private void getingJson(){
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            String name, des, image, position;
            JSONObject jsonObject = new JSONObject(json);
            JSONArray guests = jsonObject.getJSONArray("guests");
            Log.d(TAG, "onCreateView: " + guests.length());
            for (int i = 0; i < guests.length(); i++) {
                JSONObject jo_inside = guests.getJSONObject(i);
                name = jo_inside.getString("name");
                des = jo_inside.getString("description");
                image = jo_inside.getString("image");
                position = jo_inside.getString("position");
                guestss.add(new ProgramAndGuests(name, image, position, des, "", ""
                        ,"","","",""));
                Log.d(TAG, "onCreateView: "+ name);
                Log.d(TAG, "onCreateView: "+ des);
                Log.d(TAG, "onCreateView: "+ i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        if(getActivity().getWindow() == null){
            return;
        }
        Slide slide = new Slide();
        slide.setDuration(1000);
        getActivity().getWindow().setExitTransition(slide);
    }
    private void updateList(){

        adapter = new SpeakerAdapter(progressBar, guestss);
        mRecyclerForSpeaker.setAdapter(adapter);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}

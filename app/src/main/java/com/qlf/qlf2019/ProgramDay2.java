package com.qlf.qlf2019;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.qlf.qlf2019.Utils.Contants;
import com.qlf.qlf2019.Utils.ProgramAdapter;
import com.qlf.qlf2019.models.ProgramAndGuests;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProgramDay2 extends Fragment {
    private static final String TAG = "ProgramDay2";
    private RecyclerView mRecyclerForDay2;
    private ProgramAdapter mAdapter;
    private DatabaseReference databaseReference;
    private ArrayList<String> listSpeakers = new ArrayList<>();
    private ProgressBar progressBar;
    private ArrayList<ProgramAndGuests> guestss = new ArrayList<>();

    public ProgramDay2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_program_day2, container, false);
        mRecyclerForDay2 = view.findViewById(R.id.recyclerDay2);
        progressBar = view.findViewById(R.id.progress_circular);
        mRecyclerForDay2.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        databaseReference = FirebaseDatabase.getInstance().getReference().child(Contants.PROGRAM).child(Contants.DAY2);
        databaseReference.keepSynced(true);
        mRecyclerForDay2.setLayoutManager(linearLayoutManager);
        gettingJson();
        progressBar.setVisibility(View.GONE);
        return view;
    }
    private void gettingJson(){
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
            String title, speakers, time, venue;
            JSONObject jsonObject = new JSONObject(json);
            JSONObject guests = jsonObject.getJSONObject("programs");
            JSONArray jo_inside = guests.getJSONArray("day2");

            Log.d(TAG, "onCreateView: " + guests.length());
            for (int i = 0; i < jo_inside.length(); i++) {
                JSONObject jsonDay1 =  jo_inside.getJSONObject(i);
                title = jsonDay1.getString("title");
                JSONArray speakersA = jsonDay1.getJSONArray("speakers");
                speakers = speakersA.get(0).toString();

                time = jsonDay1.getString("time");
                venue = jsonDay1.getString("venue");
                guestss.add(new ProgramAndGuests("", "", "", "", "", ""
                        ,speakers,time,title,venue));
                Log.d(TAG, "onCreateView: "+ time);
                Log.d(TAG, "onCreateView: "+ title);
                Log.d(TAG, "onCreateView: "+ i);
            }
            updateList();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void updateList(){

        mAdapter = new ProgramAdapter(guestss);
        mRecyclerForDay2.setAdapter(mAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }
}

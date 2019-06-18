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
public class ProgramDay1 extends Fragment {
    private static final String TAG = "ProgramDay1";
    private RecyclerView mRecyclerForDay1;
    private ProgramAdapter mAdapter;
    private DatabaseReference databaseReference;
    private ArrayList<String> listSpeakers = new ArrayList<>();
    private ProgressBar progressBar;
    private ArrayList<ProgramAndGuests> guestss = new ArrayList<>();


    public ProgramDay1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_program_day1, container, false);
        mRecyclerForDay1 = view.findViewById(R.id.recyclerDay1);
        progressBar = view.findViewById(R.id.progress_circular);
        mRecyclerForDay1.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerForDay1.setLayoutManager(linearLayoutManager);
        gettingJson();
        updateList();

        mAdapter.notifyDataSetChanged();

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
            JSONArray jo_inside = guests.getJSONArray("day1");

            Log.d(TAG, "onCreateView: " + jo_inside.length());
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
//                Log.d(TAG, "onCreateView: "+ title);
                Log.d(TAG, "onCreateView: "+ guestss.get(i).getTitle());
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "gettingJson: ", e);
        }
    }

    private void updateList(){
        Log.d(TAG, "updateList: " + guestss.size());
        mAdapter = new ProgramAdapter(guestss);
        mRecyclerForDay1.setAdapter(mAdapter);
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

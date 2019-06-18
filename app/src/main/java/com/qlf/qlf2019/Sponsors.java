package com.qlf.qlf2019;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.qlf.qlf2019.Utils.SponsorsAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Sponsors extends Fragment {
    private static final String TAG = "Sponsors";
    private RecyclerView mRecycler;
    private SponsorsAdapter mAdapter;
    private ArrayList<String> url = new ArrayList<>();
    private DatabaseReference databaseReference;
    private ProgressBar progressBar;



    public Sponsors() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share, container, false);
        mRecycler = view.findViewById(R.id.sponsors);
        mRecycler.setHasFixedSize(true);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(TAG);
        mRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        progressBar = view.findViewById(R.id.progress_circular);
        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("images_sponsors");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Log.d(TAG, "onDataChange: " + snapshot.getKey());
                            url.add(snapshot.getValue().toString());
                        }
                        Log.d(TAG, "onDataChange: " +url);
                        manipulateList();
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        return view;
    }
 public void manipulateList(){
        mAdapter = new SponsorsAdapter(url);
        mRecycler.setAdapter(mAdapter);
 }
}

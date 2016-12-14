package com.example.bakhtiyar.overviewallfirebase;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadFragment extends Fragment {

    StorageReference store;

    DatabaseReference databaseReference;

    FirebaseAuth firebaseAuth;

    Data data;

    View view;

    ListView listView;

    ArrayList<Data> dataList;

    CustomArrayAdapter customArrayAdapter;

    FloatingActionButton fab;

    public ReadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        view =inflater.inflate(R.layout.fragment_read, container, false);

        Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
        firebaseAuth = FirebaseAuth.getInstance();

        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        //databaseReference = FirebaseDatabase.getInstance().getReference(StaticClass.UUID);

        dataList = new ArrayList<>();

        listView = (ListView) view.findViewById(R.id.listview);


        try {


            StaticClass.databaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    data = dataSnapshot.getValue(Data.class);

                    Log.d("Add", data.getName());

                    dataList.add(data);

                    customArrayAdapter = new CustomArrayAdapter(dataList, getContext());

                    listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

                    listView.setAdapter(customArrayAdapter);


                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {




                    for (int i=0;i<dataList.size();i++){
                        Data dat=dataSnapshot.getValue(Data.class);
                        if(dat.getName()==dataList.get(i).getName()){

                            dataList.remove(i);


                            customArrayAdapter.notifyDataSetChanged();
                        }

                    }

                    Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }catch (Exception e){

            Toast.makeText(getContext(), ""+e, Toast.LENGTH_SHORT).show();
        }



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0;i<dataList.size();i++){

                    if(CheckClass.isChecked[i]){


                       store = FirebaseStorage.getInstance().getReferenceFromUrl(dataList.get(i).getPicUrl());

                       // StorageReference storageReference = store.child(dataList.get(i).getPicUrl());

                        store.delete();

                        firebaseAuth = FirebaseAuth.getInstance();
                        FirebaseDatabase.getInstance().getReference(firebaseAuth.getCurrentUser().getUid()).child(dataList.get(i).getId()).removeValue();

                        dataList.remove(i);
                        customArrayAdapter.notifyDataSetChanged();

                        CheckClass.isChecked = new boolean[dataList.size()];

                    }

                }
            }
        });


        return view;
    }

}

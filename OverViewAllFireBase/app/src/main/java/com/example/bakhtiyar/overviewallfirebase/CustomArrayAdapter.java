package com.example.bakhtiyar.overviewallfirebase;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Bakhtiyar on 12/7/2016.
 */
public class CustomArrayAdapter extends BaseAdapter {

    ArrayList<Data> arrayList;




    CheckBox checkBox;

    LayoutInflater infl;

    ImageView img;

    TextView name, fname, salary;

    FirebaseAuth firebaseAuth;



    Context context;

    public CustomArrayAdapter(ArrayList<Data> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        CheckClass.isChecked = new boolean[arrayList.size()];

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
      view =  infl.from(context).inflate(R.layout.custom_list,viewGroup,false);

        CheckClass.isChecked[i] = false;

       final int index = i;

        checkBox = (CheckBox) view.findViewById(R.id.radio);

//        CheckClass.isChecked[index] = b;
//        firebaseAuth = FirebaseAuth.getInstance();
//        FirebaseDatabase.getInstance().getReference(firebaseAuth.getCurrentUser().getUid()).child(arrayList.get(index).getId()).child("flag").setValue(b);



//        CheckClass.isChecked[index] = b;
//        firebaseAuth = FirebaseAuth.getInstance();
//        FirebaseDatabase.getInstance().getReference(firebaseAuth.getCurrentUser().getUid()).child(arrayList.get(index).getId()).child("flag").setValue(false);



        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    CheckClass.isChecked[index] = b;

                    firebaseAuth = FirebaseAuth.getInstance();
                    FirebaseDatabase.getInstance().getReference(firebaseAuth.getCurrentUser().getUid()).child(arrayList.get(index).getId()).child("flag").setValue(b);

                }else {

                    CheckClass.isChecked[index] = b;
                    firebaseAuth = FirebaseAuth.getInstance();
                    FirebaseDatabase.getInstance().getReference(firebaseAuth.getCurrentUser().getUid()).child(arrayList.get(index).getId()).child("flag").setValue(false);

                }
            }
        });




        name = (TextView) view.findViewById(R.id.rdname);

        fname = (TextView) view.findViewById(R.id.rdfname);

        salary = (TextView) view.findViewById(R.id.rdsalary);

        img = (ImageView) view.findViewById(R.id.rdimg);

        name.setText(arrayList.get(i).getName());

        fname.setText(arrayList.get(i).getFathername());

        salary.setText(""+arrayList.get(i).getSalary());


        checkBox.setChecked(arrayList.get(i).isFlag());

        Picasso.with(context)
                .load(arrayList.get(i).getPicUrl())
                .into(img);







        return view;
    }
}

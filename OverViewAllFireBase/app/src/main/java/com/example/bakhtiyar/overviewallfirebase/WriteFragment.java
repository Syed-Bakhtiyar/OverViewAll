package com.example.bakhtiyar.overviewallfirebase;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class WriteFragment extends Fragment {

    public final int PICK_IMAGE_REQUEST = 234;

    DatabaseReference databaseReference;

    StorageReference storageReference;

    FirebaseAuth firebaseAuth;

    Data mydata;

    View view;

    ImageButton imageButton;

    File file;

    EditText edit_name, edit_fname, edit_salary;

    Bitmap bitmap;

    Button submit;

    String name ,fatherName;

    double salary =-1;

    public Uri filepath;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PICK_IMAGE_REQUEST ) {

            if (filepath!=null){

                Toast.makeText(getContext(), "No File Select", Toast.LENGTH_SHORT).show();
            }
            else {

                filepath = data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filepath);
                    imageButton.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(getContext(), "Ok" + e, Toast.LENGTH_SHORT).show();
                }
            }

        }





        super.onActivityResult(requestCode, resultCode, data);
    }

    public WriteFragment() {
        // Required empty public constructor
    }



//    Intent intent = new Intent();
//
//    intent.setType("image/*");
//    intent.setAction(Intent.ACTION_GET_CONTENT);
//
//
//    startActivityForResult(Intent.createChooser(intent, "Select an Image"), PICK_IMAGE_REQUEST);



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view=inflater.inflate(R.layout.fragment_write, container, false);


        firebaseAuth = FirebaseAuth.getInstance();

        //StaticClass.UUID = firebaseAuth.getCurrentUser().getUid();

        storageReference = FirebaseStorage.getInstance().getReference(firebaseAuth.getCurrentUser().getUid());

        edit_name = (EditText) view.findViewById(R.id.name);

        edit_fname = (EditText) view.findViewById(R.id.father);

        edit_salary = (EditText) view.findViewById(R.id.Salary);

        imageButton = (ImageButton) view.findViewById(R.id.img_button);

        submit = (Button) view.findViewById(R.id.submit);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);


                startActivityForResult(Intent.createChooser(intent, "Select an Image"), PICK_IMAGE_REQUEST);

            }
        });




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    name = edit_name.getText().toString();

                    fatherName = edit_fname.getText().toString();

                    salary = Double.parseDouble(edit_salary.getText().toString());

                    if (TextUtils.isEmpty(name)|| TextUtils.isEmpty(fatherName) || salary==-1){
                        Toast.makeText(getContext(), "Please Complete This Form", Toast.LENGTH_SHORT).show();

                        return;
                    }



//



                    else {

                        if(filepath!=null){

                            file = new File(String.valueOf(filepath));

                            final StorageReference store = storageReference.child("Images/"+file.getName()+".jpg");

                            store.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

//                            progressDialog.dismiss();

                                    mydata = new Data(name,fatherName,salary ,StaticClass.databaseReference.push().getKey(),taskSnapshot.getDownloadUrl().toString(),false);
                                    StaticClass.databaseReference.child(mydata.getId()).setValue(mydata);

                                    Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();

                                    edit_name.setText("");
                                    edit_fname.setText("");
                                    edit_salary.setText("");
                                    imageButton.setImageBitmap(null);

                                }


                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });

                        }






                    }


                }catch (Exception e){

                    Toast.makeText(getContext(), ""+e, Toast.LENGTH_SHORT).show();
                }
            }
        });


        view.findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseAuth.signOut();
                startActivity(new Intent(getContext(),MainActivity.class));

            }
        });



        return view;
    }

}

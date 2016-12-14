package com.example.bakhtiyar.overviewallfirebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    EditText email, password;

    ProgressDialog progressDialog;

    Button login;

    String txt_email,txt_password;

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);

        login = (Button) findViewById(R.id.btn_login);

        txt = (TextView) findViewById(R.id.link_signup);

        progressDialog = new ProgressDialog(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txt_email = email.getText().toString();

                txt_password = password.getText().toString();

                if(TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_password)){

                    Toast.makeText(MainActivity.this, "Please Insert Name and Password", Toast.LENGTH_SHORT).show();

                    return;
                }

                else {
                    progressDialog.setMessage("Wait...");
                    progressDialog.show();
                    firebaseAuth.signInWithEmailAndPassword(txt_email,txt_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                progressDialog.dismiss();

                                StaticClass.UUID = firebaseAuth.getCurrentUser().getUid();

                                StaticClass.databaseReference = FirebaseDatabase.getInstance().getReference(firebaseAuth.getCurrentUser().getUid());

                                startActivity(new Intent(MainActivity.this,MainUserActivity.class));
//                                Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(MainActivity.this, "Not Successfull", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }

            }
        });


        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });



    }
}

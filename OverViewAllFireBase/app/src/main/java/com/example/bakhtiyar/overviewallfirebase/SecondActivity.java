package com.example.bakhtiyar.overviewallfirebase;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    EditText email, password;

    String txt_email, txt_password;

    Button sign_up;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        firebaseAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.sign_input_email);
        password = (EditText) findViewById(R.id.sign_input_password);

        sign_up = (Button) findViewById(R.id.sign_btn_login);


        progressDialog = new ProgressDialog(this);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txt_email = email.getText().toString();

                txt_password = password.getText().toString();

                if(TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_password)){

                    Toast.makeText(SecondActivity.this, "please type email and password", Toast.LENGTH_SHORT).show();

                    return;

                }else {
                    firebaseAuth.createUserWithEmailAndPassword(txt_email,txt_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            progressDialog.setMessage("Wait...");

                            progressDialog.show();

                            if(task.isSuccessful()){

                                progressDialog.dismiss();

                                Toast.makeText(SecondActivity.this, "Successful", Toast.LENGTH_SHORT).show();

                            }else {

                                progressDialog.dismiss();
                                Toast.makeText(SecondActivity.this, "Not Successful", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });

                }



            }
        });

    }
}

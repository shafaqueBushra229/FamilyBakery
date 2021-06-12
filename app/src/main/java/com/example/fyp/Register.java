package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import io.alterac.blurkit.BlurLayout;

public class Register extends AppCompatActivity {
    BlurLayout blurLayout;

    private EditText mEmail,mPass;
    private TextView mTextView;
    private Button signUpBtn;

    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        blurLayout = findViewById(R.id.blurLayout);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        mEmail = findViewById(R.id.email);
        mPass = findViewById(R.id.password);
        mTextView = findViewById(R.id.loginTextView);
        signUpBtn = findViewById(R.id.Register);

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });


        signUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

    }

    private void createUser(){
        String email = mEmail.getText().toString();
        String pass = mPass.getText().toString();

        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if(!pass.isEmpty()){

                mAuth.createUserWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                             @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                 User user = new User(mEmail.getText().toString(),mPass.getText().toString());

                                Toast.makeText(Register.this,"Register Successfully !!",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this,Login.class));
                                String id = task.getResult().getUser().getUid();
                                database.getReference().child("Users").child(id).setValue(user);

                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this,"Register Fail!!",Toast.LENGTH_SHORT).show();
                    }
                });

            }else{
                mPass.setText("Empty Fields Are Not Allowed");
            }}
        else if(email.isEmpty()){
            mEmail.setError("Empty Fields Are Not Allowed");
        }else {
            mEmail.setError("Please Enter Correct Email");
        }
    }
}

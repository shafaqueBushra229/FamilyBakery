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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import io.alterac.blurkit.BlurLayout;

public class Login extends AppCompatActivity {

    BlurLayout blurLayout;
    private EditText mEmail,mPass;
    private TextView mTextView;
    private Button signUpBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        blurLayout = findViewById(R.id.blurLayout);
        getSupportActionBar().hide();

        mEmail = findViewById(R.id.email);
        mPass = findViewById(R.id.password);
        mTextView = findViewById(R.id.RegisterTextView);
        signUpBtn = findViewById(R.id.Login);

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });


        signUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

      /*  Intent intent = new Intent();
        intent*/
    }
    @Override
    public void onBackPressed(){
        // super.onBackPressed();
    }

   private void loginUser(){
        String email = mEmail.getText().toString();
        String pass = mPass.getText().toString();

        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                if(!pass.isEmpty()){

                   mAuth.signInWithEmailAndPassword(email,pass)
                           .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                               @Override
                               public void onSuccess(AuthResult authResult) {
                                   Toast.makeText(Login.this,"Login Sucessfully !!",Toast.LENGTH_SHORT).show();
                                   startActivity(new Intent(Login.this,MainActivity.class));
                                   finish();
                               }
                           }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                           Toast.makeText(Login.this,"Login Failed !!",Toast.LENGTH_SHORT).show();
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
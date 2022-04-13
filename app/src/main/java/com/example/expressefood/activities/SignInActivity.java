package com.example.expressefood.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.expressefood.R;
import com.example.expressefood.commom.Commom;
import com.example.expressefood.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {

    private Button btnSignIn;
    private LinearLayout lnSingUpNext;
    private EditText edPhone,edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        lnSingUpNext = findViewById(R.id.lnSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);
        edPhone=findViewById(R.id.edPhoneInput);
        edPassword=findViewById(R.id.edPasswordInput);

        final FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference tableUser=firebaseDatabase.getReference("User");

        //Next Sign up
        lnSingUpNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this,SignUpActivity.class));
            }
        });


        //Sign in
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Dialog loangding
                ProgressDialog mDialog= new ProgressDialog(SignInActivity.this);
                mDialog.setMessage("Please waiting...");
                mDialog.show();

                tableUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //check user not exits in firebase
                        if(dataSnapshot.child(edPhone.getText().toString()).exists()){
                            //get user infomation
                            mDialog.dismiss();
                            User user=dataSnapshot.child(edPhone.getText().toString()).getValue(User.class);
                            if(user.getPassword().equals(edPassword.getText().toString())){
                                Commom.currentUser=user;
                                startActivity(new Intent(SignInActivity.this, MainActivity.class));
                                finish();
                            }
                            else {
                                Toast.makeText(SignInActivity.this, "Sign in failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            mDialog.dismiss();
                            Toast.makeText(SignInActivity.this, "User is not exist", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

}
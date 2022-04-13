package com.example.expressefood.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.expressefood.R;
import com.example.expressefood.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {
    private Button btnSignUp;
    private LinearLayout lnSignIn;
    private EditText edtPassword,edtConfirnPassword,edtPhone,etname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        lnSignIn=findViewById(R.id.lnSignInIntern);
        btnSignUp=findViewById(R.id.btnSignUp);
        edtPassword=findViewById(R.id.edPasswordInput);
        edtConfirnPassword=findViewById(R.id.edPasswordConfirmInput);
        edtPhone=findViewById(R.id.edPhoneInput);
        etname=findViewById(R.id.edNameInput);

        final FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference tableUser=firebaseDatabase.getReference("User");


        lnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Dialog loangding
                ProgressDialog mDialog= new ProgressDialog(SignUpActivity.this);
                mDialog.setMessage("Please waiting...");
                mDialog.show();

                if(isValidSignUpDetails()){
                    tableUser.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child(edtPhone.getText().toString()).exists()){
                                mDialog.dismiss();
                                showToast("Phone number alredy register");
                            }
                            else {
                                mDialog.dismiss();
                                User user=new User(etname.getText().toString(),edtPassword.getText().toString(),edtPhone.getText().toString());
                                tableUser.child(edtPhone.getText().toString()).setValue(user);
                                showToast("Sign Up successfully");
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }
        });
    }

    private void showToast(String toast){
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }
    private Boolean isValidSignUpDetails(){

        if (TextUtils.isEmpty(edtPhone.getText().toString())) {
            showToast("Enter Phone");
            return false;
        } else if (TextUtils.isEmpty(edtPassword.getText().toString())) {
            showToast("Enter Password");
            return false;
        } else if (TextUtils.isEmpty(edtConfirnPassword.getText().toString())) {
            showToast("Enter Confirm Password");
            return false;
        } else if (edtConfirnPassword.getText().toString().length() < 6) {
            showToast("Password must be more than 6 character");
            return false;
        } else if (!edtPassword.getText().toString().equals(edtConfirnPassword.getText().toString())) {
            showToast("Password & confirm password must be same");
            return false;
        } else {
            return true;
        }
    }
}
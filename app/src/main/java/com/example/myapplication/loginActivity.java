package com.example.myapplication;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Common.Common;
import com.rengwuxian.materialedittext.MaterialEditText;

import com.example.myapplication.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class loginActivity extends AppCompatActivity {
     private MaterialEditText NewUser, NewPassword, NewEmail;
    private MaterialEditText edtUser, edtPassword;

    Button btnSignUp, btnSignIn;

    FirebaseDatabase database;
    DatabaseReference users;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        edtUser =  findViewById(R.id.username);
        edtPassword =  findViewById(R.id.password);

        btnSignUp = findViewById(R.id.register);
        btnSignIn = findViewById(R.id.login);
        NewUser =  findViewById(R.id.newUsername);
        NewPassword =   findViewById(R.id.newPassword);
        NewEmail = findViewById(R.id.email);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showSignUpDialog();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn(edtUser.getText().toString(), edtPassword.getText().toString());
            }
        });

    }

    private void SignIn(final String user, final String password) {


        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(user).exists()) {
                    if (!user.isEmpty()) {
                        User login = dataSnapshot.child(user).getValue(User.class);
                        assert login != null;
                        if (login.getPassword().equals(password)) {

                            Intent homeActivity = new Intent(loginActivity.this, Home.class);
                            Common.currentUser = login;
                            startActivity(homeActivity);
                            finish();
                        }else
                            Toast.makeText(loginActivity.this, "Wrong password !", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(loginActivity.this, "Please enter your user name !", Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(loginActivity.this, "User is not exists !", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showSignUpDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(loginActivity.this);
        alertDialog.setTitle("Sign Up");
        alertDialog.setMessage("Please fill full information");

        LayoutInflater inflater = this.getLayoutInflater();
        View sign_up_layout = inflater.inflate(R.layout.sign_up_layout,null);

        NewUser = sign_up_layout.findViewById(R.id.newUsername);
        NewPassword = sign_up_layout.findViewById(R.id.newPassword);
        NewEmail = sign_up_layout.findViewById(R.id.email);

        alertDialog.setView(sign_up_layout);
        alertDialog.setIcon(R.drawable.ic_android_black_24dp);

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final  User user = new User(NewUser.getText().toString(),NewPassword.getText().toString(),NewEmail.getText().toString());

                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(user.getUsername()).exists())
                            Toast.makeText(loginActivity.this,"User Already Exists!",Toast.LENGTH_SHORT).show();
                        else {
                            users.child(user.getUsername()).setValue(user);
                            Intent intent = new Intent(loginActivity.this,Home.class);
                            startActivity(intent);
                            finish();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                dialogInterface.dismiss();
            }
        });



        alertDialog.show();
    }
}
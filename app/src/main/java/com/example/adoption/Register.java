package com.example.adoption;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    FirebaseFirestore db;

    EditText new_name, new_password;
    TextView logIn;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        new_name = findViewById(R.id.newNameEditText);
        new_password = findViewById(R.id.newPasswordEditText);
        logIn = findViewById(R.id.logInText);
        checkBox = findViewById(R.id.checkBox);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, LogIn.class));
            }
        });

    }

    public void RegisterOnClick(View view) {

        // if details are correct
        if (new_name.length() != 0 && new_password.length() != 0 && (new_name.length() >= 3 && new_name.length() <= 20) && (new_password.length() >= 5 && new_password.length() <= 10)) {
            db = FirebaseFirestore.getInstance();

            // save new user in database
            DocumentReference docRef = db.collection("users").document(new_name.getText().toString());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            new_name.setError("this user name is already taken");
                        } else {
                            db.collection("users")
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                            if (task.isSuccessful()) {
                                                Map<String, Object> currentUser = new HashMap<>();
                                                currentUser.put("Name", new_name.getText().toString());
                                                currentUser.put("Password", new_password.getText().toString());
                                                currentUser.put("Cats", new ArrayList<String>());
                                                currentUser.put("Dogs", new ArrayList<String>());

                                                boolean isChecked = checkBox.isChecked();
                                                if (isChecked) {
                                                    // Checkbox is checked
                                                    currentUser.put("Admin", true);
                                                    User.admin = true;
                                                } else {
                                                    // Checkbox is not checked
                                                    currentUser.put("Admin", false);
                                                    User.admin = false;
                                                }

                                                db.collection("users").document(new_name.getText().toString()).set(currentUser);

                                                User.userName = new_name.getText().toString();

                                                startActivity(new Intent(Register.this, MainActivity.class));

                                            } else {
                                                Log.w(TAG, "Error getting documents.", task.getException());
                                            }
                                        }
                                    });
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });


        }
        // display detail errors
        else {
            if (new_name.length() == 0) {
                new_name.setError("enter your name");
            } else {
                if (new_name.length() < 3) {
                    new_name.setError("at least 3 characters long");
                }
                if (new_name.length() > 20) {
                    new_name.setError("at most 20 characters long");
                }
            }

            if (new_password.length() == 0) {
                new_password.setError("enter your password");
            } else {
                if (new_password.length() < 5) {
                    new_password.setError("at least 5 characters long");
                }
                if (new_password.length() > 10) {
                    new_password.setError("at most 10 characters long");
                }
            }
        }
    }
}
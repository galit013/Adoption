package com.example.adoption;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LogIn extends AppCompatActivity {

    FirebaseFirestore db;

    EditText name, password;
    TextView register;

    String correct_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        name = findViewById(R.id.nameEditText);
        password = findViewById(R.id.passwordEditText);
        register = findViewById(R.id.registerText);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogIn.this, Register.class));
            }
        });

    }

    public void logInOnClick(View view) {

        // if details are correct
        if (name.length() != 0 && password.length() != 0 && (name.length() >= 3 && name.length() <= 20) && (password.length() >= 5 && password.length() <= 10)) {

            db = FirebaseFirestore.getInstance();
            DocumentReference docRef = db.collection("users").document(name.getText().toString());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                // check if details match data in database
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            correct_password = document.getData().get("Password").toString();
                            if(correct_password.equals(password.getText().toString())){
                                User.userName = name.getText().toString();
                                User.admin = Boolean.parseBoolean(document.getData().get("Admin").toString());
                                startActivity(new Intent(LogIn.this, MainActivity.class));
                            }
                            else{
                                password.setError("wrong password");
                            }
                        } else {
                            name.setError("such name doesn't exists");
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });

        }
        // display detail errors
        else {
            if (name.length() == 0) {
                name.setError("enter your name");
            } else {
                if (name.length() < 3) {
                    name.setError("at least 3 characters long");
                }
                if (name.length() > 20) {
                    name.setError("at most 20 characters long");
                }
            }

            if (password.length() == 0) {
                password.setError("enter your password");
            } else {
                if (password.length() < 5) {
                    password.setError("at least 5 characters long");
                }
                if (password.length() > 10) {
                    password.setError("at most 10 characters long");
                }
            }
        }
    }
}
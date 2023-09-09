package com.example.adoption;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    TextView titleTv, adminTV, total_cats, cat_names, total_dogs, dog_names;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        titleTv = findViewById(R.id.titleTV);
        adminTV = findViewById(R.id.adminTV);
        total_cats = findViewById(R.id.total_cats);
        total_dogs = findViewById(R.id.total_dogs);
        cat_names = findViewById(R.id.cat_names);
        dog_names = findViewById(R.id.dog_names);

        titleTv.setText("Hello " + User.userName);
        if(User.admin){
            adminTV.setText("You are an admin");
        }
        else{
            adminTV.setText("You are not an admin");
        }

        // access to database
        db = FirebaseFirestore.getInstance();

        DocumentReference userRef = db.collection("users").document(User.userName);
        // Retrieve the current user data from Firestore
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        // Get the ArrayLists
                        ArrayList<String> cats = (ArrayList<String>) document.get("Cats");
                        ArrayList<String> dogs = (ArrayList<String>) document.get("Dogs");

                        total_cats.setText("total: " + cats.size());
                        total_dogs.setText("total: " + dogs.size());

                        if (cats.size() != 0){
                            cat_names.setText(TextUtils.join(", ", cats));
                        }
                        else{
                            cat_names.setText("you haven't adopted any cats");
                        }

                        if (dogs.size() != 0){
                            dog_names.setText(TextUtils.join(", ", dogs));
                        }
                        else{
                            dog_names.setText("you haven't adopted any dogs");
                        }

                    }
                    else {
                        Log.d("TAG", "User document doesn't exist.");
                    }
                } else {
                    Log.w("TAG", "Error getting user document", task.getException());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // create menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // check which menu item was clicked
        int id = item.getItemId();
        switch (id) {
            case R.id.home_screen:
                startActivity(new Intent(Profile.this, MainActivity.class));
                return true;
            case R.id.cats_screen:
                startActivity(new Intent(Profile.this, Cats.class));
                return true;
            case R.id.dogs_screen:
                Intent intent = new Intent(Profile.this, Dogs.class);
                startActivity(intent);
                return true;
            case R.id.add_animal_screen:
                if(User.admin){
                    startActivity(new Intent(Profile.this, AddAnimal.class));
                }
                else{
                    Toast.makeText(getApplicationContext(), "only admins can add animals", Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.profile:
                return true;
            case R.id.log_out:
                startActivity(new Intent(Profile.this, LogIn.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
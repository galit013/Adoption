package com.example.adoption;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.util.ArrayList;

public class Cats extends AppCompatActivity {

    Button details_cleo, details_luna, details_oliver, details_simba;
    Button adopt_cleo, adopt_luna, adopt_oliver, adopt_simba;
    ImageView cleo_cat, luna_cat, oliver_cat, simba_cat;

    FirebaseFirestore db;
    String name, breed, color;
    int age, weight;
    Boolean adopted;
    String text_for_fragment;

    String cat_name, document_cat;
    String cleo_name, luna_name, oliver_name, simba_name;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cats);

        details_cleo = findViewById(R.id.details_cleo_cat);
        details_luna = findViewById(R.id.details_luna_cat);
        details_oliver = findViewById(R.id.details_oliver_cat);
        details_simba = findViewById(R.id.details_simba_cat);
        adopt_cleo = findViewById(R.id.adopt_cleo_cat);
        adopt_luna = findViewById(R.id.adopt_luna_cat);
        adopt_oliver = findViewById(R.id.adopt_oliver_cat);
        adopt_simba = findViewById(R.id.adopt_simba_cat);
        cleo_cat = findViewById(R.id.cleo_cat_img);
        luna_cat = findViewById(R.id.luna_cat_img);
        oliver_cat = findViewById(R.id.oliver_cat_img);
        simba_cat = findViewById(R.id.simba_cat_img);

        // Retrieve the file path and file name from the shared preference
        SharedPreferences prefs = getSharedPreferences("my_prefs_cats", MODE_PRIVATE);
        String filePathCleo = prefs.getString("Cleo", null);
        String filePathLuna = prefs.getString("Luna", null);
        String filePathOliver = prefs.getString("Oliver", null);
        String filePathSimba = prefs.getString("Simba", null);

        // אם קיימת תמונה של החתול באחסון המקומי
        if(prefs.contains("Cleo")){
            // Load the bitmap image from the saved file
            if (filePathCleo != null) {
                File file = new File(filePathCleo);
                Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                // show on screen on ImageView
                cleo_cat.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 160, 160, false));
            }
        }

        if(prefs.contains("Luna")){
            // Load the bitmap image from the saved file
            if (filePathLuna != null) {
                File file = new File(filePathLuna);
                Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                // show on screen on ImageView
                luna_cat.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 160, 160, false));
            }
        }

        if(prefs.contains("Oliver")){
            // Load the bitmap image from the saved file
            if (filePathOliver != null) {
                File file = new File(filePathOliver);
                Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                // show on screen on ImageView
                oliver_cat.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 160, 160, false));
            }
        }

        if(prefs.contains("Simba")){
            // Load the bitmap image from the saved file
            if (filePathSimba != null) {
                File file = new File(filePathSimba);
                Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                // show on screen on ImageView
                simba_cat.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 160, 160, false));
            }
        }

        // access to database
        db = FirebaseFirestore.getInstance();

        db.collection("cats").document("Cleo").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        cleo_name = document.get("Name").toString();
                    }
                }
            }
        });

        db.collection("cats").document("Luna").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        luna_name = document.get("Name").toString();
                    }
                }
            }
        });

        db.collection("cats").document("Oliver").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        oliver_name = document.get("Name").toString();
                    }
                }
            }
        });

        db.collection("cats").document("Simba").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        simba_name = document.get("Name").toString();
                    }
                }
            }
        });

        details_cleo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DocumentReference docRef = db.collection("cats").document("Cleo");

                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                name = document.get("Name").toString();
                                breed = document.get("Breed").toString();
                                color = document.get("Color").toString();
                                age = Integer.valueOf(document.get("Age").toString());
                                weight = Integer.valueOf(document.get("Weight").toString());

                                text_for_fragment = "Name: " + name + ". Age: " + age + ". Weight: " + weight + ". Breed: " + breed + ". Color: " + color;
                                Animal.details_text = text_for_fragment;

                                loadFragment(new AnimalFragment());

                            }
                            // if not, continue
                            else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                                Log.d(TAG, "No such document");
                        }
                    }
                });

            }
        });

        details_luna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference docRef = db.collection("cats").document("Luna");

                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                name = document.get("Name").toString();
                                breed = document.get("Breed").toString();
                                color = document.get("Color").toString();
                                age = Integer.valueOf(document.get("Age").toString());
                                weight = Integer.valueOf(document.get("Weight").toString());

                                text_for_fragment = "Name: " + name + ". Age: " + age + ". Weight: " + weight + ". Breed: " + breed + ". Color: " + color;

                                Animal.details_text = text_for_fragment;

                                loadFragment(new AnimalFragment());

                            }
                            // if not, continue
                            else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    }
                });

            }
        });

        details_oliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference docRef = db.collection("cats").document("Oliver");

                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                name = document.get("Name").toString();
                                breed = document.get("Breed").toString();
                                color = document.get("Color").toString();
                                age = Integer.valueOf(document.get("Age").toString());
                                weight = Integer.valueOf(document.get("Weight").toString());

                                text_for_fragment = "Name: " + name + ". Age: " + age + ". Weight: " + weight + ". Breed: " + breed + ". Color: " + color;

                                Animal.details_text = text_for_fragment;

                                loadFragment(new AnimalFragment());

                            }
                            // if not, continue
                            else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    }
                });

            }
        });

        details_simba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference docRef = db.collection("cats").document("Simba");

                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                name = document.get("Name").toString();
                                breed = document.get("Breed").toString();
                                color = document.get("Color").toString();
                                age = Integer.valueOf(document.get("Age").toString());
                                weight = Integer.valueOf(document.get("Weight").toString());

                                text_for_fragment = "Name: " + name + ". Age: " + age + ". Weight: " + weight + ". Breed: " + breed + ". Color: " + color;

                                Animal.details_text = text_for_fragment;

                                loadFragment(new AnimalFragment());

                            }
                            // if not, continue
                            else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    }
                });

            }
        });

    }


    private void loadFragment(Fragment fragment) {
        // create a FragmentManager
        FragmentManager fm = getFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        // save the changes
        fragmentTransaction.commit();
    }

    public void adoptCat(View view) {
        switch(view.getId()) {
            case R.id.adopt_cleo_cat:
                cat_name = cleo_name;
                document_cat = "Cleo";
                break;
            case R.id.adopt_luna_cat:
                cat_name = luna_name;
                document_cat = "Luna";
                break;
            case R.id.adopt_oliver_cat:
                cat_name = oliver_name;
                document_cat = "Oliver";
                break;
            case R.id.adopt_simba_cat:
                cat_name = simba_name;
                document_cat = "Simba";
                break;
        }
        DocumentReference docRef = db.collection("cats").document(document_cat);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        adopted = Boolean.parseBoolean(document.get("Adopted").toString());
                        if (adopted){
                            AlertDialog.Builder builder = new AlertDialog.Builder(Cats.this);
                            builder.setTitle(cat_name + " was already adopted!")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // do nothing
                                        }
                                    });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(Cats.this);
                            builder.setTitle("Are you sure you want to adopt " + cat_name + "?")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // Get the reference to the user document
                                            DocumentReference userRef = db.collection("users").document(User.userName);

                                            // Retrieve the current user data from Firestore
                                            userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        DocumentSnapshot document = task.getResult();
                                                        if (document.exists()) {
                                                            // Get the current "Cats" ArrayList
                                                            ArrayList<String> currentCats = (ArrayList<String>) document.get("Cats");
                                                            if (currentCats == null) {
                                                                currentCats = new ArrayList<String>();
                                                            }

                                                            // Add the new cat name to the ArrayList
                                                            currentCats.add(cat_name);

                                                            // Update the "Cats" field with the modified ArrayList
                                                            userRef.update("Cats", currentCats)
                                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void aVoid) {
                                                                            Log.d("TAG", "Cats field updated successfully.");
                                                                        }
                                                                    })
                                                                    .addOnFailureListener(new OnFailureListener() {
                                                                        @Override
                                                                        public void onFailure(@NonNull Exception e) {
                                                                            Log.w("TAG", "Error updating Cats field", e);
                                                                        }
                                                                    });
                                                        } else {
                                                            Log.d("TAG", "User document doesn't exist.");
                                                        }
                                                    } else {
                                                        Log.w("TAG", "Error getting user document", task.getException());
                                                    }

                                                    docRef.update("Adopted", true).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            // The update was successful
                                                            Log.d(TAG, "updated");
                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            // Handle any errors that occurred
                                                            Log.d(TAG, "error");
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    })
                                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int id) {
                                            // do nothing
                                        }
                                    });
                            AlertDialog dialog = builder.create();
                            dialog.show();

                        }

                    }
                    // if not, continue
                    else {
                        Log.d(TAG, "No such document");
                    }

                } else {
                    Log.d(TAG, "No such document");
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
                startActivity(new Intent(Cats.this, MainActivity.class));
                return true;
            case R.id.cats_screen:
                return true;
            case R.id.dogs_screen:
                Intent intent = new Intent(Cats.this, Dogs.class);
                startActivity(intent);
                return true;
            case R.id.add_animal_screen:
                if(User.admin){
                    startActivity(new Intent(Cats.this, AddAnimal.class));
                }
                else{
                    Toast.makeText(getApplicationContext(), "only admins can add animals", Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.profile:
                startActivity(new Intent(Cats.this, Profile.class));
                return true;
            case R.id.log_out:
                startActivity(new Intent(Cats.this, LogIn.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
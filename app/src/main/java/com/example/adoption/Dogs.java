package com.example.adoption;

import static android.content.ContentValues.TAG;

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

public class Dogs extends AppCompatActivity {

    Button details_zeus, details_daisy, details_luna, details_max;
    Button adopt_zeus, adopt_daisy, adopt_luna, adopt_max;
    ImageView zeus_dog, daisy_dog, luna_dog, max_dog;

    FirebaseFirestore db;
    String name, breed, color;
    int age, weight;
    Boolean adopted;
    String text_for_fragment;

    String dog_name, document_dog;
    String zeus_name, daisy_name, luna_name, max_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs);

        details_zeus = findViewById(R.id.details_zeus_dog);
        details_daisy = findViewById(R.id.details_daisy_dog);
        details_luna = findViewById(R.id.details_luna_dog);
        details_max = findViewById(R.id.details_max_dog);
        adopt_zeus = findViewById(R.id.adopt_zeus_dog);
        adopt_daisy = findViewById(R.id.adopt_daisy_dog);
        adopt_luna = findViewById(R.id.adopt_luna_dog);
        adopt_max = findViewById(R.id.adopt_max_dog);
        zeus_dog = findViewById(R.id.zeus_dog_img);
        daisy_dog = findViewById(R.id.daisy_dog_img);
        luna_dog = findViewById(R.id.luna_dog_img);
        max_dog = findViewById(R.id.max_dog_img);

        // Retrieve the file path and file name from the shared preference
        SharedPreferences prefs = getSharedPreferences("my_prefs_dogs", MODE_PRIVATE);
        String filePathZeus = prefs.getString("Zeus", null);
        String filePathDaisy = prefs.getString("Daisy", null);
        String filePathLuna = prefs.getString("Luna", null);
        String filePathMax = prefs.getString("Max", null);


        if(prefs.contains("Zeus")){
            // Load the bitmap image from the saved file
            if (filePathZeus != null) {
                File file = new File(filePathZeus);
                Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                // show on screen on ImageView
                zeus_dog.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 160, 160, false));
            }
        }

        if(prefs.contains("Daisy")){
            // Load the bitmap image from the saved file
            if (filePathDaisy != null) {
                File file = new File(filePathDaisy);
                Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                // show on screen on ImageView
                daisy_dog.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 160, 160, false));
            }
        }

        if(prefs.contains("Luna")){
            // Load the bitmap image from the saved file
            if (filePathLuna != null) {
                File file = new File(filePathLuna);
                Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                // show on screen on ImageView
                luna_dog.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 160, 160, false));
            }
        }

        if(prefs.contains("Max")){
            // Load the bitmap image from the saved file
            if (filePathMax != null) {
                File file = new File(filePathMax);
                Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                // show on screen on ImageView
                max_dog.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 160, 160, false));
            }
        }

        // access to database
        db = FirebaseFirestore.getInstance();

        db.collection("dogs").document("Zeus").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        zeus_name = document.get("Name").toString();
                    }
                }
            }
        });

        db.collection("dogs").document("Daisy").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        daisy_name = document.get("Name").toString();
                    }
                }
            }
        });

        db.collection("dogs").document("Luna").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
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

        db.collection("dogs").document("Max").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        max_name = document.get("Name").toString();
                    }
                }
            }
        });

        details_zeus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference docRef = db.collection("dogs").document("Zeus");

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
                                Log.d(TAG, "No document");
                            }
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    }
                });

            }
        });

        details_daisy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference docRef = db.collection("dogs").document("Daisy");

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
                                Log.d(TAG, "No document");
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
                DocumentReference docRef = db.collection("dogs").document("Luna");

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
                                Log.d(TAG, "No document");
                            }
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    }
                });

            }
        });

        details_max.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference docRef = db.collection("dogs").document("Max");

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
                                Log.d(TAG, "No document");
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

    public void adoptDog(View view) {
        switch(view.getId()) {
            case R.id.adopt_zeus_dog:
                dog_name = zeus_name;
                document_dog = "Zeus";
                break;
            case R.id.adopt_daisy_dog:
                dog_name = daisy_name;
                document_dog = "Daisy";
                break;
            case R.id.adopt_luna_dog:
                dog_name = luna_name;
                document_dog = "Luna";
                break;
            case R.id.adopt_max_dog:
                dog_name = max_name;
                document_dog = "Max";
                break;
        }
        DocumentReference docRef = db.collection("dogs").document(document_dog);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        adopted = Boolean.parseBoolean(document.get("Adopted").toString());
                        if (adopted){
                            AlertDialog.Builder builder = new AlertDialog.Builder(Dogs.this);
                            builder.setTitle(dog_name + " was already adopted!")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // do nothing
                                        }
                                    });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(Dogs.this);
                            builder.setTitle("Are you sure you want to adopt " + dog_name + "?")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            System.out.println("ok");
                                            // Get the reference to the user document
                                            DocumentReference userRef = db.collection("users").document(User.userName);

                                            // Retrieve the current user data from Firestore
                                            userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        DocumentSnapshot document = task.getResult();
                                                        if (document.exists()) {
                                                            // Get the current "Dogs" ArrayList
                                                            ArrayList<String> currentDogs = (ArrayList<String>) document.get("Dogs");
                                                            if (currentDogs == null) {
                                                                currentDogs = new ArrayList<String>();
                                                            }

                                                            // Add the new cat name to the ArrayList
                                                            currentDogs.add(dog_name);

                                                            // Update the "Cats" field with the modified ArrayList
                                                            userRef.update("Dogs", currentDogs)
                                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void aVoid) {
                                                                            Log.d("TAG", "Dogs field updated successfully.");
                                                                        }
                                                                    })
                                                                    .addOnFailureListener(new OnFailureListener() {
                                                                        @Override
                                                                        public void onFailure(@NonNull Exception e) {
                                                                            Log.w("TAG", "Error updating Dogs field", e);
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
                startActivity(new Intent(Dogs.this, MainActivity.class));
                return true;
            case R.id.cats_screen:
                Intent intent = new Intent(Dogs.this, Cats.class);
                startActivity(intent);
                return true;
            case R.id.dogs_screen:
                return true;
            case R.id.add_animal_screen:
                if(User.admin){
                    startActivity(new Intent(Dogs.this, AddAnimal.class));
                }
                else{
                    Toast.makeText(getApplicationContext(), "only admins can add animals", Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.profile:
                startActivity(new Intent(Dogs.this, Profile.class));
                return true;
            case R.id.log_out:
                startActivity(new Intent(Dogs.this, LogIn.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
package com.example.adoption;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class AddAnimal extends AppCompatActivity {

    Spinner pick_animal;
    EditText animal_name, animal_age, animal_breed, animal_weight, animal_color;
    Button add_animal;
    ImageView camera, gallery;
    FirebaseFirestore db;
    int enough_room;
    String selectedOption;

    private static final int MY_CAMERA_PERMISSION_CODE = 2;
    private static final int MY_GALLERY_PERMISSION_CODE = 3;
    private static final int CAMERA_REQUEST_CODE = 0;
    private static final int GALLERY_REQUEST_CODE = 1;

    Uri filepath;
    Bitmap bitmap = null;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);

        pick_animal = findViewById(R.id.pick_animal);
        animal_name = findViewById(R.id.animal_name);
        animal_age = findViewById(R.id.animal_age);
        animal_breed = findViewById(R.id.animal_breed);
        animal_weight = findViewById(R.id.animal_weight);
        animal_color = findViewById(R.id.animal_color);
        add_animal = findViewById(R.id.addBtn);
        camera = findViewById(R.id.camera);
        gallery = findViewById(R.id.gallery);

        // מערך של האופציות לבחירת כלבים או חתולים - מכניסים לספינר
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"cats", "dogs"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pick_animal.setAdapter(adapter);

        pick_animal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // מה שהמשתמש בחר
                selectedOption = (String) parent.getItemAtPosition(position);

                db = FirebaseFirestore.getInstance();
                DocumentReference docRef;
                enough_room = 0;

                if(selectedOption == "cats"){
                    docRef = db.collection(selectedOption).document("Cleo");

                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    // אם החתול מאומץ
                                    if(Boolean.parseBoolean(document.get("Adopted").toString())){
                                        enough_room = 1;
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

                    docRef = db.collection(selectedOption).document("Luna");

                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    if(Boolean.parseBoolean(document.get("Adopted").toString())){
                                        enough_room = 2;
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

                    docRef = db.collection(selectedOption).document("Oliver");

                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    if(Boolean.parseBoolean(document.get("Adopted").toString())){
                                        enough_room = 3;
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

                    docRef = db.collection(selectedOption).document("Simba");

                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    if(Boolean.parseBoolean(document.get("Adopted").toString())){
                                        enough_room = 4;
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


                if(selectedOption == "dogs"){
                    docRef = db.collection(selectedOption).document("Zeus");

                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    if(Boolean.parseBoolean(document.get("Adopted").toString())){
                                        enough_room = 1;
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

                    docRef = db.collection(selectedOption).document("Daisy");

                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    if(Boolean.parseBoolean(document.get("Adopted").toString())){
                                        enough_room = 2;
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

                    docRef = db.collection(selectedOption).document("Luna");

                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    if(Boolean.parseBoolean(document.get("Adopted").toString())){
                                        enough_room = 3;
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

                    docRef = db.collection(selectedOption).document("Max");

                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    if(Boolean.parseBoolean(document.get("Adopted").toString())){
                                        enough_room = 4;
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

                //  מחכה 2 שניות לסיום הבדיקה
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // This method will be executed once the timer is over - go to log in screen
                        if(enough_room == 0){
                            Toast.makeText(getApplicationContext(), "there is not enough room for more " + selectedOption + "!", Toast.LENGTH_LONG).show();
                        }
                    }
                }, 2000);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "please pick the type of the animal", Toast.LENGTH_LONG).show();
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check permission and open camera
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else
                {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
                }
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check permission and open gallery
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE , Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_GALLERY_PERMISSION_CODE);
                }
                else
                {
                    Intent intentGallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intentGallery, GALLERY_REQUEST_CODE);
                }
            }
        });

        add_animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(enough_room > 0){
                    if(animal_name.length() == 0){
                        animal_name.setError("enter animals name");
                    }
                    else if(animal_age.length() == 0){
                        animal_age.setError("enter animals age");
                    }
                    else if(animal_breed.length() == 0){
                        animal_breed.setError("enter animals breed");
                    }
                    else if(animal_weight.length() == 0){
                        animal_weight.setError("enter animals weight");
                    }
                    else if(animal_color.length() == 0){
                        animal_color.setError("enter animals color");
                    }
                    else if(animal_name.length() < 3){
                        animal_name.setError("enter a valid animals name");
                    }
                    else if(animal_age.length() < 0 || animal_age.length() > 3){
                        animal_age.setError("enter a valid animals age");
                    }
                    else if(animal_breed.length() < 3){
                        animal_breed.setError("enter a valid animals breed");
                    }
                    else if(animal_weight.length() < 0 || animal_weight.length() > 4){
                        animal_weight.setError("enter a valid animals weight");
                    }
                    else if(animal_color.length() < 3){
                        animal_color.setError("enter a valid animals color");
                    }
                    else{
                        if(selectedOption == "cats"){
                            if(enough_room == 1){
                                DocumentReference docRef = db.collection("cats").document("Cleo");
                                docRef.update("Name", animal_name.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Age", Integer.valueOf(animal_age.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Breed", animal_breed.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Weight", Integer.valueOf(animal_weight.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Color", animal_color.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Adopted", false).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                savePicture("cats","Cleo");
                            }

                            else if(enough_room == 2){
                                DocumentReference docRef = db.collection("cats").document("Luna");
                                docRef.update("Name", animal_name.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Age", Integer.valueOf(animal_age.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Breed", animal_breed.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Weight", Integer.valueOf(animal_weight.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Color", animal_color.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Adopted", false).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                savePicture("cats", "Luna");
                            }

                            else if(enough_room == 3){
                                DocumentReference docRef = db.collection("cats").document("Oliver");
                                docRef.update("Name", animal_name.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Age", Integer.valueOf(animal_age.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Breed", animal_breed.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Weight", Integer.valueOf(animal_weight.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Color", animal_color.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Adopted", false).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                savePicture("cats","Oliver");
                            }

                            else if(enough_room == 4){
                                DocumentReference docRef = db.collection("cats").document("Simba");
                                docRef.update("Name", animal_name.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Age", Integer.valueOf(animal_age.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Breed", animal_breed.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Weight", Integer.valueOf(animal_weight.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Color", animal_color.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Adopted", false).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                savePicture("cats","Simba");
                            }
                        }


                        if(selectedOption == "dogs"){
                            if(enough_room == 1){
                                DocumentReference docRef = db.collection("dogs").document("Zeus");
                                docRef.update("Name", animal_name.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Age", Integer.valueOf(animal_age.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Breed", animal_breed.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Weight", Integer.valueOf(animal_weight.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Color", animal_color.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Adopted", false).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                savePicture("dogs","Zeus");
                            }

                            else if(enough_room == 2){
                                DocumentReference docRef = db.collection("dogs").document("Daisy");
                                docRef.update("Name", animal_name.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Age", Integer.valueOf(animal_age.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Breed", animal_breed.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Weight", Integer.valueOf(animal_weight.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Color", animal_color.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Adopted", false).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                savePicture("dogs","Daisy");
                            }

                            else if(enough_room == 3){
                                DocumentReference docRef = db.collection("dogs").document("Luna");
                                docRef.update("Name", animal_name.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Age", Integer.valueOf(animal_age.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Breed", animal_breed.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Weight", Integer.valueOf(animal_weight.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Color", animal_color.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Adopted", false).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                savePicture("dogs","Luna");
                            }

                            else if(enough_room == 4){
                                DocumentReference docRef = db.collection("dogs").document("Max");
                                docRef.update("Name", animal_name.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Age", Integer.valueOf(animal_age.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Breed", animal_breed.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Weight", Integer.valueOf(animal_weight.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Color", animal_color.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                docRef.update("Adopted", false).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // The update was successful
                                        Log.d(TAG, "updated");
                                    }
                                });
                                savePicture("dogs","Max");
                            }
                        }
                        Toast.makeText(getApplicationContext(), "the animal was added successfully!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(AddAnimal.this, MainActivity.class));
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "there is not enough room for this type of animal", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AddAnimal.this, MainActivity.class));
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // create bitmap to display image on ImageView from gallery or camera
        if (resultCode == RESULT_OK) {
            switch(requestCode){
                case GALLERY_REQUEST_CODE:
                    filepath = data.getData();
                    try {
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(filepath));

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;

                case CAMERA_REQUEST_CODE:
                    bitmap = (Bitmap) data.getExtras().get("data");
                    break;
            }
        }
    }

    public void savePicture(String animal_type, String name){

        // create bitmap from image file to save profile picture
        if(bitmap != null) {
            file = new File(getFilesDir(), name + ".png");
            try {
                OutputStream out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Store the file path and file name in a shared preference
            SharedPreferences prefs = getSharedPreferences("my_prefs_" + animal_type, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(name, file.getPath());
            editor.commit();
        }
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
                startActivity(new Intent(AddAnimal.this, MainActivity.class));
                return true;
            case R.id.cats_screen:
                Intent intent = new Intent(AddAnimal.this, Cats.class);
                startActivity(intent);
                return true;
            case R.id.dogs_screen:
                startActivity(new Intent(AddAnimal.this, Dogs.class));
                return true;
            case R.id.add_animal_screen:
                return true;
            case R.id.profile:
                startActivity(new Intent(AddAnimal.this, Profile.class));
                return true;
            case R.id.log_out:
                startActivity(new Intent(AddAnimal.this, LogIn.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
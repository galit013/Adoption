package com.example.adoption;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String names[] = {"cats", "dogs"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);

        ListAdapter adapter = new ListAdapter(getApplicationContext(), names);
        listView.setAdapter(adapter);

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
                return true;
            case R.id.cats_screen:
                startActivity(new Intent(MainActivity.this, Cats.class));
                return true;
            case R.id.dogs_screen:
                Intent intent = new Intent(MainActivity.this, Dogs.class);
                startActivity(intent);
                return true;
            case R.id.add_animal_screen:
                if(User.admin){
                    startActivity(new Intent(MainActivity.this, AddAnimal.class));
                }
                else{
                    Toast.makeText(getApplicationContext(), "only admins can add animals", Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.profile:
                startActivity(new Intent(MainActivity.this, Profile.class));
                return true;
            case R.id.log_out:
                startActivity(new Intent(MainActivity.this, LogIn.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
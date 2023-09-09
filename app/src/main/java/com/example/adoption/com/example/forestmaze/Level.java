package com.example.forestmaze;

import androidx.appcompat.app.*;

public class Level extends AppCompatActivity {

	com.google.firebase.firestore.FirebaseFirestore db;
	Object score;
	Object level;
	Object description;
	Object answer;
	Object explanation;
	Object doorNum1;
	Object doorNum2;
	Object doorNum3;
	String userName = com.example.forestmaze.CurrentUser.currentUser;
	android.widget.ImageView door1;
	android.widget.ImageView door2;
	android.widget.ImageView door3;
	android.widget.ImageView img_door1;
	android.widget.ImageView img_door2;
	android.widget.ImageView img_door3;
	android.widget.ImageView level_img;
	android.widget.ImageView speaker_off;
	android.widget.ImageView speaker_on;
	android.widget.ImageView bird;
	android.widget.TextView level_num;
	android.widget.TextView level_text;
	String level_num_doc;
	android.speech.tts.TextToSpeech txt_to_speech;
	int door_number_to_hide1 = com.example.forestmaze.CurrentUser.currentDoorNumber1;
	int door_number_to_hide2 = com.example.forestmaze.CurrentUser.currentDoorNumber2;

	/**
	 * 
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		// TODO - implement Level.onCreate
		throw new UnsupportedOperationException();
	}

	/**
	 * stop text to speech
	 */
	@Override
	protected void onDestroy() {
		// TODO - implement Level.onDestroy
		throw new UnsupportedOperationException();
	}

	/**
	 * creating door objects
	 * @param answer
	 * @param expl
	 */
	public void CreateDoors(int answer, String expl) {
		// TODO - implement Level.CreateDoors
		throw new UnsupportedOperationException();
	}

	/**
	 * click door 1
	 * @param view
	 */
	public void onClickDoor1(android.view.View view) {
		// TODO - implement Level.onClickDoor1
		throw new UnsupportedOperationException();
	}

	/**
	 * click door 2
	 * @param view
	 */
	public void onClickDoor2(android.view.View view) {
		// TODO - implement Level.onClickDoor2
		throw new UnsupportedOperationException();
	}

	/**
	 * click door 3
	 * @param view
	 */
	public void onClickDoor3(android.view.View view) {
		// TODO - implement Level.onClickDoor3
		throw new UnsupportedOperationException();
	}

	/**
	 * hide wrong doors if necessary
	 * @param number1
	 * @param number2
	 */
	public void HideDoor(int number1, int number2) {
		// TODO - implement Level.HideDoor
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param menu
	 */
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO - implement Level.onCreateOptionsMenu
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param item
	 */
	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		// TODO - implement Level.onOptionsItemSelected
		throw new UnsupportedOperationException();
	}

}
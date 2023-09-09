package com.example.forestmaze;

import androidx.appcompat.app.*;

public class GameMap extends AppCompatActivity {

	com.google.firebase.firestore.FirebaseFirestore db;
	android.widget.TextView userNametv;
	android.widget.TextView userScoretv;
	android.widget.ImageView locationIcon;
	android.widget.ImageView playBtn;
	android.widget.ImageView userProfileImgView;
	String userName = com.example.forestmaze.CurrentUser.currentUser;
	Object score;
	Object level;
	/**
	 * location array for the x and y position of the location image
	 */
	int[][] locations_array = {
            {10, 1380},
            {420, 1300},
            {655, 1050},
            {240, 980},
            {12, 810},
            {520, 650},
            {40, 460},
            {180, 40},
            {405, 395},
            {680, 80}
    };
	private static int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 13;

	/**
	 * 
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		// TODO - implement GameMap.onCreate
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param menu
	 */
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO - implement GameMap.onCreateOptionsMenu
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param item
	 */
	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		// TODO - implement GameMap.onOptionsItemSelected
		throw new UnsupportedOperationException();
	}

	/**
	 * Function to display the custom dialog.
	 */
	void showCustomDialog() {
		// TODO - implement GameMap.showCustomDialog
		throw new UnsupportedOperationException();
	}

	/**
	 * click play button
	 * @param view
	 */
	public void onClickPlayBtn(android.view.View view) {
		// TODO - implement GameMap.onClickPlayBtn
		throw new UnsupportedOperationException();
	}

}
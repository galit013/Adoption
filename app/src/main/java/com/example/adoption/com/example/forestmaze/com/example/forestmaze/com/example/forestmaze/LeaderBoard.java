package com.example.forestmaze;

import androidx.appcompat.app.*;

public class LeaderBoard extends AppCompatActivity {

	com.google.firebase.firestore.FirebaseFirestore db;
	android.widget.TextView user_name;
	android.widget.TextView user_score;
	android.widget.ImageView medal;
	android.widget.ListView listView;
	String[] names = new String[5];
	int[] scores = new int[5];

	/**
	 * 
	 * @param savedInstanceState
	 */
	@SuppressLint("MissingInflatedId")
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		// TODO - implement LeaderBoard.onCreate
		throw new UnsupportedOperationException();
	}

	/**
	 * click on the x button
	 * @param view
	 */
	public void escape(android.view.View view) {
		// TODO - implement LeaderBoard.escape
		throw new UnsupportedOperationException();
	}

}
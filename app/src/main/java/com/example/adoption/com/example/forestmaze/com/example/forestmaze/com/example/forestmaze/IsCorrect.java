package com.example.forestmaze;

import androidx.appcompat.app.*;

public class IsCorrect extends AppCompatActivity {

	android.widget.TextView message_title;
	android.widget.TextView explanation;
	android.widget.TextView score_tv;
	android.widget.Button next;
	android.widget.Button try_again;
	String isCorrect;
	String explanation_text;
	String userName = com.example.forestmaze.CurrentUser.currentUser;
	int level;
	int score;
	com.google.firebase.firestore.FirebaseFirestore db;

	/**
	 * 
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		// TODO - implement IsCorrect.onCreate
		throw new UnsupportedOperationException();
	}

	/**
	 * click next button
	 * @param view
	 */
	public void onClickNext(android.view.View view) {
		// TODO - implement IsCorrect.onClickNext
		throw new UnsupportedOperationException();
	}

}
package com.example.forestmaze;

import androidx.appcompat.app.*;
import androidx.preference.*;

public class Settings extends AppCompatActivity {

	/**
	 * 
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		// TODO - implement Settings.onCreate
		throw new UnsupportedOperationException();
	}

	public android.content.Context getContext() {
		// TODO - implement Settings.getContext
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param item
	 */
	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		// TODO - implement Settings.onOptionsItemSelected
		throw new UnsupportedOperationException();
	}


	public static class SettingsFragment extends PreferenceFragmentCompat {

		com.google.firebase.firestore.FirebaseFirestore db;

		/**
		 * 
		 * @param savedInstanceState
		 * @param rootKey
		 */
		@Override
		public void onCreatePreferences(android.os.Bundle savedInstanceState, String rootKey) {
			// TODO - implement SettingsFragment.onCreatePreferences
			throw new UnsupportedOperationException();
		}

	}

}
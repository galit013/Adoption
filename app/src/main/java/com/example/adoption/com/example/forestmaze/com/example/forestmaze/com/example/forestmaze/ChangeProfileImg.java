package com.example.forestmaze;

import androidx.appcompat.app.*;

public class ChangeProfileImg extends AppCompatActivity {

	android.widget.ImageView profile_img;
	android.widget.ImageButton gallery;
	android.widget.ImageButton camera;
	android.graphics.Bitmap bitmap = null;
	android.net.Uri filepath;
	java.io.File file = null;
	private static final int CAMERA_REQUEST_CODE = 0;
	private static final int GALLERY_REQUEST_CODE = 1;

	/**
	 * 
	 * @param savedInstanceState
	 */
	@SuppressLint("MissingInflatedId")
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		// TODO - implement ChangeProfileImg.onCreate
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
		// TODO - implement ChangeProfileImg.onActivityResult
		throw new UnsupportedOperationException();
	}

	/**
	 * click the x button
	 * @param view
	 */
	public void onClickCancel(android.view.View view) {
		// TODO - implement ChangeProfileImg.onClickCancel
		throw new UnsupportedOperationException();
	}

	/**
	 * click save button
	 * @param view
	 */
	public void onClickSave(android.view.View view) {
		// TODO - implement ChangeProfileImg.onClickSave
		throw new UnsupportedOperationException();
	}

}
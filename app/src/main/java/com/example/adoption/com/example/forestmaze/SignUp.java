package com.example.forestmaze;

import androidx.appcompat.app.*;

public class SignUp extends AppCompatActivity {

	android.widget.EditText userName;
	android.widget.EditText userPassword;
	android.widget.TextView userNameCheck;
	android.widget.TextView userPasswordCheck;
	android.widget.TextView login;
	android.widget.ImageButton galleryBtn;
	android.widget.ImageButton cameraBtn;
	android.widget.ImageView imgViewProfile;
	android.net.Uri filepath;
	com.google.firebase.firestore.FirebaseFirestore db;
	android.graphics.Bitmap bitmap = null;
	java.io.File file;
	private static final int MY_CAMERA_PERMISSION_CODE = 2;
	private static final int MY_GALLERY_PERMISSION_CODE = 3;
	private static final int CAMERA_REQUEST_CODE = 0;
	private static final int GALLERY_REQUEST_CODE = 1;

	/**
	 * 
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		// TODO - implement SignUp.onCreate
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
		// TODO - implement SignUp.onActivityResult
		throw new UnsupportedOperationException();
	}

	/**
	 * click sign up button
	 * @param view
	 */
	public void signUp(android.view.View view) {
		// TODO - implement SignUp.signUp
		throw new UnsupportedOperationException();
	}

}
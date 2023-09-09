package com.example.adoption;

import static android.content.ContentValues.TAG;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class AnimalFragment extends Fragment {

    View view;
    TextView details_text;
    TextToSpeech txt_to_speech;
    Button speaker, backBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_animal_fragmet, container, false);

        details_text = view.findViewById(R.id.details_text);
        speaker = view.findViewById(R.id.speaker);
        backBtn = view.findViewById(R.id.backBtn);

        details_text.setText(Animal.details_text);

        // create text to speech
        txt_to_speech = new TextToSpeech(this.getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    // set language to US English
                    int result = txt_to_speech.setLanguage(Locale.US);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.d(TAG, "language not supported");
                    }
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Close the fragment
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(AnimalFragment.this);
                fragmentTransaction.commit();
            }
        });

        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = Animal.details_text;
                // speak the entered text
                txt_to_speech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        return view;
    }

    // stop text to speech
    @Override
    public void onDestroy() {
        super.onDestroy();
        // release resources
        if (txt_to_speech != null) {
            txt_to_speech.stop();
            txt_to_speech.shutdown();
        }
    }
}
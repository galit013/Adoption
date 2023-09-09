package com.example.adoption;

import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

public class progressBarAnim extends Animation {

    private final Context context;
    private final ProgressBar progressBar;
    private final float valueStart;
    private final float valueEnd;
    private final TextView percentValue;

    public progressBarAnim(Context context, ProgressBar progressBar, int valueStart, int valueEnd, TextView percentValue) {
        this.context = context;
        this.progressBar = progressBar;
        this.valueStart = valueStart;
        this.valueEnd = valueEnd;
        this.percentValue = percentValue;
    }

    @Override
    protected void applyTransformation(float progressTime, Transformation t) {
        super.applyTransformation(progressTime, t);
        float value = valueStart + (valueEnd - valueStart) * progressTime;
        progressBar.setProgress((int)value);
        percentValue.setText((int)value + "%");
        if (value == valueEnd)
            context.startActivity(new Intent(context, LogIn.class));

    }

}

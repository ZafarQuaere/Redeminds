package com.redeminds.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;

import com.redeminds.app.databinding.ActivityVerifySuccessfulBinding;

public class VerifySuccessfulActivity extends AppCompatActivity {

    ActivityVerifySuccessfulBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_verify_successful);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getSupportActionBar().hide();

        Drawable drawable = mBinding.verifySuccessfulImageview.getDrawable();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) drawable;
                animatedVectorDrawable.start();

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(VerifySuccessfulActivity.this, TopicsActivity.class));

                    }
                };

                Handler handler = new Handler();
                handler.postDelayed(runnable, 1500);
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(runnable, 500);
    }
}
package com.redeminds.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.redeminds.app.databinding.ActivityGetStartedBinding;

public class GetStartedActivity extends AppCompatActivity {

    ActivityGetStartedBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_get_started);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getSupportActionBar().hide();
        mBinding.getStartedStartButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView rocketImageView = mBinding.getStartedRocketImageview;
                TranslateAnimation animate = new TranslateAnimation(0, 0, 0, -(rocketImageView.getY() + rocketImageView.getHeight()));
                animate.setDuration(500);
                animate.setFillAfter(true);
                animate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        startActivity(new Intent(GetStartedActivity.this, LoginActivity.class));

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                rocketImageView.startAnimation(animate);
            }
        });
    }
}
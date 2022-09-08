package com.redeminds.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.redeminds.app.databinding.ActivityTopicsBinding;
import com.redeminds.app.databinding.ActivityVerifySuccessfulBinding;

public class TopicsActivity extends AppCompatActivity {

    ActivityTopicsBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_topics);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getSupportActionBar().hide();

        mBinding.topicsContinueButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TopicsActivity.this, JourneyActivity.class));
            }
        });
    }
}
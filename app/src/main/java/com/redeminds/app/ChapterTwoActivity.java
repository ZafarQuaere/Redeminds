package com.redeminds.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.WindowCompat;
import androidx.databinding.DataBindingUtil;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.redeminds.app.databinding.ActivityChapterTwoBinding;

public class ChapterTwoActivity extends AppCompatActivity {

    ActivityChapterTwoBinding mBinding;
    private TextureView mTextureView;
    private SurfaceTexture mSurface;
    private MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_chapter_two);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getSupportActionBar().hide();

        mBinding.chapterTwoWholeRelativelayout.setLayoutTransition(new LayoutTransition());
        showIntro();


    }

    private void showIntro(){
        mBinding.chapterTwoTitleRelativelayout.setVisibility(View.VISIBLE);
        mBinding.chapterTwoTitleNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showScroll();
            }
        });
    }


    private void showScroll(){
        mBinding.chapterTwoTitleRelativelayout.setVisibility(View.GONE);
        mBinding.chapterTwoScrollRelativelayout.setVisibility(View.VISIBLE);
        mBinding.chapterTwoScrollNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(ChapterTwoActivity.this, ChapterTwoActivity.class));
                if(mBinding.chapterTwoScrollWizardTextview.getVisibility() == View.VISIBLE){
                    showBeforeAssessment1Speak();
                } else {
                    mBinding.chapterTwoScrollWizardLinearlayout.setVisibility(View.GONE);
                    mBinding.chapterTwoScrollWizardTextview.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    private void showBeforeAssessment1Speak(){
        mBinding.chapterTwoScrollRelativelayout.setVisibility(View.GONE);
        mBinding.chapterTwoWizardBeforeAssessment1Relativelayout.setVisibility(View.VISIBLE);
        mBinding.chapterTwoWizardBeforeAssessment1NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAssessment();
            }
        });

    }

    private void showAssessment(){
        mBinding.chapterTwoWizardBeforeAssessment1Relativelayout.setVisibility(View.GONE);
        mBinding.chapterTwoAssessment1Relativelayout.setVisibility(View.VISIBLE);
        mBinding.chapterTwoAssessment1NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAfterAssessment1Speak();
            }
        });

    }

    private void showAfterAssessment1Speak(){
        mBinding.chapterTwoAssessment1Relativelayout.setVisibility(View.GONE);
        mBinding.chapterTwoWizardAfterAssessment1Relativelayout.setVisibility(View.VISIBLE);
        mBinding.chapterTwoWizardAfterAssessment1NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChapterTwoActivity.this, ChapterThreeActivity.class));
            }
        });

    }
}
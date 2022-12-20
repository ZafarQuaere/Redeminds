package com.redeminds.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.WindowCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.LayoutTransition;
import android.app.Dialog;
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
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.redeminds.app.assessment.AssessmentType2Adapter;
import com.redeminds.app.databinding.ActivityChapterThreeBinding;
import com.redeminds.app.utils.Assessment31;
import com.redeminds.app.utils.AssessmentUtil;
import com.redeminds.app.utils.Utils;

import java.util.List;

public class ChapterThreeActivity extends AppCompatActivity {

    ActivityChapterThreeBinding mBinding;
    private TextureView mTextureView;
    private SurfaceTexture mSurface;
    private MediaPlayer mPlayer;
    private Dialog progressDialog;
    private TextView btnSubmit;
    private TextView helpIcon,txtNext;
    private RecyclerView rvAsst31;

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
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_chapter_three);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getSupportActionBar().hide();

        mTextureView = mBinding.chapterThreeVideoviewTextureview;
        initUI();

        mTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {
                mSurface = surfaceTexture;
                VideoView videoView = mBinding.chapterThreeVideoview;
                videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.chapter_three_video);
                videoView.seekTo(1);
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        Surface s = new Surface(mSurface);
                        mPlayer = mp;
                        mp.setSurface(s);
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        showIntro();

                                    }
                                });
                            }
                        };
                        scaleVideo(mp, runnable);
                    }
                });
            }


            @Override
            public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surfaceTexture) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surfaceTexture) {

            }
        });
    }

    private void initUI() {
        btnSubmit = findViewById(R.id.btnSubmit);
        helpIcon = findViewById(R.id.helpIcon);
        txtNext = findViewById(R.id.txtNext);
        rvAsst31 = findViewById(R.id.rvAsst31);
    }


    private void scaleVideo(MediaPlayer mPlayer, Runnable runnable) {

        RelativeLayout.LayoutParams videoParams = (RelativeLayout.LayoutParams) mTextureView
                .getLayoutParams();
        Point dm = new Point();
        ChapterThreeActivity.this.getWindowManager().getDefaultDisplay()
                .getRealSize(dm);

        final int height = dm.y;
        final int width = dm.x;
        int videoHeight = mPlayer.getVideoHeight();
        int videoWidth = mPlayer.getVideoWidth();
        double hRatio = 1;

        hRatio = (height * 1.0 / videoHeight) / (width * 1.0 / videoWidth);
        int x = (int) (hRatio <= 1 ? 0 : Math.round((-(hRatio - 1) / 2)
                * width));
        int y = (int) (hRatio >= 1 ? 0 : Math
                .round((((-1 / hRatio) + 1) / 2) * height));
        videoParams.setMargins(x, y, 0, 0);
        videoParams.width = width - x - x;
        videoParams.height = height - y - y;
        Log.e("Params", "x:" + x + " y:" + y);
        mTextureView.setScaleX(1.00001f);//<-- this line enables smoothing of the picture in TextureView.
        mTextureView.requestLayout();
        mTextureView.invalidate();
        mBinding.chapterThreeVideoview.setZOrderOnTop(true);
        mBinding.chapterThreeWholeRelativelayout.setLayoutTransition(new LayoutTransition());
        //progressDialog.dismiss();


        Handler h = new Handler();
        h.postDelayed(runnable, 500);
    }

    private void showIntro(){
        mBinding.chapterThreeTitleRelativelayout.setVisibility(View.VISIBLE);
        mBinding.chapterThreeTitleNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showScroll1();
            }
        });
    }


    private void showScroll1(){
        mBinding.chapterThreeTitleRelativelayout.setVisibility(View.GONE);
        mBinding.chapterThreeVideoview.start();
        mBinding.chapterThreeVideoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mBinding.chapterThreeScroll1Relativelayout.setVisibility(View.VISIBLE);
                mBinding.chapterThreeScroll1Relativelayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showBeforeAssessment1Speak();
                    }
                });
            }
        });
    }

    private void showBeforeAssessment1Speak(){
        mBinding.chapterThreeScroll1Relativelayout.setVisibility(View.GONE);
        mBinding.chapterThreeOlwinSpeakBeforeAssessment1Relativelayout.setVisibility(View.VISIBLE);
        mBinding.chapterThreeOlwinSpeakBeforeAssessment1NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAssessment1();
            }
        });
    }

    private void showAssessment1() {
        mBinding.chapterThreeOlwinSpeakBeforeAssessment1Relativelayout.setVisibility(View.GONE);
        mBinding.chapterThreeAssessment1Relativelayout.setVisibility(View.VISIBLE);

        List<Assessment31> list = AssessmentUtil.INSTANCE.getAssessment31Data();
        AssessmentType2Adapter adapter = new AssessmentType2Adapter(this,list);
        rvAsst31.setAdapter(adapter);
        rvAsst31.setLayoutManager(new LinearLayoutManager(this));
        txtNext.setOnClickListener(v -> {
            showScroll2();
        });
        btnSubmit.setOnClickListener(view -> {
            Utils.ERROR(adapter.getSelectedItem().getQuestion()+"  "+adapter.getSelectedItem().getValue());
            Utils.showToast(this,adapter.getSelectedItem().getQuestion());
        });

    }

    private void showScroll2(){
        mBinding.chapterThreeAssessment1Relativelayout.setVisibility(View.GONE);
        mBinding.chapterThreeScroll2Relativelayout.setVisibility(View.VISIBLE);
        mBinding.chapterThreeScroll2NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBeforeAssessment2Speak();
            }
        });
    }

    private void showBeforeAssessment2Speak(){
        mBinding.chapterThreeScroll2Relativelayout.setVisibility(View.GONE);
        mBinding.chapterThreeOlwinSpeakBeforeAssessment2Relativelayout.setVisibility(View.VISIBLE);
        mBinding.chapterThreeOlwinSpeakBeforeAssessment2NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAssessment2();
            }
        });
    }

    private void showAssessment2() {
        mBinding.chapterThreeOlwinSpeakBeforeAssessment2Relativelayout.setVisibility(View.GONE);
        mBinding.chapterThreeAssessment2Relativelayout.setVisibility(View.VISIBLE);
        mBinding.chapterThreeAssessment2NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBeforeAssessment3Speak();
            }
        });
    }

    private void showBeforeAssessment3Speak(){
        mBinding.chapterThreeAssessment2Relativelayout.setVisibility(View.GONE);
        mBinding.chapterThreeOlwinSpeakBeforeAssessment3Relativelayout.setVisibility(View.VISIBLE);
        mBinding.chapterThreeOlwinSpeakBeforeAssessment3NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAssessment3();
            }
        });
    }

    private void showAssessment3() {
        mBinding.chapterThreeOlwinSpeakBeforeAssessment3Relativelayout.setVisibility(View.GONE);
        mBinding.chapterThreeAssessment3Relativelayout.setVisibility(View.VISIBLE);
        mBinding.chapterThreeAssessment3NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAfterAssessment3Speak();
            }
        });
    }

    private void showAfterAssessment3Speak(){
        mBinding.chapterThreeAssessment3Relativelayout.setVisibility(View.GONE);
        mBinding.chapterThreeOlwinSpeakAfterAssessment3Relativelayout.setVisibility(View.VISIBLE);
        mBinding.chapterThreeOlwinSpeakAfterAssessment3NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChapterThreeActivity.this, ChapterFourActivity.class));
                //finish();
            }
        });
    }


}
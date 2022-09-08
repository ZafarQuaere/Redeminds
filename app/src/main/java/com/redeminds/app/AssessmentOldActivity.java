package com.redeminds.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.view.WindowCompat;
import androidx.databinding.DataBindingUtil;

import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.redeminds.app.databinding.ActivityAssessmentOldBinding;

public class AssessmentOldActivity extends AppCompatActivity {

    ActivityAssessmentOldBinding mBinding;

    SurfaceTexture mSurface;
    MediaPlayer mPlayer;
    TextureView mTextureView;

    boolean question1, question2, question3, question4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_assessment_old);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getSupportActionBar().hide();

        mTextureView = mBinding.assessmentVideoviewTextureview;

        VideoView videoView = mBinding.assessmentVideoview;
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.game_start);
        videoView.seekTo(1);
        mTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener(){
            @Override
            public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {
                mSurface = surfaceTexture;
                VideoView videoView = mBinding.assessmentVideoview;
                videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.question_start);
                videoView.seekTo(1);
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        Surface s = new Surface(mSurface);
                        mPlayer = mp;
                        mp.setSurface(s);

                        scaleVideo(mp);
                    }
                });
                videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mBinding.introRelativelayout.setVisibility(View.VISIBLE);
                        mBinding.introRelativelayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mBinding.introRelativelayout.setVisibility(View.GONE);
                                mBinding.questionsRelativelayout.setVisibility(View.VISIBLE);

                                mBinding.question1Option1Imageview.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        question1 = true;
                                        mBinding.question1Option1Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_clicked));
                                        mBinding.question1Option2Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question1Option3Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question1Option4Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question1Option5Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                    }
                                });

                                mBinding.question1Option2Imageview.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        question1 = true;
                                        mBinding.question1Option2Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_clicked));
                                        mBinding.question1Option1Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question1Option3Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question1Option4Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question1Option5Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                    }
                                });
                                mBinding.question1Option3Imageview.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        question1 = true;
                                        mBinding.question1Option3Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_clicked));
                                        mBinding.question1Option1Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question1Option2Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question1Option4Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question1Option5Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                    }
                                });
                                mBinding.question1Option4Imageview.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        question1 = true;
                                        mBinding.question1Option4Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_clicked));
                                        mBinding.question1Option1Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question1Option2Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question1Option3Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question1Option5Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                    }
                                });
                                mBinding.question1Option5Imageview.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        question1 = true;
                                        mBinding.question1Option5Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_clicked));
                                        mBinding.question1Option1Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question1Option2Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question1Option3Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question1Option4Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                    }
                                });

                                mBinding.question2Option1Imageview.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        question2 = true;
                                        mBinding.question2Option1Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_clicked));
                                        mBinding.question2Option2Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question2Option3Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question2Option4Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question2Option5Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                    }
                                });

                                mBinding.question2Option2Imageview.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        question2 = true;
                                        mBinding.question2Option2Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_clicked));
                                        mBinding.question2Option1Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question2Option3Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question2Option4Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question2Option5Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                    }
                                });
                                mBinding.question2Option3Imageview.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        question2 = true;
                                        mBinding.question2Option3Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_clicked));
                                        mBinding.question2Option1Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question2Option2Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question2Option4Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question2Option5Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                    }
                                });
                                mBinding.question2Option4Imageview.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        question2 = true;
                                        mBinding.question2Option4Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_clicked));
                                        mBinding.question2Option1Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question2Option2Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question2Option3Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question2Option5Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                    }
                                });
                                mBinding.question2Option5Imageview.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        question2 = true;
                                        mBinding.question2Option5Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_clicked));
                                        mBinding.question2Option1Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question2Option2Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question2Option3Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question2Option4Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                    }
                                });


                                mBinding.question3Option1Imageview.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        question3 = true;
                                        mBinding.question3Option1Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_clicked));
                                        mBinding.question3Option2Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question3Option3Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question3Option4Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question3Option5Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                    }
                                });

                                mBinding.question3Option2Imageview.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        question3 = true;
                                        mBinding.question3Option2Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_clicked));
                                        mBinding.question3Option1Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question3Option3Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question3Option4Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question3Option5Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                    }
                                });
                                mBinding.question3Option3Imageview.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        question3 = true;
                                        mBinding.question3Option3Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_clicked));
                                        mBinding.question3Option1Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question3Option2Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question3Option4Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question3Option5Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                    }
                                });
                                mBinding.question3Option4Imageview.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        question3 = true;
                                        mBinding.question3Option4Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_clicked));
                                        mBinding.question3Option1Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question3Option2Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question3Option3Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question3Option5Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                    }
                                });
                                mBinding.question3Option5Imageview.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        question3 = true;
                                        mBinding.question3Option5Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_clicked));
                                        mBinding.question3Option1Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question3Option2Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question3Option3Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question3Option4Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                    }
                                });


                                mBinding.question4Option1Imageview.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        question4 = true;
                                        mBinding.question4Option1Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_clicked));
                                        mBinding.question4Option2Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question4Option3Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question4Option4Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question4Option5Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                    }
                                });

                                mBinding.question4Option2Imageview.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        question4 = true;
                                        mBinding.question4Option2Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_clicked));
                                        mBinding.question4Option1Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question4Option3Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question4Option4Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question4Option5Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                    }
                                });
                                mBinding.question4Option3Imageview.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        question4 = true;
                                        mBinding.question4Option3Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_clicked));
                                        mBinding.question4Option1Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question4Option2Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question4Option4Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question4Option5Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                    }
                                });
                                mBinding.question4Option4Imageview.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        question4 = true;
                                        mBinding.question4Option4Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_clicked));
                                        mBinding.question4Option1Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question4Option2Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question4Option3Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question4Option5Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                    }
                                });
                                mBinding.question4Option5Imageview.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        question4 = true;
                                        mBinding.question4Option5Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_clicked));
                                        mBinding.question4Option1Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question4Option2Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question4Option3Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                        mBinding.question4Option4Imageview.setImageDrawable(ContextCompat.getDrawable(AssessmentOldActivity.this,  R.drawable.radio_unclicked));
                                    }
                                });

                                mBinding.assessmentSubmit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        */

        /*Drawable drawable_unclick = ContextCompat.getDrawable(AssessmentActivity.this, R.drawable.radio_unclicked);
                                        Drawable drawable_click = ContextCompat.getDrawable(AssessmentActivity.this, R.drawable.radio_clicked);
                                        boolean question1 = mBinding.question1Option1Imageview.getDrawable().equals(drawable_click) ||
                                                mBinding.question1Option2Imageview.getDrawable().equals(drawable_click) ||
                                                mBinding.question1Option3Imageview.getDrawable().equals(drawable_click) ||
                                                mBinding.question1Option4Imageview.getDrawable().equals(drawable_click) ||
                                                mBinding.question1Option5Imageview.getDrawable().equals(drawable_click);

                                        boolean question2 = mBinding.question2Option1Imageview.getDrawable().equals(drawable_click) ||
                                                mBinding.question2Option2Imageview.getDrawable().equals(drawable_click) ||
                                                mBinding.question2Option3Imageview.getDrawable().equals(drawable_click) ||
                                                mBinding.question2Option4Imageview.getDrawable().equals(drawable_click) ||
                                                mBinding.question2Option5Imageview.getDrawable().equals(drawable_click);

                                        boolean question3 = mBinding.question3Option1Imageview.getDrawable().equals(drawable_click) ||
                                                mBinding.question3Option2Imageview.getDrawable().equals(drawable_click) ||
                                                mBinding.question3Option3Imageview.getDrawable().equals(drawable_click) ||
                                                mBinding.question3Option4Imageview.getDrawable().equals(drawable_click) ||
                                                mBinding.question3Option5Imageview.getDrawable().equals(drawable_click);

                                        boolean question4 = mBinding.question4Option1Imageview.getDrawable().equals(drawable_click) ||
                                                mBinding.question4Option2Imageview.getDrawable().equals(drawable_click) ||
                                                mBinding.question4Option3Imageview.getDrawable().equals(drawable_click) ||
                                                mBinding.question4Option4Imageview.getDrawable().equals(drawable_click) ||
                                                mBinding.question4Option5Imageview.getDrawable().equals(drawable_click);
*//*
                                        Log.v("answered", question1 + " " + question2 + " " + question3 + " " + question4);

                                        if(question1 && question2 && question3 && question4){
                                            Toast.makeText(AssessmentOldActivity.this, "Submit successfully", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(AssessmentOldActivity.this, "Please answer all the questions", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }
                        });
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
        });*/
    }

    private void scaleVideo(MediaPlayer mPlayer) {

        RelativeLayout.LayoutParams videoParams = (RelativeLayout.LayoutParams) mTextureView
                .getLayoutParams();
        Point dm = new Point();
        AssessmentOldActivity.this.getWindowManager().getDefaultDisplay()
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
        mBinding.assessmentVideoview.setZOrderOnTop(true);
        mBinding.assessmentVideoview.start();

    }
}
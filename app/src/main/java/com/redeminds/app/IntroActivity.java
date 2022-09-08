package com.redeminds.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.WindowCompat;
import androidx.databinding.DataBindingUtil;

import android.animation.LayoutTransition;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.redeminds.app.databinding.ActivityIntroBinding;

public class IntroActivity extends AppCompatActivity {

    ActivityIntroBinding mBinding;

    private TextureView mTextureView;
    private SurfaceTexture mSurface;
    private MediaPlayer mPlayer;
    private Dialog progressDialog;

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
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_intro);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getSupportActionBar().hide();

        mTextureView = mBinding.introVideoviewTextureview;

        /*progressDialog = new Dialog(IntroActivity.this, android.R.style.Theme_Black);
        View view = LayoutInflater.from(IntroActivity.this).inflate(R.layout.progress_bar_tranparent, (ViewGroup) findViewById(R.id.progress_bar_layout));
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        progressDialog.setContentView(view);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();*/

        mTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {
                mSurface = surfaceTexture;
                VideoView videoView = mBinding.introVideoview;
                videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.intro_video);
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


    private void scaleVideo(MediaPlayer mPlayer, Runnable runnable) {

        RelativeLayout.LayoutParams videoParams = (RelativeLayout.LayoutParams) mTextureView
                .getLayoutParams();
        Point dm = new Point();
        IntroActivity.this.getWindowManager().getDefaultDisplay()
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
        mBinding.introBgImageview.setVisibility(View.GONE);
        mBinding.introVideoview.setZOrderOnTop(true);
        mBinding.introWholeRelativelayout.setLayoutTransition(new LayoutTransition());
        //progressDialog.dismiss();

        Handler h = new Handler();
        h.postDelayed(runnable, 500);
    }

    private void showIntro(){
        mBinding.introTitleRelativelayout.setVisibility(View.VISIBLE);
        mBinding.introTitleNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showScroll();
            }
        });
    }

    private void showScroll(){
        mBinding.introTitleRelativelayout.setVisibility(View.GONE);
        mBinding.introVideoview.start();
        mBinding.introVideoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mBinding.introScrollRelativelayout.setVisibility(View.VISIBLE);
                mBinding.introScrollNextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(IntroActivity.this, ChapterOneActivity.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    }
                });
            }
        });
    }


    @Override
    public void onBackPressed() {
        //TODO: dialog to close
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(IntroActivity.this);
        dialogBuilder.setMessage("Switch To Dashboard?");
        dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(IntroActivity.this, DashboardActivity.class));
            }
        });
        dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogBuilder.show();
    }
}
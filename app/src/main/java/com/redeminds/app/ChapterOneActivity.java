package com.redeminds.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.WindowCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.redeminds.app.databinding.ActivityChapterOneBinding;

import java.util.ArrayList;

public class ChapterOneActivity extends AppCompatActivity {

    ActivityChapterOneBinding mBinding;
    private TextureView mTextureView;
    private SurfaceTexture mSurface;
    private MediaPlayer mPlayer;
    private Dialog progressDialog;


    private int mBeforeAssessmentSpeakIndex = 1;

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
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_chapter_one);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getSupportActionBar().hide();

        mTextureView = mBinding.chapterOneVideoviewTextureview;

        /*progressDialog = new Dialog(ChapterOneActivity.this, android.R.style.Theme_Black);
        View view = LayoutInflater.from(ChapterOneActivity.this).inflate(R.layout.progress_bar_tranparent, (ViewGroup) findViewById(R.id.progress_bar_layout));
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
                VideoView videoView = mBinding.chapterOneVideoview;
                videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.intro_video);
                videoView.seekTo(11000);
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
        ChapterOneActivity.this.getWindowManager().getDefaultDisplay()
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
        mBinding.chapterOneBgImageview.setVisibility(View.GONE);
        mBinding.chapterOneVideoview.setZOrderOnTop(true);
        mBinding.chapterOneWholeRelativelayout.setLayoutTransition(new LayoutTransition());
        //progressDialog.dismiss();


        Handler h = new Handler();
        h.postDelayed(runnable, 500);
    }

    private void showIntro(){
        mBinding.chapterOneTitleRelativelayout.setVisibility(View.VISIBLE);
        mBinding.chapterOneTitleNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showScroll();
            }
        });
    }

    private void showScroll(){
        mBinding.chapterOneTitleRelativelayout.setVisibility(View.GONE);
        mBinding.chapterOneScrollRelativelayout.setVisibility(View.VISIBLE);
        mBinding.chapterOneScrollNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(ChapterOneActivity.this, ChapterOneActivity.class));
                if(mBinding.chapterOneScrollWizardTextview.getVisibility() == View.VISIBLE){
                    showBeforeAssessmentSpeak();
                } else {
                    mBinding.chapterOneScrollWizardLinearlayout.setVisibility(View.GONE);
                    mBinding.chapterOneScrollWizardTextview.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    private void showBeforeAssessmentSpeak(){
        mBinding.chapterOneScrollRelativelayout.setVisibility(View.GONE);
        mBinding.chapterOneWizardRelativelayout.setVisibility(View.VISIBLE);
        mBinding.chapterOneWizardBeforeAssessmentSpeak1Relativelayout.setVisibility(View.VISIBLE);
        mBinding.chapterOneWizardNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(mBeforeAssessmentSpeakIndex){
                    case 1:
                        mBinding.chapterOneWizardBeforeAssessmentSpeak1Relativelayout.setVisibility(View.GONE);
                        mBinding.chapterOneWizardBeforeAssessmentSpeak2Relativelayout.setVisibility(View.VISIBLE);
                        break;

                    case 2:
                        mBinding.chapterOneWizardBeforeAssessmentSpeak2Relativelayout.setVisibility(View.GONE);
                        mBinding.chapterOneWizardBeforeAssessmentSpeak3Relativelayout.setVisibility(View.VISIBLE);
                        mBinding.chapterOneEternityGemstoneImageview.setVisibility(View.VISIBLE);
                        break;

                    case 3:
                        startActivity(new Intent(ChapterOneActivity.this, ChapterTwoActivity.class));
                        break;

                    case 4:
                        startActivity(new Intent(ChapterOneActivity.this, ChapterTwoActivity.class));
                        break;
                }
                mBeforeAssessmentSpeakIndex++;
                Log.v("index", mBeforeAssessmentSpeakIndex + "");
            }
        });

    }

    private void showAssessment(){
        mBinding.chapterOneWizardRelativelayout.setVisibility(View.GONE);
        mBinding.chapterOneAssessmentRelativelayout.setVisibility(View.VISIBLE);
        ArrayList<Boolean> optionsArrayList = new ArrayList<Boolean>();
        for(int i = 0; i < 25; i++){
            optionsArrayList.add(false);
        }
        OptionsAdapter optionsAdapter = new OptionsAdapter(optionsArrayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ChapterOneActivity.this, 5);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mBinding.chapterOneRecyclerview.setLayoutManager(gridLayoutManager);
        mBinding.chapterOneRecyclerview.setAdapter(optionsAdapter);
    }



    private class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.MyViewHolder> {

        private ArrayList<Boolean> dataSet;

        public OptionsAdapter(ArrayList<Boolean> data) {
            dataSet = data;
            Log.v("array_size", dataSet.size() + "");
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_speak, parent, false);

            MyViewHolder myViewHolder = new MyViewHolder(view);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
            ImageView Tick = holder.tick;
            RelativeLayout Layout = holder.layout;

            if(dataSet.get(position)){
                Tick.setVisibility(View.VISIBLE);
            } else {
                Tick.setVisibility(View.GONE);
            }

            int finalposition = position;
            Layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int row = finalposition / 5;
                    int first_position = row * 5;
                    int option_no = finalposition % 5;
                    Log.v("row and option", row + " " + option_no);
                    for(int i = first_position; i < first_position + 5; i++){
                        if(i == finalposition){
                            dataSet.set(i, true);
                        } else {
                            dataSet.set(i, false);
                        }
                    }
                    notifyDataSetChanged();
                }
            });
        }


        @Override
        public int getItemCount() {
            return dataSet.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView tick;
            RelativeLayout layout;

            public MyViewHolder(View itemView) {
                super(itemView);
                tick = (ImageView) itemView.findViewById(R.id.list_item_options_tick);
                layout = (RelativeLayout) itemView.findViewById(R.id.list_item_options_relativelayout);
            }
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ChapterOneActivity.this);
        dialogBuilder.setMessage("Switch To Dashboard?");
        dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(ChapterOneActivity.this, DashboardActivity.class));
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
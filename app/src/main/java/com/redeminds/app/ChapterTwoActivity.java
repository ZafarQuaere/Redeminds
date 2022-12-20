package com.redeminds.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.WindowCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.TextView;
import android.widget.VideoView;

import com.redeminds.app.assessment.AssessmentType1Adapter;
import com.redeminds.app.assessment.AssessmentType2Adapter;
import com.redeminds.app.databinding.ActivityChapterTwoBinding;
import com.redeminds.app.utils.Assessment21;
import com.redeminds.app.utils.Assessment31;
import com.redeminds.app.utils.AssessmentUtil;
import com.redeminds.app.utils.Utils;

import java.util.List;

public class ChapterTwoActivity extends AppCompatActivity {

    ActivityChapterTwoBinding mBinding;
    private TextureView mTextureView;
    private SurfaceTexture mSurface;
    private MediaPlayer mPlayer;
    private TextView btnSubmit;
    private TextView helpIcon,txtNext;
    private RecyclerView rvAsst21;
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
        initUI();

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

    private void initUI() {
        btnSubmit = findViewById(R.id.btnSubmit);
        helpIcon = findViewById(R.id.helpIcon);
        txtNext = findViewById(R.id.txtNext);
        rvAsst21 = findViewById(R.id.rvAsst21);
    }

    private void showAssessment(){
        mBinding.chapterTwoWizardBeforeAssessment1Relativelayout.setVisibility(View.GONE);
        mBinding.chapterTwoAssessment1Relativelayout.setVisibility(View.VISIBLE);

        // display assessment layout and view
       List<Assessment21> list = AssessmentUtil.INSTANCE.getAssessment21Data(this);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i) +"");
        }
         AssessmentType1Adapter adapter = new AssessmentType1Adapter(this,list);
        rvAsst21.setAdapter(adapter);
        rvAsst21.setLayoutManager(new LinearLayoutManager(this));
        txtNext.setOnClickListener(v -> {
            showAfterAssessment1Speak();
        });
        btnSubmit.setOnClickListener(view -> {
            List<Assessment21> selectedItems = adapter.getSelectedItems();
            for (int i = 0; i < selectedItems.size(); i++) {
                if (selectedItems.get(i).getCheckedId() != -1) {
                    System.out.println("Selected Question >>  " + selectedItems.get(i).getQuestion());
                    System.out.println("Selected Selected Index " + selectedItems.get(i).getSelectedIndex());
                    System.out.println("Selected SelectedText " + getSelectedText(selectedItems.get(i).getSelectedIndex()));
                }
            }
        });

       /* mBinding.chapterTwoAssessment1NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAfterAssessment1Speak();
            }
        });*/

    }
    private String getSelectedText(int selectedIndex) {
        switch (selectedIndex){
            case 0:
                return "Strongly Disagree";
            case 1:
                return "Disagree";
            case 2:
                return "Not sure/Neutral";
            case 3:
                return "Agree";
            case 4:
                return "Strongly Agree";
            default: return "";
        }
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
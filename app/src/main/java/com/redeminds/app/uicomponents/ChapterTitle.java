package com.redeminds.app.uicomponents;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.redeminds.app.R;
import com.redeminds.app.databinding.CustomChapterTitleBinding;


public class ChapterTitle extends LinearLayout {

    CustomChapterTitleBinding mBinding;

    public ChapterTitle(Context context) {
        super(context);
        initControl(context, null);
    }

    public ChapterTitle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initControl(context, attrs);
    }

    public ChapterTitle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context, attrs);
    }

    public ChapterTitle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initControl(context, attrs);
    }

    private void initControl(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mBinding = CustomChapterTitleBinding.inflate(inflater, this, true)/*, R.layout.custom_chapter_title, this, true)*/;
        if(attrs != null){
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ChapterTitle);

            String chapterNumber = ta.getString(R.styleable.ChapterTitle_chapter_number);
            String chapterName = ta.getString(R.styleable.ChapterTitle_chapter_name);
            String chapterTagline = ta.getString(R.styleable.ChapterTitle_chapter_tagline);

            ChapterTitleObject data = new ChapterTitleObject(chapterNumber, chapterName, chapterTagline);

            mBinding.setChapterData(data);
        }
    }


    public class ChapterTitleObject {
        String chapter_number, chapter_name, chapter_tagline;

        public ChapterTitleObject(String chapter_number, String chapter_name, String chapter_tagline) {
            this.chapter_number = chapter_number;
            this.chapter_name = chapter_name;
            this.chapter_tagline = chapter_tagline;
        }

        public String getChapter_number() {
            return chapter_number;
        }

        public void setChapter_number(String chapter_number) {
            this.chapter_number = chapter_number;
        }

        public String getChapter_name() {
            return chapter_name;
        }

        public void setChapter_name(String chapter_name) {
            this.chapter_name = chapter_name;
        }

        public String getChapter_tagline() {
            return chapter_tagline;
        }

        public void setChapter_tagline(String chapter_tagline) {
            this.chapter_tagline = chapter_tagline;
        }
    }
}

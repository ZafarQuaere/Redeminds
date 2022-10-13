package com.redeminds.app.uicomponents;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.redeminds.app.R;
import com.redeminds.app.databinding.CustomScrollBinding;

public class Scroll extends LinearLayout {
   CustomScrollBinding mBinding;

   public Scroll(Context context) {
      super(context);
      initControl(context, null);
   }

   public Scroll(Context context, @Nullable AttributeSet attrs) {
      super(context, attrs);
      initControl(context, attrs);
   }

   public Scroll(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      initControl(context, attrs);
   }

   public Scroll(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
      super(context, attrs, defStyleAttr, defStyleRes);
      initControl(context, attrs);
   }

   private void initControl(Context context, AttributeSet attrs) {
      LayoutInflater inflater = (LayoutInflater)
              context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

      mBinding = CustomScrollBinding.inflate(inflater, this, true)/*, R.layout.custom_chapter_title, this, true)*/;
      if(attrs != null){
         TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.Scroll);

         String title = ta.getString(R.styleable.Scroll_scroll_title);
         String content = ta.getString(R.styleable.Scroll_scroll_content);

         ScrollObject data = new ScrollObject(title, content);

        mBinding.setScrollData(data);

         LinearLayout.LayoutParams layoutParamsTitle = (LinearLayout.LayoutParams)mBinding.customScrollTitle.getLayoutParams();
         layoutParamsTitle.setMargins(0, ta.getDimensionPixelSize(R.styleable.Scroll_scroll_title_margin_top, 0), 0, 0);
         mBinding.customScrollTitle.setLayoutParams(layoutParamsTitle);

         LinearLayout.LayoutParams layoutParamsContent = (LinearLayout.LayoutParams)mBinding.customScrollContent.getLayoutParams();
         layoutParamsContent.setMargins(0, ta.getDimensionPixelSize(R.styleable.Scroll_scroll_content_margin_top, 0), 0, 0);
         mBinding.customScrollContent.setLayoutParams(layoutParamsContent);
      }
   }


   public class ScrollObject {
      String title, content;

      public ScrollObject(String title, String content) {
         this.title = title;
         this.content = content;
      }

      public String getTitle() {
         return title;
      }

      public void setTitle(String title) {
         this.title = title;
      }

      public String getContent() {
         return content;
      }

      public void setContent(String content) {
         this.content = content;
      }
   }
}

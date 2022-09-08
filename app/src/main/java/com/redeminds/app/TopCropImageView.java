package com.redeminds.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class TopCropImageView extends ImageView {
   public TopCropImageView(Context context) {
      super(context);
   }

   public TopCropImageView(Context context, @Nullable AttributeSet attrs) {
      super(context, attrs);
   }

   public TopCropImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
   }

   public TopCropImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
      super(context, attrs, defStyleAttr, defStyleRes);
   }

   @Override
   protected boolean setFrame(int l, int t, int r, int b) {
      Matrix matrix = getImageMatrix();
      float scaleFactor = getWidth()/(float)getDrawable().getIntrinsicWidth();
      matrix.setScale(scaleFactor, scaleFactor, 0, 0);
      setImageMatrix(matrix);
      return super.setFrame(l, t, r, b);
   }
}

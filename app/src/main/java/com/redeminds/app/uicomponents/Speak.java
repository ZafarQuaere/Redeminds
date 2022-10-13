package com.redeminds.app.uicomponents;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.redeminds.app.R;

public class Speak extends RelativeLayout {


    public Speak(Context context) {
        super(context);
        initControl(context, null);
    }

    public Speak(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context, attrs);
    }

    public Speak(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context, attrs);
    }

    public Speak(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initControl(context, attrs);
    }

    private void initControl(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.custom_speak, this);
        if(attrs != null){
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.Speak);

            boolean flip = ta.getBoolean(R.styleable.Speak_flip, false);
            if(flip){
                ImageView SpeakBG = view.findViewById(R.id.speak_custom_speak_bg_imageview);
                SpeakBG.setScaleX(-1);
            }

            String speak = ta.getString(R.styleable.Speak_speak_text);
            TextView SpeakTextView = view.findViewById(R.id.speak_custom_speak_textview);

            SpeakTextView.setText(speak);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)SpeakTextView.getLayoutParams();
            layoutParams.setMargins(ta.getDimensionPixelSize(R.styleable.Speak_speak_text_margin_start, 0), ta.getDimensionPixelSize(R.styleable.Speak_speak_text_margin_top, 0), 0, 0);
            SpeakTextView.setLayoutParams(layoutParams);
        }
    }

}

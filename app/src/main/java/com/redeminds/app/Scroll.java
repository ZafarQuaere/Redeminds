package com.redeminds.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public class Scroll extends RelativeLayout {


    public Scroll(Context context) {
        super(context);
        initControl(context);
    }

    public Scroll(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context);
    }

    public Scroll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context);
    }

    public Scroll(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initControl(context);
    }

    private void initControl(Context context) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.list_item_options, this);
    }

}

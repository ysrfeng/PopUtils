package com.ysr;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.NumberPicker;

/**
 * Created by ysr on 2017/3/10 下午7:58.
 * 邮箱 ysr200808@163.com
 */

public class TextPicker extends NumberPicker {
    public TextPicker(Context context) {
        super(context);
    }

    public TextPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("NewApi")
    public TextPicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}

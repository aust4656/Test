package com.example.appnews;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

public class MyImageView extends AppCompatImageView {
    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setImageFromUrl(String strUrl, final Activity activity){
        OkNews.getBitFromUrl(strUrl, new OkNews.MyBit() {
            @Override
            public void getBit(final Bitmap bitmap) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyImageView.this.setImageBitmap(bitmap);
                    }
                });
            }
        });
    }
}

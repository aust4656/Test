package com.example.demo8_30;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

public class MyImageVIew extends AppCompatImageView {
    public MyImageVIew(Context context) {
        super(context);
    }

    public MyImageVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageVIew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public  void setImageUrl(String url, final Activity activity){
        HttpUtil.getResult(url, HttpUtil.TYPE_IMAGE, new HttpUtil.MyCallBack() {
            @Override
            public void getStr(final String str) { }
            @Override
            public void getImg(final Bitmap bitmap) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyImageVIew.this.setImageBitmap(bitmap);
                    }
                });
            }
        });
    }
}

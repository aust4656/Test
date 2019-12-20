package com.example.demo8_30;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil {
    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_STRING = 0;
    interface MyCallBack{
        void getStr(String str);
        void getImg(Bitmap bitmap);
    }
    public static void getResult(String url, final int type, final MyCallBack myCallBack){
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) { }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (type == TYPE_IMAGE) {
                    myCallBack.getImg(BitmapFactory.decodeStream(response.body().byteStream()));
                }else{
                    myCallBack.getStr(response.body().string());
                }
            }
        });
    }
}

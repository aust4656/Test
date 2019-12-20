package com.example.appnews;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkNews {

    interface MyStr{
        void getStr(String str);
    }
    interface MyBit{
        void getBit(Bitmap bitmap);
    }

    public static void getStrFromUrl(String jsonUrl, final MyStr myStr){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(jsonUrl).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                myStr.getStr(response.body().string());
            }
        });
    }

    public static void getBitFromUrl(String BitUrl, final MyBit myBit){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(BitUrl).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                myBit.getBit(bitmap);
            }
        });
    }
}

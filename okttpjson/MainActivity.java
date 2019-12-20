package com.example.appnews;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> title = new ArrayList<>();
    List<String> thumbnail_pic_s = new ArrayList<>();
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.gridView);

        OkNews.getStrFromUrl("http://v.juhe.cn/toutiao/index?type=top&key=9c20700a787eda795393ec17eaa715bf", new OkNews.MyStr() {
            @Override
            public void getStr(final String str) {
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                    JSONArray jsonArray = jsonObject1.getJSONArray("data");
                    for(int i = 0 ; i < jsonArray.length() ; i++){
                        JSONObject news = jsonArray.getJSONObject(i);
                        title.add(news.getString("title"));
                        thumbnail_pic_s.add(news.getString("thumbnail_pic_s"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        final BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return title.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null)
                    convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_view,null);
                MyImageView imageView = convertView.findViewById(R.id.imageView);
                imageView.setImageFromUrl(thumbnail_pic_s.get(position),MainActivity.this);
                TextView textView = convertView.findViewById(R.id.textView);
                textView.setText(title.get(position));
                return convertView;
            }
        };
        gridView.setAdapter(baseAdapter);
    }
}

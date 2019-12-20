package com.example.demo8_30;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv1 = findViewById(R.id.tv1);
    }

    public void click(View view) {
        Intent intent = getIntent();
        intent.putExtra("blood",100);
        setResult(9,intent);
        finish();
    }
}

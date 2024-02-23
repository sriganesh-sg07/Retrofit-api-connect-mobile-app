package com.example.apitest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);
        textView3 = findViewById(R.id.text3);
        textView4 = findViewById(R.id.text4);

        Intent intent = getIntent();
        String failedMessage = intent.getStringExtra("failed");
        if (failedMessage != null) {
            textView1.setText(failedMessage);
        } else {
            String title = intent.getStringExtra("title");
            String id = intent.getStringExtra("id");
            String userId = intent.getStringExtra("userId");
            String body = intent.getStringExtra("body");

            textView1.setText(title);
            textView2.setText(id);
            textView3.setText(userId);
            textView4.setText(body);
        }
    }
}

package com.example.apitest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;

    Button submit;

    EditText text;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1=findViewById(R.id.text1);
        textView2=findViewById(R.id.text2);
        textView3=findViewById(R.id.text3);
        textView4=findViewById(R.id.text4);
        submit=findViewById(R.id.submit);
        text=findViewById(R.id.te1);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action when button is clicked
                String atext=text.getText().toString();
                if (atext.isEmpty()) {
                    Toast.makeText(MainActivity.this,"Please enter a number",Toast.LENGTH_SHORT).show();
                    return;
                }
                int a=Integer.parseInt(atext);

                Retrofit retrofit=new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create()).build();
                JSONplaceholder api=retrofit.create(JSONplaceholder.class);
                Call<List<Post>> call= api.getPosts();
                call.enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        if(response.isSuccessful()){
                            List<Post> posts=response.body();
                            if(posts !=null && !posts.isEmpty() && a>1){
                                Post firstpost= posts.get(a-1);
                                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                                i.putExtra("title", firstpost.getTitle());
                                i.putExtra("id", firstpost.getId());
                                i.putExtra("userId", firstpost.getUserId());
                                i.putExtra("body", firstpost.getBody());
                                startActivity(i);
                            }
                            else{
                                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                                i.putExtra("failed", "Failed to retrieve data");
                                startActivity(i);
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        textView1.setText("Network error");
                    }
                });


            }
        });

    }
}
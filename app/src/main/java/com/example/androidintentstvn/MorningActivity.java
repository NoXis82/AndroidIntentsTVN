package com.example.androidintentstvn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MorningActivity extends AppCompatActivity {
    private String host;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morning);
        TextView textView = findViewById(R.id.text_view);
        Button btnStartSync = findViewById(R.id.sync_start);
        host = getIntent().getDataString();
        String time = getIntent().getStringExtra(host);
        textView.setText(host + "\n" + time);
        btnStartSync.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse(host));
                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(sendIntent);
                }
            }
        });
    }
}
package com.example.androidintentstvn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String HOST_MORNING = "http://morning";
    private static final String HOST_AFTERNOON = "http://afternoon";
    private static final String HOST_EVENING = "http://evening";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSync = findViewById(R.id.sync);
        btnSync.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                Date time = Calendar.getInstance().getTime();
                Toast.makeText(v.getContext(), time.toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_SYNC);
                if (hour >= 6 && hour < 14) {
                    intent.setData(Uri.parse(HOST_MORNING));
                    intent.putExtra(HOST_MORNING, time.toString());
                }
                if (hour == 14) {
                    intent.setData(Uri.parse(HOST_AFTERNOON));
                    intent.putExtra(HOST_AFTERNOON, time.toString());
                }
                if (hour >= 15 || hour < 6) {
                    intent.setData(Uri.parse(HOST_EVENING));
                    intent.putExtra(HOST_EVENING, time.toString());
                }
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }
}
package com.example.androidintentstvn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
                Date currentDate = new Date();
                DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                DateFormat hourFormat = new SimpleDateFormat("HH", Locale.getDefault());
                String hour = hourFormat.format(currentDate);
                String time = timeFormat.format(currentDate);
                Toast.makeText(v.getContext(), time, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_SYNC);
                if (Integer.parseInt(hour) >= 6 && Integer.parseInt(hour) < 14) {
                    intent.setData(Uri.parse(HOST_MORNING));
                    intent.putExtra(HOST_MORNING, time);
                }
                if (Integer.parseInt(hour) >= 14 && Integer.parseInt(hour) < 15) {
                    intent.setData(Uri.parse(HOST_AFTERNOON));
                    intent.putExtra(HOST_AFTERNOON, time);
                }
                if (Integer.parseInt(hour) >= 15 ||
                        Integer.parseInt(hour) >= 0 && Integer.parseInt(hour) < 6) {
                    intent.setData(Uri.parse(HOST_EVENING));
                    intent.putExtra(HOST_EVENING, time);
                }
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }
}
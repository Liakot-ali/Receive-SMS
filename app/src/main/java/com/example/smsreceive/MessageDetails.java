package com.example.smsreceive;
//      Author : Md Liakot Ali liton
//      Student Id : 1802035
//      Dept. of CSE, HSTU


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MessageDetails extends AppCompatActivity {

    TextView sender, date, time, body;
    String msg_sender, msg_date, msg_time, msg_body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);
        InitializeAll();
    }

    private void InitializeAll() {
        msg_sender = getIntent().getStringExtra("sender");
        msg_date = getIntent().getStringExtra("date");
        msg_time = getIntent().getStringExtra("time");
        msg_body = getIntent().getStringExtra("message");


        sender = findViewById(R.id.details_sender);
        date = findViewById(R.id.details_date);
        time = findViewById(R.id.details_time);
        body = findViewById(R.id.details_body);

        sender.setText(msg_sender);
        date.setText(msg_date);
        time.setText(msg_time);
        body.setText(msg_body);

    }
}
package com.example.smsreceive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ClassSMS> arrayList;
    AdapterSMS adapter;

    TextView emptyText;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializeAll();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[] {Manifest.permission.RECEIVE_SMS} , 1000);
        }

        ClassSQLiteHelper helper = new ClassSQLiteHelper(MainActivity.this);
        Cursor  cursor = helper.showMessage();
        if(cursor.getCount() == 0)
        {
            emptyText.setVisibility(View.VISIBLE);
        }
        else {
            emptyText.setVisibility(View.GONE);
            while (cursor.moveToNext())
            {
                Log.e("CURSOR", "onCreate: " + cursor);
                String id, time, date, body, sender;
                int seen;
                id = cursor.getString(0);
                time = cursor.getString(1);
                date = cursor.getString(2);
                body = cursor.getString(3);
                sender = cursor.getString(4);
                seen = cursor.getInt(5);

                ClassSMS sms = new ClassSMS(id, time, date, body, sender, seen);
                arrayList.add(sms);
            }

            adapter = new AdapterSMS(MainActivity.this, arrayList);
            recyclerView.setAdapter(adapter);
        }

    }

    private void InitializeAll() {
        arrayList = new ArrayList<>();
        emptyText = findViewById(R.id.msg_empty_text);
        recyclerView = findViewById(R.id.msg_recycler_view);

        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode  == 1000){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Not Granted", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
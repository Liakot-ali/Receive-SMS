package com.example.smsreceive;
//      Author : Md Liakot Ali liton
//      Student Id : 1802035
//      Dept. of CSE, HSTU


import android.annotation.SuppressLint;
import android.content.Context;

import android.content.Intent;
import android.graphics.Color;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.StringTokenizer;

public class AdapterSMS extends RecyclerView.Adapter<AdapterSMS.ViewHolder> {

    ArrayList<ClassSMS> arrayList;
    Context activityContext;

    public AdapterSMS(Context context, ArrayList<ClassSMS> list) {

        activityContext = context;
        arrayList = list;
    }

    //------To hold every list item view------
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sender, time, body;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sender = itemView.findViewById(R.id.msg_sender);
            time = itemView.findViewById(R.id.msg_time);
            body = itemView.findViewById(R.id.msg_body);
        }
    }

    @NonNull
    @Override
    public AdapterSMS.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View view = inflater.inflate(R.layout.model, viewGroup, false);

        return new AdapterSMS.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSMS.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.itemView.setTag(arrayList.get(position));
        int seen = arrayList.get(position).getSeen();
        Log.e("SEEN", "onBindViewHolder: " + seen);
        if(seen == 1){
            holder.sender.setTextColor(Color.GRAY);
            holder.body.setTextColor(Color.GRAY);
            holder.time.setTextColor(Color.GRAY);
        }else{
            holder.sender.setTextColor(Color.BLACK);
            holder.body.setTextColor(Color.BLACK);
            holder.time.setTextColor(Color.BLACK);
        }
        Date d = new Date();
        String date = DateFormat.format("dd-MM-yyyy hh:mm aa", d).toString();
        StringTokenizer tk = new StringTokenizer(date);
        date = tk.nextToken();


        Log.e("DATE", "onBindViewHolder: " + date + " " + arrayList.get(position).getDate());


        if(Objects.equals(date, arrayList.get(position).getDate())){
            holder.time.setText(arrayList.get(position).getTime());
        }else{
            holder.time.setText(arrayList.get(position).getDate());
        }
        holder.sender.setText(arrayList.get(position).getSender());
        holder.body.setText(String.valueOf(arrayList.get(position).getBody()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                if(seen == 0) {
                    ClassSQLiteHelper helper = new ClassSQLiteHelper(activityContext);
                    helper.UpdateSeen(arrayList.get(position).getId());
                    arrayList.get(position).setSeen(1);
                    notifyDataSetChanged();
                }
                Intent intent = new Intent(activityContext, MessageDetails.class);
                intent.putExtra("sender", arrayList.get(position).getSender());
                intent.putExtra("message", arrayList.get(position).getBody());
                intent.putExtra("time", arrayList.get(position).getTime());
                intent.putExtra("date", arrayList.get(position).getDate());
                activityContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}

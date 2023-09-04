package com.example.smsreceive;

import android.annotation.SuppressLint;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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

        holder.sender.setText(arrayList.get(position).getSender());
        holder.time.setText(arrayList.get(position).getTime());
        holder.body.setText(String.valueOf(arrayList.get(position).getBody()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activityContext, "Under Construction", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}

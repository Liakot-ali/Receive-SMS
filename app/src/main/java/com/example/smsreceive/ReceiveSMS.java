package com.example.smsreceive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;
import java.util.Objects;
import java.util.StringTokenizer;

public class ReceiveSMS extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();
            SmsMessage[] msg = null;
            if(bundle != null)
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msg = new SmsMessage[pdus.length];
                    for (int i = 0; i<msg.length; i++)
                    {
                        msg[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        String msg_body = msg[i].getMessageBody();
                        Toast.makeText(context, msg_body, Toast.LENGTH_LONG).show();

                        String msg_sender = msg[i].getOriginatingAddress();
                        String msg_id = String.valueOf(System.currentTimeMillis());
                        Date d = new Date();
                        String date = DateFormat.format("dd-MM-yyyy hh:mm:ss a", d).toString();
                        StringTokenizer tk = new StringTokenizer(date);
                        String msg_date = tk.nextToken();
                        String msg_time = tk.nextToken();

                        Log.e("CLASS", "onReceive: " + msg_id + " " +  msg_time + " " + msg_date  + " " + msg_body + " " + msg_sender);

                        ClassSMS sms = new ClassSMS(msg_id, msg_time, msg_date, msg_body, msg_sender, 0);
                        ClassSQLiteHelper helper = new ClassSQLiteHelper(context);
                        helper.addNewMessage(sms);
                    }
                }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}

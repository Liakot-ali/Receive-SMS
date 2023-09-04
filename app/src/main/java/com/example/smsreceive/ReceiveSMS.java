package com.example.smsreceive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.Objects;

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
                    }
                }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}

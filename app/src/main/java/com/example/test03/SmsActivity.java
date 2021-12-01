package com.example.test03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class SmsActivity extends AppCompatActivity {
    EditText sms;
    Button bttnSend;
    RadioButton rbParents1;
    RadioButton rbFriends1;
    RadioButton rbJob1;
    private static final String TAG = "test1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        sms = (EditText) findViewById(R.id.sms);
        bttnSend = (Button) findViewById(R.id.bttn_send);
        rbParents1 = (RadioButton) findViewById(R.id.rb_parents1) ;
        rbFriends1 = (RadioButton) findViewById(R.id.rb_friends1);
        rbJob1 = (RadioButton) findViewById(R.id.rb_job1);
        String message = sms.getText().toString();
        SmsManager smsManager = SmsManager.getDefault();
        Log.d(TAG, ContactActivity.friends.toString());
        bttnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rbParents1.isChecked())
                {
                    for (String kisi: ContactActivity.parents){
                        smsManager.sendTextMessage(kisi,null,message,null,null);
                    }


                }else if (rbFriends1.isChecked())
                {
                    for (String kisi: ContactActivity.friends){
                        smsManager.sendTextMessage(kisi,null,message,null,null);
                    }
                }else if(rbJob1.isChecked())
                {
                    for (String kisi: ContactActivity.job){
                        smsManager.sendTextMessage(kisi,null,message,null,null);
                    }
                }
            }
        });



    }
}
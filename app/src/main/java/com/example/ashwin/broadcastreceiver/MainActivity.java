package com.example.ashwin.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String mMessageString = "Hello World";
    private TextView mMessageTextView;
    private Button mGoToButton;

    private BroadcastReceiver mMyBroadCastReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(intent.hasExtra("message")){
                        Log.d("debuglogging", "MainActivity : onReceive() : message : " + intent.getStringExtra("message"));
                        mMessageString = intent.getStringExtra("message");
                        mMessageTextView.setText(mMessageString);
                    }
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocalBroadcastManager.getInstance(this).registerReceiver(mMyBroadCastReciever, new IntentFilter("new_message"));

        initViews();
    }

    private void initViews() {
        mMessageTextView = (TextView) findViewById(R.id.message_text_view);
        mMessageTextView.setText(mMessageString);

        mGoToButton = (Button) findViewById(R.id.go_to_button);
        mGoToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BroadcastActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMyBroadCastReciever);
        super.onDestroy();
    }
}

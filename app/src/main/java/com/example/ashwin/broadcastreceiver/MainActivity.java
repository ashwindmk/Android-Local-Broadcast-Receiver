package com.example.ashwin.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "local-receiver";

    private String mMessageString = "Hello World";
    private TextView mMessageTextView;
    private Button mRegisterOneButton, mUnregisterOneButton, mToBroadcastButton;

    private BroadcastReceiver anonymousLocalBroadCastReciever = new BroadcastReceiver() {
        // This anonymous class will cause memory leak
        @Override
        public void onReceive(Context context, final Intent intent) {
            Log.d(TAG, "MainActivity : onReceive() : message : " + intent.getStringExtra("message"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private LocalBroadcastReceiver localBroadcastReceiver1;

    private void initViews() {
        mMessageTextView = (TextView) findViewById(R.id.message_text_view);
        mMessageTextView.setText(mMessageString);

        localBroadcastReceiver1 = new LocalBroadcastReceiver(MainActivity.this);

        mRegisterOneButton = findViewById(R.id.register_one_button);
        mRegisterOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // You will receive the broadcast multiple times if you register it multiple times, even if your receiver instance is singleton.
                Log.w(TAG, "MainActivity | mRegisterOneButton.onClick | registered");
                IntentFilter intentFilter = new IntentFilter(LocalBroadcastReceiver.ACTION);
                intentFilter.addCategory("cat1");
                intentFilter.addCategory("cat2");
                intentFilter.addCategory("cat3");
                LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(localBroadcastReceiver1, intentFilter);
            }
        });

        mUnregisterOneButton = findViewById(R.id.unregister_one_button);
        mUnregisterOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This will unregister the receiver instance, irrespective of how many times you have registered it.
                Log.w(TAG, "MainActivity | mRegisterOneButton.onClick | unregistered");
                LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(localBroadcastReceiver1);
            }
        });

        mToBroadcastButton = (Button) findViewById(R.id.to_broadcast_button);
        mToBroadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BroadcastActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(TAG, "MainActivity | onDestroy");
    }
}

package com.example.ashwin.broadcastreceiver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class BroadcastActivity extends AppCompatActivity {
    private EditText mMessageEditText;
    private Button mBroadcastButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        initViews();
    }

    private void initViews() {
        mMessageEditText = (EditText) findViewById(R.id.broadcast_message_edit_text);

        mBroadcastButton = (Button) findViewById(R.id.broadcast_button);
        mBroadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = mMessageEditText.getText().toString();
                Intent intent = new Intent(LocalBroadcastReceiver.ACTION);
                //Intent intent = new Intent();  // Action is compulsory
                intent.addCategory("cat1");
                intent.addCategory("cat2");
                //intent.addCategory("cat3");
                //intent.addCategory(null);  // NullPointerException
                intent.putExtra("message", msg);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
                Toast.makeText(getApplicationContext(), "Message Broadcast Success", Toast.LENGTH_LONG).show();
            }
        });
    }
}

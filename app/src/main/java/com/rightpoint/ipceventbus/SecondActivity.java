package com.rightpoint.ipceventbus;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.rightpoint.ipceventbus.msg.Msg;
import com.rightpoint.ipceventbus.msg.MsgSticky;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class SecondActivity extends AppCompatActivity {
    private final String TAG = "eventMsg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findViewById(R.id.btn_post2remote).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post2remote(new Msg(2333));
            }
        });

        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true)
    public void receiveMsg(MsgSticky msg) {
        Log.d(TAG, "receiveStickyMsg: msg = " + msg.msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

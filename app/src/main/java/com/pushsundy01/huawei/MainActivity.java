  
/*
      Copyright 2021. Futurewei Technologies Inc. All rights reserved.
      Licensed under the Apache License, Version 2.0 (the "License");
      you may not use this file except in compliance with the License.
      You may obtain a copy of the License at
        http:  www.apache.org/licenses/LICENSE-2.0
      Unless required by applicable law or agreed to in writing, software
      distributed under the License is distributed on an "AS IS" BASIS,
      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
      See the License for the specific language governing permissions and
      limitations under the License.
*/
package com.pushsundy01.huawei;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.common.ApiException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "PushDemoLog";
    private String CHANNEL_ID = "PUSH_CHANNEL_TEST";
    private Button btnToken;
    private String pushtoken = "";
    TextView tvView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getToken();
        setContentView(R.layout.activity_main);

        createNotificationChannel();  //create push channel;
    }
   ///** * get token */
    private void getToken() {
        Log.i(TAG, "get token: begin");
        // get token
        new Thread() {
            @Override
            public void run() {
                try {
                    // read from agconnect-services.json
                    String appId = AGConnectServicesConfig.fromContext(MainActivity.this).getString("client/app_id");
                    pushtoken = HmsInstanceId.getInstance(MainActivity.this).getToken(appId, "HCM");
                    if(!TextUtils.isEmpty(pushtoken)) {
                        Log.i(TAG, "get token:" + pushtoken);
                    }
                    runOnUiThread(new Runnable() {//
                        @Override
                        public void run() {
                            tvView = findViewById(R.id.tv_log);
                            ((TextView) tvView).setText("This token is: " + pushtoken);
                        }
                    });
                } catch (Exception e) {
                    Log.i(TAG,"getToken failed, " + e);
                }
            }
        }.start();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel
        String descriptionText = "huawei push channel";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID,(CharSequence ) CHANNEL_ID, importance);
        mChannel.setDescription( descriptionText);
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(mChannel);
    }
}

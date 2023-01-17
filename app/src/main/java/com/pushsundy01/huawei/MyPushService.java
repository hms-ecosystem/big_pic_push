  
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

import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.huawei.hms.push.HmsMessageService;
import com.huawei.hms.push.RemoteMessage;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MyPushService extends HmsMessageService {
    private static final String TAG = "PushDemoLog";

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.i(TAG, "receive token:" + s);
    }
    String strDataMsg=null;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getData().length() > 0) {
            strDataMsg =remoteMessage.getData();
            Log.i(TAG, "Message data payload: " + remoteMessage.getData());
        }
        if (remoteMessage.getNotification() != null) {
            Log.i(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        try {
            displayNotification(remoteMessage);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void displayNotification(RemoteMessage message) throws ExecutionException{

        String strTitle="", strMsgBody="",  strBigImageOrPicUri="";
        String CHANNEL_ID = "PUSH_CHANNEL_TEST";
        try {
            //get corresponding value from the pairs from AGC console
            Map mapData = message.getDataOfMap();
            strTitle = (String) mapData.get("title");
            strMsgBody =(String) mapData.get("message");
            strBigImageOrPicUri = (String) mapData.get("imageurl");
        } catch (Exception e) {
            Log.i("error push", e.toString());
        }
        NotificationCompat.Builder noteficationBuilder = new  NotificationCompat.Builder(this, CHANNEL_ID);

        NotificationManager manager= getSystemService(NotificationManager.class);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Bitmap bitmapBigPic = new TaskGetBigPicture(strBigImageOrPicUri).getBmp();
            Bitmap bitmapLargeIcon =bitmapBigPic;

            noteficationBuilder.setSmallIcon(R.mipmap.ic_launcher);
            try {
                noteficationBuilder.setLargeIcon(bitmapLargeIcon)
                        .setContentTitle(strTitle)
                        .setContentText(strMsgBody)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                noteficationBuilder
                        .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(bitmapBigPic));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            Log.e("error push", "Build.VERSION.SDK_INT:"+ Build.VERSION.SDK_INT +" must greater or equal 9!"  );
            return;
        }
        manager.notify(1,noteficationBuilder.build());
    }
}

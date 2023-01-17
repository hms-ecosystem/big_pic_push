  
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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import android.graphics.Matrix;

public class TaskGetBigPicture {
    private String strBigPic = null;
    private Bitmap bmp=null;
    public TaskGetBigPicture(String strBigImageOrPicUri){
        strBigPic = strBigImageOrPicUri;
        execTask();
    }
    public void execTask() {
        try {
            bmp = getNotificationBitmap(strBigPic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Bitmap getBmp(){
        return bmp;
    }
    private Bitmap getNotificationBitmap(String url){
        try {
            Bitmap bitmap = getBitmap(url);
            return getResizedBitmap(bitmap, 200, 400);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
}
    private  Bitmap getBitmap( String imageUrl){
        try {
            URL url= new URL(imageUrl);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.getResponseCode();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            return BitmapFactory.decodeStream(inputStream);
        }catch (SocketTimeoutException e){
            e.printStackTrace();
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Bitmap getResizedBitmap( Bitmap bitmap, int newHeight, int newWidth)  {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = (float) newWidth / width;
        float scaleHeight = (float) newHeight / height;

        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);
        // "RECREATE" THE NEW BITMAP
        return Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, false);
    }
}

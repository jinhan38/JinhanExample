package com.jinhanexample.hybrid;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.google.android.play.core.splitinstall.ac;
import com.jinhanexample.MainActivity;

import java.io.File;
import java.util.Objects;

public class AndroidBridge {

    private WebView mWebView;
    private Integer curNum;

    public AndroidBridge(WebView mWebView, Integer curNum) {
        this.mWebView = mWebView;
        this.curNum = curNum;
    }

    public static final int PERMISSIONS_REQUEST_CODE = 100;
    private Activity activity = HybridWebViewActivityJava.activity;
    private HybridWebViewActivity hybridWebViewActivity;

    public final String[] REQUIRED_PERMISSIONS = {Manifest.permission.CAMERA, // 카메라
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};  // 외부 저장소

    private static final String TAG = "AndroidBridge";
    public static String DOWNLOAD_FILE_NAME = "";
    private String downloadBaseUrl = "https://devapi.lifeplusmentalcare.com:2080";

    //웹 자바스크립트에서 호출
    @JavascriptInterface
    public void onDownloadStart(String url, String fileName) {

        Log.e(TAG, "clicked!");
        Log.e(TAG, "onDownloadStart: url : " + url);
        Log.e(TAG, "onDownloadStart: fileName : " + fileName);

        Log.e(TAG, "fileDownload: url : " + url);
        Log.e(TAG, "fileDownload: fileName : " + fileName);
        //아래 줄은 외부저장소의 경로를 가져오는 코드  : 경로 =/mnt/sdcard/Download
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String strDir = file.getAbsolutePath();

//        ContentResolver resolver = activity.getContentResolver();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, );

//        Log.d(TAG, "onDownloadStart: 경로 : " + strDir + "/HANWHA");
//        val resolver = context.contentResolver
//        val contentValues = ContentValues().apply {
//            put(MediaStore.MediaColumns.DISPLAY_NAME, "CuteKitten001")
//            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
//            put(MediaStore.MediaColumns.RELATIVE_PATH, "DCIM/PerracoLabs")
//        }
//
//        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
//
//        resolver.openOutputStream(uri).use {
//            // TODO something with the stream
//        }


        storagePermissionCheck(downloadBaseUrl + url, strDir, fileName);


//        DownloadManager.getInstance().setSavePath(strDir + "/ARTEUM"); // 저장하려는 경로 지정.
//        DownloadManager.getInstance().setDownloadUrl("https://www.arteum.co.kr" + url);


    }


    @SuppressLint("ObsoleteSdkInt")
    private void storagePermissionCheck(String url, String dir, String fileName) {

        Log.d(TAG, "storagePermissionCheck: url : " + url);

        boolean result = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED) {
                //거부했을 때 다시 요청
                ActivityCompat.requestPermissions(activity, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);

            } else {
                //동의 했을 때
                result = true;
            }

        } else {
            //버전 낮은경우
            result = true;
        }

        if (result) ((HybridWebViewActivityJava) activity).startDownload(url, dir, fileName);

    }
}

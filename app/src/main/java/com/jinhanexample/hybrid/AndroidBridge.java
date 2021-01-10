package com.jinhanexample.hybrid;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class AndroidBridge {

    private WebView mWebView;
    private Integer curNum;

    private final Handler handler = new Handler();

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

        //아래 줄은 외부저장소의 경로를 가져오는 코드  : 경로 =/mnt/sdcard/Download
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String strDir = file.getAbsolutePath();

        storagePermissionCheck(downloadBaseUrl + url, strDir, fileName);

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


        File direct = new File(Environment.getExternalStorageDirectory() + "/download");

        if (!direct.exists()) {
            direct.mkdir();
        }

        if (result) ((HybridWebViewActivityJava) activity).startDownload(url, dir, fileName);

    }


    @JavascriptInterface
    public void postMessage(final String message) {
        handler.post(() -> {

            JSONObject result;
            try {
                result = new JSONObject(message);

                String callback = result.getString("callback");
                String msg = result.getString("message");
                String action = result.getString("action");
                Log.d(TAG, "postMessage: callback : " + callback);
                Log.d(TAG, "postMessage: message : " + msg);

                if (action.equals("getCustInfo")) {
                    Log.d(TAG, "postMessage: getCustInfo 진입");
                    String custNo = "고객번호";
                    String custId = "고객 아이디";
                    String custName = "고객 이름";
                    mWebView.loadUrl("javascript:custInfoCallBackAndroid('" + custNo + ", " + custId + ", " + custName + "')"); //params 값 전송

                } else {

                    AlertDialog dialog = new AlertDialog.Builder(activity).
                            setMessage(msg).
                            setPositiveButton("확인", (dialog1, which) -> {
                                Toast.makeText(activity, "확인 클릭", Toast.LENGTH_SHORT).show();
                                dialog1.cancel();
                                //삭제 콜백 보내기
                                mWebView.loadUrl("javascript:confirmCallbackAndroid()"); //params 값 전송
                            }).setNegativeButton("취소", (dailog2, which) -> {
                        Toast.makeText(activity, "취소 클릭", Toast.LENGTH_SHORT).show();
                        dailog2.cancel();
                    }).create();
                    dialog.show();

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        });

    }

}

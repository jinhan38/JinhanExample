package com.jinhanexample.hybrid;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Movie;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jinhanexample.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class HybridWebViewActivityJava extends AppCompatActivity {

    //    public static String baseUrl = "https://devapi.lifeplusmentalcare.com:2080/not/NOT01010000.do";
    public static String baseUrl = "https://devapi.lifeplusmentalcare.com:2080/not/NOT02010000.do";
    public static Activity activity;
    private WebView mWebView;
    private String intentKeyword;
    private AndroidBridge androidBridge;
    private static final String TAG = "HybridWebViewActivityJa";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hybrid_web_view);
        activity = this;

        mWebView = findViewById(R.id.webViewJava);

//        intentKeyword = "?ntprCustCode=9062971819";
//        Log.d(TAG, "browserSetting: $url");
        browserSettings();

    }


    private void browserSettings() {

        androidBridge = new AndroidBridge(mWebView, 0);
        androidBridge.setBaseUrl(baseUrl);
        mWebView.addJavascriptInterface(androidBridge, "AndroidApp");

        WebSettings settings = mWebView.getSettings();
        mWebView.setWebViewClient(new WebViewClient());
        settings.setJavaScriptEnabled(true); //javascript 허용
        settings.setSupportMultipleWindows(false); //다중웹뷰 허용
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//javascript의 window.open 허용
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE); //개발중엔 no_cache, 배포중엔 load_default

        mWebView.setWebChromeClient(new WebChromeClient() {
            //webView Alert 띄우기 위해 추가해야하는 코드
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                AlertDialog dialog = new AlertDialog.Builder(view.getContext()).
                        setMessage(message).
                        setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).create();
                dialog.show();
                result.confirm();
                return true;
//                return super.onJsAlert(view, null, message, result);
            }
        });
        String urlLoad = "https://devapi.lifeplusmentalcare.com:2080/not/NOT02010000.do?ntprCustCode=9009998689&custNo=9064294429";
        mWebView.loadUrl(urlLoad);
//        mWebView.loadUrl(baseUrl);

        //기본 웹뷰 세팅
        //메인 추가 웹뷰 세팅
        settings.setAllowFileAccess(true);//파일 엑세스
    }


    /**
     * 자바스크립트에서 호출하면 파일 다운로드시키는 함수
     *
     * @param url
     * @param dir
     */
    public void startDownload(String url, String dir, String fileName) {

        File file = new File(getUrlDecode(dir));

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle(fileName); // 알림에서 보일 이름, 화면 상단 notification에서 나타나는 이름이다.
        request.setDescription("Downloading file...");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationUri(Uri.fromFile(file));
        request.setMimeType("image/jpeg");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, System.currentTimeMillis() + "");//파일네임
        request.setAllowedOverMetered(true);
        request.setAllowedOverRoaming(true);
        DownloadManager manager = (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

    }


    public String getUrlDecode(String _strFileName) {
        String strRet = null;
        try {
            strRet = URLDecoder.decode(_strFileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            strRet = "";
        }
        return strRet;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void jsonParse(String result) {

        try {
            JSONObject jsonObject = new JSONObject(result);
            int isRegister = jsonObject.getInt("isRegister");
            int focus = jsonObject.getInt("focus");
            String text = jsonObject.getString("text");
            int userNum = jsonObject.getInt("userNum");


        } catch (JSONException e) {
            Log.e(TAG, "Could not parse JSON. Error: " + e.getMessage());
        }
    }


}

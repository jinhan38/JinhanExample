package com.jinhanexample.hybrid;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jinhanexample.R;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class HybridWebViewActivityJava extends AppCompatActivity {

    public static String baseUrl = "https://devapi.lifeplusmentalcare.com:2080/not/NOT01010000.do";
    public static Activity activity;
    private WebView mWebView;
    private String intentKeyword;
    private AndroidBridge androidBridge;
    private ProgressBar progressBar;
    ValueCallback mFilePathCallback;
    private static final String TAG = "HybridWebViewActivityJa";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hybrid_web_view);
        activity = this;

        mWebView = findViewById(R.id.webViewJava);

        intentKeyword = "?ntprCustCode=9062971819";
        Log.d(TAG, "browserSetting: $url");
        browserSettings();

    }


    private void browserSettings() {

        androidBridge = new AndroidBridge(mWebView, 0);
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
        //캐시파일 사용 금지(운영중엔 주석처리 할 것)
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE); //개발중엔 no_cache, 배포중엔 load_default

        String url = baseUrl + intentKeyword;
        mWebView.loadUrl(url);

        //기본 웹뷰 세팅
        //메인 추가 웹뷰 세팅
        settings.setAllowFileAccess(true);//파일 엑세스
//        settings.setLoadWithOverviewMode(true);
//        settings.setAppCachePath(mainActivity.getApplicationContext().getCacheDir().getAbsolutePath());
//        settings.setPluginState(WebSettings.PluginState.ON);


//        mWebView.setDownloadListener(new DownloadListener() {
//            @Override
//            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
//                mWebView.loadUrl(JavaScriptInterface.getBase64StringFromBlobUrl(url));
//                Log.e("logURL", url);
//            }
//        });

//        mWebView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public boolean onShowFileChooser(WebView webView, ValueCallback filePathCallback, FileChooserParams fileChooserParams) {
//                mFilePathCallback = filePathCallback;
//
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
//                intent.setType("image/*");
//
//                startActivityForResult(intent, 0);
//                return true;
//            }
//
//
//        });

    }


    /**
     * 자바스크립트에서 호출하면 파일 다운로드시키는 함수
     *
     * @param url
     * @param dir
     */
    public void startDownload(String url, String dir, String fileName) {

//        File file = File(getExternalFilesDir(null), "dev_submit.mp4")

//        File file = new File(getUrlDecode(dir));
//
//        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
//        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
//        request.setTitle(fileName);
//        request.setDescription("Downloading file...");
//        request.allowScanningByMediaScanner();
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        request.setDestinationUri(Uri.fromFile(file));
//        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "" + System.currentTimeMillis());
//        request.setAllowedOverMetered(true);
//        request.setAllowedOverRoaming(true);
//        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
//        manager.enqueue(request);

        File file = new File(getUrlDecode(dir));


        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle(fileName);
        request.setDescription("Downloading file...");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationUri(Uri.fromFile(file));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "" + System.currentTimeMillis());
        request.setAllowedOverMetered(true);
        request.setAllowedOverRoaming(true);
        DownloadManager manager = (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
        Toast.makeText(activity, "파일 다운로드가 완료되었습니다.", Toast.LENGTH_SHORT).show();


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


}

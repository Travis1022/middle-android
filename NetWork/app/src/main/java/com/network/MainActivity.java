package com.network;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 网络编程与网络框架OkHttp
 * create by Travis on 2017-08-10
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.tv_result)
    TextView mTvResult;

    private OkHttpClient mOkHttpClient;
    private final static String baseUrl = "https://www.baidu.com/";
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_asy_get, R.id.btn_asy_post, R.id.btn_asy_upload, R.id.btn_asy_download, R.id.btn_asy_mul, R.id.btn_asy_final})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //异步Get请求
            case R.id.btn_asy_get:
                asyGet();
                break;
            //异步Post请求
            case R.id.btn_asy_post:
                asyPost();
                break;
            //异步上传文件
            case R.id.btn_asy_upload:
                asyUpload();
                break;
            //异步下载文件
            case R.id.btn_asy_download:
                asyDownload();
                break;
            //异步上传multipart文件
            case R.id.btn_asy_mul:
                asyUploadMultiPart();
                break;
            //封装框架
            case R.id.btn_asy_final:
                useFinalOkHttp();
                break;
        }
    }

    /**
     * 封装OkHttp框架
     */
    private void useFinalOkHttp() {

    }

    /**
     * 异步上传文件
     */
    private void asyUploadMultiPart() {

    }

    /**
     * 异步下载文件
     */
    private void asyDownload() {

    }

    /**
     * 异步上传文件
     */
    private void asyUpload() {

    }

    /**
     * 异步Post请求
     */
    private void asyPost() {

    }

    /**
     * 异步Get请求
     */
    private void asyGet() {
        mOkHttpClient = new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url(baseUrl);
        requestBuilder.method("GET", null);
        final Request request = requestBuilder.build();
        Call mCall = mOkHttpClient.newCall(request);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("MainActivity", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.cacheResponse() != null) {
                    result = "缓存结果：\n" + response.cacheResponse().toString();
                } else {
                    result = "请求结果：\n" + response.networkResponse().toString();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
                        mTvResult.setText(result);
                    }
                });
            }
        });
    }
}

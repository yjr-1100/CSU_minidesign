package com.yjr1100.qrcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class manager extends AppCompatActivity {
    private RecyclerView managerRvMain;
    private JSONObject jsondata;
    private JSONArray goodeslist;
    private Handler handler;

    Thread subthread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                String path = getString(R.string.ip)+"nosendergoodes";
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("ser-Agent", "Fiddler");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setConnectTimeout(5 * 1000);
                // 如果请求响应码是200，则表示成功
                System.out.println(conn.getResponseCode());
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    //获取服务器上的数据
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                    try {
                        //解码
                        String jsonStr = URLDecoder.decode(in.readLine(), "UTF-8");
                        final JSONObject objectT = new JSONObject(jsonStr);
                        String msg = objectT.getString("msg");
                        Log.i("objectT", objectT.getString("msg"));
                        if (msg.equals("查询成功")) {
                            /*查询成功了，要修改页面的东西*/
                            System.out.println(objectT.getString("goodes"));
                            goodeslist = objectT.getJSONArray("goodes");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    in.close();
                    conn.disconnect();
                } else {
                    Looper.prepare();
                    Toast.makeText(manager.this, "寄件加载失败请重试", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SocketTimeoutException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(manager.this, "连接超时", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        subthread.start();
        while (subthread.isAlive() == true){
            System.out.println("0");
        }
        System.out.println("------------");
        final Intent intent = new Intent();
        managerRvMain = findViewById(R.id.manaz);
        managerRvMain.setLayoutManager(new LinearLayoutManager(manager.this));
        managerRvMain.addItemDecoration(new MyDecoration());
        managerRvMain.setAdapter(new LinearAdapter(manager.this, new LinearAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                intent.setClass(manager.this,managerchosesender.class);
                try {
                    String goodeinfo = goodeslist.get(pos).toString();
                    intent.putExtra("goodesinfo", goodeinfo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
                finish();
            }
        },goodeslist,null));
    }
    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.dividerHeight));
        }
    }
}

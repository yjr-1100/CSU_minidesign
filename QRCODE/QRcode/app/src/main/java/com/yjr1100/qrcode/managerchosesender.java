package com.yjr1100.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;


public class managerchosesender extends AppCompatActivity {
    Spinner senderspinner;
    Button submit;
    JSONArray senderslist;
    Handler handler;
    JSONObject goodesinfo;
    ArrayList<String> sendernamelist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managerchosesender);
        System.out.println("0-0-0-0-0-0-0-0-0-0-0-0-0-0-0");
        System.out.println(getIntent().getStringExtra("goodesinfo"));
        try {
            goodesinfo = new JSONObject(getIntent().getStringExtra("goodesinfo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        senderspinner = findViewById(R.id.senderspinner);
        submit = findViewById(R.id.configsender);
        subthread.start();
        while (subthread.isAlive()==true){

        }
        System.out.println(senderslist);
        ArrayAdapter<String> senderadapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item,sendernamelist);
        senderadapter.setDropDownViewResource(R.layout.spinnerdropdownview);
        senderspinner.setAdapter(senderadapter);
        senderspinner.setOnItemSelectedListener(new OnItemSelectedListenerImpl());
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(senderforgoodes.isAlive()){
                    senderforgoodes.stop();
                }else{
                    senderforgoodes.start();
                }
            }
        });


    }

    Thread senderforgoodes = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                String path = getString(R.string.ip)+"changesenderforgoodes";
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("ser-Agent", "Fiddler");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setConnectTimeout(5 * 1000);
                /*上传的数据*/
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(URLEncoder.encode(goodesinfo.toString(), "UTF-8").getBytes());
                System.out.println(goodesinfo.toString());
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
                        if (msg.equals("分配成功")) {
                            /*分配成功了，跳回上一页*/
                            Looper.prepare();
                            Toast.makeText(managerchosesender.this, msg, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setClass(managerchosesender.this,manager.class);
                            startActivity(intent);
                            finish();

                        }else{
                            Looper.prepare();
                            Toast.makeText(managerchosesender.this, "分配失败请稍后", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    in.close();
                    conn.disconnect();
                } else {
                    Looper.prepare();
                    Toast.makeText(managerchosesender.this, "分配失败请重试", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(managerchosesender.this, "连接超时", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    });


    Thread subthread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                String path = getString(R.string.ip)+"getsender";
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
                            System.out.println(objectT.getString("senders"));
                            senderslist = objectT.getJSONArray("senders");
                            for(int i=0;i<senderslist.length();i++){
                                JSONObject j = new JSONObject(senderslist.get(i).toString());
                                sendernamelist.add(j.getString("username"));
                            }
                            JSONObject j = new JSONObject(senderslist.get(0).toString());
                            goodesinfo.put("s_id",j.getInt("uid"));
                        }else{
                            senderslist = new JSONArray();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    in.close();
                    conn.disconnect();
                } else {
                    Looper.prepare();
                    Toast.makeText(managerchosesender.this, "寄件加载失败请重试", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(managerchosesender.this, "连接超时", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    });
    //下拉框选择事件
    private class OnItemSelectedListenerImpl implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {
            String city = parent.getItemAtPosition(position).toString();
            JSONObject j = null;
            try {
                j = new JSONObject(senderslist.get(position).toString());
                goodesinfo.put("s_id",j.getInt("uid"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Toast.makeText(managerchosesender.this, "选择的快递员是：" + city,
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // TODO Auto-generated method stub
        }

    }

}

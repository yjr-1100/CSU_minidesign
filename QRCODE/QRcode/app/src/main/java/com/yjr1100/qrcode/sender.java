package com.yjr1100.qrcode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.qrcode.QRCodeReader;
import com.journeyapps.barcodescanner.CaptureActivity;

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

import static com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE;


public class sender extends AppCompatActivity {
    ImageView scanimg;
    JSONObject userinfo;
    JSONObject jsondata;
    JSONArray goodeslist,senderlist;
    Handler handler;
    RecyclerView managerRvMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sender);
        scanimg = findViewById(R.id.scanimg);
        scanimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(sender.this);
                intentIntegrator.setPrompt("lalalala");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(CaptureActivity.class);
                intentIntegrator.initiateScan();



            }
        });
        System.out.println(getIntent().getStringExtra("userinfo"));
        getIntent().getStringExtra("userinfo");
        try {
            userinfo = new JSONObject(getIntent().getStringExtra("userinfo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Thread subthread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    submit(userinfo.getInt("uid"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        subthread.start();
        while (subthread.isAlive() ==true){

        }

        final Intent intent = new Intent();
        managerRvMain = findViewById(R.id.senderitems);
        managerRvMain.setLayoutManager(new LinearLayoutManager(this));
        managerRvMain.addItemDecoration(new sender.MyDecoration());
        managerRvMain.setAdapter(new LinearAdapter(this, new LinearAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                intent.setClass(sender.this,showqrcode.class);
                try {
                    String goodeinfo = goodeslist.get(pos).toString();
                    intent.putExtra("goodesinfo", goodeinfo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        },goodeslist,senderlist));
    }
    public void submit(int uid) {
        try {
            String path = getString(R.string.ip)+"getneedsendgoodes";
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("ser-Agent", "Fiddler"); conn.setRequestProperty("Content-Type", "application/json"); conn.setConnectTimeout(5 * 1000);
            // 包装并上传数据
            OutputStream outputStream = conn.getOutputStream();
            jsondata = new JSONObject();
            jsondata.put("uid",String.valueOf(uid));
            outputStream.write(URLEncoder.encode(jsondata.toString(), "UTF-8").getBytes());
            System.out.println(jsondata.toString());
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
                    if(msg.equals("查询成功")){
                        /*查询成功了，要修改页面的东西*/
                        Looper.prepare();
                        Toast.makeText(this, "查询成功", Toast.LENGTH_SHORT).show();
                        goodeslist = objectT.getJSONArray("goodes");
                        senderlist = objectT.getJSONArray("senders");
                    }else{
                        goodeslist = new JSONArray();
                        senderlist = new JSONArray();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                in.close();
                conn.disconnect();
            }else{
                Looper.prepare();
                Toast.makeText(this, "寄件加载失败请重试", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (SocketTimeoutException e) {

            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(sender.this, "连接超时", Toast.LENGTH_SHORT).show();
                    return;
                }
            });
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                try {
                    JSONObject js = new JSONObject(result.getContents());
                    if(js.getInt("s_id")==userinfo.getInt("uid")){
                        Intent intent = new Intent();
                        intent.setClass(sender.this,goodesdetails.class);
                        intent.putExtra("goodesinfo", result.getContents());
                        startActivity(intent);
                    }else{
                        Toast.makeText(this, "您暂无权限产看此货物", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.dividerHeight));
        }
    }



}

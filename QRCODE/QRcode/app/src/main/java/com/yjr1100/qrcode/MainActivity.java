package com.yjr1100.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

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

public class MainActivity extends AppCompatActivity {
    Button loginbtn;
    Button registerbtn;
    TextView phone;
    ImageView imguser;
    TextView pwd;
    Handler handler;
    private JSONObject jsondata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginbtn = findViewById(R.id.loginbtn);
        registerbtn = findViewById(R.id.registerbtn);
        phone = findViewById(R.id.phonetext);
        pwd = findViewById(R.id.pwdtext);
        imguser = findViewById(R.id.imguser);
        handler = new Handler();
        Glide.with(MainActivity.this).load("https://tupian.qqw21.com/article/UploadPic/2021-10/202110422424219051.jpg").into(imguser);
        /*登录*/
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*测试是否输入*/
                if(TextUtils.isEmpty(phone.getText().toString())||
                        TextUtils.isEmpty(pwd.getText().toString())){
                    //判断是不是空
                    Toast.makeText(getApplicationContext(),"请输入用户名和密码",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    /*发送请求进行登录*/
                    jsondata = new JSONObject();
                    try {
                        jsondata.put("phonenum",phone.getText().toString());
                        jsondata.put("password",pwd.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    new Thread(new Runnable(){
                        @Override
                        public void run() {
                            submit();
                        }
                    }).start();

                }

            }
        });

        /*注册*/
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*跳转注册界面*/
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,register.class);
                startActivity(intent);
            }
        });
    }

    public void submit() {
        try {

            String path = getString(R.string.ip)+"loginServlet";
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("ser-Agent", "Fiddler"); conn.setRequestProperty("Content-Type", "application/json"); conn.setConnectTimeout(5 * 1000);
            // 包装并上传数据
            OutputStream outputStream = conn.getOutputStream();
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
                    if(msg.equals("用户名或密码错误")){
                        /*登录失败提示*/
                        Looper.prepare();
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }else{
                        /*登录成成功跳转*/
                        System.out.println(objectT.getString("userinfo"));
                        JSONObject userinfo = new JSONObject(objectT.getString("userinfo"));
                        Intent intent = new Intent();
                        if(userinfo.getInt("identifier") == 0){
                            /*顾客跳转*/
                            //前一个（MainActivity.this）是目前页面，后面一个是要跳转的下一个页面
                            intent.setClass(MainActivity.this,ContantActivity.class);
                            intent.putExtra("userinfo", objectT.getString("userinfo"));
                            startActivity(intent);
                            finish();
                        }else if(userinfo.getInt("identifier") == 1){
                            intent.setClass(MainActivity.this,sender.class);
                            intent.putExtra("userinfo", objectT.getString("userinfo"));
                            startActivity(intent);
                            finish();
                        }else if(userinfo.getInt("identifier") == 2){
                            intent.setClass(MainActivity.this,manager.class);
                            intent.putExtra("userinfo", objectT.getString("userinfo"));
                            startActivity(intent);
                            finish();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                in.close();
                conn.disconnect();
            }else{
                Looper.prepare();
                Toast.makeText(MainActivity.this, "请稍后再试", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(MainActivity.this, "连接超时", Toast.LENGTH_SHORT).show();
                    return;
                }
            });
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

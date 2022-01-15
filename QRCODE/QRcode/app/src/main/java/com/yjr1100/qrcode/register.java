package com.yjr1100.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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

public class register extends AppCompatActivity {
    private Button regsub;
    private TextView rephone,regpwd,regpwdconfig,regname,titlereg;
    private RadioGroup chosegroup;
    private Integer identifier;
    private JSONObject jsondata;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        regsub = findViewById(R.id.regsubmit);
        rephone = findViewById(R.id.regphone);
        regpwd = findViewById(R.id.regpwd);
        regpwdconfig = findViewById(R.id.regpwdconfig);
        chosegroup = findViewById(R.id.choseidentyfy);
        regname = findViewById(R.id.regname);
        titlereg = findViewById(R.id.titlereg);
        identifier = 0;
        chosegroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                System.out.println(i);
                if(i==R.id.userreg){
                    identifier = 0;
                    titlereg.setText("用户注册");
                }else if(i==R.id.senderreg){
                    identifier = 1;
                    titlereg.setText("快递员注册");
                }
            }
        });
        regsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(rephone.getText().toString())||TextUtils.isEmpty(regpwd.getText().toString())||TextUtils.isEmpty(regpwdconfig.getText().toString())||TextUtils.isEmpty(regname.getText().toString())){
                    Toast.makeText(getApplicationContext(),"请输入完整注册信息",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!regpwd.getText().toString().equals(regpwdconfig.getText().toString())){
                    Toast.makeText(getApplicationContext(),"两次密码不同",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    /*进行注册*/
                    /*发送请求进行登录*/
                    jsondata = new JSONObject();
                    try {
                        jsondata.put("phonenum",rephone.getText().toString());
                        jsondata.put("password",regpwd.getText().toString());
                        jsondata.put("regname",regname.getText().toString());
                        jsondata.put("identifier",identifier);
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
    }
    public void submit() {
        try {
            String path = getString(R.string.ip)+"usersregister";
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
                    Integer code = objectT.getInt("code");
                    String msg = objectT.getString("msg");
                    Log.i("objectT", objectT.getString("msg"));
                    if(code==0){
                        /*登录失败提示*/
                        Looper.prepare();
                        Toast.makeText(register.this, msg, Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }else{
                        /*注册成功跳转回登录页面*/
                        Looper.prepare();
                        Toast.makeText(register.this, "注册成功请登录", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClass(register.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                        Looper.loop();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                in.close();
                conn.disconnect();
            }else{
                Looper.prepare();
                Toast.makeText(register.this, "请稍后再试", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(register.this, "连接超时", Toast.LENGTH_SHORT).show();
                    return;
                }
            });
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

package com.yjr1100.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class goodesdetails extends AppCompatActivity {
    private TextView t1,t2,t3,t4,t5,t6,t7;
    JSONObject goodesinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goodesdetails);
        t1 = findViewById(R.id.textView14);
        t2 = findViewById(R.id.textView15);
        t3 = findViewById(R.id.textView16);
        t4 = findViewById(R.id.textView17);
        t5 = findViewById(R.id.textView18);
        t6 = findViewById(R.id.textView19);
        try {
            goodesinfo = new JSONObject(getIntent().getStringExtra("goodesinfo"));
            t1.setText(goodesinfo.getString("goodname"));
            t2.setText(goodesinfo.getString("recaddress"));
            t3.setText(goodesinfo.getString("recname"));
            t4.setText(goodesinfo.getString("recphonenum"));
            t5.setText(goodesinfo.getString("sendername"));
            t6.setText(goodesinfo.getString("senderphonenum"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

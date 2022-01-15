package com.yjr1100.qrcode;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class showqrcode extends AppCompatActivity {
    private ImageView qrcodeshow;
    JSONObject goodesinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showqrcode);
        qrcodeshow = findViewById(R.id.qrcodeshow);
        try {
            goodesinfo = new JSONObject(getIntent().getStringExtra("goodesinfo"));
            System.out.println(goodesinfo.getString("s_id"));
            if(!goodesinfo.getString("s_id").equals("null")){
                Glide.with(this).load(getString(R.string.ip)+"getqrcodeservlet?goodesinfo="+goodesinfo.toString()).into(qrcodeshow);
                //设置图片长按识别
                System.out.println("7777777");
                readImagePicture(qrcodeshow);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /*
     * 长按图片解析二维码
     */
    private void readImagePicture(final ImageView imageView) {
        // TODO Auto-generated method stub
        //长按，通过zxing读取图片，判断是否有二维码
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View viewm) {
                Bitmap obmp = ((BitmapDrawable) (imageView).getDrawable()).getBitmap();
                recogQRcode(obmp);
                return false;
            }
        });
    }

    //识别二维码的函数
    public void recogQRcode(Bitmap obmp){
        int width = obmp.getWidth();
        int height = obmp.getHeight();
        int[] data = new int[width * height];
        obmp.getPixels(data, 0, width, 0, 0, width, height);
        RGBLuminanceSource source = new RGBLuminanceSource(width, height, data);
        BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();
        Result re = null;
        try {
            re = reader.decode(bitmap1);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (ChecksumException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        }
        if (re == null) {
            System.out.println("00000000000000000011111111111111");
        } else {
            showSelectAlert(obmp, re);
        }

    }

    protected void saveImageToGallery(Bitmap bitmap) {
        // 首先保存图片
        String path = Environment.getExternalStorageDirectory()+"/QRcodeImage/";
        File fileDir;
        /**
         * 文件目录如果不存在，则创建
         */
        fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        String bitName = System.currentTimeMillis()+".JPG";
        String filePath =Environment.getExternalStorageDirectory().getPath()+"/DCIM/Camera/"+ bitName;
        File file=new File(filePath);
        if(file.exists()){
            file.delete();
        }
        FileOutputStream out;
        try{
            out = new FileOutputStream(file);
            // 格式为 JPEG，照相机拍出的图片为JPEG格式的，PNG格式的不能显示在相册中
            if(bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out))
            {
                out.flush();
                out.close();
// 插入图库
                MediaStore.Images.Media.insertImage(this.getContentResolver(), file.getAbsolutePath(), bitName, null);

            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();

        }
        // 发送广播，通知刷新图库的显示
        this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + filePath)));

        Toast.makeText(getBaseContext(), "图片保存成功", Toast.LENGTH_SHORT).show();
        System.out.println("图片保存成功");

    }


    private void showSelectAlert(final Bitmap bitmap,final Result re) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择");
        String str[] = {"保存图片", "识别二维码"};
        builder.setItems(str, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterfacem, int i) {
                switch (i) {
                    case 0: {
                        saveImageToGallery(bitmap);
                    }
                    break;
                    case 1: {
                        //Toast出内容
                        Intent intent = new Intent();
                        intent.setClass(showqrcode.this,goodesdetails.class);
                        intent.putExtra("goodesinfo", re.getText());
                        startActivity(intent);
                        finish();
                    }
                    break;
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterfacem, int i) {
            }
        });
        builder.show();
    }
}

package com.yjr1100.qrcodebackend.utils;/*
 * @Auther:YJR-1100
 * @Date:2022/1/4 - 01 - 04 - 16:00
 * @Version:1.0
 * @Description:
 *
 *//*
 * @ClassName NetUtils
 * @Description TODO
 * @Author YJR-1100
 * @Date 2022/1/4 16:00
 * @Version 1.0
 */

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class NetUtils {

    public static byte[] readBytes(InputStream is) {
        try {
            byte[] buffer = new byte[1024];
            int len = -1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String readString(InputStream is) {
        return new String(readBytes(is));
    }

}


package com.yjr1100.qrcodebackend.utils;/*
 * @Auther:YJR-1100
 * @Date:2022/1/5 - 01 - 05 - 10:12
 * @Version:1.0
 * @Description:
 *
 *//*
 * @ClassName QrCodeUtils
 * @Description TODO
 * @Author YJR-1100
 * @Date 2022/1/5 10:12
 * @Version 1.0
 */
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import jdk.nashorn.internal.runtime.ECMAException;

/**
 * 二维码工具类
 * @author limingcheng
 *
 */

public class QrCodeUtils {
    /**
     * 生成一个二维码图片
     * @param width
     * @param height
     * @param content
     * @return
     * @throws WriterException
     * @throws IOException
     */
    public byte[] createQRCode(int width, int height, String content) throws Exception, IOException {
        // 二维码基本参数设置
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");// 设置编码字符集utf-8
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);// 设置纠错等级L/M/Q/H,纠错等级越高越不易识别，当前设置等级为最高等级H
        hints.put(EncodeHintType.MARGIN, 0);// 可设置范围为0-10，但仅四个变化0 1(2) 3(4 5 6) 7(8 9 10)
        /*HashMap<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8"); // 指定编码方式,防止中文乱码
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);*/
        // 生成图片类型为QRCode
        BarcodeFormat format = BarcodeFormat.QR_CODE;
        // 创建位矩阵对象
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, format, width, height, hints);
        // 位矩阵对象转流对象
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "jpg", os);
        return os.toByteArray();
    }



/*    public static void main(String[] args) throws WriterException, IOException {
        byte[] b = createQRCode(100, 100, "遇见最好的自己！");
        OutputStream os = new FileOutputStream("./bestme.png");
        os.write(b);
        os.close();
    }*/

}
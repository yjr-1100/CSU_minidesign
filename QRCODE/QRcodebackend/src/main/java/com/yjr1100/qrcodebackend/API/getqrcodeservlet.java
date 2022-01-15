package com.yjr1100.qrcodebackend.API;/*
 * @Auther:YJR-1100
 * @Date:2022/1/5 - 01 - 05 - 21:58
 * @Version:1.0
 * @Description:
 *
 */

import com.google.zxing.WriterException;
import com.yjr1100.qrcodebackend.Dao.GoodesDao;
import com.yjr1100.qrcodebackend.utils.NetUtils;
import com.yjr1100.qrcodebackend.utils.QrCodeUtils;
import net.sf.json.JSONObject;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLDecoder;

@WebServlet("/getqrcodeservlet")
public class getqrcodeservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String goodesinfo = request.getParameter("goodesinfo");
        QrCodeUtils qr = new QrCodeUtils();
        try {
            byte[] b = qr.createQRCode(206,206,goodesinfo);
            ByteArrayInputStream in = new ByteArrayInputStream(b);    //将b作为输入流；
            BufferedImage image = ImageIO.read(in);     //将in作为输入流，读取图片存入image中，而这里in可以为ByteArrayInputStream();
            ImageIO.write(image,"jpg",response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //获取数据
        InputStream inputStream = request.getInputStream();
        String json = NetUtils.readString(inputStream);
        json = URLDecoder.decode(json, "UTF-8");
        JSONObject goodesinfojson = JSONObject.fromObject(json);
        QrCodeUtils qr = new QrCodeUtils();
        try {

            byte[] b = qr.createQRCode(206,206,goodesinfojson.toString());
            ByteArrayInputStream in = new ByteArrayInputStream(b);    //将b作为输入流；
            BufferedImage image = ImageIO.read(in);     //将in作为输入流，读取图片存入image中，而这里in可以为ByteArrayInputStream();
            ImageIO.write(image,"jpg",response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }


        //OutputStream os = new FileOutputStream("./bestme.png");
        //os.write(b);
        //os.close();

    }
}

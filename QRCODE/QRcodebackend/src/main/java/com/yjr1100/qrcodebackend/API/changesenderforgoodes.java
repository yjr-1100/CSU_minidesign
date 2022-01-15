package com.yjr1100.qrcodebackend.API;/*
 * @Auther:YJR-1100
 * @Date:2022/1/5 - 01 - 05 - 20:11
 * @Version:1.0
 * @Description:
 *
 */

import com.google.zxing.WriterException;
import com.yjr1100.qrcodebackend.Dao.GoodesDao;
import com.yjr1100.qrcodebackend.Dao.UserDao;
import com.yjr1100.qrcodebackend.domain.User;
import com.yjr1100.qrcodebackend.utils.NetUtils;
import com.yjr1100.qrcodebackend.utils.QrCodeUtils;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;

@WebServlet(name = "changesenderforgoodes", value = "/changesenderforgoodes")
public class changesenderforgoodes extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //获取数据
        InputStream inputStream = request.getInputStream();
        String json = NetUtils.readString(inputStream);
        json = URLDecoder.decode(json, "UTF-8");
        JSONObject goodesinfojson = JSONObject.fromObject(json);

        JSONObject jsonObject = new JSONObject();
        GoodesDao goodesDao = new GoodesDao();
        int reslut = goodesDao.updatesid(goodesinfojson);
        if(reslut == 0){
            jsonObject.put("msg", "分配失败请稍后");
        }else{
            jsonObject.put("msg", "分配成功");

        }
        String serverStr = URLEncoder.encode(jsonObject.toString(), "UTF-8");
        //发送数据
        PrintWriter printWriter = response.getWriter();
        printWriter.write(serverStr);
    }
}

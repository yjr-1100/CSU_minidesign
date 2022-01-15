package com.yjr1100.qrcodebackend.API;/*
 * @Auther:YJR-1100
 * @Date:2022/1/4 - 01 - 04 - 22:20
 * @Version:1.0
 * @Description:
 *
 */

import com.yjr1100.qrcodebackend.Dao.GoodesDao;
import com.yjr1100.qrcodebackend.utils.NetUtils;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/addgoodServlet")
public class addgoodServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //获取数据
        System.out.println("--------------");
        InputStream inputStream = request.getInputStream();
        String json = NetUtils.readString(inputStream);
        json = URLDecoder.decode(json, "UTF-8");
        JSONObject jsonT = JSONObject.fromObject(json);
        JSONObject jsonObject = new JSONObject();
        System.out.println(jsonT.toString());

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy.MM.dd hh:mm");
        jsonT.put("sendtime",dateFormat.format(date));
        GoodesDao goodesDao = new GoodesDao();
        int insertusergoodes = goodesDao.insertusergoodes(jsonT);
        System.out.println(insertusergoodes);
        if(insertusergoodes == 1){
            jsonObject.put("msg", "插入成功");
        }else if(insertusergoodes ==0){
            jsonObject.put("msg", "插入失败");
        }
        String serverStr = URLEncoder.encode(jsonObject.toString(), "UTF-8");
        //发送数据
        PrintWriter printWriter = response.getWriter();
        printWriter.write(serverStr);
    }
}

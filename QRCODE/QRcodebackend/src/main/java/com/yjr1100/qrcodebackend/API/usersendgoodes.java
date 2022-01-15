package com.yjr1100.qrcodebackend.API;/*
 * @Auther:YJR-1100
 * @Date:2022/1/4 - 01 - 04 - 18:14
 * @Version:1.0
 * @Description:
 *
 */

import com.yjr1100.qrcodebackend.Dao.GoodesDao;
import com.yjr1100.qrcodebackend.Dao.UserDao;
import com.yjr1100.qrcodebackend.domain.Goodes;
import com.yjr1100.qrcodebackend.domain.User;
import com.yjr1100.qrcodebackend.utils.NetUtils;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@WebServlet("/usersendgoodes")
public class usersendgoodes extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //获取数据
        InputStream inputStream = request.getInputStream();
        String json = NetUtils.readString(inputStream);
        json = URLDecoder.decode(json, "UTF-8");
        System.out.println("----------json----------");
        System.out.println(json);
        JSONObject jsonT = JSONObject.fromObject(json);
        JSONObject jsonObject = new JSONObject();
        String uid = jsonT.getString("uid");
        GoodesDao goodesDao = new GoodesDao();
        UserDao userDao = new UserDao();
        List<Map<String, Object>> goodeslist= goodesDao.getusergoodes(Integer.valueOf(uid));
        List<Map<String, Object>> senderlist= userDao.getsenders();
        System.out.println(goodeslist);
        if(goodeslist == null){
            jsonObject.put("msg", "暂无寄出物品");
        }else{
            jsonObject.put("msg", "查询成功");
            jsonObject.put("goodes",goodeslist);
            jsonObject.put("senders",senderlist);
        }
        String serverStr = URLEncoder.encode(jsonObject.toString(), "UTF-8");
        //发送数据
        PrintWriter printWriter = response.getWriter();
        printWriter.write(serverStr);
    }
}

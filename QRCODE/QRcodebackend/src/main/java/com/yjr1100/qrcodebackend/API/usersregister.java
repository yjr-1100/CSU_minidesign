package com.yjr1100.qrcodebackend.API;/*
 * @Auther:YJR-1100
 * @Date:2022/1/5 - 01 - 05 - 1:49
 * @Version:1.0
 * @Description:
 *
 */

import com.yjr1100.qrcodebackend.Dao.UserDao;
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

@WebServlet("/usersregister")
public class usersregister extends HttpServlet {
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
        JSONObject jsonT = JSONObject.fromObject(json);
        JSONObject jsonObject = new JSONObject();
/*        String phonenum = jsonT.getString("phonenum");
        String password = jsonT.getString("password");
        int identify = jsonT.getInt("identifier");*/

        UserDao userDao = new UserDao();
        if(userDao.isexit(jsonT.getString("phonenum"))){
            int result = userDao.userregister(jsonT);
            if(result == 0){
                jsonObject.put("code",0);
                jsonObject.put("msg", "注册失败");
            }else if(result == 1){
                jsonObject.put("code",1);
                jsonObject.put("msg", "注册成功，请登录");
            }
        }else{
            jsonObject.put("code",0);
            jsonObject.put("msg", "手机号已存在");
        }

        String serverStr = URLEncoder.encode(jsonObject.toString(), "UTF-8");
        //发送数据
        PrintWriter printWriter = response.getWriter();
        printWriter.write(serverStr);
    }
}

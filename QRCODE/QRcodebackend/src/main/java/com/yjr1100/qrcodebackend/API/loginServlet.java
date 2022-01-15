package com.yjr1100.qrcodebackend.API;/*
 * @Auther:YJR-1100
 * @Date:2021/11/24 - 11 - 24 - 0:29
 * @Version:1.0
 * @Description:
 *
 */

import com.yjr1100.qrcodebackend.utils.NetUtils;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import com.yjr1100.qrcodebackend.Dao.UserDao;
import com.yjr1100.qrcodebackend.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

@WebServlet( "/loginServlet")
public class loginServlet extends HttpServlet {
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
        String phonenum = jsonT.getString("phonenum");
        String password = jsonT.getString("password");
        User loginuser = new User();
        loginuser.setPhonenum(phonenum);
        loginuser.setPassword(password);
        UserDao userDao = new UserDao();
        User user = userDao.login(loginuser);
        if(user == null){
            jsonObject.put("msg", "用户名或密码错误");
        }else{
            jsonObject.put("msg", "登录成功，请稍后");
            System.out.println(user.toString());
            jsonObject.put("userinfo",user.toString());
        }
        String serverStr = URLEncoder.encode(jsonObject.toString(), "UTF-8");
        //发送数据
        PrintWriter printWriter = response.getWriter();
        printWriter.write(serverStr);

    }

}

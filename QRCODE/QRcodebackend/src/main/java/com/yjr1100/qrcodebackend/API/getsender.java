package com.yjr1100.qrcodebackend.API;/*
 * @Auther:YJR-1100
 * @Date:2022/1/5 - 01 - 05 - 18:58
 * @Version:1.0
 * @Description:
 *
 */

import com.yjr1100.qrcodebackend.Dao.GoodesDao;
import com.yjr1100.qrcodebackend.Dao.UserDao;
import com.yjr1100.qrcodebackend.domain.User;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@WebServlet("/getsender")
public class getsender extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //获取数据
        JSONObject jsonObject = new JSONObject();
        UserDao userDao = new UserDao();
        List<Map<String, Object>> senderlist= userDao.getsenders();
        System.out.println(senderlist);
        if(senderlist == null){
            jsonObject.put("msg", "暂无快递员");
        }else{
            jsonObject.put("msg", "查询成功");
            jsonObject.put("senders",senderlist);
        }
        String serverStr = URLEncoder.encode(jsonObject.toString(), "UTF-8");
        //发送数据
        PrintWriter printWriter = response.getWriter();
        printWriter.write(serverStr);
    }
}

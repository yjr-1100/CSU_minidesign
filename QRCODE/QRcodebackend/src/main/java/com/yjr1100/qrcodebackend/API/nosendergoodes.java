package com.yjr1100.qrcodebackend.API;/*
 * @Auther:YJR-1100
 * @Date:2022/1/5 - 01 - 05 - 9:36
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
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@WebServlet(name = "nosendergoodes", value = "/nosendergoodes")
public class nosendergoodes extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //获取数据
        JSONObject jsonObject = new JSONObject();
        GoodesDao goodesDao = new GoodesDao();
        List<Map<String, Object>> goodeslist= goodesDao.getnosendergoodes();
        System.out.println(goodeslist);
        if(goodeslist == null){
            jsonObject.put("msg", "暂无分配物品");
        }else{
            jsonObject.put("msg", "查询成功");
            jsonObject.put("goodes",goodeslist);
        }
        String serverStr = URLEncoder.encode(jsonObject.toString(), "UTF-8");
        //发送数据
        PrintWriter printWriter = response.getWriter();
        printWriter.write(serverStr);
    }
}

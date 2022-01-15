package yjr1100.ServletAPI;/*
 * @Auther:YJR-1100
 * @Date:2021/11/26 - 11 - 26 - 16:35
 * @Version:1.0
 * @Description:
 *
 */

import net.sf.json.JSONObject;
import yjr1100.Dao.UserDao;
import yjr1100.domain.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

@WebServlet("/userInfoChangeServlet")
public class userInfoChangeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        Map<String,String[]> map = request.getParameterMap();
        System.out.println(map.size());
        String status = request.getParameter("status");
        JSONObject jsonObject = new JSONObject();
        UserDao userDao = new UserDao();
        if(status.equals("username")){
            String aNew = request.getParameter("new");
            String old = request.getParameter("old");
            System.out.println(status);
            System.out.println(aNew);
            System.out.println(old);
            if(!userDao.isexit(aNew)){
                // 提示用户名已经存在
                jsonObject.put("statu",-1);
                jsonObject.put("msg","用户名已存在");
            }else{
                int result = userDao.resetusername(old, aNew);
                if(result==1){
                    jsonObject.put("statu",1);
                    jsonObject.put("msg","用户名修改成功");
                    User user = userDao.getuser(status, aNew);
                    Cookie cookie = new Cookie("user", URLEncoder.encode(user.toString(),"utf-8"));
                    System.out.println(user.toString());
                    response.addCookie(cookie);
                }else{
                    jsonObject.put("statu",-1);
                    jsonObject.put("msg","修改失败");
                }
            }
        }
        else if(status.equals("mail")){
            String aNew = request.getParameter("new");
            String old = request.getParameter("old");
            String movecode = request.getParameter("movecode");
            System.out.println(status);
            System.out.println(aNew);
            System.out.println(old);
            String randomcode_session =(String) request.getSession().getAttribute("randomcode_session");
            System.out.println("====邮箱修改的验证码=====");
            System.out.println(randomcode_session);
            System.out.println("====邮箱修改的验证码=====");
            if(randomcode_session!=null && randomcode_session.equals(movecode)){
                // 验证码正确，开始修改
                if(!userDao.isexitMail(aNew)){
                    // 提示邮箱已经被绑定已经存在
                    jsonObject.put("statu",-1);
                    jsonObject.put("msg","邮箱已被绑定");
                }else{
                    int result = userDao.resetmail(old, aNew);
                    if(result==1){
                        jsonObject.put("statu",1);
                        jsonObject.put("msg","邮箱修改成功");
                        User user = userDao.getuser(status, aNew);
                        Cookie cookie = new Cookie("user", URLEncoder.encode(user.toString(),"utf-8"));
                        System.out.println(user.toString());
                        response.addCookie(cookie);
                    }else{
                        jsonObject.put("statu",-1);
                        jsonObject.put("msg","修改失败");
                    }
                }
            }else{
                jsonObject.put("statu",-1);
                jsonObject.put("msg","动态码错误");
            }


        }
        else if(status.equals("phonenum")){
            String aNew = request.getParameter("new");
            String old = request.getParameter("old");
            String movecode = request.getParameter("movecode");
            System.out.println(status);
            System.out.println(aNew);
            System.out.println(old);
            String randomcode_session =(String) request.getSession().getAttribute("randomcode_session");
            System.out.println("====手机号修改的验证码=====");
            System.out.println(randomcode_session);
            System.out.println("====手机号修改的验证码=====");
            if(randomcode_session!=null && randomcode_session.equals(movecode)){
                // 验证码正确，开始修改
                if(!userDao.isexitPhone(aNew)){
                    // 提示用户名已经存在
                    jsonObject.put("statu",-1);
                    jsonObject.put("msg","手机号已经绑定");
                }else{
                    int result = userDao.resetphonenum(old, aNew);
                    if(result==1){
                        jsonObject.put("statu",1);
                        jsonObject.put("msg","手机号修改成功");
                        User user = userDao.getuser(status, aNew);
                        Cookie cookie = new Cookie("user", URLEncoder.encode(user.toString(),"utf-8"));
                        System.out.println(user.toString());
                        response.addCookie(cookie);
                    }else{
                        jsonObject.put("statu",-1);
                        jsonObject.put("msg","修改失败");
                    }
                }
            }else{
                jsonObject.put("statu",-1);
                jsonObject.put("msg","动态码错误");
            }


        }
        else if(status.equals("password")){
            String aNew = request.getParameter("new");
            String old = request.getParameter("old");
            String username = request.getParameter("movecode");
            System.out.println(status);
            System.out.println(username);
            System.out.println(aNew);
            System.out.println(old);
            User rightuser = userDao.checkpwd_uname(username, old);
            if(rightuser == null){
                jsonObject.put("statu",-1);
                jsonObject.put("msg","密码错误");
            }else{
                int result = userDao.resetpassword(username, aNew);
                if(result==1){
                    jsonObject.put("statu",1);
                    jsonObject.put("msg","密码修改成功");
                    User user = userDao.getuser(status, aNew);
                    Cookie cookie = new Cookie("user", URLEncoder.encode(user.toString(),"utf-8"));
                    System.out.println(user.toString());
                    response.addCookie(cookie);
                }else{
                    jsonObject.put("statu",-1);
                    jsonObject.put("msg","修改失败");
                }
            }
        }
        response.getWriter().println(jsonObject.toString());

    }
}

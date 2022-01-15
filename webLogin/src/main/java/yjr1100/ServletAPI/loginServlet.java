package yjr1100.ServletAPI;/*
 * @Auther:YJR-1100
 * @Date:2021/11/24 - 11 - 24 - 0:29
 * @Version:1.0
 * @Description:
 *
 */

import java.net.URLEncoder;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import yjr1100.Dao.UserDao;
import yjr1100.domain.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet( "/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        Map<String,String[]> map = request.getParameterMap();
        System.out.println(map.size());
        if(map.size()==2){
            String phonecode = request.getParameter("phonecode");
            String randomcodeinput = request.getParameter("randomcode");
            String randomcode_session =(String) request.getSession().getAttribute("randomcode_session");

            if(randomcode_session!=null && randomcode_session.equals(randomcodeinput)){
                //登录成功
                User user = null;
                if(phonecode.contains("@")){
                    user = new UserDao().ifbind(phonecode,"mail");
                    System.out.println(user);
                }else{
                    user = new UserDao().ifbind(phonecode,"phonenum");
                    System.out.println(user);
                }

                if(user ==null){
                    //没有绑定账号的用户，跳去绑定账号
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("statu",-1);
                    jsonObject.put("msg","用户没有绑定");
                    response.getWriter().println(jsonObject.toString());
                }else{
                    //绑定了账号的user，跳去成功页面
                    JSONObject jsonObject = new JSONObject();
                    Cookie cookie = new Cookie("user", URLEncoder.encode(user.toString(),"utf-8"));
                    response.addCookie(cookie);
                    jsonObject.put("statu",1);
                    jsonObject.put("msg","用户已经绑定");
                    response.getWriter().println(jsonObject.toString());
                }

            }else{
                // 登录失败
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("statu",0);
                jsonObject.put("msg","动态码无效");
                response.getWriter().println(jsonObject.toString());
            }
        }
        else{
            if(request.getParameter("username").equals("")|request.getParameter("password").equals("")){
                request.setAttribute("errmsg","请输入用户名和密码!");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }else{
                User loginuser = new User();
                try {
                    BeanUtils.populate(loginuser,map);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                UserDao userDao = new UserDao();
                User user = userDao.login(loginuser);
                request.setAttribute("loginuser",loginuser);
                System.out.println(request.getContextPath());

                if(user == null){
                    request.setAttribute("errmsg","用户名或密码错误!");
                    request.getRequestDispatcher("/login.jsp").forward(request,response);
                }else{
                    request.setAttribute("user",user);
                    Cookie cookie = new Cookie("user", URLEncoder.encode(user.toString(),"utf-8"));
                    System.out.println(user.toString());
                    response.addCookie(cookie);
                    response.sendRedirect(request.getContextPath()+"/index.jsp");
                }
            }

        }

    }
}

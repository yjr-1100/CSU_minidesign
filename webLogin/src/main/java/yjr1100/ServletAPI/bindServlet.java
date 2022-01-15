package yjr1100.ServletAPI;/*
 * @Auther:YJR-1100
 * @Date:2021/11/28 - 11 - 28 - 8:19
 * @Version:1.0
 * @Description:
 *
 */

import yjr1100.Dao.UserDao;
import yjr1100.domain.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

@WebServlet("/bindServlet")
public class bindServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String number = request.getParameter("inputnum");
        Map<String,String[]> map = request.getParameterMap();
        for (String key : map.keySet()) {
            System.out.println(key);
        }
// 打印值集合
        for (String[] value : map.values()) {
            System.out.println(value);
        }
        System.out.println(username);
        System.out.println(password);
        System.out.println(repassword);
        System.out.println(number);
        if(username.equals("")||password.equals("")||repassword.equals("")||number==null){
            request.setAttribute("errmsg","请输入完整的注册信息");
            request.setAttribute("num",number);
            request.getRequestDispatcher("/userbind.jsp?").forward(request,response);
        }else{
            UserDao userDao = new UserDao();
            if(!userDao.isexit(username)){
                // 提示用户名已经存在
                request.setAttribute("errmsg","用户名已存在");
                request.setAttribute("num",number);
                request.getRequestDispatcher("/userbind.jsp").forward(request,response);
            }else{
                User user = new User();
                if(number.contains("@")){
                    user.setMail(number);
                }else{
                    user.setPhonenum(number);
                }
                user.setUsername(username);
                user.setPassword(password);
                System.out.println(user);
                int statues = userDao.register(user);
                if(statues ==1){
                    request.setAttribute("user",user);
                    Cookie cookie = new Cookie("user", URLEncoder.encode(user.toString(),"utf-8"));
                    System.out.println(user.toString());
                    response.setContentType("text/html;charset=UTF-8");
                    response.addCookie(cookie);
                    response.sendRedirect(request.getContextPath()+"/index.jsp?status=绑定成功");
                }else{
                    request.setAttribute("errmsg","注册失败请重试");
                    request.setAttribute("num",number);
                    request.getRequestDispatcher("/userbind.jsp").forward(request,response);
                }
            }


        }
    }
}

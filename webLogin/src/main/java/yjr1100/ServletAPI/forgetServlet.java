package yjr1100.ServletAPI;/*
 * @Auther:YJR-1100
 * @Date:2021/11/25 - 11 - 25 - 18:12
 * @Version:1.0
 * @Description:
 *
 */

import net.sf.json.JSONObject;
import yjr1100.Dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/forgetServlet")
public class forgetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String,String[]> map = request.getParameterMap();
        String username = request.getParameter("username");
        String num = request.getParameter("num");
        System.out.println(username);
        System.out.println(username);
        if(username.equals("")||num.equals("")){
            request.setAttribute("errmsg","请输入完整的用户信息");
            request.getRequestDispatcher("/register.jsp?username="+"username").forward(request,response);
        }else{
            UserDao userDao = new UserDao();
            String status;
            if(num.contains("@")){
                status="mail";
            }else{
                status = "phonenum";
            }
            JSONObject jsonObject = new JSONObject();

            Boolean isnamewithnumcorrect = userDao.isnamewithnumcorrect(username, status, num);
            if(isnamewithnumcorrect){
                // 是绑定的东西
                jsonObject.put("statu",1);
            }else{
                // 不是绑定的对应的手机号
                jsonObject.put("statu",-1);
            }

            response.getWriter().println(jsonObject.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String,String[]> map = request.getParameterMap();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String number = request.getParameter("number");
        String checkcode = request.getParameter("checkcode");
        System.out.println(checkcode);
        // 拿到session中的动态验证码
        String randomcode_session =(String) request.getSession().getAttribute("randomcode_session");
        // 检查参数完整性
        if(username.equals("")||password.equals("")||repassword.equals("")||number.equals("")||checkcode.equals("")){
            request.setAttribute("errmsg","请输入完整的注册信息");
            request.getRequestDispatcher("/register.jsp?username="+username).forward(request,response);
        }else{
            UserDao userDao = new UserDao();
            int resetpassword = userDao.resetpassword(username, password);
            if(resetpassword ==1){
                request.getRequestDispatcher("/login.jsp?status=密码重置成功请登录").forward(request,response);
            }else{
                request.getRequestDispatcher("/forget.jsp?username="+username+"&msg="+"密码重置失败").forward(request,response);
            }
        }
    }
}

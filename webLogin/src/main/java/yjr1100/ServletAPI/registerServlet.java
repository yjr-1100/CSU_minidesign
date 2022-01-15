package yjr1100.ServletAPI;/*
 * @Auther:YJR-1100
 * @Date:2021/11/24 - 11 - 24 - 0:34
 * @Version:1.0
 * @Description:
 *
 */

import org.apache.commons.beanutils.BeanUtils;
import yjr1100.Dao.UserDao;
import yjr1100.domain.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        System.out.println("----------------");
        System.out.println(randomcode_session);
        System.out.println("----------------");
        // 检查参数完整性
        if(username.equals("")||password.equals("")||repassword.equals("")||number.equals("")||checkcode.equals("")){
            request.setAttribute("errmsg","请输入完整的注册信息");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }
        else{
            UserDao userDao = new UserDao();
            if(!userDao.isexit(username)){
                // 提示用户名已经存在
                request.setAttribute("errmsg","用户名已存在");
                request.getRequestDispatcher("/register.jsp").forward(request,response);
            }else{
                if(number.length()==11&& !userDao.isexitPhone(number)){
                    // 提示手机号已经注册
                    request.setAttribute("errmsg","手机号已经注册");
                    request.getRequestDispatcher("/register.jsp").forward(request,response);
                }else if(!userDao.isexitMail(number)){
                    // 提示邮箱已经注册
                    request.setAttribute("errmsg","邮箱已经注册");
                    request.getRequestDispatcher("/register.jsp").forward(request,response);
                }else{
                    // 检验验证码的正确性
                    if(randomcode_session!=null&&randomcode_session.equals(checkcode)){
                        //    正确，进行注册
                        User register = new User();
                        register.setUsername(username);register.setPassword(password);
                        if(number.contains("@")){
                            register.setMail(number);
                        }else{
                            register.setPhonenum(number);
                        }
                        int statues = userDao.register(register);
                        if(statues ==1){
                            request.getRequestDispatcher("/login.jsp?status=注册成功请登录").forward(request,response);
                        }else{
                            request.setAttribute("errmsg","注册失败请重试");
                            request.getRequestDispatcher("/register.jsp").forward(request,response);
                        }

                    }else{
                        // 错误提示验证码错误
                        request.setAttribute("errmsg","动态码无效");
                        request.getRequestDispatcher("/register.jsp").forward(request,response);
                    }
                }
            }

        }

    }
}

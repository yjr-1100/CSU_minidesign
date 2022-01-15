package yjr1100.ServletAPI;/*
 * @Auther:YJR-1100
 * @Date:2021/11/28 - 11 - 28 - 14:49
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
import java.util.List;

@WebServlet("/managerServlet")
public class managerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        response.setContentType("text/html;charset=UTF-8");
        UserDao userDao = new UserDao();
        JSONObject jsonObject = new JSONObject();
        if(action.equals("list")){
            List queryall = userDao.queryall();
            request.setAttribute("userslist",queryall);
            request.getRequestDispatcher("/manager.jsp?").forward(request,response);
        }else if(action.equals("delete")){
            int result = userDao.rmuser(request.getParameter("username"));
            request.setAttribute("userslist",userDao.queryall());
            if(result ==1){
                request.getRequestDispatcher("/manager.jsp?result=1").forward(request,response);
            }else{
                request.getRequestDispatcher("/manager.jsp?result=0").forward(request,response);
            }
        }
        else if(action.equals("userchange")){
            String oldname = request.getParameter("oldusername");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String phonecode = request.getParameter("phonecode");
            String mail = request.getParameter("mail");
            User olduser = userDao.getuser("username", oldname);
            userDao.rmuser(oldname);
            if(mail!=""&&!userDao.isexitMail(mail)){
                userDao.register(olduser);
                jsonObject.put("statu",-1);
                jsonObject.put("msg","该邮箱已被绑定");
                response.getWriter().println(jsonObject.toString());
            }else if(phonecode!=""&&!userDao.isexitPhone(phonecode)){
                userDao.register(olduser);
                jsonObject.put("statu",-1);
                jsonObject.put("msg","该手机号已被绑定");
                response.getWriter().println(jsonObject.toString());
            }else if(username!=""&&!userDao.isexit(username)){
                userDao.register(olduser);
                jsonObject.put("statu",-1);
                jsonObject.put("msg","用户名已存在");
                response.getWriter().println(jsonObject.toString());
            }else{
                User user = new User();
                user.setPassword(password);user.setUsername(username);
                if(!mail.equals("")){user.setMail(mail);}
                if(!phonecode.equals("")){user.setPhonenum(phonecode);}
                int result = userDao.register(user);
                if(result==1){
                    jsonObject.put("statu",1);
                    jsonObject.put("msg","修改成功");
                    response.getWriter().println(jsonObject.toString());
                }else{
                    jsonObject.put("statu",-1);
                    jsonObject.put("msg","修改失败");
                    response.getWriter().println(jsonObject.toString());
                }
            }
        }
        else if(action.equals("useradd")){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String phonecode = request.getParameter("phonecode");
            String mail = request.getParameter("mail");
            if(username.equals("")||password.equals("")){
                jsonObject.put("statu",-1);
                jsonObject.put("msg","请输入用户名和密码");
                response.getWriter().println(jsonObject.toString());
            }else{
                if(!mail.equals("")&&!userDao.isexitMail(mail)){
                    jsonObject.put("statu",-1);
                    jsonObject.put("msg","该邮箱已被绑定");
                    response.getWriter().println(jsonObject.toString());
                }
                else if(!phonecode.equals("")&&!userDao.isexitPhone(phonecode)){
                    jsonObject.put("statu",-1);
                    jsonObject.put("msg","该手机号已被绑定");
                    response.getWriter().println(jsonObject.toString());
                }
                else if(!username.equals("")&&!userDao.isexit(username)){
                    jsonObject.put("statu",-1);
                    jsonObject.put("msg","用户名已存在");
                    response.getWriter().println(jsonObject.toString());
                }
                else{
                    User user = new User();
                    user.setPassword(password);user.setUsername(username);
                    if(!mail.equals("")){user.setMail(mail);}
                    if(!phonecode.equals("")){user.setPhonenum(phonecode);}

                    int result = userDao.register(user);
                    if(result==1){
                        jsonObject.put("statu",1);
                        jsonObject.put("msg","添加成功");
                        response.getWriter().println(jsonObject.toString());
                    }else{
                        jsonObject.put("statu",-1);
                        jsonObject.put("msg","添加失败");
                        response.getWriter().println(jsonObject.toString());
                    }
                }

            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}

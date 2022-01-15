package yjr1100.ServletAPI;/*
 * @Auther:YJR-1100
 * @Date:2021/11/24 - 11 - 24 - 13:13
 * @Version:1.0
 * @Description:
 *
 */

import net.sf.json.JSONObject;
import yjr1100.utils.MailCode;
import yjr1100.utils.PhoneCode;

import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

@WebServlet("/codesendServlet")
public class codesendServlet extends HttpServlet {
    private String randomcode(){
        Random random = new Random();
        String str = "18253945367680297014";
        // 存储验证码
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            char c = str.charAt(random.nextInt(str.length()));  //随机得到一个字符
            code.append(c);
        }
        return code.toString();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // 生成手机验证码，并存储起来
        String randomcode_session = randomcode();
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(300);
        session.setAttribute("randomcode_session",randomcode_session);

        System.out.println("----手机/邮箱验证码----");
        System.out.println(randomcode_session);
        System.out.println("----手机/邮箱验证码----");
        // 用户输入的图片验证码
        String chekcodeinput = request.getParameter("chekcode");
        //得到存储的图片验证码checkCode_session，并清除
        String checkCode_session = (String) session.getAttribute("checkCode_session");
        session.removeAttribute("checkCode_session");
        JSONObject jsonObject = new JSONObject();
        System.out.println("图片验证码");
        System.out.println(checkCode_session);
        System.out.println("图片验证码");
        if(chekcodeinput.equalsIgnoreCase(checkCode_session)){
            // 验证码正确，发送手机验证码
            if(!request.getParameter("phonecode").contains("@")){ //发送手机验证码
                try {
                    PhoneCode sdcode = new PhoneCode();
                    String rusult = sdcode.sendCode(request.getParameter("phonecode"),request.getParameter("style"),randomcode_session);
                    System.out.println(rusult);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
            //    发送邮箱验证码
                MailCode sdmailCode = new MailCode();
                try {
                    String rusult = sdmailCode.sendMailCode(randomcode_session,request.getParameter("phonecode"));
                    System.out.println(rusult);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            jsonObject.put("statu",1);
            jsonObject.put("msg","验证码正确，动态码发送成功");
        }else{
        //    验证码错误
            jsonObject.put("statu",-1);
            jsonObject.put("msg","验证码错误");
        }
        response.getWriter().println(jsonObject.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

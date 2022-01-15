package yjr1100.utils;/*
 * @Auther:YJR-1100
 * @Date:2021/11/25 - 11 - 25 - 10:18
 * @Version:1.0
 * @Description:
 *
 *//*
 * @ClassName MailCode
 * @Description TODO
 * @Author YJR-1100
 * @Date 2021/11/25 10:18
 * @Version 1.0
 * 发送邮箱验证码
 */

import javax.mail.*;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailCode {
    static Boolean mark = false;
    static Properties prop = null;
    // Session对象管理客户端与邮件服务器的连接会话
    static Session session_send=null;
    static Transport ts_send = null;
    static MimeMessage mimeMessage = null;

    static {
        //创建参数配置, 用于连接邮件服务器的参数配置
        prop = new Properties();
        prop.setProperty("mail.host", "smtp.163.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        //1、创建session  Session对象管理客户端与邮件服务器的连接会话,getInstance获取的都是新对象。
        session_send = Session.getInstance(prop);
        session_send.setDebug(true);//开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        //2、通过session得到transport对象 Transport对象控制邮件的发送，具体过程是：连接服务器->发送邮件->关闭连接
        //创建邮件对象
        mimeMessage = new MimeMessage(session_send);

        //邮件发送人
        try {
            mimeMessage.setFrom(new InternetAddress("y1192167274@163.com"));
        } catch (MessagingException e) {
            e.printStackTrace();
        }



        //邮件标题
        try {
            mimeMessage.setSubject("验证码");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        try {
            ts_send = session_send.getTransport();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }

        try {
            ts_send.connect("smtp.163.com", "y1192167274@163.com", "XKYTGILWKSSUQFFA");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    /*
     * @Author YJR-1100
     * @Description //TODO
     * @Date 21:40
     * @param checkcode 要发送的验证码
     * @return 发送邮箱验证码，返回返送的状态，json格式字符串
     **/
    public String sendMailCode (String checkcode,String addres){
        //邮件接收人
        try {
            mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(addres));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        if(ts_send.isConnected()){
            //邮件内容
            try {
                mimeMessage.setContent("您通过邮箱获得验证码："+checkcode+"，验证码5分钟内有效","text/html;charset=UTF-8");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            //发送邮件
            try {
                ts_send.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            try {
                ts_send.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }else{
            try {
                ts_send.connect("smtp.163.com", "y1192167274@163.com", "XKYTGILWKSSUQFFA");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            //邮件内容
            try {
                mimeMessage.setContent(checkcode,"text/html;charset=UTF-8");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            //发送邮件
            try {
                ts_send.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            try {
                ts_send.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        return "1";
    }



}

import javax.mail.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.mail.Session;


public class Login {
    String count = null;
    String pwd = null;
    Boolean mark = false;
    Properties prop = null;
    // Session对象管理客户端与邮件服务器的连接会话
    Session session_send=null;
    Transport ts_send = null;

    JDialog logjf = null;
    JPanel jp = new JPanel();
    JLabel countLable = new JLabel("用户名");
    JLabel pwdLable = new JLabel("密码");
    JTextField counttext = new JTextField();
    JPasswordField pwdtext = new JPasswordField();
    JButton logbt = new JButton("登录");
    JButton exitbt = new JButton("取消");

    void get_send_session_and_ts() {
        //创建参数配置, 用于连接邮件服务器的参数配置
        prop = new Properties();
        prop.setProperty("mail.host", "smtp.163.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        //1、创建session  Session对象管理客户端与邮件服务器的连接会话,getInstance获取的都是新对象。
        session_send = Session.getInstance(prop);
        session_send.setDebug(true);//开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        //2、通过session得到transport对象 Transport对象控制邮件的发送，具体过程是：连接服务器->发送邮件->关闭连接
        try {
            ts_send = session_send.getTransport();
        } catch (NoSuchProviderException noSuchProviderException) {
            mark = false;
            noSuchProviderException.printStackTrace();
        }
    }


    Login(JFrame jf) throws MessagingException {
        mark = false;
        logjf = new JDialog(jf);
        logjf.setSize(290,220);
        logjf.setLocationRelativeTo(jf);
        logjf.setModal(true);
        jp.setLayout(null);
        pwdLable.setSize(80,25);
        pwdLable.setLocation(35,75);
        pwdLable.setFont(new Font("微软雅黑",1,16));
        countLable.setSize(80,25);
        countLable.setLocation(35,25);
        countLable.setFont(new Font("微软雅黑",1,16));
        counttext.setSize(120,25);
        counttext.setLocation(110,25);
        pwdtext.setSize(120,25);
        pwdtext.setLocation(110,75);

        logbt.setSize(80,30);
        logbt.setLocation(45,120);
        logbt.setFocusPainted(true);
        exitbt.setSize(80,30);
        exitbt.setLocation(145,120);
        get_send_session_and_ts();

        logbt.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                count = counttext.getText();
                pwd = pwdtext.getText();
                if(count.equals("")){
                    JOptionPane.showMessageDialog(null, "用户名不能为空","格式错误",JOptionPane.ERROR_MESSAGE);
                }
                else if(pwd.equals("")){
                    JOptionPane.showMessageDialog(null, "密码不能为空","格式错误",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    //3、使用邮箱的用户名和密码连上邮件服务器
                    try {
                        ts_send.connect("smtp.163.com", count, pwd);
                        mark = true;
                        JOptionPane.showMessageDialog(null,"登录成功", "登录成功", JOptionPane.INFORMATION_MESSAGE);
                        logjf.dispose();
                        ts_send.close();
                    } catch (MessagingException messagingException) {
                        mark=false;
                        counttext.setText("");
                        pwdtext.setText("");
                        JOptionPane.showMessageDialog(null, "用户名或密码错误","登录失败",JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

        exitbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jp.add(counttext);
        jp.add(pwdtext);
        jp.add(pwdLable);
        jp.add(countLable);
        jp.add(logbt);
        jp.add(exitbt);
        logjf.add(jp);

        jp.setVisible(true);
        logjf.setVisible(true);

    }
}



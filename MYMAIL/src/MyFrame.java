import javax.mail.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;


public class MyFrame {
    String sendtitle = null;
    String sendcontent = null;
    String fujianliststring = null;
    ArrayList <String> fujian_lists = new ArrayList<String>();
    String receiveMailAccount=null;
    String myEmailAccount=null;
    String myPwd = null;

    Session session_send=null;
    Transport ts_send = null;
    Session session_receive=null;
    Store store_receive = null;

    Login log = null ;
    JFrame jf = new JFrame("YJR-1100 MAIL");
    JPanel panel =new JPanel();

    JPanel panel1 =new JPanel();
    JPanel panel2 =new JPanel();
    JButton receivemailbt = new JButton("收信箱");
    JButton writemailbt = new JButton("写信");
    JLabel receiveMailAccountlable = new JLabel("收件人：");
    JTextField receiveMailAccounttext = new JTextField();
    JLabel titlelable = new JLabel("   主题：");
    JTextField titletext = new JTextField();
    JLabel contentlable = new JLabel("   内容：");
    JTextArea contenttext = new JTextArea();
    JScrollPane scroll = new JScrollPane();
    JButton fujianbt = new JButton("附件：");
    MyTextArea fujiantext = new MyTextArea();
    ImageIcon img=new ImageIcon("Head1.png");
    JLabel headlable = new JLabel(img);
    JButton sendbt = new JButton("发送");
    JButton send_exitbt = new JButton("取消发送");

    DefaultTableModel tableModel;
    JScrollPane jScrollPane1 = new JScrollPane();
    JTable receive_table;
    String[] columnTitle = {"发件人","主题","时间","删除"};
    Folder folder = null;
    Vector<Vector<String>> datas= new Vector<Vector<String>>();
    Vector<String> title = new Vector<>();
    String [][] data;
    Message[] messages;

    MyFrame() throws Exception {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        jf.setResizable(false);
        jf.setVisible(true);
        jf.setSize(800,710);
        jf.setLocation(300,100);
        log = new Login(jf);
        if(log.mark){
            System.out.println("登录成功");
            myEmailAccount = log.count+"@163.com";
            myPwd = log.pwd;
            session_send = log.session_send;
            ts_send = session_send.getTransport();
            get_receive_session_and_ts();


        }
        log = null;

        panel.setLayout(null);  panel1.setLayout(null);  panel2.setLayout(null);
        panel.setBackground(new Color(245, 249, 252));
        headlable.setLocation(0,0);
        headlable.setSize(800,70);
        writemailbt.setBounds(200,70,200,30);
        writemailbt.setFont(new Font("微软雅黑",1,16));
        writemailbt.setForeground(new Color(34,34,34));
        writemailbt.setBackground(new Color(245,249,252));
        writemailbt.setBorderPainted(false);
        writemailbt.setFocusPainted(false);

        receivemailbt.setBounds(0,70,200,30);
        receivemailbt.setFont(new Font("微软雅黑",1,16));
        receivemailbt.setForeground(new Color(34,34,34));
        receivemailbt.setBackground(new Color(217, 231, 242));
        receivemailbt.setFocusPainted(false);
        receivemailbt.setBorderPainted(false);
        writemailbt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                writemailbt.setBackground(new Color(217, 231, 242));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                writemailbt.setBackground(new Color(245,249,252));
            }
        });
        receivemailbt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                receivemailbt.setBackground(new Color(217, 231, 242));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                receivemailbt.setBackground(new Color(245,249,252));

            }
        });

        panel2.setLocation(0,100);
        panel2.setSize(800,600);
        panel2.setBackground(new Color(245, 247, 250));
        receiveMailAccounttext.setSize(650,30);
        receiveMailAccounttext.setLocation(100,20);
        receiveMailAccounttext.setFont(new Font("微软雅黑",0,16));
        receiveMailAccountlable.setSize(65,30);
        receiveMailAccountlable.setLocation(25,20);
        receiveMailAccountlable.setFont(new Font("微软雅黑",1,16));
        titletext.setSize(650,30);
        titletext.setLocation(100,60);
        titletext.setFont(new Font("微软雅黑",0,16));
        titlelable.setSize(65,30);
        titlelable.setLocation(25,60);
        titlelable.setFont(new Font("微软雅黑",1,16));
        contenttext.setSize(650,350);
        contenttext.setLocation(100,100);
        contenttext.setFont(new Font("微软雅黑",0,15));
        contenttext.setLineWrap(true);
        scroll.setBounds(100,100,650,350);
        scroll.setViewportView(contenttext);
        contentlable.setSize(65,30);
        contentlable.setLocation(25,100);
        contentlable.setFont(new Font("微软雅黑",1,16));
        fujiantext.setSize(650,50);
        fujiantext.setLocation(100,460);
        fujiantext.setFont(new Font("微软雅黑",0,13));
        fujiantext.setEditable(false);
        fujiantext.setBorder(new LineBorder(new java.awt.Color(187, 215, 238), 1, false));
        fujianbt.setSize(85,30);
        fujianbt.setLocation(20,460);
        fujianbt.setFont(new Font("微软雅黑",1,16));
        fujianbt.setContentAreaFilled(false);  //消除按钮背景颜色
        fujianbt.setBorderPainted(false);
        fujianbt.setFocusPainted(false);//出去突起
        fujianbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new File("."));//设置默认显示为当前文件夹
                fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);//设置选择模式（只选文件、只选文件夹、文件和文件均可选）
                fc.setMultiSelectionEnabled(true);//是否允许多选
                fc.addChoosableFileFilter(new FileNameExtensionFilter("zip(*.zip, *.rar)", "zip", "rar"));//文件过滤器
                fc.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png, *.gif)", "jpg", "png", "gif"));
                int result = fc.showOpenDialog(null);
                if(result == JFileChooser.APPROVE_OPTION)
                {
                    List<File> file_ls = Arrays.asList(fc.getSelectedFiles());//获取打开的文件
                    for(File file:file_ls)
                        fujiantext.append(file.getAbsolutePath()+"\n");
                }
            }
        });
        sendbt.setSize(100,37);
        sendbt.setLocation(240,520);
        sendbt.setFont(new Font("微软雅黑",1,16));
        sendbt.setBackground(new Color(64, 161, 42));
        sendbt.setForeground(new Color(255,255,255));
        send_exitbt.setSize(100,37);
        send_exitbt.setLocation(440,520);
        send_exitbt.setFont(new Font("微软雅黑",1,16));
        send_exitbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contenttext.setText("");
                fujiantext.setText("");
                receiveMailAccounttext.setText("");
                titletext.setText("");
                fujian_lists.clear();

            }
        });
        sendbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receiveMailAccount = receiveMailAccounttext.getText();
                sendtitle = titletext.getText();
                sendcontent = contenttext.getText();
                fujianliststring = fujiantext.getText();
                if(!fujianliststring.equals("")){
                    fujian_lists = new ArrayList(Arrays.asList(fujianliststring.split("\n")));
                }
                System.out.println(fujian_lists.size());

                try {
                    Message message = createMimeMessage(session_send,myEmailAccount,receiveMailAccount,sendtitle,sendcontent,fujian_lists);
                    //5、发送邮件
                    if(ts_send.isConnected()){
                        ts_send.sendMessage(message, message.getAllRecipients());
                        ts_send.close();
                    }else{
                        ts_send.connect("smtp.163.com", myEmailAccount, myPwd);
                        ts_send.sendMessage(message, message.getAllRecipients());
                        ts_send.close();
                    }
                    fujian_lists.clear();
                    contenttext.setText("");
                    fujiantext.setText("");
                    receiveMailAccounttext.setText("");
                    titletext.setText("");
                } catch (Exception exception) {
                    exception.printStackTrace();
//                    System.out.println("kkkkkkkkkk");
                    fujian_lists.clear();
                    contenttext.setText("");
                    fujiantext.setText("");
                    receiveMailAccounttext.setText("");
                    titletext.setText("");
                }


            }
        });
        panel2.add(receiveMailAccountlable);
        panel2.add(receiveMailAccounttext);
        panel2.add(titlelable);
        panel2.add(titletext);
        panel2.add(contentlable);
        panel2.add(fujianbt);
        panel2.add(fujiantext);
        panel2.add(send_exitbt);
        panel2.add(sendbt);
        panel2.add(scroll);


        panel1.setLocation(0,100);
        panel1.setSize(800,600);
        panel1.setBackground(new Color(245,249,252));

        jScrollPane1.setBounds(0,5,790,600);
        jScrollPane1.setBackground(new Color(21,3,123));
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        data= new String[][]{
                {"sd", "qwqwe", "werrgjsldkdfj","删除"},
                {"er", "w8v", "qwelkewjfl", "删除"},
                {"sdf", "eifj", "ldfje", "删除"}
        };
        messages = folder.getMessages();
        for(int i = 0;i<columnTitle.length;i++){
            title.add(columnTitle[i]);
        }
        parseMessage(messages);
        System.out.println(data.length);
        for(int i=0;i<data.length;i++){
            Vector<String> str= new Vector<>();
            str.add(data[i][0]);
            str.add(data[i][1]);
            str.add(data[i][2]);
            str.add(data[i][3]);
            datas.add(str);
        }
        tableModel = new DefaultTableModel(datas, title);
        receive_table = new JTable(tableModel);
        receive_table.getColumnModel().getColumn(0).setPreferredWidth(200);
        receive_table.getColumnModel().getColumn(1).setPreferredWidth(400);
        receive_table.getColumnModel().getColumn(2).setPreferredWidth(140);
        receive_table.getColumnModel().getColumn(3).setPreferredWidth(50);
        JTableHeader tab_header = receive_table.getTableHeader();					//获取表头
        tab_header.setFont(new Font("宋体", 1, 16));

        tab_header.setBackground(new Color(12,12,12));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 25));	//修改表头的高度
        receive_table.setSelectionBackground(new Color(217, 229, 239));
        receive_table.setSelectionForeground(new Color(12,12,12));
        receive_table.setBackground(new Color(245, 249, 252));
//        receive_table.setShowVerticalLines(false);
        //表头剧中
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) receive_table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(renderer.CENTER);
        //删除那一列居中
        DefaultTableCellRenderer  r  =  new  DefaultTableCellRenderer();
        r.setHorizontalAlignment(JTextField.CENTER);
        receive_table.getColumn("删除").setCellRenderer(r);
        receive_table.setFont(new Font("宋体", 0, 14));
        receive_table.setRowHeight(25);
        receive_table.getTableHeader().setReorderingAllowed(false); //列不能被拖动
        receive_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        receive_table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e){
                int row= receive_table.getSelectedRow();
                int col= receive_table.getSelectedColumn();
                if(e.getValueIsAdjusting()==true){
                    if(col==3){ //鼠标按下和放开都有反应
                        System.out.println("删除");
                        System.out.println(row);
                        try {
                            if(folder.isOpen()){
                                messages[row].setFlag(Flags.Flag.DELETED, true);
                                datas.remove(row);
                                receive_table.updateUI();
                                folder.close(true);
                            }else{
                                store_receive.connect(myEmailAccount, myPwd);
                                // 获得收件箱
                                folder = store_receive.getFolder("INBOX");
                                // 打开收件箱
                                folder.open(Folder.READ_WRITE);	//打开收件箱 Folder.READ_WRITE：可读可写（可以修改邮件的状态）
                                messages=folder.getMessages();
                                messages[row].setFlag(Flags.Flag.DELETED, true);
                                datas.remove(row);
                                receive_table.updateUI();
                                folder.close(true);
                            }
                        } catch (MessagingException messagingException) {
                            messagingException.printStackTrace();
                        }
                    }
                    else{
                        if(!folder.isOpen()){
                            try {
                                folder = store_receive.getFolder("INBOX");
                            } catch (MessagingException messagingException) {
                                messagingException.printStackTrace();
                            }
                            // 打开收件箱
                            try {
                                folder.open(Folder.READ_WRITE);	//打开收件箱 Folder.READ_WRITE：可读可写（可以修改邮件的状态）
                            } catch (MessagingException messagingException) {
                                messagingException.printStackTrace();
                            }
                            try {
                                messages=folder.getMessages();
                            } catch (MessagingException messagingException) {
                                messagingException.printStackTrace();
                            }

                        }
                        JFrame detailjf = new JFrame("邮件详情");
                        JPanel panel1 = new JPanel();
                        JLabel title = new JLabel();
                        JLabel receiveperson = new JLabel("收件人:");
                        JLabel receivepersontext = new JLabel();
                        JLabel sendperson = new JLabel("发件人:");
                        JLabel sendpersontext = new JLabel();
                        JLabel receivetime = new JLabel("时   间:");
                        JLabel receivetimetext = new JLabel();
                        JLabel fujian = new JLabel("附   件:");
                        JLabel fujiantext = new JLabel();
                        JTextArea detailtext = new JTextArea();
                        JScrollPane detailscroll = new JScrollPane();
                        detailjf.setBounds(200,200,600,500);
                        panel1.setLayout(null);
                        panel1.setBackground(new Color(245,247,250));
                        title.setBounds(20,15,100,15);
                        title.setText(data[row][1]);
                        title.setFont(new Font("微软雅黑",1,15));
                        title.setForeground(new Color(34,34,34));
                        title.setBackground(new Color(12,32,46));
                        sendperson.setBounds(20,45,40,15);
                        sendperson.setFont(new Font("微软雅黑",0,12));
                        sendperson.setForeground(new Color(153,153,153));
                        sendpersontext.setBounds(70,45,400,15);
                        sendpersontext.setForeground(new Color(51,51,51));
                        sendpersontext.setFont(new Font("微软雅黑",0,12));
                        sendpersontext.setText(data[row][0]);
                        receiveperson.setBounds(20,70,40,15);
                        receiveperson.setFont(new Font("微软雅黑",0,12));
                        receiveperson.setForeground(new Color(153,153,153));
                        receivepersontext.setBounds(70,70,400,15);
                        receivepersontext.setForeground(new Color(51,51,51));
                        receivepersontext.setFont(new Font("微软雅黑",0,12));
                        receivepersontext.setText("y1192167274");
                        receivetime.setBounds(20,95,40,15);
                        receivetime.setFont(new Font("微软雅黑",0,12));
                        receivetime.setForeground(new Color(153,153,153));
                        receivetimetext.setBounds(70,95,400,15);
                        receivetimetext.setForeground(new Color(153,153,153));
                        receivetimetext.setFont(new Font("微软雅黑",0,12));
                        receivetimetext.setText(data[row][2]);
                        fujian.setBounds(20,118,40,15);
                        fujian.setFont(new Font("微软雅黑",0,12));
                        fujian.setForeground(new Color(153,153,153));
                        boolean mark = false;
                        try {
                            mark = isContainAttachment(messages[row]);
                        } catch (MessagingException messagingException) {
                            messagingException.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        fujiantext.setBounds(70,118,400,15);
                        fujiantext.setForeground(new Color(85,85,85));
                        fujiantext.setFont(new Font("微软雅黑",0,12));
                        if(mark){
                            JButton downlod = new JButton("下载附件");
                            downlod.setBounds(100,118,100,15);
                            downlod.setFont(new Font("微软雅黑",0,12));
                            downlod.setForeground(new Color(15,96,153));
                            downlod.setBorderPainted(false);
                            downlod.setFocusPainted(false);
                            downlod.setContentAreaFilled(false);
                            downlod.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseEntered(java.awt.event.MouseEvent e) {
                                    downlod.setBackground(new Color(15,96,153));
                                    downlod.setForeground(new Color(255,255,255));
                                    downlod.setContentAreaFilled(true);
                                }
                                @Override
                                public void mouseExited(MouseEvent e) {
                                    downlod.setForeground(new Color(15,96,153));
                                    downlod.setContentAreaFilled(false);
                                }
                            });
                            fujiantext.setText("");
                            downlod.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String path = "";
                                    JFileChooser fc = new JFileChooser();
                                    fc.setCurrentDirectory(new File("."));//设置默认显示为当前文件夹
                                    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//只选择文件夹
                                    fc.setMultiSelectionEnabled(false);//是否允许多选
                                    int result = fc.showOpenDialog(null);
                                    if(result == JFileChooser.APPROVE_OPTION)
                                    {
                                        List<File> file_ls = Arrays.asList(fc.getSelectedFiles());//获取打开的文件
                                        for(File file:file_ls)
                                            path = file.getAbsolutePath();
                                    }
                                    System.out.println(path);
                                    try {
                                        saveAttachment(messages[row],path);
                                        JOptionPane.showMessageDialog(null,"下载成功", "下载成功", JOptionPane.INFORMATION_MESSAGE);
                                    } catch (MessagingException messagingException) {
                                        messagingException.printStackTrace();
                                        JOptionPane.showMessageDialog(null, "下载失败，没有连接到服务器","下载失败",JOptionPane.ERROR_MESSAGE);
                                    } catch (IOException ioException) {
//                                    ioException.printStackTrace();
                                        JOptionPane.showMessageDialog(null, "下载失败","下载失败",JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            });
                            panel1.add(downlod);

                        }else{
                            fujiantext.setText("无附件");
                        }
                        detailtext.setBounds(0,140,600,320);
                        detailtext.setForeground(new Color(85,85,85));
                        detailtext.setFont(new Font("微软雅黑",0,14));
                        detailtext.setEditable(false);
                        detailtext.setMargin(new Insets(20,15,0,15));
                        detailtext.setLineWrap(true);
                        StringBuffer content = new StringBuffer(30);
                        try {
                            getMailTextContent(messages[row], content);
                        } catch (MessagingException messagingException) {
                            messagingException.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        detailtext.setText(content.toString());

                        detailscroll.setBounds(0,140,587,320);
                        detailscroll.setViewportView(detailtext);
                        detailscroll.setVerticalScrollBarPolicy(
                                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                        panel1.add(title);
                        panel1.add(receiveperson);
                        panel1.add(sendperson);
                        panel1.add(sendpersontext);
                        panel1.add(receiveperson);
                        panel1.add(receivepersontext);
                        panel1.add(receivetime);
                        panel1.add(receivetimetext);
                        panel1.add(fujian);
                        panel1.add(fujiantext);
                        panel1.add(detailscroll);
                        detailjf.add(panel1);
                        detailjf.setVisible(true);
                    }
                }

            }
        });

        jScrollPane1.setViewportView(receive_table);

        panel1.add(jScrollPane1);

        writemailbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel2.setVisible(true);panel1.setVisible(false);
                receivemailbt.setBackground(new Color(245,249,252));
                writemailbt.setBackground(new Color(217,231,242));
            }
        });
        receivemailbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel2.setVisible(false);panel1.setVisible(true);
                writemailbt.setBackground(new Color(245,249,252));
                receivemailbt.setBackground(new Color(217,231,242));
            }
        });


        panel.add(receivemailbt);
        panel.add(writemailbt);
        panel.add(headlable);
        panel.add(panel1);
        panel.add(panel2);
        ////////////////////////
        ///////////////////////
        panel2.setVisible(false);panel1.setVisible(true);
        panel.setVisible(true);
        jf.add(panel);
        jf.setVisible(true);
    }

    void get_receive_session_and_ts() throws MessagingException {
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "pop3");		// 协议
        props.setProperty("mail.pop3.port", "110");				// 端口
        props.setProperty("mail.pop3.host", "pop3.163.com");	// pop3服务器
        // 创建Session实例对象
        session_receive = Session.getInstance(props);
        // 得到store对象
        store_receive = session_receive.getStore("pop3");
        store_receive.connect(myEmailAccount, myPwd);
        // 获得收件箱
        folder = store_receive.getFolder("INBOX");
        // 打开收件箱
        folder.open(Folder.READ_WRITE);	//打开收件箱 Folder.READ_WRITE：可读可写（可以修改邮件的状态）
    }
    //发送邮件
    public static MimeMessage createMimeMessage(Session session, String sendcount, String receivecount,String title,String content,List <String> ls) throws Exception {
        // 1. 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendcount, sendcount.split("@")[0], "UTF-8"));
        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.addRecipient(RecipientType.TO, new InternetAddress(receivecount, receivecount.split("@")[0], "UTF-8"));
        // 4. Subject: 邮件主题
        message.setSubject(title, "UTF-8");
        MimeMultipart mm = new MimeMultipart();

        MimeBodyPart text = new MimeBodyPart();
        //    这里添加图片的方式是将整个图片包含到邮件内容中, 实际上也可以以 http 链接的形式添加网络图片
        text.setContent(content, "text/html;charset=UTF-8");
        mm.addBodyPart(text);
        //创建附件节点
        if(!ls.isEmpty()){
            System.out.println("ksksksksksksksksksk");
            System.out.println("ksksksksksksksksksk");
            System.out.println("ksksksksksksksksksk");
            MimeBodyPart attachment = new MimeBodyPart();
            for(String st:ls){
                System.out.println(st);
                DataHandler dh2 = new DataHandler(new FileDataSource(st));  // 读取本地文件
                attachment.setDataHandler(dh2);			                                    // 将附件数据添加到“节点”
                attachment.setFileName(MimeUtility.encodeText(dh2.getName()));	            // 设置附件的文件名（需要编码）
                mm.addBodyPart(attachment);		// 如果有多个附件，可以创建多个多次添加
            }
        }
        //  设置发件时间
        message.setSentDate(new Date());
        //  设置附件 的关系（合成一个大的混合“节点” / Multipart ）
        mm.setSubType("mixed");			// 混合关系
        //  设置整个邮件的关系（将最终的混合“节点”作为邮件的内容添加到邮件对象）
        message.setContent(mm);
        // 13. 保存上面的所有设置
        message.saveChanges();
        return message;
    }
    //获得邮件主题
    public static String getSubject(MimeMessage msg) throws UnsupportedEncodingException, MessagingException {
        return MimeUtility.decodeText(msg.getSubject());
    }
    //获得邮件发件人
    public static String getFrom(MimeMessage msg) throws MessagingException, UnsupportedEncodingException {
        String from = "";
        Address[] froms = msg.getFrom();
        if (froms.length < 1)
            throw new MessagingException("没有发件人!");
        InternetAddress address = (InternetAddress) froms[0];
        String person = address.getPersonal();
        if (person != null) {
            person = MimeUtility.decodeText(person) + " ";
        } else {
            person = "";
        }
        from = person + "<" + address.getAddress() + ">";
        return from;
    }
    //获取发送时间
    public static String getSentDate(MimeMessage msg, String pattern) throws MessagingException {
        Date receivedDate = msg.getSentDate();
        if (receivedDate == null)  return "";
        if (pattern == null || "".equals(pattern))
            pattern = "yyyy/MM/dd/ HH:mm ";
        return new SimpleDateFormat(pattern).format(receivedDate);
    }
    //获取邮件文本内容
    public static void getMailTextContent(Part part, StringBuffer content) throws MessagingException, IOException {
        //如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断
        boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;
        if (part.isMimeType("text/*") && !isContainTextAttach) {
            content.append(part.getContent().toString());
        } else if (part.isMimeType("message/rfc822")) {
            getMailTextContent((Part)part.getContent(),content);
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                getMailTextContent(bodyPart,content);
            }
        }
    }
    //判断是否包含附件
    public static boolean isContainAttachment(Part part) throws MessagingException, IOException {
        boolean flag = false;
        if (part.isMimeType("multipart/*")) {
            MimeMultipart multipart = (MimeMultipart) part.getContent();
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                String disp = bodyPart.getDisposition();
                if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {
                    flag = true;
                } else if (bodyPart.isMimeType("multipart/*")) {
                    flag = isContainAttachment(bodyPart);
                } else {
                    String contentType = bodyPart.getContentType();
                    if (contentType.indexOf("application") != -1) {
                        flag = true;
                    }

                    if (contentType.indexOf("name") != -1) {
                        flag = true;
                    }
                }

                if (flag) break;
            }
        } else if (part.isMimeType("message/rfc822")) {
            flag = isContainAttachment((Part)part.getContent());
        }
        return flag;
    }
    //下载附件
    public static void saveAttachment(Part part, String destDir) throws UnsupportedEncodingException, MessagingException,
            FileNotFoundException, IOException {
        if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();	//复杂体邮件
            //复杂体邮件包含多个邮件体
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                //获得复杂体邮件中其中一个邮件体
                BodyPart bodyPart = multipart.getBodyPart(i);
                //某一个邮件体也有可能是由多个邮件体组成的复杂体
                String disp = bodyPart.getDisposition();
                if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {
                    InputStream is = bodyPart.getInputStream();
                    saveFile(is, destDir, decodeText(bodyPart.getFileName()));
                } else if (bodyPart.isMimeType("multipart/*")) {
                    saveAttachment(bodyPart,destDir);
                } else {
                    String contentType = bodyPart.getContentType();
                    if (contentType.indexOf("name") != -1 || contentType.indexOf("application") != -1) {
                        saveFile(bodyPart.getInputStream(), destDir, decodeText(bodyPart.getFileName()));
                    }
                }
            }
        } else if (part.isMimeType("message/rfc822")) {
            saveAttachment((Part) part.getContent(),destDir);
        }
    }
    private static void saveFile(InputStream is, String destDir, String fileName)
            throws FileNotFoundException, IOException {
        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(new File(destDir + fileName)));
        int len = -1;
        while ((len = bis.read()) != -1) {
            bos.write(len);
            bos.flush();
        }
        bos.close();
        bis.close();
    }
    public static String decodeText(String encodeText) throws UnsupportedEncodingException {
        if (encodeText == null || "".equals(encodeText)) {
            return "";
        } else {
            return MimeUtility.decodeText(encodeText);
        }
    }
    //解析邮件
    public void parseMessage(Message... messages) throws MessagingException, IOException {
        if (messages == null || messages.length < 1){
            data = new String[][]{
                {" ", " ", " "," "}
            };
            JOptionPane.showMessageDialog(null, "没有信息","收信箱为空",JOptionPane.INFORMATION_MESSAGE);
        }else{
            data= new String [messages.length][4];
            // 解析邮件的一些信息
            for (int i = 0, count = messages.length; i < count; i++) {
                MimeMessage msg = (MimeMessage) messages[i];
                data[i][0] = getFrom(msg);
                data[i][1] = getSubject(msg);
                data[i][2] =  getSentDate(msg, null);
                data[i][3] = "删除";
            }
        }
    }

    public static void main(String[] args) throws Exception {
        MyFrame mf = new MyFrame();

    }
}

package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Startgame {
    public static void main(String[] args) {
//      //创建一个窗体
        JFrame jf = new JFrame();
        //给窗体设置标题
        jf.setTitle("🐍贪吃蛇 by yjr-1100");
        //设置窗体大小,弹出的坐标
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        jf.setBounds((width-770)/2,(height-770)/2,770,775);
        //设置窗口大小不可调
        jf.setResizable(false);
        //创建面板
        GamePanel gp = new GamePanel();
        //清空流式布局
        gp.setLayout(null);
        //关闭游戏
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        //添加面板
        jf.add(gp);
        //设置可见
        jf.setVisible(true);

    }
}

package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel {
    // 定义两个数组，一个放x轴坐标，一个放y轴坐标
    int [] snakeX = new int[500];
    int [] snakeY = new int[500];
    // 定义一个开始的标志
    boolean isStart;
    boolean begain;
    boolean isDie;
    // 定义一个蛇的长度
    int lenth;
    // 定义蛇的走向
    String direction;
    // 食物的X，Y轴坐标
    int foodX;
    int foodY;
    //定时器
    Timer timer;
    // 分数
    int score;
    public void  init_snake(){
        score=0;
        isDie = false;
        begain = false;
        isStart = false;
        String [] direct = {"L","R","U","D","L","R","U","R","U","D","L","R","U","U","L","U","L","R","U","D"};
        direction = direct[new Random().nextInt(20)]; //L 左 R 右  U 上  D 下
        lenth = 3;
        //蛇头的坐标

        snakeX[0] = ((int)(Math.random()*20)+3)*25;
        snakeY[0] = (new Random().nextInt(20)+7)*25;
        if("L".equals(direction)){
            //第一节身体
            snakeX[1] = snakeX[0]+25;
            snakeY[1] = snakeY[0];
            //第二节身子
            snakeX[2] = snakeX[0]+50;
            snakeY[2] = snakeY[0];;
        }
        if("R".equals(direction)){
            //第一节身体
            snakeX[1] = snakeX[0]-25;
            snakeY[1] = snakeY[0];
            //第二节身子
            snakeX[2] = snakeX[0]-50;
            snakeY[2] = snakeY[0];;
        }
        if("U".equals(direction)){
            //第一节身体
            snakeX[1] = snakeX[0];
            snakeY[1] = snakeY[0]+25;
            //第二节身子
            snakeX[2] = snakeX[0];
            snakeY[2] = snakeY[0]+50;;
        }
        if("D".equals(direction)){
            //第一节身体
            snakeX[1] = snakeX[0];
            snakeY[1] = snakeY[0]-25;
            //第二节身子
            snakeX[2] = snakeX[0];
            snakeY[2] = snakeY[0]-50;;
        }
        //初始化食物的坐标
        foodX =  300;
        foodY = 225;
    }


    public GamePanel(){
//        JButton bt1 = new JButton("退 出");
//        JButton bt = new JButton("开始游戏");
//        bt1.setSize(120,50);  bt1.setLocation(100,560);
//        bt.setSize(120,50);  bt.setLocation(100,500);
//        bt1.setFont(new Font("微软雅黑",0,18)); this.add(bt1);
//        bt1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.exit(0);
//            }
//        });
//        //开始游戏
//        bt.setFont(new Font("微软雅黑",0,18));  this.add(bt);
//        bt.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                begain=true;
//                isStart=true;
//                remove(bt);
//                remove(bt1);
//                repaint();
//            }
//        });
        init_snake();
        //当前面板获取焦点
        this.setFocusable(true);
        //加入监听 键盘按键按下
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("lsdjfsdsdfj");
                super.keyPressed(e);
                int keycode = e.getKeyCode();
                if(keycode ==KeyEvent.VK_SPACE){
                    if(isDie){
                        init_snake();
                        isDie=false;
                        isStart = true;
                        begain = true;
                    }else {
                        isStart = !isStart;
                        begain = true;
                        repaint(); //重绘，会去调用 paintBorder 根据开始和暂停会改变提示语
                    }
                }
                if(isStart){
                    if(keycode == KeyEvent.VK_UP){
                        direction="U";
                    }
                    if(keycode == KeyEvent.VK_DOWN){
                        direction="D";
                    }
                    if(keycode == KeyEvent.VK_RIGHT){
                        direction="R";
                    }
                    if(keycode == KeyEvent.VK_LEFT){
                        direction="L";
                    }
                }
            }
        });
        //定时器初始化
        timer = new Timer(100, new ActionListener() {
            /*
            * 100毫秒监听一次，需要监听的动作就写在这个里 */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isStart&&isDie==false){
                    //蛇身子先动，向前一个的位置覆盖，最后动蛇头
                    for(int i=lenth-1;i>0;i--){
                        snakeX[i]=snakeX[i-1];
                        snakeY[i]=snakeY[i-1];
                    }
                    if("R".equals(direction)){
                        snakeX[0]+=25;
                    }
                    if("L".equals(direction)){
                        snakeX[0]-=25;
                    }
                    if("U".equals(direction)){
                        snakeY[0]-=25;
                    }
                    if("D".equals(direction)){
                        snakeY[0]+=25;
                    }
                    //防止超过边界
                    if(snakeX[0]<25)
                        snakeX[0]=700;
                    if(snakeX[0]>700)
                        snakeX[0]=25;
                    if(snakeY[0]<75)
                        snakeY[0]=700;
                    if(snakeY[0]>700)
                        snakeY[0]=75;

                    //检测吃到食物
                    if(foodX==snakeX[0]&&foodY==snakeY[0]){
                        score+=1;
                        //蛇长度+1
                        snakeY[lenth]=snakeY[lenth-1];
                        snakeX[lenth]=snakeX[lenth-1];
                        lenth++;
                        //食物坐标改变
                        foodX =((int)(Math.random()*28)+1)*25 ;//[25,700]
                        foodY =(new Random().nextInt(26)+3)*25;//[75,700]->[3,28]*25
                    }
                    //死亡判断
                    for(int i=1;i<lenth;i++){
                        if(snakeX[i]==snakeX[0]&&snakeY[i]==snakeY[0])
                            isDie = true;
                    }
                    repaint();
                }
            }
        });
        timer.start();
    }
    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);

        this.setBackground(new Color(188, 184, 226));
        // 画头部信息
        // paintIcon四个参数 ： this 当前面板，g指当前画笔，后面是位置坐标
        Images.authorimg.paintIcon(this,g,12,8);
        //画蛇跑的地盘
        g.setColor(new Color(169, 171, 212));
        g.fillRect(25,75,700,650);
        //画初始的蛇
        if("R".equals(direction)){
            Images.headrimg.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        if("L".equals(direction)){
            Images.headlimg.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        if("U".equals(direction)){
            Images.headuimg.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        if("D".equals(direction)){
            Images.headdimg.paintIcon(this,g,snakeX[0],snakeY[0]);
        }

        for(int i = 1;i<lenth;i++){
            Images.bodyimg.paintIcon(this,g,snakeX[i],snakeY[i]);
        }

        //显示分数
        g.setColor(Color.red);
        g.setFont(new Font("微软雅黑",Font.BOLD,30));
        g.drawString(new String(String.valueOf(score)),20,50);

        // 游戏暂停，页面中间提示
        if(isStart == false&&begain==true){
            g.setColor(Color.red);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格继续游戏",230,330);
        }
        if(isStart == false&&begain==false){
            g.setColor(Color.red);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格开始游戏",230,330);
        }

        //画食物
        Images.foodimg.paintIcon(this,g,foodX,foodY);
        //死亡提示
        if(isDie){
            g.setColor(Color.red);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("游戏结束，按下空格重新开始",130,330);

        }



    }
}

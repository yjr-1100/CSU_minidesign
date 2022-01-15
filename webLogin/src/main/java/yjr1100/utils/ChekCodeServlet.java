package yjr1100.utils;/*
 * @Auther:YJR-1100
 * @Date:2021/11/23 - 11 - 23 - 23:33
 * @Version:1.0
 * @Description:
 *
 */

import com.sun.org.apache.bcel.internal.Const;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/ChekCodeServlet")
public class ChekCodeServlet extends HttpServlet {
    private Random random = new Random();

    /*
     * @Author YJR-1100
     * @Description //TODO
     * @Date 23:42
     * @param null
     * @return 随机获取的颜色
     **/
    private  Color getRandomColor(){
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        return new Color(red,green,blue);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 创建缓存图像
        final int width = 80,height = 40;
        //
        BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        // 获取画笔对象
        Graphics g = img.getGraphics();
        // 设置画笔颜色
        g.setColor(Color.white);
    //    填充矩形区域
        g.fillRect(0,0,width,height);
        // 字符集
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz1234567890";
        // 存储验证码
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            char c = str.charAt(random.nextInt(str.length()));  //随机得到一个字符
            code.append(c);
            // 7. 设置字的颜色为随机
            g.setColor(getRandomColor());
            // 8. 设置字体，大小为18。参数：字体，样式，大小
            g.setFont(new Font(Font.DIALOG, Font.BOLD + Font.ITALIC, 19));
            // 9. 将每个字符画到图片，x增加，y不变。
            //参数：画字符，x位置，y位置。把c从字符转成字符串
            g.drawString(c+"", 10 + (i * 15), 25);
        }
        String checkCode_session = code.toString();
        //存入session
        HttpSession session = request.getSession();
        session.setAttribute("checkCode_session",checkCode_session);

        for (int i = 0; i < 8; i++) {
            // 10. 线的位置是随机的，x范围在width之中，y的范围在height之中。
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            // 11. 画8条干扰线，每条线的颜色不同
            g.setColor(getRandomColor());
            g.drawLine(x1, y1, x2, y2);
        }
        ImageIO.write(img,"jpg",response.getOutputStream());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

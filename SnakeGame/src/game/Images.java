package game;

import javax.swing.*;
import java.net.URL;

public class Images {
    /*
    * 把图片路径封装为一个对象*/
    public static URL begainURL = Images.class.getResource("/images/begain.png");// 这个 / 就是指代了一个相对路径
    //将图片封装为程序中的一个对象：
    public static ImageIcon begainimg = new ImageIcon(begainURL);
    public static URL bodyURL = Images.class.getResource("/images/body.png");// 这个 / 就是指代了一个相对路径
    //将图片封装为程序中的一个对象：
    public static ImageIcon bodyimg = new ImageIcon(bodyURL);
    /* 把图片路径封装为一个对象*/
    public static URL headuURL = Images.class.getResource("/images/headu.png");// 这个 / 就是指代了一个相对路径
    //将图片封装为程序中的一个对象：
    public static ImageIcon headuimg = new ImageIcon(headuURL);
    /* 把图片路径封装为一个对象*/
    public static URL headdURL = Images.class.getResource("/images/headd.png");// 这个 / 就是指代了一个相对路径
    //将图片封装为程序中的一个对象：
    public static ImageIcon headdimg = new ImageIcon(headdURL);
    /* 把图片路径封装为一个对象*/
    public static URL headrURL = Images.class.getResource("/images/headr.png");// 这个 / 就是指代了一个相对路径
    //将图片封装为程序中的一个对象：
    public static ImageIcon headrimg = new ImageIcon(headrURL);
    /* 把图片路径封装为一个对象*/
    public static URL headlURL = Images.class.getResource("/images/headl.png");// 这个 / 就是指代了一个相对路径
    //将图片封装为程序中的一个对象：
    public static ImageIcon headlimg = new ImageIcon(headlURL);
    /* 把图片路径封装为一个对象*/
    public static URL foodURL = Images.class.getResource("/images/food.png");// 这个 / 就是指代了一个相对路径
    //将图片封装为程序中的一个对象：
    public static ImageIcon foodimg = new ImageIcon(foodURL);
    /* 把图片路径封装为一个对象*/
    public static URL authorURL = Images.class.getResource("/images/author.png");// 这个 / 就是指代了一个相对路径
    //将图片封装为程序中的一个对象：
    public static ImageIcon authorimg = new ImageIcon(authorURL);



}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Mypanel extends JPanel {
    JButton instubt = new JButton("学生信息录入");
    JButton insourbt = new JButton("课程信息录入");
    JButton inchosebt = new JButton("选课信息录入");
    BufferedWriter stuwriter =null;
    BufferedWriter soursewriter =null;
    BufferedWriter chosewriter =null;
    BufferedReader stureader = null;
    BufferedReader soursereader = null;
    BufferedReader chosereader = null;
    File f1,f2,f3;
    ArrayList<String> studentlist = new ArrayList<>();
    ArrayList<String> sourselist = new ArrayList<>();
    ArrayList<String> chosesourselist = new ArrayList<>();
    public void init_start() throws IOException { //创建读取文件的对象
        f1 = new File("D:\\studay\\FileSystem\\student.txt");
        f2 = new File("D:\\studay\\FileSystem\\sourse.txt");
        f3 = new File("D:\\studay\\FileSystem\\chosesourse.txt");
        stureader = new BufferedReader(new FileReader(f1));
        soursereader = new BufferedReader(new FileReader(f2));
        chosereader = new BufferedReader(new FileReader(f3));
        String str;
        try{
            while ((str = stureader.readLine())!=null){
                studentlist.add(str);
            }
        }catch (Exception e){ }
        try{
            while ((str = soursereader.readLine())!=null){
                sourselist.add(str);
            }
        }catch (Exception e){ }
        try{
            while ((str = chosereader.readLine())!=null){
                chosesourselist.add(str);
            }
        }catch (Exception e){
        }finally {
            chosereader.close();
            stureader.close();
            soursereader.close();
        }

//        for (int i = 0; i < wordlist.size(); i++) {
//            System.out.println(wordlist.get(i));
//        }
        stuwriter =new BufferedWriter(new FileWriter(f1));
        soursewriter =new BufferedWriter(new FileWriter(f2));
        chosewriter =new BufferedWriter(new FileWriter(f3));
    }

    public Mypanel() throws IOException {
        init_start();

    }

}



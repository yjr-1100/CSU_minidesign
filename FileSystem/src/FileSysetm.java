import java.io.*;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.Scanner;

public class FileSysetm{
    public static void main(String[] args) throws IOException {
        BufferedWriter stuwriter =null;
        BufferedWriter soursewriter =null;
        BufferedWriter chosewriter =null;
        BufferedReader stureader = null;
        BufferedReader soursereader = null;
        BufferedReader chosereader = null;
        File f1,f2,f3;
        Student studentlist [] = new Student[50];
        Sourse sourselist [] = new Sourse[50];
        ChoseSourse chosesourselist [] = new ChoseSourse[50];
        f1 = new File("D:\\Users\\YJR\\Desktop\\FileSystem\\student.txt");
        f2 = new File("D:\\Users\\YJR\\Desktop\\FileSystem\\sourse.txt");
        f3 = new File("D:\\Users\\YJR\\Desktop\\FileSystem\\chosesourse.txt");
        stureader = new BufferedReader(new FileReader(f1));
        soursereader = new BufferedReader(new FileReader(f2));
        chosereader = new BufferedReader(new FileReader(f3));
        String str;
        int stunumber=0; //记录数据库中学生的数量
        int soursenumber=0;  //记录课程的数量
        int chosenumber=0;  //记录选课信息的数量
        try{
            while ((str = stureader.readLine())!=null){
                //name=张三,id=8208190407,sex=0,age=20,classes=计科1901
                System.out.println(str);
                String [] stuinfo = null;
                stuinfo = str.split(",");
                Student stunow = new Student();
                stunow.set_name(stuinfo[0].split("=")[1]);
                stunow.set_id(stuinfo[1].split("=")[1]);
                stunow.set_sex(Integer.parseInt(stuinfo[2].split("=")[1]));
                stunow.set_age(Integer.parseInt(stuinfo[3].split("=")[1]));
                stunow.set_class(stuinfo[4].split("=")[1]);
                studentlist[stunumber++]=stunow;
            }
            while ((str = soursereader.readLine())!=null){
                //    课程编号，课程名称，学分
                System.out.println(str);
                String [] sourseinfo = null;
                sourseinfo = str.split(",");
                Sourse soursenow = new Sourse();
                soursenow.setClass_id(sourseinfo[0].split("=")[1]);
                soursenow.setClass_name(sourseinfo[1].split("=")[1]);
                soursenow.setCredit(Integer.parseInt(sourseinfo[2].split("=")[1]));
                sourselist[soursenumber++] = soursenow;
            }
            while ((str = chosereader.readLine())!=null){
                //    学号、课程编号、成绩
                System.out.println(str);
                String [] choseinfo= null;
                choseinfo = str.split(",");
                ChoseSourse chosenow = new ChoseSourse();
                chosenow.setStu_id(choseinfo[0].split("=")[1]);
                chosenow.setClass_id(choseinfo[1].split("=")[1]);
                chosenow.setGrade(Integer.parseInt(choseinfo[2].split("=")[1]));
                chosesourselist[chosenumber++] = chosenow;
            }
            System.out.println(stunumber);
            System.out.println(soursenumber);
            System.out.println(chosenumber);
        }catch (Exception e){
        }finally {
            chosereader.close();
            stureader.close();
            soursereader.close();
        }
        stuwriter =new BufferedWriter(new FileWriter(f1));
        soursewriter =new BufferedWriter(new FileWriter(f2));
        chosewriter =new BufferedWriter(new FileWriter(f3));
        Scanner sc = new Scanner(System.in);
        String option = " ";
        while (true){
            System.out.println("--------------欢迎使用基于文件系统的学籍管理系统-------------");
            System.out.println("|                 1、学生信息录入                       |");
            System.out.println("|                 2、课程信息录入                       |");
            System.out.println("|                 3、选课信息录入                       |");
            System.out.println("|                 4、选课成绩录入                       |");
            System.out.println("|                 5、信息查询                          |");
            System.out.println("|                 6、学生信息删除                       |");
            System.out.println("|                 7、退出                             |");
            System.out.println("请输入你希望进行的操作序号：");
            option = sc.next();
            if(option.equals("1")){
                System.out.println("--------------学生信息录入操作-------------");
                System.out.println("请输入 姓名 学号 性别 年龄 班级 用_分隔 return返回");
                while(true){
                    option = sc.next();
                    if(option.equals("return")){ break ;}
                    else{
//                        name=张三,id=8208190407,sex=0,age=20,classes=计科1901
                        String [] stuinfo = new String[5];
                        stuinfo= option.split("_");
                        Student stunow = new Student();
                        stunow.set_name(stuinfo[0]);
                        stunow.set_id(stuinfo[1]);
                        stunow.set_sex(Integer.parseInt(stuinfo[2]));
                        stunow.set_age(Integer.parseInt(stuinfo[3]));
                        stunow.set_class(stuinfo[4]);
                        studentlist[stunumber++]=stunow;
                    }
                }
            }
            else if(option.equals("2")){
                System.out.println("--------------课程信息录入操作-------------");
                System.out.println("请输入 课程编号 课程名称 学分 用_分隔 return返回");
                while (true){
                    option = sc.next();
                    if(option.equals("return")){ break; }
                    else{
                        //    课程编号，课程名称，学分
                        String [] sourseinfo = new String[3];
                        sourseinfo = option.split("_");
                        Sourse soursenow = new Sourse();
                        soursenow.setClass_id(sourseinfo[0]);
                        soursenow.setClass_name(sourseinfo[1]);
                        soursenow.setCredit(Integer.parseInt(sourseinfo[2]));
                        sourselist[soursenumber++] = soursenow;
                    }
                }
            }
            else if(option.equals("3")){
                System.out.println("--------------选课信息录入操作-------------");
                System.out.println("请输入 学号 课程编号 用_分隔 return返回");
                while (true){
                    option = sc.next();
                    if(option.equals("return")){ break; }
                    else{
                        //    学号、课程编号
                        String [] choseinfo = new String[3];
                        choseinfo = option.split("_");
                        ChoseSourse chosenow = new ChoseSourse();
                        chosenow.setStu_id(choseinfo[0]);
                        chosenow.setClass_id(choseinfo[1]);
//                        chosenow.setGrade(Integer.parseInt(choseinfo[2]));
                        chosesourselist[chosenumber++] = chosenow;
                    }
                }
            }else if(option.equals("4")){
                System.out.println("--------------选课信息录入操作-------------");
                System.out.println("请输入 学号 课程编号 成绩 用_分隔 return返回");
                while (true){
                    option = sc.next();
                    if(option.equals("return")){ break; }
                    else{
                        //    学号、课程编号
                        String [] choseinfo = new String[3];
                        choseinfo = option.split("_");
                        for(int i=0;i<chosenumber;i++){
                            if(chosesourselist[i].getStu_id().equals(choseinfo[0])&&chosesourselist[i].getClass_id().equals(choseinfo[1])){
                                chosesourselist[i].setGrade(Integer.parseInt(choseinfo[2]));
                            }
                        }
                    }
                }

            }
            else if(option.equals("5")){
                while (true){
                    System.out.println("------------------信息查询操作-------------");
                    System.out.println("|                1、学生基本信息查询         |");
                    System.out.println("|                2、课程基本信息查询         |");
                    System.out.println("|                3、学生选课及成绩查询       |");
                    System.out.println("|                4、学生平均成绩查询         |");
                    System.out.println("|                5、返回                   |");
                    option = sc.next();
                    if(option.equals("1")){
                        while (true){
                            System.out.println("请输入学生学号进行查询，输入return返回");
                            option = sc.next();
                            if(option.equals("return")|option.equals("RETURN")){break; }
                            else{
                                for(int i=0;i<stunumber;i++){
                                    if(option.equals(studentlist[i].get_id())){
                                        System.out.println("  姓名     学号    性别   年龄   班级");
                                        System.out.println(" "+studentlist[i].get_name()+" "+studentlist[i].get_id()+"  "+studentlist[i].get_sex()+"  "+studentlist[i].get_age()+" "+studentlist[i].get_class()+"\n");
                                    }
                                }
                            }
                        }
                    }else if(option.equals("2")){
                        while (true){
                            System.out.println("请输入课程编号进行查询，输入return返回");
                            option = sc.next();
                            if(option.equals("return")|option.equals("RETURN")){break; }
                            else{
                                for(int i=0;i<soursenumber;i++){
                                    if(option.equals(sourselist[i].getClass_id())){
                                        System.out.println(" 课程编号   课程名称   学分");
                                        System.out.println("  "+sourselist[i].getClass_id()+"      "+sourselist[i].getClass_name()+"    "+sourselist[i].getCredit()+"\n");
                                    }
                                }
                            }
                        }

                    }else if(option.equals("3")){
                        while (true){
                            System.out.println("请输入学生学号进行查询，输入return返回");
                            option = sc.next();
                            if(option.equals("return")|option.equals("RETURN")){break; }
                            else{
                                for(int i=0;i<stunumber;i++){
                                    if(option.equals(String.valueOf(studentlist[i].get_id()))){
                                        System.out.println("学生姓名："+studentlist[i].get_name());
                                    }
                                }
                                System.out.println("选修课程及成绩: ");
                                for(int i=0;i<chosenumber;i++){
                                    if(option.equals(chosesourselist[i].getStu_id())){//通过学号找到选课记录
                                        for(int j=0;j<soursenumber;j++){
                                            if(chosesourselist[i].getClass_id().equals(sourselist[j].getClass_id())){//通过选课的课程编号找到课程名称
                                                System.out.println(sourselist[j].getClass_name()+"  "+chosesourselist[i].getGrade());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }else if(option.equals("4")){
                        while (true){
                            System.out.println("请输入学生学号进行查询，输入return返回");
                            option = sc.next();
                            if(option.equals("return")|option.equals("RETURN")){break; }
                            else{
                                for(int i=0;i<stunumber;i++){
                                    if(option.equals(String.valueOf(studentlist[i].get_id()))){
                                        System.out.print("学生姓名："+studentlist[i].get_name()+" ");
                                    }
                                }
                                int totoal_credit = 0; //总学分
                                int sum = 0; //总成绩
                                for(int i=0;i<chosenumber;i++){
                                    if(option.equals(chosesourselist[i].getStu_id())){//通过学号找到选课记录
                                        for(int j=0;j<soursenumber;j++){
                                            if(chosesourselist[i].getClass_id().equals(sourselist[j].getClass_id())){//通过选课的课程编号找到学分
                                                totoal_credit += sourselist[j].getCredit();
                                                sum += chosesourselist[i].getGrade()*sourselist[j].getCredit();
                                            }
                                        }
                                    }
                                }
                                System.out.println("平均成绩："+sum/totoal_credit);
                            }
                        }
                    }else{ break; }
                }
            }
            else if(option.equals("6")){
                while (true){
                    System.out.println("请输入要删除的学生学号 return返回");
                    option = sc.next();
                    if(option.equals("return")){ break; }
                    else{
                        for(int i=0;i<stunumber;i++){
                            if(studentlist[i].get_id().equals(option)){
                                studentlist[i] = new Student();
                            }
                        }
                    }
                }
            }
            else if(option.equals("7")){
                for(int i=0;i<stunumber;i++){
                    if(!studentlist[i].get_id().equals("-1")){
                        stuwriter.write("name="+studentlist[i].get_name()+",id="+studentlist[i].get_id()+",sex="+studentlist[i].get_sex()+",age="+studentlist[i].get_age()+",classes="+studentlist[i].get_class()+"\n");
                    }
                }
                for(int i=0;i<soursenumber;i++){
                    soursewriter.write("class_id="+sourselist[i].getClass_id()+",class_name="+sourselist[i].getClass_name()+",credit="+sourselist[i].getCredit()+"\n");
                }
                for(int i=0;i<chosenumber;i++){
                    chosewriter.write("stu_id="+chosesourselist[i].getStu_id()+",class_id="+chosesourselist[i].getClass_id()+",grade="+chosesourselist[i].getGrade()+"\n");
                }
                stuwriter.flush();
                soursewriter.flush();
                chosewriter.flush();
                stuwriter.close();
                soursewriter.close();
                chosewriter.close();
                break;

            }
        }
    }
}

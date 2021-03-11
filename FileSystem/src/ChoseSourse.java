public class ChoseSourse {
//    学号、课程编号、成绩
    private String stu_id;
    private String class_id;
    private int grade;
    public String getStu_id(){return stu_id;}
    public String getClass_id(){return class_id;}
    public int getGrade(){return grade;}
    public void setStu_id(String ID){stu_id = ID;}
    public void setClass_id(String ID){class_id = ID;}
    public void setGrade(int grade1){grade = grade1;}
    public ChoseSourse(){
        stu_id = null;
        class_id = null;
        grade = 0;
    }
}

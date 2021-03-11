public class Sourse {
//    课程编号，课程名称，学分
    private String class_id;
    private String class_name;
    private int credit;
    public String getClass_id(){return class_id;}
    public String getClass_name(){return class_name;}
    public int getCredit(){return credit;}
    public void setClass_id(String ID){class_id=ID;}
    public void setClass_name(String NAME){class_name = NAME;}
    public void setCredit(int credit1){credit = credit1;}
    public Sourse(){
        class_name = null;
        class_id = null;
        credit = 0;
    }
}

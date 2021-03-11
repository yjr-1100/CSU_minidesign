public class Student {
//    学号，姓名，性别，年龄，班级
    private String id;
    private String name;
    private int sex; // 0男 1女
    private int age;
    private String classes;
    public String get_id(){return id;}
    public String get_name(){return name;}
    public int get_sex(){return sex;}
    public int get_age(){return age;}
    public String get_class(){return classes;}
    public void set_id(String ID){id = ID;}
    public void set_name(String NAME){name = NAME;}
    public void set_sex(int SEX){sex = SEX;}
    public void set_age(int AGE){age = AGE;}
    public void set_class(String CLASSES){classes = CLASSES;}

    public Student(){
        id = "-1";
        name = null;
        sex = -1;
        age = -1;
        classes = null;
    }


}

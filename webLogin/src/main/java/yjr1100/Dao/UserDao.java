package yjr1100.Dao;/*
 * @Auther:YJR-1100
 * @Date:2021/11/23 - 11 - 23 - 0:16
 * @Version:1.0
 * @Description:
 *
 *//*
 * @ClassName UserDao
 * @Description TODO
 * @Author YJR-1100
 * @Date 2021/11/23 0:16
 * @Version 1.0
 */

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import yjr1100.domain.User;
import yjr1100.utils.DRUIDUtils;

import java.util.List;
import java.util.Map;

public class UserDao {
    JdbcTemplate template = new JdbcTemplate(DRUIDUtils.getDataSource());

    public User login(User loginUser){
        try {
            String sql = "select * from user where username = ? and password = ?";
            System.out.println(loginUser.getUsername());
            System.out.println(loginUser.getPassword());
            User user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(),loginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    public User getuser(String status,String  data){
        try {
            String sql = "select * from user where "+status+" = ?";
            User user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class), data);
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    public int register(User register){
        String sql = "INSERT INTO user (username,password,phonenum,mail) VALUES(?,?,?,?)";
        int updatestate = template.update(sql, register.getUsername(), register.getPassword(), register.getPhonenum(), register.getMail());
        return updatestate;
    }

    public Boolean isexit(String username){
        String sql1 = "select password from user where username = ?";
        System.out.println(username);
        try{
            Map<String, Object> map = template.queryForMap(sql1, username);
            System.out.println(map.toString());
            return false;
        }catch(Exception e){
            return true;
        }
    }

    public Boolean isnamewithnumcorrect(String username,String status,String num){
        String sql1 = "select username from user where "+status+" = ?";
        try{
            Map<String, Object> map = template.queryForMap(sql1, num);
            System.out.println(map.get("username"));
            if(map.get("username").equals(username)){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            return false;
        }
    }

    public User checkpwd_uname(String username,String password){
        try {
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),
                    username,password);
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int resetpassword(String username,String password){
        String sql = "UPDATE user SET password = ? WHERE username = ?";
        int update = template.update(sql, password, username);
        return update;
    }

    public int resetusername(String old,String aNew){
        String sql = "UPDATE user SET username = ? WHERE username = ?";
        int update = template.update(sql, aNew, old);
        return update;
    }

    public int resetmail(String old,String aNew){
        String sql = "UPDATE user SET mail = ? WHERE username = ?";
        int update = template.update(sql, aNew, old);
        return update;
    }

    public int resetphonenum(String old,String aNew){
        String sql = "UPDATE user SET phonenum = ? WHERE username = ?";
        int update = template.update(sql, aNew, old);
        return update;
    }

    public Boolean isexitPhone(String phonenum){
        String sql1 = "select * from user where phonenum = ?";
        System.out.println(phonenum);
        try{
            Map<String, Object> map = template.queryForMap(sql1, phonenum);
            System.out.println(map.toString());
            return false;
        }catch(Exception e){
            return true;
        }
    }

    public Boolean isexitMail(String mail){
        String sql1 = "select * from user where mail = ?";
        System.out.println(mail);
        try{
            Map<String, Object> map = template.queryForMap(sql1, mail);
            System.out.println(map.toString());
            return false;
        }catch(Exception e){
            return true;
        }
    }

    public User ifbind(String num,String state){
        try {
            /*String sql = "select * from user where username = ? and password = ?";
            System.out.println(loginUser.getUsername());
            System.out.println(loginUser.getPassword());
            */
            String sql = "select * from user where "+state+" = ? ";
            User user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),num);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int rmuser(String username){
        String sql="delete from user where username = ?";
        int result = template.update(sql, username);
        return result;

    }

    public List queryall(){
        String sql = "select * from user";
        List<Map<String, Object>> mapList = template.queryForList(sql);
        return mapList;
    }

    public int canchange(String stuta,String num){
        String sql1 = "select * from user where "+stuta+" = ?";
        List<Map<String, Object>> maps = template.queryForList(sql1, num);
        return 1;
    }
}

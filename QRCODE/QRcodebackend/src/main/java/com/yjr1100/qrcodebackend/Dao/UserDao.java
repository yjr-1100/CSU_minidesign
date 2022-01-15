package com.yjr1100.qrcodebackend.Dao;/*
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

import net.sf.json.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import com.yjr1100.qrcodebackend.domain.User;
import com.yjr1100.qrcodebackend.utils.DRUIDUtils;

import java.util.List;
import java.util.Map;

public class UserDao {
    JdbcTemplate template = new JdbcTemplate(DRUIDUtils.getDataSource());


    public User login(User loginUser){
        try {
            String sql = "select * from user where phonenum = ? and password = ?";
            User user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),loginUser.getPhonenum(),loginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int userregister(JSONObject regisjson){
        String sql = "INSERT INTO user (username,password,phonenum,identifier) VALUES(?,?,?,?)";
        try{
            int updatestate = template.update(sql,regisjson.getString("regname"),
                    regisjson.getString("password"),regisjson.getString("phonenum"),regisjson.getInt("identifier"));
            return updatestate;
        }catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public List<Map<String, Object>> getsenders(){
        String sql1 = "select uid,username from user where identifier = 1";
        try{
            List<Map<String, Object>> senderlist = template.queryForList(sql1);
            if(!senderlist.isEmpty()){
                return senderlist;
            }else{
                return null;
            }
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public Boolean isexit(String phonenum){
        String sql1 = "select password from user where phonenum = ?";
        System.out.println(phonenum);
        try{
            Map<String, Object> map = template.queryForMap(sql1, phonenum);
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




}

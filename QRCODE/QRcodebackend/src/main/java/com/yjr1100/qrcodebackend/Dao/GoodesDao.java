package com.yjr1100.qrcodebackend.Dao;/*
 * @Auther:YJR-1100
 * @Date:2022/1/4 - 01 - 04 - 18:18
 * @Version:1.0
 * @Description:
 *
 *//*
 * @ClassName GoodesDao
 * @Description TODO
 * @Author YJR-1100
 * @Date 2022/1/4 18:18
 * @Version 1.0
 */

import com.yjr1100.qrcodebackend.utils.DRUIDUtils;
import net.sf.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import com.yjr1100.qrcodebackend.domain.User;
import com.yjr1100.qrcodebackend.utils.DRUIDUtils;

import java.util.List;
import java.util.Map;

public class GoodesDao {
    JdbcTemplate template = new UserDao().template;

    public List<Map<String, Object>> getusergoodes(Integer uid){
        String sql1 = "select * from goodes where u_id  = ?";
        try{
            List<Map<String, Object>> goodeslist = template.queryForList(sql1, uid);
            System.out.println(goodeslist.toString());
            System.out.println("00000000000000000000");
            if(!goodeslist.isEmpty()){
                return goodeslist;
            }else{
                return null;
            }
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public List<Map<String, Object>> getneedsendgoodes(Integer uid){
        String sql1 = "select * from goodes where s_id  = ?";
        try{
            List<Map<String, Object>> goodeslist = template.queryForList(sql1, uid);
            System.out.println(goodeslist.toString());
            if(!goodeslist.isEmpty()){
                return goodeslist;
            }else{
                return null;
            }
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public List<Map<String, Object>> getnosendergoodes(){
        String sql1 = "select * from goodes where s_id is null";
        try{
            List<Map<String, Object>> goodeslist = template.queryForList(sql1);
            System.out.println(goodeslist.toString());
            System.out.println("nosendernoserndernonsernder");
            if(!goodeslist.isEmpty()){
                return goodeslist;
            }else{
                return null;
            }
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public int insertusergoodes(JSONObject goodjson){
        String sql = "INSERT INTO goodes (goodname,senderphonenum,sendername,recphonenum,recname,recaddress,sendtime,u_id) VALUES(?,?,?,?,?,?,?,?)";
        try{
            int updatestate = template.update(sql,goodjson.getString("goodname"),
                    goodjson.getString("senderphonenum"),goodjson.getString("sendername"),goodjson.getString("recphonenum"),goodjson.getString("recname"),goodjson.getString("recaddress"),goodjson.getString("sendtime"),goodjson.getInt("u_id"));
            return updatestate;
        }catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int updatesid(JSONObject goodesinfojson){
        String sql = "update goodes set s_id=? where gid=?";
        try{
            int updatestate = template.update(sql,goodesinfojson.getInt("s_id"),goodesinfojson.getInt("gid"));
            return updatestate;
        }catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }
}

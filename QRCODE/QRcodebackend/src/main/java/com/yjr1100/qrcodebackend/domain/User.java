package com.yjr1100.qrcodebackend.domain;/*
 * @Auther:YJR-1100
 * @Date:2022/1/4 - 01 - 04 - 15:30
 * @Version:1.0
 * @Description:
 *
 *//*
 * @ClassName User
 * @Description TODO
 * @Author YJR-1100
 * @Date 2022/1/4 15:30
 * @Version 1.0
 */

import net.sf.json.JSONObject;

public class User {
    private Integer uid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /*用户名*/
    private String username;
    /*密码*/
    private String password;
    /*手机号*/
    private String phonenum;
    /*用户标识 用户是0 快递员是1 管理员是2*/
    private Integer identifier;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        System.out.println("------------------------");
        JSONObject jsonObject = new JSONObject();
        System.out.println("========================");
        jsonObject.put("username",username);
        jsonObject.put("phonenum",phonenum);
        jsonObject.put("identifier",identifier);
        jsonObject.put("uid",uid);
        return jsonObject.toString();
    }
}

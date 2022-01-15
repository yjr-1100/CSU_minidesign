package yjr1100.domain;/*
 * @Auther:YJR-1100
 * @Date:2021/11/23 - 11 - 23 - 0:13
 * @Version:1.0
 * @Description:
 *
 *//*
 * @ClassName User
 * @Description TODO
 * @Author YJR-1100
 * @Date 2021/11/23 0:13
 * @Version 1.0
 */

import net.sf.json.JSONObject;

public class User {
    private String username=null;
    private String password=null;
    private String phonenum=null;
    private String mail=null;
    private Integer isadmain=null;

    public Integer getIsadmain() {
        return isadmain;
    }

    public void setIsadmain(Integer isadmain) {
        this.isadmain = isadmain;
    }


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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "username="+username+",phonenum="+phonenum+
                ",mail=" + mail+",isadmain="+isadmain;
    }

    //@Override
    //public String toString() {
    //    JSONObject jsonObject = new JSONObject();
    //    jsonObject.put("username",username);
    //    jsonObject.put("phonenum",phonenum);
    //    jsonObject.put("mail",mail);
    //    jsonObject.put("isadmain",isadmain);
    //    return jsonObject.toString();
    //}
}

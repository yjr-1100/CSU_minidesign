package com.yjr1100.qrcodebackend.domain;/*
 * @Auther:YJR-1100
 * @Date:2022/1/4 - 01 - 04 - 15:31
 * @Version:1.0
 * @Description:
 *
 *//*
 * @ClassName Goodes
 * @Description TODO
 * @Author YJR-1100
 * @Date 2022/1/4 15:31
 * @Version 1.0
 */

import net.sf.json.JSONObject;

public class Goodes {
    //货物id
    private Integer gid;
    //货物名称
    private String goodname;

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    //寄件人手机号
    private String senderphonenum;
    //寄件人姓名
    private String sendername;
    //收件人手机
    private String recphonenum;
    //收件人姓名
    private String recname;
    //收件人地址
    private String recaddress;
    //寄件时间
    private String sendtime;
    //寄件人id
    private Integer u_id;
    //快递员id
    private Integer s_id;

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public Integer getS_id() {
        return s_id;
    }

    public void setS_id(Integer s_id) {
        this.s_id = s_id;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("senderphonenum",senderphonenum);
        jsonObject.put("sendername",sendername);
        jsonObject.put("recphonenum",recphonenum);
        jsonObject.put("recname",recname);
        jsonObject.put("recaddress",recaddress);
        jsonObject.put("sendtime",sendtime);
        jsonObject.put("goodname",goodname);
        jsonObject.put("u_id",u_id);
        jsonObject.put("s_id",s_id);
        jsonObject.put("gid",gid);
        return jsonObject.toString();
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getSenderphonenum() {
        return senderphonenum;
    }

    public void setSenderphonenum(String senderphonenum) {
        this.senderphonenum = senderphonenum;
    }

    public String getSendername() {
        return sendername;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }

    public String getRecphonenum() {
        return recphonenum;
    }

    public void setRecphonenum(String recphonenum) {
        this.recphonenum = recphonenum;
    }

    public String getRecname() {
        return recname;
    }

    public void setRecname(String recname) {
        this.recname = recname;
    }

    public String getRecaddress() {
        return recaddress;
    }

    public void setRecaddress(String recaddress) {
        this.recaddress = recaddress;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

}

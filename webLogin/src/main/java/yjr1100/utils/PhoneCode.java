package yjr1100.utils;/*
 * @Auther:YJR-1100
 * @Date:2021/11/24 - 11 - 24 - 13:01
 * @Version:1.0
 * @Description:
 *
 *//*
 * @ClassName PhoneCode
 * @Description TODO
 * @Author YJR-1100
 * @Date 2021/11/24 13:01
 * @Version 1.0
 */

import com.zhenzi.sms.ZhenziSmsClient;

import java.util.HashMap;
import java.util.Map;

public class PhoneCode {
    final String apiUrl = "https://sms_developer.zhenzikj.com";
    final String appId = "";
    final String appSecret = "";
    ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);

    /*
     * @Author YJR-1100
     * @Description //TODO
     * @Date 15:15
     * @param
     *   number---手机号
     *   style---要使用的短信模板
     *   code---发送的短信验证码
     * @return
     **/
    public String sendCode(String number,String style,String code) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("number", number);
        if(style.equals("register")){
            params.put("templateId", "2192");
        }else if(style.equals("resetpwd")){
            params.put("templateId", "2197");
        }else {
            params.put("templateId", "7297");
        }
        String[] templateParams = new String[2];
        templateParams[0] = code;
        templateParams[1] = "5分钟";
        params.put("templateParams", templateParams);
        String result = client.send(params);

        return result;
    }
}

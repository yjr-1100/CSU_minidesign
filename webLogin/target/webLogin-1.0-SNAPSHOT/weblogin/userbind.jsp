<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户绑定</title>
    <link rel="stylesheet" href="./css/register.css">
    <script src="./js/jquery3.6.0.min.js"></script>
</head>

<body>
    <%
        response.setContentType("text/html;charset=utf-8");
        boolean flag = false;
        //1. 获取所有cookie
        Cookie[] cookies = request.getCookies();
        if(cookies !=null && cookies.length>0) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if (name.equals("user")) {
                    JSONObject jsonObject = new JSONObject();
                    String[] values = URLDecoder.decode(cookie.getValue(), "utf-8").split(",");
                    for (String v : values) {
                        System.out.println(v.split("=")[0]);
                        System.out.println(v.split("=")[1]);
                        if (v.split("=")[0].equals("username")) {
                            jsonObject.put("username", v.split("=")[1]);
                        } else if (v.split("=")[0].equals("phonenum")) {
                            jsonObject.put("phonenum", v.split("=")[1]);
                        } else if (v.split("=")[0].equals("mail")) {
                            jsonObject.put("mail", v.split("=")[1]);
                        } else if (v.split("=")[0].equals("isadmain")) {
                            jsonObject.put("isadmain", v.split("=")[1]);
                        }
                    }
                    request.setAttribute("jsonuser", jsonObject);
                }
            }
        }
    %>
    <div class="main">
        <div class="register_warp">
            <form action="/webLogin_war_exploded/bindServlet" method="post">
                <div id="title" ><span >用户绑定</span></div>
                <div><span>用户名</span><input type="text" placeholder="请输入6-10位数字/字母" name="username"> <span class="tips"></span> </div>
                <div><span>密码</span><input type="password" placeholder="请输入6-11位数字/字母/下划线" name="password"> <span class="tips"></span></div>
                <div><span>确认密码</span><input type="password" placeholder="请确认密码" name="repassword"> <span class="tips"></span></div>
                <div><span>邮箱/手机</span><input type="text" placeholder="请输入邮箱/手机号" id="phonenum" readonly="readonly"
                                              value="<c:if test="${not empty requestScope.num}">${requestScope.num}</c:if><c:if test="${empty requestScope.num}">${param.num}</c:if>"  name="inputnum" > <span class="tips"></span></div>
                <p style="color: red;margin-left: 10px;font-weight: 400;font-size: 18px">${requestScope.errmsg}</p>
                <input type="submit" class="subbtn" value="绑定">
            </form>
        </div>
    </div>
<script>
    console.log($(".register_warp input"))
    let tips = $(".tips")
    for(let i =0;i<$(".register_warp input").length-2;i++){
        $(".register_warp input")[i].addEventListener('blur', function (){
            value = this.value.replace(/(^\s*)|(\s*$)/g, "")
            this.value = value
            switch (i) {
                case 0:
                    if(/^[a-zA-Z0-9]{6,10}$/.test(value)){
                        tips[i].style.color = 'green'
                        tips[i].innerText = '√'
                    }else{
                        tips[i].innerText = '×'
                        tips[i].style.color = 'red'
                    }
                    break
                case 1:
                    var mark = false
                    if(/^\w{6,11}$/.test(value)){
                        tips[i].style.color = 'green'
                        tips[i].innerText = '√'
                        mark = true
                    }else if (value===""){
                        tips[i+1].innerText = '×'
                        tips[i+1].style.color = 'red'
                        tips[i].innerText = '×'
                        tips[i].style.color = 'red'
                        mark = false
                    }
                    else{
                        tips[i].innerText = '×'
                        tips[i].style.color = 'red'
                        mark = false
                    }
                    if(mark&&this.value !=$(".register_warp input")[2].value){
                        tips[i+1].innerText = '×'
                        tips[i+1].style.color = 'red'
                    }
                    break
                case 2:
                    if($(".register_warp input")[1].value){
                        reg = new RegExp('^'+$(".register_warp input")[1].value)
                        if(reg.test(value)){
                            tips[i].style.color = 'green'
                            tips[i].innerText = '√'
                        }else{
                            tips[i].innerText = '×'
                            tips[i].style.color = 'red'
                        }
                    }else{
                        alert('请先输入密码')
                        this.value = ''
                        tips[i].innerText = '×'
                        tips[i].style.color = 'red'
                        tips[i-1].innerText = '×'
                        tips[i-1].style.color = 'red'
                    }
                    break
            }
        })
    }
</script>
</body>


</html>
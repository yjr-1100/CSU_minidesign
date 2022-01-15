<%--
  Created by IntelliJ IDEA.
  User: YJR
  Date: 2021/11/22
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录认证</title>
    <link rel="stylesheet" href="./css/login.css">
    <script src="./js/jquery3.6.0.min.js"></script>
    <script src="./js/login.js"></script>
    <script>
        <c:if test="${not empty param.status}">
            alert("${param.status}")
        </c:if>
    </script>
</head>
<body>
    <div class="main">

        <div class="left">

        </div>
        <div class="right">
            <div class="login_wrap">
                <div class="loginWay">
                    <div class="wayactive">账号登录</div>
                    <div>验证码登录</div>
                </div>
                <div class="studentIdLog">
                    <form action="/webLogin_war_exploded/loginServlet" method="post">
                        <div class="username">
                            <img src="./images/user1.png" alt="" class="icon" >
                            <input type="text" placeholder="请输入账号" name="username">
                        </div>
                        <div class="password">
                            <img src="./images/pass1.png" alt="" class="icon">
                            <input type="password" value="" placeholder="请输入密码" name="password">
                        </div>
                        <input type="hidden" name="phonenum">
                        <input type="hidden" name="mail">
                            <div><span style="color: red">${requestScope.errmsg}</span></div>
                        <input type="submit" value="登录" class="loginbtn">
                    </form>
                    <div class="tips">
                        <a href="">忘记密码</a>
                        <script>
                            let inputname = document.querySelectorAll(".tips a")[0];
                            let name = document.querySelector(".username input")
                            inputname.addEventListener('click',function (){
                                inputname.href = "/webLogin_war_exploded/forget.jsp?username="+name.value;
                            })
                        </script>
                        <a href="/webLogin_war_exploded/register.jsp">注册账号</a>
                    </div>
                </div>
                <div class="phoneLog">
                        <div class="username">
                            <img src="./images/user1.png" alt="" class="icon" >
                            <input type="text" placeholder="请输入手机号/邮箱" name="number" id="phonenum">
                            <span id="phonchek"></span>

                        </div>
                        <div class="chekcode">
                            <img src="./images/captcha1.png" alt="" class="icon">
                            <input type="text" value="" placeholder="请输入右侧验证码">
                            <img id="chekcode" src="/webLogin_war_exploded/ChekCodeServlet" >
                        </div>
                        <div class="phonecode">
                            <img src="./images/pass1.png" alt="" class="icon">
                            <input type="text" value="" placeholder="请输入动态码" name="phonecode" id="lala">
                            <input type="button" value="获取动态码" id="codebtn">
                            <script>
                                btn =document.querySelector("#codebtn");
                                console.log(btn)
                                var wait = 60;
                                btn.addEventListener('click',function (){
                                    if($("#phonenum").val() ==""){
                                        alert("请输入手机号或邮箱")
                                    }
                                    else if($(".chekcode input").val() ==""){
                                        alert("请输入验证码")
                                    }else{
                                        console.log($("#phonenum").val())
                                        console.log($(".chekcode input").val())
                                        time(btn)
                                        $.ajax({
                                            url:"/webLogin_war_exploded/codesendServlet",//要请求的服务器url
                                            data:{
                                                phonecode:$("#phonenum").val(),
                                                style:"login",
                                                chekcode:$(".chekcode input").val()
                                            },
                                            async:true,//是否是异步请求
                                            cache:false,//是否缓存结果
                                            type:"GET",//请求方式
                                            dataType:"text",//服务器返回什么类型数据 text xml javascript json(javascript对象)
                                            success:function(result){//函数会在服务器执行成功后执行，result就是服务器返回结果
                                                let jsonre = JSON.parse(result)
                                                console.log(jsonre)
                                                console.log(jsonre.msg)
                                                if(jsonre.statu === -1){
                                                    alert(jsonre.msg)
                                                    let img = document.querySelector("#chekcode");
                                                    let date = new Date().getTime();
                                                    img.src = "/webLogin_war_exploded/ChekCodeServlet?"+date
                                                    btn.removeAttribute("disabled");
                                                    btn.value = "获取动态码";
                                                    wait = 0;
                                                }else if(jsonre.statu ===1){
                                                    // time(btn)
                                                }
                                            }
                                        });

                                    }
                                })

                                function time(btn) {
                                    if (wait == 0) {
                                        btn.removeAttribute("disabled");
                                        btn.value = "获取动态码";
                                        wait = 60;
                                    } else {
                                        btn.setAttribute("disabled", true);
                                        btn.value = wait + "秒重新获取";
                                        wait--;
                                        setTimeout(function () {
                                            time(btn);
                                        }, 1000)
                                    }
                                }
                            </script>
                        </div>
                        <div><span>${requestScope.errmsg}</span></div>
                        <input type="button" value="登录" class="loginbtn">
                </div>
                
            </div>
        </div>
    </div>
    <script>
        document.querySelector("#phonenum").addEventListener("blur",function (){
            value = this.value.replace(/(^\s*)|(\s*$)/g, "")
            this.value = value
            let reg_tel = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
            let reg_mail = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
            if(value==""){
                alert("请输入手机号/邮箱")
            }
            else if(value.length == 11&& !reg_tel.test(value)){
                alert("请输入正确的手机号")
            }else if(value.length != 11){
                if(!reg_mail.test(this.value)){
                    alert("请输入正确的手机号或邮箱")
                 }//else{
                //     $("#phonchek").style.color = 'green'
                //     $("#phonchek").innerText = '√'
                // }
            }
        })
    </script>
</body>
</html>
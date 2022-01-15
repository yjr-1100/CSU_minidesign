<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<!DOCTYPE html>--%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户注册</title>
    <link rel="stylesheet" href="./css/register.css">
    <script src="./js/jquery3.6.0.min.js"></script>
</head>

<body>
<div class="main">
    <div class="register_warp">
        <form action="/webLogin_war_exploded/forgetServlet" method="post">
            <div id="title" ><span >重置密码</span></div>
            <div><span>用户名</span><input id="inputuname" name="username" type="text" placeholder="请输入6-10位数字/字母" value="${paramValues.username[0]}"><span class="tips"></span> </div>
            <div><span>密码</span><input type="password" placeholder="请输入6-11位数字/字母/下划线" name="password"> <span class="tips"></span></div>
            <div><span>确认密码</span><input type="password" placeholder="请确认密码" name="repassword"> <span class="tips"></span></div>
            <div><span>邮箱/手机</span><input type="text" placeholder="请输入邮箱/手机号" name="number" id="num"> <span class="tips"></span></div>
            <div id="checkcode"><span>验证码</span>
                <input type="text" placeholder="请输入验证码">
                <img src="/webLogin_war_exploded/ChekCodeServlet" alt="">
            </div>
            <div id="movecode">
                <span>动态码</span>
                <input type="text" value="" placeholder="请输入动态码" name="checkcode" id="lala">
                <input type="button" value="获取动态码" id="codebtn">
                <script>
                    <c:if test="${not empty param.msg}">
                        alert("${param.msg}")
                    </c:if>

                    btn =document.querySelector("#codebtn");
                    var wait = 60;
                    btn.addEventListener('click',function (){

                        if($("#phonenum").val() ==""){
                            alert("请输入手机号或邮箱")
                        }
                        else if($("#checkcode input").val() ==""){
                            alert("请输入验证码")
                        }else{
                            // 判断该手机号/邮箱是不是绑定的邮箱
                            $.ajax({
                                url:"/webLogin_war_exploded/forgetServlet",//要请求的服务器url
                                data:{
                                    num:$("#num").val(),
                                    username:$("#inputuname").val()
                                },
                                cache:false,//是否缓存结果
                                type:"GET",//请求方式
                                dataType:"json",
                                success:res=>{
                                    console.log(res)
                                    if(res.statu ==1){
                                        time(btn)
                                        $.ajax({
                                            url:"/webLogin_war_exploded/codesendServlet",//要请求的服务器url
                                            data:{
                                                phonecode:$("#num").val(),
                                                style:"login",
                                                chekcode:$("#checkcode input").val()
                                            },
                                            async:true,//是否是异步请求
                                            cache:false,//是否缓存结果
                                            type:"GET",//请求方式
                                            dataType:"text",//服务器返回什么类型数据 text xml javascript json(javascript对象)
                                            success:function(result){//函数会在服务器执行成功后执行，result就是服务器返回结果
                                                let jsonre = JSON.parse(result)
                                                console.log(jsonre)
                                                if(jsonre.statu === -1){
                                                    alert(jsonre.msg)
                                                    let img = document.querySelector("#checkcode img");
                                                    let date = new Date().getTime();
                                                    img.src = "/webLogin_war_exploded/ChekCodeServlet?"+date
                                                }
                                            }
                                        });
                                    }
                                    else{
                                        window.location.href = ("/webLogin_war_exploded/forget.jsp?username="+$("#inputuname").val()+"&msg="+"用户没有绑定该手机号/邮箱")
                                    }

                                    }
                                }
                            )

                        }
                    })
                    function time(btn) {
                        if (wait === 0) {
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
            <p style="color: red;margin-left: 10px;font-weight: 400;font-size: 18px">${requestScope.errmsg}</p>
            <input type="submit" class="subbtn" value="重置">
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
                    }else{
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
                    }
                    break
                case 3:
                    let reg_tel = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
                    let reg_mail = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
                    if(value.length == 11&& !reg_tel.test(value)){
                        alert("请输入正确的手机号")
                    }else if(value.length != 11){
                        if(!reg_mail.test(this.value)){
                            alert("请输入正确的手机号或邮箱")
                        }else{
                            tips[i].style.color = 'green'
                            tips[i].innerText = '√'
                        }
                    }

            }
        })
    }
    let img = document.querySelector("#checkcode").querySelector("img");
    img.onclick = function (){
        let date = new Date().getTime();
        img.src = "/webLogin_war_exploded/ChekCodeServlet?"+date
    }
</script>
</body>


</html>
<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="net.sf.json.JSONObject" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户中心</title>
    <script src="./js/jquery3.6.0.min.js"></script>
    <style>
        *{
            margin: 0;
            padding: 0;
            font-size: 18px;
            text-decoration:none;
        }
        a{
            color: #f2ffff;
        }
        .main{
            width: 100%;
            /* height: 100%; */
        }
        body{
            background-image: url("https://img2.baidu.com/it/u=2238208135,1192885851&fm=26&fmt=auto");
            background-repeat: repeat-y;
            
            background-size: 30%;
            background-position: left top;
        }
        .leftside{
            float: left;
            color: #ffffff;
            width: 20%;
            min-height: 1000px;
        }
        .title_warp{
            display: flex;
            flex-direction: row;

        }
        .logol{
            width: 55%;
            background-color: aqua;
            background-image: url("http://faculty.csu.edu.cn/_tsf/00/3D/mQ3AFziiYBJn.jpg");
            background-size: 169% 60px;
            height: 60px;
        }
        .title{
            display: flex;
            background-color: #2a2a2a;
            color: #b4b4b4;
            font-size: 30px;
            width: 45%;
            height: 48px;
            justify-content: center;
            padding-top: 12px;
            font-family: '仿宋';
            font-weight: 600;
        }
        .image_warp{
            width: 100%;
            margin-top: 30px ;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .personimg{
            width: 35%;
            
        }
        .person_name{
            text-align:center;
            margin-bottom: 14px;
            margin-top: 10px;
        }
        .connact_way{
            display: flex;
            flex-direction: column;
        }
        .connact_way > span{
            margin: 15px 30px;
            font-size: 16px;
        }
        .rightside{
            width: 80%;
        }
        .nav{
            background-color: #2a2a2a;
            height: 60px;
            width: 100%;
        }
        .manu{
            width: 100%;
            float: left;
        }
        .manu> a > div{
            float: left;
            display: block;
            line-height: 60px;
            color: #f2ffff;
            width: 15.4vw;
            text-align: center;
        }
        .manu > a > div:hover{
            float: left;
            display: block;
            line-height: 60px;
            color: #f2ffff;
            width: 15.4vw;
            text-align: center;
            background-color: #424242;
            cursor:pointer;
        }
        .shouye{
            float: left;
            display: block;
            line-height: 60px;
            color: #f2ffff;
            width: 15.4vw;
            text-align: center;
            background-color: #424242;
            cursor:pointer;
        }
        .rightside{
            min-height: 1000px;
            margin-left:20%;
            background-image: url("https://tse1-mm.cn.bing.net/th/id/R-C.b34174ab7d2f094a2c410d57f35a68fb?rik=eSmN9xjjHDqg4Q&riu=http%3a%2f%2fwww.desktx.com%2fd%2ffile%2fwallpaper%2fspace%2f20170508%2fb3469997c7a57e43c01e29dee71700bb.jpg&ehk=Fmh5GYaK2XWTGua0zfL4BZKQCgyN3dyqYWeJr9f0F0c%3d&risl=&pid=ImgRaw&r=0");
            background-size: cover;
        }
        .contant{
            display: flex;
            min-height: 860px;
            height: auto;
            width: 100%;
            justify-content: center;
            align-items: center;            
        }
        .mask{
            width: 98%;
            min-height: 840px;
            background-color: rgba(240, 240, 240,60%);
        }
        .footer{
            padding-top: 10px;
            color: #f2ffff;
            text-align: center;
            height: 70px;
        }
        .footer > div{
            height: 30px;
            line-height: 30px;
        }
        .footer > div > a{
            color: #f2ffff;
        }
        .artical{
            padding: 0 20px 20px 20px;
            height: 100%;
        }
        .artical > p{
            line-height: 35px;
            font-family: "楷体";
            font-size: 19px;
            text-indent: 2em;
        }
        .artical > h2{
            margin: 20px 0;
        }
        .change{
            color: red;
            font-size: 16px;
            display: inline-block;
            margin-left: 10px;
        }
        .change:hover{
            cursor: pointer;
        }
        /*弹出的修改框*/
        .login{
            display: none;
            width: 512px;
            /*height: 280px;*/
            position: fixed;
            padding-bottom: 20px;
            padding-left: 20px;
            border: #ebebeb solid 1px;
            left: 50%;
            top: 50%;
            background: #fff;
            box-shadow: 0px 0px 20px #ddd;
            z-index: 9999;
            transform: translate(-50%,-50%);
        }

        .changebox .login-title{
            width: 100%;
            margin: 10px 0px 0px 0px;
            text-align: center;
            line-height: 40px;
            height: 40px;
            font-size: 18px;
            position: relative;
            cursor: move;
        }
        .changebox .login-input-content{
            margin-top: 20px;
        }
        .changebox .login-button{
            width: 50%;
            margin: 30px auto 0px auto;
            line-height: 40px;
            font-size: 14px;
            border: #ebebeb 1px solid;
            text-align: center;
        }
        .changebox .login-bg{
            display: none;
            width: 100%;
            height: 100%;
            position: fixed;
            top: 0px;
            left: 0px;
            background: rgba(0,0,0,.3);
        }
        .changebox a{
            text-decoration: none;
            color: black;
        }
        .changebox .login-button a{
            display: block;
        }
        .changebox .login-input input.list-input{
            float: left;
            line-height: 35px;
            height: 35px;
            width: 350px;
            border: #ebebeb 1px solid;
            text-indent: 5px;
        }
        .changebox .login-input{
            overflow: hidden;
            margin: 0px 0px 20px 0px;
        }
        .changebox .login-input label{
            float: left;
            width: 90px;
            padding-right: 10px;
            text-align: right;
            line-height: 35px;
            height: 35px;
            font-size: 14px;
        }
        .changebox .login-title span{
            position: absolute;
            font-size: 12px;
            right: -20px;
            top: -30px;
            background-color: #fff;
            border: #ebebeb solid 1px;
            width: 40px;
            height: 40px;
            border-radius: 20px;
        }
        #chekcodeimg{
            margin-left: 20px;
        }
        #chekcodeimg{
            cursor: pointer;
         }
        #getcode{
            display: inline-block;
            color: #2b6cdb;
            background-color: transparent;
            margin-left: 20px;
            height: 35px;
            line-height: 35px;
            border: 0;
        }
        #getcode:hover{
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="main">
        <div class="leftside">
            <div class="title_warp">
                <div class="logol"></div>
                <div class="title">用户中心</div>
            </div>
            <%
                response.setContentType("text/html;charset=utf-8");
                boolean flag = false;
                //1. 获取所有cookie
                Cookie[] cookies = request.getCookies();
                if(cookies !=null && cookies.length>0){
                    for(Cookie cookie:cookies){
                        String name = cookie.getName();
                        if(name.equals("user")){
                            JSONObject jsonObject = new JSONObject();
                            String[] values = URLDecoder.decode(cookie.getValue(),"utf-8").split(",");
                            for(String v:values){
                                System.out.println(v.split("=")[0]);
                                System.out.println(v.split("=")[1]);
                                if(v.split("=")[0].equals("username")){
                                    jsonObject.put("username",v.split("=")[1]);
                                }else if(v.split("=")[0].equals("phonenum")){
                                    jsonObject.put("phonenum",v.split("=")[1]);
                                }else if(v.split("=")[0].equals("mail")){
                                    jsonObject.put("mail",v.split("=")[1]);
                                }
                                else if(v.split("=")[0].equals("isadmain")){
                                    jsonObject.put("isadmain",v.split("=")[1]);
                                }
                            }
                            request.setAttribute("jsonuser",jsonObject);
            %>
            <div class="maindetail">
                <div class="image_warp">
                    <image class="personimg" src="./images/no_img.png" mode="widthFix" lazy-load="false" binderror="" bindload="">
                    </image>
                </div>
                <div class="person_name">${jsonuser.username}</div>
                <div class="connact_way">

                    <span>用户名: ${jsonuser.username} <p class="change">修改</p></span>
                    <span>手机号:
                        <c:if test="${jsonuser.phonenum=='null'}">
                            <p  class="change">绑定</p>
                        </c:if>
                        <c:if test="${jsonuser.phonenum!='null'}">
                            ${jsonuser.phonenum}
                            <p  class="change">修改</p>
                        </c:if>

                    </span>
                    <span>邮箱:
                        <c:if test="${jsonuser.mail=='null'}">
                            <p  class="change">绑定</p>
                        </c:if>
                        <c:if test="${jsonuser.mail!='null'}">
                            ${jsonuser.mail}
                            <p  class="change">修改</p>
                        </c:if>
                    </span>
                    <span><p class="change">修改密码</p></span>
                    <c:if test="${jsonuser.isadmain  eq '1'}">
                        <span><a href="/webLogin_war_exploded/managerServlet?action=list" style="color: red;display: inline-block; margin-left: 10px;">用户管理</a></span>
                    </c:if>


                    <%
                                }
                            }
                        }

                    %>

                </div>
            </div>

        </div>

        <div class="rightside">
            <div class="nav">
                <div class="manu">
                    <a href="javascript:;" class="shouye"><div>首页</div></a>
                    <a href="javascript:;" class="yanjiu"><div>研究领域</div></a>
                    <a href="javascript:;" class="xiangmu"><div>项目经验</div></a>
                    <a href="javascript:;" class="huojian"><div>获奖信息</div></a>
                    <a href="javascript:;" class="blog"><div>我的博文</div></a>
                </div>
            </div>
            <div class="contant">
                <div class="mask">
                    <div class="artical">
                        <h2>个人简介</h2>
                        <p>暂无</p>
                        <h2>教育经历</h2>
                        <p>暂无</p>
                        <p>暂无</p>
                        <h2>社会兼职</h2>
                        <p>暂无</p>
                    </div>
                </div>
            </div>
            <div class="footer">
                <div>杨嘉睿版权所有</div>
                <div>
                    <a href="">中南大学</a> |
                    <a href="">Central South University</a>
                </div>
            </div>
        </div>
    </div>


    <div class="changebox">
        <div id="login" class="login">
            <div class="login-title" id="title"><p>登录会员</p>
                <span><a href="javascript:void(0)" class="close-login" id="closeBtn">关闭</a></span>
            </div>
            <div class="login-input-content">
<%--                <div class="login-input">--%>
<%--                    <label ><p id="old">用户名：</p></label>--%>
<%--                    <input type="text"  id="username" class="list-input" disabled="true">--%>
<%--                </div>--%>
                <div class="login-input">
                    <label ><p id="new">登录密码：</p></label>
                    <input type="text"  id="password" class="list-input">
                </div>


            </div>
            <div class="login-button" id="loginBtn"><a href="javascript:void(0);"id='login-button-submit'>确认</a></div>
        </div>
        <!-- 遮挡层 -->
        <div class="login-bg" id="bg"></div>
    </div>
    <script>
        //  1. 获取元素
        var login = document.querySelector('.login');
        var mask = document.querySelector('.login-bg');
        var link = document.querySelectorAll('.change');
        var closebtn = document.querySelector('#closeBtn');
        var title = document.querySelector('#title');
        let name = title.querySelector("p")
        let old = document.querySelector("#old")
        let new_ = document.querySelector("#new")
        let wait = 60;
        let newinput = document.querySelector('#password')
        let login_input_content = document.querySelector(".login-input-content")
        console.log(name)
        let reg_tel = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
        let reg_mail = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
        newinput.addEventListener('blur',function (){
            value = this.value.replace(/(^\s*)|(\s*$)/g, "")
            this.value = value
            if(value==""){
                alert("请输入"+new_.innerText)
            }
            else if(value.length == 11&& !reg_tel.test(value)){
                alert("请输入正确的手机号")
            }else if(value.length > 11) {
                if (!reg_mail.test(this.value)) {
                    alert("请输入正确的手机号或邮箱")
                }
            }
        })
        // 2. 点击弹出层这个link
        for(let i=0;i<link.length;i++){
            link[i].addEventListener('click',function(){
                login.style.display = 'block';
                mask.style.display = 'block';
                console.log(i)
                switch(i){
                    case 0:
                        if(!document.querySelector("#old")){
                            createoldlable("旧用户名","${jsonuser.username}")
                        }else{
                            document.querySelector("#old").innerText="旧用户名"
                            document.querySelector("#username").value = "${jsonuser.username}"
                            document.querySelector("#username").disabled = "true"
                        }
                        name.innerText="用户名修改"
                        new_.innerText="新用户名"
                        if(document.querySelector("#getcode")){
                            login_input_content.removeChild(login_input_content.lastChild)
                            login_input_content.removeChild(login_input_content.lastChild)
                        }
                        break
                    case 1:
                        if(!document.querySelector("#getcode")){
                            createimgcode()
                            createphonecode()
                        }
                        <c:if test="${jsonuser.phonenum=='null'}">
                            name.innerText="手机号绑定"
                            new_.innerText="手机号："
                            if(document.querySelector("#old")) {
                                login_input_content.removeChild(login_input_content.firstChild)
                            }
                        </c:if>
                        <c:if test="${jsonuser.phonenum != 'null'}">
                            if(!document.querySelector("#old")){
                                createoldlable("旧手机号：","${jsonuser.phonenum}")
                            }else{
                                document.querySelector("#old").innerText="旧手机号："
                                document.querySelector("#username").value = "${jsonuser.phonenum}"
                                document.querySelector("#username").disabled = "true"
                            }
                            name.innerText="手机号修改"
                            new_.innerText="新手机号："
                        </c:if>
                        break
                    case 2:
                        if(!document.querySelector("#getcode")){
                            createimgcode()
                            createphonecode()
                        }
                        <c:if test="${jsonuser.mail=='null'}">
                            name.innerText="邮箱绑定："
                            new_.innerText="邮箱号："
                            if(document.querySelector("#old")){
                                login_input_content.removeChild(login_input_content.firstChild)
                            }
                        </c:if>
                        <c:if test="${jsonuser.mail != 'null'}">
                            if(!document.querySelector("#old")){
                                createoldlable("旧邮箱：","${jsonuser.mail}")
                            }else{
                                document.querySelector("#old").innerText="旧邮箱："
                                document.querySelector("#username").value = "${jsonuser.mail}"
                                document.querySelector("#username").disabled = "true"
                            }
                                name.innerText="邮箱修改"
                                new_.innerText="新邮箱："
                        </c:if>
                        break
                    case 3:
                        name.innerText="密码修改"
                        if(!document.querySelector("#old")){
                            createoldlable("旧密码","",true)
                        }else{
                            console.log("sldkfjlskdjflskdjflsdf")
                            document.querySelector("#old").innerText="旧密码"
                            document.querySelector("#username").value = ""
                            document.querySelector("#username").disabled = false
                        }
                        new_.innerText="新密码"
                        if(document.querySelector("#getcode")){
                            login_input_content.removeChild(login_input_content.lastChild)
                            login_input_content.removeChild(login_input_content.lastChild)
                        }
                        break
                }


                    
            })
        }

        var createoldlable = function (lablename,valueinput,sta){
            /*<div class="login-input">
                    <label ><p id="old">用户名：</p></label>
                    <input type="text"  id="username" class="list-input" disabled="true">
                </div>*/
            let div1 = document.createElement("div")
            div1.className = "login-input"
            let label1 = document.createElement("label")
            let p1 = document.createElement("p")
            p1.setAttribute("id","old");
            p1.innerText=lablename
            label1.appendChild(p1)
            let input1 = document.createElement("input")
            input1.type="text";
            input1.setAttribute("id","username")
            input1.className = "list-input"
            if(sta){
                input1.disabled=false
            }else{
                input1.disabled=true
            }
            input1.value = valueinput
            div1.appendChild(label1);
            div1.appendChild(input1);
            login_input_content.insertBefore(div1,login_input_content.firstChild)
        }
        var createimgcode = function (){
            /*<div class="login-input">
                    <label ><p id="">验证码：</p></label>
                    <input type="text" value="" placeholder="请输入右侧验证码" class="list-input" style="width: 175px">
                    <img id="chekcodeimg" src="/webLogin_war_exploded/ChekCodeServlet" >
                </div>*/
            let div1 = document.createElement("div")
            div1.className = "login-input"
            let label1 = document.createElement("label")
            let p1 = document.createElement("p")
            p1.setAttribute("id","checkcode");p1.innerText="验证码：";
            label1.appendChild(p1)

            let input1 = document.createElement("input")
            input1.type="text";
            input1.setAttribute("placeholder","请输入右侧验证码")
            input1.setAttribute("id","imgcheckcodeinput")
            input1.className = "list-input"
            input1.style.width = "175px"
            let img = document.createElement("img")
            img.setAttribute("id","chekcodeimg")
            img.src = "/webLogin_war_exploded/ChekCodeServlet"
            div1.appendChild(label1);
            div1.appendChild(input1);
            div1.appendChild(img);
            login_input_content.appendChild(div1)
            img.onclick = function (){
                let date = new Date().getTime();
                img.src = "/webLogin_war_exploded/ChekCodeServlet?"+date
            }
        }
        var createphonecode = function (){
            var div1 = document.createElement("div")
            div1.className = "login-input"
            var label1 = document.createElement("label")
            var p1 = document.createElement("p")
            p1.setAttribute("id","checkcode");p1.innerText="动态码：";
            label1.appendChild(p1)
            var input1 = document.createElement("input")
            input1.type="text";
            input1.setAttribute("id","checkcodeinput")
            input1.className = "list-input"
            input1.style.width = "175px"
            var input2 = document.createElement("input")
            input2.type="button"
            input2.setAttribute("id","getcode")
            input2.value = "获取动态码"
            div1.appendChild(label1);
            div1.appendChild(input1);
            div1.appendChild(input2);
            login_input_content.appendChild(div1)
            let reg_tel = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
            let reg_mail = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
            btn =document.querySelector("#getcode");
            console.log(btn)

            btn.addEventListener('click',function (){
                if($("#password").val() ==""){
                    alert("请输入"+document.querySelector("#new").innerText)
                }
                if($("#password").val().length <11){
                    alert("请输入正确的"+document.querySelector("#new").innerText)
                }
                else if($("#password").val().length ==11&&!reg_tel.test($("#password").val())){
                    alert("请输入正确的手机号111")
                }
                else  if($("#password").val().length !=11&&!reg_mail.test($("#password").val())){
                    alert("请输入正确的邮箱")
                }
                else if($("#imgcheckcodeinput").val().length ==""){
                    alert("请输入验证码")
                }else{
                    console.log($("#password").val())
                    console.log($("#imgcheckcodeinput").val())
                    $.ajax({
                        url:"/webLogin_war_exploded/codesendServlet",//要请求的服务器url
                        data:{
                            phonecode:$("#password").val(),
                            style:"res",
                            chekcode:$("#imgcheckcodeinput").val()
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
                                let img = document.querySelector("#chekcodeimg");
                                let date = new Date().getTime();
                                img.src = "/webLogin_war_exploded/ChekCodeServlet?"+date
                            }else if(jsonre.statu ===1){
                                time(btn)
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
        }
        
        // 确认
        document.querySelector("#loginBtn").addEventListener('click',function(){
            if(name.innerText ==="用户名修改"){
                console.log("1")
                let oldname=$("#username").val()
                let newname=$("#password").val()
                if(newname === ""){
                    alert("请输入新用户名")
                }else if (/^[a-zA-Z0-9]{6,10}$/.test(newname)){
                    //    发送请求
                    userinfochange("username",oldname,newname)
                }else{
                    alert("用户名不合法")
                }
            }
            else if(name.innerText.indexOf("手机号")!=-1){
                console.log("2")
                let oldname=document.querySelector(".person_name").innerText
                let newname=$("#password").val()
                let movecode = $("#checkcodeinput").val()
                console.log(oldname)
                console.log(newname)
                console.log(movecode)
                // 判断不为空
                if(newname ===""||movecode ===""){
                    alert("请输入手机号/动态码")
                }else if(!/^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/.test(newname)){
                    alert("请输入正确的手机号")
                }else{
                    //发请求
                    userinfochange("phonenum",oldname,newname,movecode)
                }
            }
            else if(name.innerText.indexOf("邮箱")!=-1){
                console.log("3")
                let oldname=document.querySelector(".person_name").innerText
                let newname=$("#password").val()
                let movecode = $("#checkcodeinput").val()
                console.log(oldname)
                console.log(newname)
                console.log(movecode)
                // 判断不为空
                if(newname ===""||movecode ===""){
                    alert("请输入邮箱/动态码")
                }else if(!/^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/.test(newname)){
                    alert("请输入正确的邮箱")
                }else{
                    //发请求
                    userinfochange("mail",oldname,newname,movecode)
                }
            }
            else if(name.innerText ==="密码修改"){
                console.log("4")
                let username=document.querySelector(".person_name").innerText
                let old=$("#username").val()
                let anew=$("#password").val()
                console.log(username)
                console.log(old)
                console.log(anew)
                if(old === ""){
                    alert("请输入原密码")
                }if(anew === ""){
                    alert("请输入新密码")
                }else if (/^\w{6,11}$/.test(anew)){
                    //    发送请求
                    userinfochange("password",old,anew,username)
                }else{
                    alert("密码不合法")
                }
            }
        })

        let userinfochange = function (status,...args){
            console.log(status)
            console.log(args)
            let prime = {}
            prime["status"]=status
            prime["old"] = args[0]
            prime["new"] = args[1]
            if(args.length == 3){
                prime["movecode"] = args[2]
            }

            console.log(prime)
            $.ajax({
                url:"/webLogin_war_exploded/userInfoChangeServlet",//要请求的服务器url
                data:prime,
                async:true,//是否是异步请求
                cache:false,//是否缓存结果
                type:"POST",//请求方式
                dataType:"json",//服务器返回什么类型数据 text xml javascript json(javascript对象)
                success:res=>{//函数会在服务器执行成功后执行，result就是服务器返回结果
                    console.log(res)
                    if(res.statu == 1){
                        alert(res.msg)
                        window.location="/webLogin_war_exploded/user.jsp"
                    }else{
                        alert(res.msg)
                        document.querySelector("#imgcheckcodeinput").value = ""
                        document.querySelector("#checkcodeinput").value = ""
                        let date = new Date().getTime();
                        document.querySelector("#chekcodeimg").src = "/webLogin_war_exploded/ChekCodeServlet?"+date
                    }
                }
            });
        }

        // 点击关闭隐藏
        closebtn.addEventListener('click',function(){
            login.style.display = 'none';
            mask.style.display = 'none';
            if(document.querySelector('#username')){
                document.querySelector('#username').value=""
            }
            if(document.querySelector("#new")){
                document.querySelector("#new").value=""
            }if(document.querySelector("#password")){
                document.querySelector("#password").value = ""
            }
            if(document.querySelector("#imgcheckcodeinput")){
                document.querySelector("#imgcheckcodeinput").value = ""
            }
            if(document.querySelector("#checkcodeinput")){
                document.querySelector("#checkcodeinput").value = ""
            }
            if(document.querySelector("#chekcodeimg")){
                let date = new Date().getTime();
                document.querySelector("#chekcodeimg").src = "/webLogin_war_exploded/ChekCodeServlet?"+date
            }
            if(document.querySelector("#getcode")){
                // document.querySelector("#getcode").removeAttribute("disabled");
                // document.querySelector("#getcode").value = "获取动态码";
                wait = 0;
            }

        })

        // 拖拽
        // (1) 鼠标按下获得鼠标在盒子内的坐标
        title.addEventListener('mousedown',function(e){
            var x = e.pageX - login.offsetLeft;
            var y = e.pageY - login.offsetTop;
            // 鼠标移动，鼠标在页面中的坐标-鼠标在盒子的坐标
            document.addEventListener('mousemove',move)
            function move(e){
                login.style.left = e.pageX - x +'px';
                login.style.top = e.pageY - y +'px';
            }
            document.addEventListener('mouseup',function(){
                document.removeEventListener('mousemove',move)
            })
        })

    </script>

</body>
</html>
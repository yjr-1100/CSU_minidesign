<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="./js/jquery3.6.0.min.js"></script>
    <style>
        *{
            margin: 0;
            padding: 0;
        }
        body{
            background: url("./images/bg.png");
            height: 100%;
            width: 100%;
            background-size: cover;
            background-attachment: fixed;
        }
        .logo{
            margin-left: 5%;
            margin-top: 15px;
            height: 69px;
            width: 450px;
            position: relative;
            display: inline-block;
        }
        .logo img{
            width: 246px;
            height: 69px;
            margin: 10px;
            display: inline-block;
            position: absolute;
            left: 0;
        }
        .logo span{
            display: inline-block;
            height: 38px;
            line-height: 38px;
            margin-left: 15px;
            padding-left: 15px;
            border-left: 1px solid #fff;
            color: #fff;
            font-size: 24px;
            width: 150px;
            position: absolute;
            right: 0;
            top: 25px;
        }
        .main{
            background: rgba(245, 241, 241, 0.5);
            width: 80%;
            min-height: 500px;
            height: 60%;
            margin: 0 auto;
            margin-top: 50px;
            padding: 50px;
        }
        table{
            width: 80%;
            margin: 0 auto;
            font-size: 20px;
            border-collapse: collapse;
            border-top: 1px #585757 solid;
            border-right: 1px #585757 solid;
        }
        .main table td{
            width: 120px;
            text-align:center;
            border-bottom: 1px #585757 solid;
            border-left: 1px #585757 solid;
            padding: 10px;
        }
        .title{
            font-weight: bold;
        }
        input{
            border: none;
            background: none;
        }
        .changeuser,.deleteClass{
            color: #551a8b;
        }
        .changeuser:hover,.deleteClass:hover{
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

    </style>
</head>
<body>
    <div class="logo">
        <img src="./images/logoleft.png">
        <span>管理员中心</span>
    </div>
    <div class="main">
        <table>
            <tr class="title">
                <td>用户名</td>
                <td>密码</td>
                <td>电话</td>
                <td>邮箱</td>
                <td colspan="2">操作</td>
            </tr>
            <c:forEach items="${requestScope.userslist}" var="user" varStatus="s">
                <c:if test="${user.isadmain != 1}">
                    <tr class="user_wrap">
                        <td>${user.username}</td>
                        <td>${user.password}</td>
                        <td>${user.phonenum}</td>
                        <td>${user.mail}</td>
                        <td><p  class="changeuser">修改</p></td>
                        <td><a href="/webLogin_war_exploded/managerServlet?action=delete&username=${user.username}" class="deleteClass">删除</a></td>
                    </tr>
                </c:if>
            </c:forEach>

            <tr>
				<td colspan="6"><a href="javascript:;" id="adduser">添加用户</a></td>
			</tr>
        </table>


    </div>
    <div class="changebox">
        <div id="login" class="login">
            <div class="login-title" id="title"><p>用户修改</p>
                <span><a href="javascript:void(0)" class="close-login" id="closeBtn">关闭</a></span>
            </div>
            <div class="login-input-content">
                <div class="login-input">
                    <label ><p>用户名：</p></label>
                    <input type="text"  id="uname" class="list-input">
                </div>
                    <div class="login-input">

                        <label ><p>登录密码：</p></label>

                        <input type="text"  id="upwd" class="list-input">

                    </div>
                    <div class="login-input">

                        <label ><p>电话：</p></label>

                        <input type="text"  id="uphone" class="list-input">

                    </div>
                    <div class="login-input">

                        <label ><p>邮箱：</p></label>

                        <input type="text"  id="umail" class="list-input">

                    </div>


            </div>
            <div class="login-button" id="loginBtn" style="background-color: #2b6cdb;font-size: 16px"><a href="javascript:void(0);"id='login-button-submit' style="color: #fff;">确认</a></div>
        </div>
        <!-- 遮挡层 -->
        <div class="login-bg" id="bg"></div>
    </div>
    <script>
        let rmuserlist = document.querySelectorAll(".deleteClass");
        let changeuserlist = document.querySelectorAll(".changeuser");
        let adduser = document.querySelector("#adduser");
        let user_wrap = document.querySelectorAll(".user_wrap")
        let login = document.querySelector(".login");
        let mask = document.querySelector(".login-bg");
        let title = document.querySelector("#title")
        let headtitle = title.querySelector('p');
        // 2. 点击弹出层这个link
        let oldusername;
        for(let i=0;i<changeuserlist.length;i++){
            changeuserlist[i].addEventListener("click",function (){
                headtitle.innerText = "用户修改"
                login.style.display = 'block';
                mask.style.display = 'block';
                oldusername = this.parentNode.parentNode.querySelectorAll("td")[0].innerText;
                document.querySelector('#uname').value =this.parentNode.parentNode.querySelectorAll("td")[0].innerText;
                document.querySelector('#upwd').value =this.parentNode.parentNode.querySelectorAll("td")[1].innerText;
                document.querySelector('#uphone').value =this.parentNode.parentNode.querySelectorAll("td")[2].innerText;
                document.querySelector('#umail').value =this.parentNode.parentNode.querySelectorAll("td")[3].innerText;
            })
        }
        document.querySelector("#uname").addEventListener("blur",function (){
            if(!/^[a-zA-Z0-9]{6,10}$/.test(this.value)){
                alert("请输入正确的用户名")
            }
        })
        document.querySelector("#upwd").addEventListener("blur",function (){
            if(!/^\w{6,11}$/.test(this.value)){
                alert("请输入正确的密码")
            }
        })
        document.querySelector("#uphone").addEventListener("blur",function (){
            console.log(this)
            if(!/^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/.test(this.value)){
                alert("请输入正确的手机号")
            }
        })
        document.querySelector("#umail").addEventListener("blur",function (){
            if(!/^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/.test(this.value)){
                alert("请输入正确的邮箱")
            }
        })
        // 确认
        document.querySelector("#loginBtn").addEventListener('click',function(){
            console.log(headtitle)
            let uname = document.querySelector('#uname').value
            let upwd=document.querySelector('#upwd').value
            let uphone=document.querySelector('#uphone').value
            let umail=document.querySelector('#umail').value
            if(headtitle.innerText ==="用户修改"){
                sendajax("userchange",uname,upwd,uphone,umail,oldusername)
            }else if(headtitle.innerText ==="添加用户"){
                sendajax("useradd",uname,upwd,uphone,umail)
            }


        })
        let sendajax = function (action,uname,upwd,uphone,umail,oldusername){
            $.ajax({
                url:"/webLogin_war_exploded/managerServlet",//要请求的服务器url
                data:{
                    action:action,
                    oldusername:oldusername,
                    username:uname,
                    password:upwd,
                    phonecode:uphone,
                    mail:umail,
                },
                async:true,//是否是异步请求
                cache:false,//是否缓存结果
                type:"GET",//请求方式
                dataType:"json",//服务器返回什么类型数据 text xml javascript json(javascript对象)
                success:function(result){//函数会在服务器执行成功后执行，result就是服务器返回结果
                    console.log(result)
                    console.log(result.msg)
                    if(result.statu === -1){
                        alert(result.msg)

                    }else if(result.statu ===1){
                        alert(result.msg)
                        window.location="/webLogin_war_exploded/managerServlet?action=list"
                    }
                }
            });
        }
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
        // 点击关闭隐藏
        document.querySelector('#closeBtn').addEventListener('click',function(){
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
                document.querySelector("#getcode").removeAttribute("disabled");
                document.querySelector("#getcode").value = "获取动态码";
                wait = 60;
            }

        })
        // 添加用户
        adduser.addEventListener("click",function (){
            headtitle.innerText = "添加用户"
            login.style.display = 'block';
            mask.style.display = 'block';
            oldusername = this.parentNode.parentNode.querySelectorAll("td")[0].innerText;
            document.querySelector('#uname').value =""
            document.querySelector('#upwd').value =""
            document.querySelector('#uphone').value =""
            document.querySelector('#umail').value =""
        })
        console.log("${param.result}")
        <c:if test="${param.result} eq 1">
            alert("删除成功")
        </c:if>
        <c:if test="${param.result} == 0">
            alert("删除失败")
        </c:if>
    </script>
</body>
</html>
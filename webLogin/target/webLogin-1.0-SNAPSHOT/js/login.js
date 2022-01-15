window.onload = function(){
    let loginWay = document.querySelector(".loginWay")
    let ways = loginWay.children
    let ways_wraps  = loginWay.parentNode.children
    let tips = document.querySelector(".tips")
    console.log(ways_wraps)
    console.log(tips)
    for(let i=0;i<ways.length;i++){
        ways[i].addEventListener('click',function(){
            for(let j=0;j<ways.length;j++){
                ways[j].className=""
                ways_wraps[j+1].style.display="none"
            }
            ways[i].className="wayactive"
            ways_wraps[i+1].style.display = "block"
            /* if(i == 1){
                tips.style.display="none"
            }else{
                tips.style.display="flex"
            } */
        })
    }
    let img = document.querySelector("#chekcode");
    img.onclick = function (){
        let date = new Date().getTime();
        img.src = "/webLogin_war_exploded/ChekCodeServlet?"+date
    }


    document.querySelector(".phoneLog .loginbtn").addEventListener('click',function (){
        if($(".phoneLog .username input").val() ===""){
            alert("请输入手机号/邮箱")
        }else if($(".phoneLog .chekcode input").val() === ""){
            alert("请输入图片验证码")
        }
        else if($("#lala").val() === ""){
            alert("请输入动态码")
        }else{
            console.log($(".phonecode input").val())
            $.post("http://localhost:8080/webLogin_war_exploded/loginServlet", {phonecode:$("#phonenum").val(), randomcode:$(".phonecode input").val()},function(data){
                let jsonre = JSON.parse(data)
                console.log(jsonre)
                if(jsonre.statu ==0){
                    // 登录失败
                    alert("登录失败，动态码无效")
                    let date = new Date().getTime();
                    img.src = "/webLogin_war_exploded/ChekCodeServlet?"+date
                    document.querySelector(".chekcode input").value=""
                    document.querySelector("#lala").value=""

                }else if(jsonre.statu ==1){
                    //绑定了账号的user，跳去成功页面
                    window.location="/webLogin_war_exploded/index.jsp"

                }else if(jsonre.statu ==-1){
                    //没有绑定账号的用户，跳去绑定账号
                    window.location="/webLogin_war_exploded/userbind.jsp?num="+document.querySelector("#phonenum").value

                }

            });
        }
    })
}
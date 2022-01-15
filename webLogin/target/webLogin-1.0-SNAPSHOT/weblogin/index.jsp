<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--<!DOCTYPE html>-->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/banner.css">
    <link rel="stylesheet" href="./css/head.css">
    <link rel="stylesheet" href="./css/row-a1.css">
    <link rel="stylesheet" href="./css/fright.css">
    <link rel="stylesheet" href="./css/back.css">
    <title>中南大学</title>
    <script src="./js/head.js"></script>
</head>
<body>
<div class="main">
    <!--    头部-->
    <div class="headenghead"></div>
    <div class="head">
        <!-- 搜索框和搜索框隐藏 -->
        <div class="searchBox">
            <div class="search">
                <input type="text" class="inputtext">
                <input type="button" class="subbtn">
            </div>
            <div class="closesearchbtn"></div>
        </div>
        <div class="searchbtn"></div>
        <!-- 搜索结束  -->

        <!--  -->
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
                            System.out.println("=============");
                            System.out.println(v);
                            System.out.println(v.split("=")[0]);
                            System.out.println(v.split("=")[1]);
                            System.out.println("=============");
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
        <div class="headwrap">
            <div class="top">
                <div class="top-l">
                    <ul>
                        <li><a href="">学生</a></li>
                        <li><a href="">教职工</a></li>
                        <li><a href="">未来学生</a></li>
                        <li><a href="">校友校董</a></li>
                        <li><a href="">招聘</a></li>
                    </ul>
                </div>
                <div class="top-r">
                    <ul>
                        <script>
                            console.log("${requestScope.jsonuser.username}")
                        </script>
                        <c:if test="${not empty requestScope.jsonuser.username !='null'}">
                            <li><a href="/webLogin_war_exploded/user.jsp" style="color: red;font-weight: 600;font-size: 16px">${requestScope.jsonuser.username}欢迎您</a></li>
                        </c:if>

                        <c:if test="${empty jsonuser.username}">
                            <li><a href="/webLogin_war_exploded/login.jsp">登录</a></li>
                        </c:if>

                        <li id=""><a href="">研究机构</a><i><img src="./images/i-down1.png" alt=""></i>
                            <div class="dd " id="yanjiu" myheight=400>
                                <a href="http://hpcm.csu.edu.cn/" target="_blank">高性能复杂制造国家重点实验室</a>
                                <a href="http://sklpm.csu.edu.cn/" target="_blank">粉末冶金国家重点实验室</a>
                                <a href="http://zjsgczx.csu.edu.cn/" target="_blank">重金属污染防治国家工程技术研究中心</a>
                                <a href="http://ncrcgd.xiangya.com.cn" target="_blank">国家老年疾病临床医学研究中心</a>
                                <a href="https://ncrcmdxy.csu.edu.cn/index.html" target="_blank">国家精神心理疾病临床医学研究中心</a>
                                <a href="http://ncrc.csu.edu.cn/" target="_blank">国家代谢性疾病临床医学研究中心</a>
                                <a href="http://www.hngxjk.com/intro/26.html" target="_blank">人类干细胞国家工程研究中心</a>
                                <a href="http://pmri.csu.edu.cn/" target="_blank">粉末冶金国家工程研究中心</a>
                                <a href="https://bdi.csu.edu.cn/" target="_blank">医疗大数据应用技术国家工程实验室</a>
                                <a href="http://rnmlab.csu.edu.cn/" target="_blank">难冶有色金属资源高效利用国家工程实验室</a>
                                <a href="http://hsrlab.csu.edu.cn/" target="_blank">高速铁路建造技术国家工程实验室</a>
                                <a href="http://wx11.jiaodaoren.com" target="_blank">药物临床评价技术国家地方联合工程实验室</a>
                                <a href="https://lari.csu.edu.cn/" target="_blank">有色金属先进结构材料与制造协同创新中心</a>
                                <a href="https://www.xyeyy.com/5/38/58/index.htm"
                                   target="_blank">精神疾病诊疗技术国家地方联合工程实验室</a>
                                <a href="http://www.xyproteomics.org/" target="_blank">抗癌药物国家地方联合工程实验室</a>
                                <a href="http://stte.csu.edu.cn/gd2018/" target="_blank">轨道交通列车安全保障技术国家地方联合工程研究中心</a>
                                <a href="http://2011rts.bjtu.edu.cn/" target="_blank">轨道交通安全协同创新中心</a>
                                <a href="http://zyxh.csu.edu.cn/platformabout/548.html" target="_blank">有色金属资源循环利用国家地方联合工程研究中心</a>
                                <a href="https://lari.csu.edu.cn/" target="_blank">国家高性能铝材工程化与创新能力建设平台</a>
                            </div>
                        </li>
                        <li><a href="">教育机构</a><i><img src="./images/i-down1.png" alt=""></i>
                            <div class="dd" myheight=490>
                                <a href="https://clj.csu.edu.cn" target="_blank">文学与新闻传播学院</a>
                                <a href="http://sfl.csu.edu.cn/" target="_blank">外国语学院</a>
                                <a href="http://art.csu.edu.cn/" target="_blank">建筑与艺术学院</a>
                                <a href="http://bs.csu.edu.cn/" target="_blank">商学院</a>
                                <a href="http://law.csu.edu.cn/" target="_blank">法学院</a>
                                <a href="http://ps.csu.edu.cn/" target="_blank">马克思主义学院</a>
                                <a href="http://csuspa.csu.edu.cn/" target="_blank">公共管理学院</a>
                                <a href="http://math.csu.edu.cn/" target="_blank">数学与统计学院</a>
                                <a href="http://wl.csu.edu.cn/" target="_blank">物理与电子学院</a>
                                <a href="http://ccce.csu.edu.cn/" target="_blank">化学化工学院</a>
                                <div style="width:50%;float:left;">
                                    <a href="http://cmee.csu.edu.cn/" target="_blank" style="width:auto;">机电工程学院</a>
                                    <a href="http://lari.csu.edu.cn" target="_blank" style="width:auto;">(轻合金研究院)</a>
                                </div>
                                <a href="http://energy.csu.edu.cn" target="_blank">能源科学与工程学院</a>
                                <a href="http://mse.csu.edu.cn/" target="_blank">材料科学与工程学院</a>
                                <a href="http://pmri.csu.edu.cn/" target="_blank">粉末冶金研究院</a>
                                <a href="http://stte.csu.edu.cn/" target="_blank">交通运输工程学院</a>
                                <a href="http://civil.csu.edu.cn/" target="_blank">土木工程学院</a>
                                <a href="http://smse.csu.edu.cn/" target="_blank">冶金与环境学院</a>
                                <a href="http://gip.csu.edu.cn/" target="_blank">地球科学与信息物理学院</a>
                                <a href="http://srse.csu.edu.cn/" target="_blank">资源与安全工程学院</a>
                                <a href="http://mpb.csu.edu.cn" target="_blank">资源加工与生物工程学院</a>
                                <a href="http://soa.csu.edu.cn/" target="_blank">自动化学院</a>
                                <div style="width:50%;float:right;">
                                    <a href="http://cse.csu.edu.cn/" target="_blank" style="width:auto;">计算机学院</a>
                                    <a href="https://bdi.csu.edu.cn/" target="_blank" style="width:auto;">(大数据研究院)</a>
                                </div>
                                <a href="http://saa.csu.edu.cn/" target="_blank">航空航天学院</a>
                                <a href="http://sports.csu.edu.cn/" target="_blank">体育教研部</a>
                                <a href="http://jcyxy.csu.edu.cn/" target="_blank">基础医学院</a>
                                <a href="http://sph.csu.edu.cn/" target="_blank">湘雅公共卫生学院</a>
                                <a href="https://xynursing.csu.edu.cn/" target="_blank">湘雅护理学院</a>
                                <a href="http://xykqyy.csu.edu.cn/" target="_blank">湘雅口腔医学院</a>
                                <a href="http://yxy.csu.edu.cn/" target="_blank">湘雅药学院</a>
                                <a href="http://life.csu.edu.cn/" target="_blank">生命科学学院</a>
                            </div>
                        </li>
                        <li><a href="">图书馆</a></li>
                        <li><a href="">信息门户</a></li>
                    </ul>
                </div>
            </div>
            <div class="nav">
                <div class="nav-l">
                    <ul>
                        <li>
                            <div class="v1">走进中南</div>
                            <div class="sub dh2">
                                <dl class="clearfix">
                                    <dd><a href="zjzn/xxgk.htm"><i></i>学校概况</a></dd>

                                    <dd><a href="zjzn/lsyg.htm"><i></i>历史沿革</a></dd>

                                    <dd><a href="zjzn/xxld.htm"><i></i>学校领导</a></dd>

                                    <dd><a href="zjzn/jgsz.htm"><i></i>机构设置</a></dd>

                                    <dd><a href="zjzn/sjzn.htm"><i></i>数据中南</a></dd>

                                </dl>
                                <div class="navpic"><a href=""><img src="./images/navpic.jpg" alt=""></a></div>
                            </div>
                        </li>
                        <li>
                            <div class="v1">教育教学</div>
                            <div class="sub  dh3">
                                <dl class="clearfix">
                                    <dd><a href="https://bksy.csu.edu.cn" target="_blank"><i></i>本科生教育</a></dd>
                                    <dd><a href="http://gra.csu.edu.cn" target="_blank"><i></i>研究生教育</a></dd>
                                    <dd><a href="https://intl.csu.edu.cn/zwb.htm" target="_blank"><i></i>留学生教育</a></dd>
                                    <dd><a href="http://sce.csu.edu.cn/" target="_blank"><i></i>继续教育</a></dd>
                                    <dd><a href="http://netclass.csu.edu.cn/jpkc/" target="_blank"><i></i>精品课程</a></dd>
                                </dl>
                                <div class="navpic"><a href=""><img src="./images/navpic3.jpg" alt=""></a></div>
                            </div>
                        </li>
                        <li>
                            <div class="v1">学院学科</div>
                            <div class="sub  dh4">
                                <dl class="clearfix">
                                    <dd><a href="xyxk1/ejxy.htm"><i></i>二级学院</a></dd>

                                    <dd><a href="xyxk1/xkzy.htm"><i></i>学科专业</a></dd>

                                </dl>
                                <div class="navpic"><a href=""><img src="./images/navpic4.jpg" alt=""></a></div>
                            </div>
                        </li>
                        <li>
                            <div class="v1">师资队伍</div>
                            <div class="sub  dh5">
                                <dl class="clearfix">
                                    <dd><a href="szdw3/lyys.htm#abc"><i></i>两院院士</a></dd>

                                    <dd><a href="szdw3/gjjq.htm#abc"><i></i>国家杰青</a></dd>

                                    <dd><a href="szdw3/gjyq.htm#abc"><i></i>国家优青</a></dd>

                                </dl>
                                <div class="navpic"><a href=""><img src="./images/navpic5.jpg" alt=""></a></div>
                            </div>
                        </li>
                    </ul>
                </div>
                <div href="csu.html" class="logo">
                    <img src="./images/logo.png" class="logo1" alt="">
                    <img src="./images/logo-1.png"  class="logo2" alt="">
                    <!-- <img src="./images/logo2.png" alt=""> -->
                </div>
                <div class="nav-r">
                    <ul>
                        <li>
                            <div class="v1">学生天地</div>
                            <div class="sub  dh9">
                                <dl class="clearfix">
                                    <dd><a href="http://xgw.csu.edu.cn" target="_blank"><i></i>学生工作网</a></dd>
                                    <dd><a href="http://54sh.csu.edu.cn" target="_blank"><i></i>升华网</a></dd>
                                    <dd><a href="http://chinadxscy.csu.edu.cn" target="_blank"><i></i>中国大学生创业网</a></dd>
                                    <dd><a href="http://ylyn.csu.edu.cn/index.php/login/in.html" target="_blank"><i></i>缘来友你</a>
                                    </dd>
                                </dl>
                                <div class="navpic"><a href="javascript:;"><img src="./images/navpic9.jpg" alt=""></a>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="v1">合作交流</div>
                            <div class="sub  dh8">
                                <dl class="clearfix">
                                    <dd><a href="http://iecd.csu.edu.cn/jlhz.htm" target="_blank"><i></i>海外交流</a></dd>
                                    <dd><a href="http://iecd.csu.edu.cn/cgcj.htm" target="_blank"><i></i>出国出境</a></dd>
                                    <dd><a href="http://iecd.csu.edu.cn/hwzj.htm" target="_blank"><i></i>海外专家</a></dd>
                                    <dd><a href="https://intl.csu.edu.cn/zwb.htm" target="_blank"><i></i>留学中南</a></dd>
                                    <dd><a href="http://iecd.csu.edu.cn/lxhw.htm" target="_blank"><i></i>留学海外</a></dd>
                                    <dd><a href="http://iecd.csu.edu.cn/gatsw.htm" target="_blank"><i></i>港澳台事务</a></dd>
                                </dl>
                                <div class="navpic"><a href=""><img src="./images/navpic8.jpg" alt=""></a></div>
                            </div>
                        </li>
                        <li>
                            <div class="v1">招生就业</div>
                            <div class="sub  dh7">
                                <dl class="clearfix">
                                    <dd><a href="http://zhaosheng.csu.edu.cn" target="_blank"><i></i>本科生招生</a></dd>
                                    <dd><a href="http://gra.its.csu.edu.cn/yjsy/" target="_blank"><i></i>研究生招生</a></dd>
                                    <dd><a href="https://intl.csu.edu.cn/zwb/lhlx.htm" target="_blank"><i></i>留学生招生</a>
                                    </dd>
                                    <dd><a href="http://sce.csu.edu.cn/" target="_blank"><i></i>继续教育招生</a></dd>
                                    <dd><a href="http://career.csu.edu.cn" target="_blank"><i></i>就业信息网</a></dd>
                                    <dd><a href="http://chinadxscy.csu.edu.cn" target="_blank"><i></i>中国大学生创业网</a></dd>
                                </dl>
                                <div class="navpic"><a href=""><img src="./images/navpic7.jpg" alt=""></a></div>
                            </div>
                        </li>
                        <li>
                            <div class="v1">科学研究</div>
                            <div class="sub  dh6">
                                <dl class="clearfix">
                                    <dd><a href="https://kxyjb.csu.edu.cn/index/tzgg.htm"
                                           target="_blank"><i></i>科研通知</a></dd>
                                    <dd><a href="https://kexie.csu.edu.cn/" target="_blank"><i></i>科学技术协会</a></dd>
                                    <dd><a href="https://kxyjb.csu.edu.cn/xswyh.htm" target="_blank"><i></i>学术委员会</a>
                                    </dd>
                                    <dd><a href="https://kxyjb.csu.edu.cn/xfjs.htm" target="_blank"><i></i>学风建设</a></dd>
                                    <dd><a href="https://kxyjb.csu.edu.cn/xxgk/bszn.htm" target="_blank"><i></i>信息公开</a>
                                    </dd>
                                </dl>
                                <div class="navpic"><a href=""><img src="./images/navpic6.jpg" alt=""></a></div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!--    轮播图-->
    <div class="banner">
        <ul class="">
            <li class="opc_on">
                <a href="http://news.csu.edu.cn/jczt/dsxxjyztw.htm" class="" target="_blank"
                   style="background-image: url(./images/banner20210528_wsbsdtsx.jpg);">
                </a>
            </li>
            <li>
                <a href="http://news.csu.edu.cn/jczt/dsxxjyztw.htm" class="" target="_blank"
                   style="background-image: url(./images/banner20210602_dj.png);">
                </a>
            </li>
            <li>
                <a href="http://news.csu.edu.cn/jczt/dsxxjyztw.htm" class="" target="_blank"
                   style="background-image: url(./images/hyjs.jpg);">
                </a>
            </li>
            <li>
                <a href="http://news.csu.edu.cn/jczt/dsxxjyztw.htm" class="" target="_blank"
                   style="background-image: url(./images/banner20201230_fayi.jpg);">
                </a>
            </li>
        </ul>
        <ol class="slick-dots" style="display: block;" role="tablist">
            <li aria-controls="navigation00" id="slick-slide00" class="activate"></li>
            <li id="slick-slide01" class=""></li>
            <li id="slick-slide02" class=""></li>
            <li id="slick-slide03" class=""></li>
        </ol>
    </div>
    <!--    中南要闻-->
    <div class="row-a1">
            假装这是中南要闻，不想写了，占个位置方便演示回到顶部，下面直接写鼠标移动上去变大
    </div>
    <!--    右侧浮窗-->
    <div class="fright on">
        <ul>
            <li class="li1">
                <a href="http://tz.its.csu.edu.cn"  target="_blank">
                    <i class="i1"></i>校内通知
                </a>
            </li>
            <li class="li2"><a href="http://oa.its.csu.edu.cn/Home/ReleaseXNGW"  target="_blank">
                    <i class="i2"></i>校内公文
                </a>
            </li>
            <li class="li3">
                <a href="http://oa.its.csu.edu.cn/Home/ReleaseMZHY"  target="_blank">
                    <i class="i3"></i>每周会议
                </a>
            </li>
            <li class="li4">
                <a href="http://oa.its.csu.edu.cn/Home/ReleaseZDXX"  target="_blank">
                    <i class="i4"></i>中大信息
                </a>
            </li>
            <li class="li5">
                <a href="https://mail.csu.edu.cn"  target="_blank">
                    <i class="i5"></i>中南邮箱
                </a>
            </li>
            <li class="li6">
                <a href="https://my.csu.edu.cn/" target="_blank">
                    <i class="i6"></i>信息门户
                </a>
            </li>
            <li class="li7">
                <a href="https://ehall.csu.edu.cn/v2/site/index" >
                <i class="i7"></i>办事大厅
            </a>
            </li>
            <span class="down"><i></i></span>
        </ul>
    </div>

    <!-- 专题网站 -->
    <div class="back">
        <div class="imgwrap">
            <h3>专题网站</h3>
            <div><img src="./images/pic-q3.jpg" alt=""></div>
            <div><img src="./images/pic-q4.jpg" alt=""></div>
            <div><img src="./images/pic-q5.jpg" alt=""></div>
            <div><img src="./images/pic-q6.jpg" alt=""></div>
            <div><img src="./images/pic-q7.jpg" alt=""></div>
        </div>
    </div>

    <!-- goback -->
    <a class="goBack"></a>
    <script>
        let fright = document.querySelector('.fright')
        fright.addEventListener('click',function () {
            if(this.className=="fright on"){
                this.className = "fright"
            }else if(this.className == "fright"){
                this.className="fright on"
            }
        })
    </script>
</div>
<script src="./js/banner.js"></script>
</body>
</html>
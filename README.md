# CSU_minidesign （校园导航，贪吃蛇，邮箱客户端，模拟数据库，csuweb实验）
本仓库存放一些CSU的简单课设

### 1.CSU校园导航（QT）
图论相关的数据结构，代码冗余量较多，但是懒的修了，如果哪位好心人看到了，欢迎修一修



### **2.极简主义贪吃蛇（JAVA）**

用一用 JFrame , 锻炼一下思路，贪吃蛇还是童年玩的样子



### 3.**模拟文件系统的数据库**(JAVA)

实现一个应用程序，包含下列功能： 
1、输入并存储10个学生的信息，3门课程的信息，和30人次的选修信息（初始时成绩为0）。 
2、根据给定的学号和课程编号，修改成绩。 
3、实现如下查询：      
	1)根据输入的学号，查询并显示某学生的基本信息；     
	2)根据输入的课程编号，查询并显示某课程的基本信息；    
	3)根据输入的学号，查询并显示该学生的姓名、选修的所有课程的名称及成绩；     
	4)根据给定的学号，查询并显示该生的平均成绩。
4、根据给定的学号，删除某学生的基本信息。

没有添加错误处理，因为没有很长时间去做，如果有好心人看到(估计没有人会看这个拉跨的东西）希望可以加一加



### 4.邮箱客户端(JAVA)

实现一个应用程序，客户端邮箱功能

1. 使用网易邮箱账号登录，注意网易邮箱需要开启SMTP/POP3,使用生成的密钥来登录。
2. 可以接收邮件，显示收信箱，可以读邮件，删除邮件，下载附件
3. 可以编辑邮件，发送邮件，添加附件

部分地方添加了错误处理，还有很多没有添加，由于课设写的匆忙，代码没有很高的独立逻辑模块划分，读起来略显麻烦，什么时候闲了想起来在回来看吧



### 5.webLogin（JavaWeb/Jsp/Html/Css/Javascript	）
上课javaweb的实验，本次实验任务为实现一个登录页面。具体要求如下：
1. 实现前端页面的基本布局。要求：
   1. 布局类似于学校门户 http://my.csu.edu.cn/login/index.jsp
   2. 顶部需有 LOGO 栏目；
   3.  左侧提供轮播图；
   4.  提供账号密码登录方式；
   5.  提供手机号码（邮箱）+验证码登录方式；
   6.  登录成功后跳到显示“登录成功”四字的页面（简单设计）；
   7. *提供忘记密码和修改密码功能；
   8. *提供用户的增删改查。 
2. 完成前后端数据交互（用 JSON 格式）
3. 数据操作要求：
   1. 数据统一存储在后端数据库中；
   2.  账号密码登录方式需进行验证，验证通过方能登录；
   3.  手机（邮箱）验证码需调用第三方短信接口发送验证码并进行验证；
   4.  后端实现技术不限、数据库系统不限。

**使用注意**

1. 使用mysql数据库，创建数据库web_shiyan和表user

   ```
   CREATE TABLE `web_shiyan`.`user`  (
     `id` int NOT NULL AUTO_INCREMENT,
     `username` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
     `password` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
     `phonenum` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
     `mail` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
     `isadmain` int NULL DEFAULT 0,
     PRIMARY KEY (`id`) USING BTREE
   ) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;
   ```

2. 修改数据库配置 

   ./webLogin/src/druid.properties 中的 username 和 password 

3. 短信验证码使用[臻子云](https://blog.csdn.net/weixin_45691686/article/details/121654353)，需要注册并且填写相关信息 

   填写 ./webLogin/src/main/java/yjr1100/utils/PhoneCode.java 中的 appId 和 appSecret

这依旧是一个有bug的实验，数据库通过注册代码进行插入应该是没有问题的，手动修改可能会有报错，代码有些许冗余，没有使用前端和后端框架。


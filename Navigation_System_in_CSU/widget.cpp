#include "widget.h"
#include "digraph.h"
#include "ui_widget.h"
#include <QPixmap>
#include <QString>
#include <QDialog>
#include <QDebug>
#include <QLabel>
#include <QPushButton>
#include <QPainter>
#include <QPoint>
#include <QPen>

Widget::Widget(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Widget)
{
    ui->setupUi(this);

    this->setWindowTitle("校园导航首页");
    //主窗口背景图片
    bglabel = ui->m_QLabel_BG;
    bglabel->setPixmap(QPixmap(":/img/CSU_main.jpg"));
    bglabel->setScaledContents(true);
    //欢迎词
    wclabel = ui->welcom_Label;
    wclabel->setText("欢迎来到校园导航系统！");
    wclabel->setFont(QFont("楷体",24,10,1));
    wclabel->setStyleSheet("color:red");
    //校区选择
    schoolmap = ui->schoolarea;
    schoolmap->setText("请选择校区：");
    schoolmap->setFont(QFont("楷体",16,10,0));
    schoolmap->setStyleSheet("color:red");
    //下拉菜单
    sltmap = ui->eare;
    sltmap->addItem("  南校区");
    sltmap->addItem("  新校区");
    sltmap->addItem("  校本部");
    sltmap->setFont(QFont("楷体",16,12,0));
    sltmap->setStyleSheet("QComboBox{background-color:	#F0F8FF;color:#242424;border: none;cursor: pointer;}\
                            QComboBox::drop-down{ border-style: none;}");
    //确定按钮
    btdh = ui->btdaohang;
    btdh->setText("确定");
    btdh->setFont(QFont("楷体",14,12,0));
    btdh->setStyleSheet("QPushButton{background-color:#F0F8FF; boder:none; color:	#242424;-webkit-transition-duration:0.4S}\
                         QPushButton:hover{background-color:#B0CEEE; color:#242424;}");

    //打开地图---------------------------------------------
    connect(btdh,&QPushButton::clicked,this,[this](){
        QString Title = sltmap->currentText();
        if(Title=="  南校区"){ this->window_in_nxq(Title); }
        else if(Title=="  新校区"){ this->window_in_nxq(Title); }
        else{ this->window_in_xbb(Title); }
    });
    //------------------------------------------------------
}


void Widget::window_in_xbb(QString title){
        //创建窗口
        Widget* dialog = new Widget();
        auto xbbmap = new Digraph(dialog,14,11);//校本部的有14条边，11个点
        dialog->move(700,70);
        dialog->resize(860,840);
        dialog->show();
        qDebug() << title;
        dialog->setWindowTitle(title);
        //背景图片
        QLabel* bglabel = new QLabel(dialog);
        bglabel->move(0,0);
        bglabel->setPixmap(QPixmap(":/img/xbb.jpg"));
        bglabel->resize(dialog->frameGeometry().width(),dialog->frameGeometry().height());
        bglabel->setScaledContents(true);
        bglabel->setVisible(true);
        //所有景点查询显示框

        //景点介绍
        QPushButton* btn1 = new QPushButton(dialog); btn1->setText("  ");
        btn1->move(220,35); btn1->setFont(QFont("楷体",14,12,0)); btn1->setStyleSheet("background:transparent;");
        btn1->setVisible(true); //btn1->setFlat(true);
        connect(btn1,&QPushButton::clicked,xbbmap,[=](){
             QWidget* btn1widget = new QWidget(); btn1widget->move(883,89);btn1widget->resize(540,520);
             QLabel* bglabel = new QLabel(btn1widget);
             bglabel->move(0,0);
             bglabel->setPixmap(QPixmap(":/img/ss.jpg"));
             bglabel->setScaledContents(true);
             bglabel->resize(540,540);
             bglabel->setVisible(true);
             btn1widget->setWindowTitle("桃花学生公寓");
             QLabel * btn1label = new QLabel(btn1widget);
             btn1label->setText("  一号景点\n  桃花公寓，环境优美\n");
             btn1label->setFont(QFont("楷体",24,10,1));
             btn1label->setStyleSheet("color:orange");
             btn1widget->show();
        });
        QPushButton* btn2 = new QPushButton(dialog);  btn2->setText("  "); btn2->move(46,385);
        btn2->setFont(QFont("楷体",14,12,0)); btn2->setStyleSheet("background:transparent;");
        btn2->setVisible(true);//btn2->setFlat(true);
        connect(btn2,&QPushButton::clicked,xbbmap,[=](){
            QWidget* btn2widget = new QWidget(); btn2widget->move(883,89); btn2widget->resize(540,540);
            QLabel* bglabel = new QLabel(btn2widget);
             bglabel->move(0,0);
             bglabel->setPixmap(QPixmap(":/img/xy.jpeg"));
             bglabel->resize(540,540);
             bglabel->setVisible(true);
             btn2widget->setWindowTitle("西苑");
             QLabel * btn2label = new QLabel(btn2widget);
             btn2label->setText("二号景点\n西苑\n环境优美，美食多\n人们用双手创造财富");
             btn2label->setFont(QFont("楷体",24,10,1));
             btn2label->setStyleSheet("color:yellow");
             btn2widget->show();
        });
        QPushButton* btn3 = new QPushButton(dialog);   btn3->setText("  ");  btn3->move(310,155);
        btn3->setFont(QFont("楷体",14,12,0));  btn3->setStyleSheet("background:transparent;");
        btn3->setVisible(true); // btn3->setFlat(true);
        connect(btn3,&QPushButton::clicked,xbbmap,[=](){
             QWidget* btn3widget = new QWidget(); btn3widget->move(883,89);  btn3widget->resize(540,540);
             btn3widget->setWindowTitle("学生食堂");  QLabel * btn3label = new QLabel(btn3widget);
             btn3label->setText("三号景点\n学生食堂，美食多多");
             btn3widget->show();
        });
        QPushButton* btn4 = new QPushButton(dialog);   btn4->setText("  ");  btn4->move(220,540);
        btn4->setFont(QFont("楷体",14,12,0));  btn4->setStyleSheet("background:transparent;");
        btn4->setVisible(true); // btn4->setFlat(true);
        connect(btn4,&QPushButton::clicked,xbbmap,[=](){
             QWidget* btn4widget = new QWidget(); btn4widget->move(883,89);   btn4widget->resize(540,540);
             btn4widget->setWindowTitle("桃花学生公寓");  QLabel * btn4label = new QLabel(btn4widget);
             btn4label->setText("四号景点\n体育馆，不受天气的影响，可以更好的锻炼");
             btn4widget->show();
        });

        QPushButton* btn5 = new QPushButton(dialog);   btn5->setText("  ");  btn5->move(200,275);
        btn5->setFont(QFont("楷体",14,12,0));  btn5->setStyleSheet("background:transparent;");
        btn5->setVisible(true); // btn5->setFlat(true);
        connect(btn5,&QPushButton::clicked,xbbmap,[=](){
             QWidget* btn5widget = new QWidget(); btn5widget->move(883,89);   btn5widget->resize(540,540);
             btn5widget->setWindowTitle("桃花学生公寓");  QLabel * btn5label = new QLabel(btn5widget);
             btn5label->setText("五号景点\n运动场，不论风吹日晒，总用运动健儿在这里锻炼");
             btn5widget->show();
        });

        QPushButton* btn6 = new QPushButton(dialog);   btn6->setText("  ");  btn6->move(500,475);
        btn6->setFont(QFont("楷体",14,12,0));  btn6->setStyleSheet("background:transparent;");
        btn6->setVisible(true);  //btn6->setFlat(true);
        connect(btn6,&QPushButton::clicked,xbbmap,[=](){
             QWidget* btn6widget = new QWidget(); btn6widget->move(883,89);   btn6widget->resize(540,540);
             btn6widget->setWindowTitle("桃花学生公寓");  QLabel * btn6label = new QLabel(btn6widget);
             btn6label->setText("六号景点\n东家坪社区，环境优美");
             btn6widget->show();
        });

        QPushButton* btn7 = new QPushButton(dialog);   btn7->setText("  ");  btn7->move(455,365);
        btn7->setFont(QFont("楷体",14,12,0));  btn7->setStyleSheet("background:transparent;");
        btn7->setVisible(true); // btn7->setFlat(true);
        connect(btn7,&QPushButton::clicked,xbbmap,[=](){
             QWidget* btn7widget = new QWidget(); btn7widget->move(883,89);  btn7widget->resize(540,540);
             btn7widget->setWindowTitle("桃花学生公寓");  QLabel * btn7label = new QLabel(btn7widget);
             btn7label->setText("七号景点\n和平广场，环境优美");
             btn7widget->show();
        });

        QPushButton* btn8 = new QPushButton(dialog);   btn8->setText("  ");  btn8->move(420,235);
        btn8->setFont(QFont("楷体",14,12,0));  btn8->setStyleSheet("background:transparent;");
        btn8->setVisible(true); // btn8->setFlat(true);
        connect(btn8,&QPushButton::clicked,xbbmap,[=](){
             QWidget* btn8widget = new QWidget(); btn8widget->move(883,89);   btn8widget->resize(540,540);
             btn8widget->setWindowTitle("桃花学生公寓");  QLabel * btn8label = new QLabel(btn8widget);
             btn8label->setText("八号景点\n云麓山庄，环境优美");
             btn8widget->show();
        });
        QPushButton* btn9 = new QPushButton(dialog);   btn9->setText("  ");  btn9->move(20,247);
        btn9->setFont(QFont("楷体",14,12,0));  btn9->setStyleSheet("background:transparent;");
        btn9->setVisible(true);  //btn9->setFlat(true);
        connect(btn9,&QPushButton::clicked,xbbmap,[=](){
             QWidget* btn9widget = new QWidget(); btn9widget->move(883,89);   btn9widget->resize(540,540);
             btn9widget->setWindowTitle("桃花学生公寓");  QLabel * btn9label = new QLabel(btn9widget);
             btn9label->setText("九号景点\n粉末冶金学院");
             btn9widget->show();
        });
        QPushButton* btn10 = new QPushButton(dialog);   btn10->setText("  ");  btn10->move(385,452);
        btn10->setFont(QFont("楷体",14,12,0));  btn10->setStyleSheet("background:transparent;");
        btn10->setVisible(true);  //btn10->setFlat(true);
        connect(btn10,&QPushButton::clicked,xbbmap,[=](){
             QWidget* btn10widget = new QWidget(); btn10widget->move(883,89);btn10widget->resize(540,540);
             btn10widget->setWindowTitle("桃花学生公寓");  QLabel * btn10label = new QLabel(btn10widget);
             btn10label->setText("十号景点\n图书馆，书声朗朗");
             btn10widget->show();
        });
        QPushButton* btn11 = new QPushButton(dialog);   btn11->setText("  ");  btn11->move(415,705);
        btn11->setFont(QFont("楷体",14,12,0));  btn11->setStyleSheet("background:transparent;");
        btn11->setVisible(true);  //btn11->setFlat(true);
        connect(btn11,&QPushButton::clicked,xbbmap,[=](){
             QWidget* btn11widget = new QWidget(); btn11widget->move(883,89);btn11widget->resize(540,540);
             btn11widget->setWindowTitle("桃花学生公寓");  QLabel * btn11label = new QLabel(btn11widget);
             btn11label->setText("十一号景点\n静宜院，环境优美");
             btn11widget->show();
        });
        QLabel* v1 = new QLabel(dialog); QLabel* v2 = new QLabel(dialog);
        QLabel* v3 = new QLabel(dialog); QLabel* v4 = new QLabel(dialog);
        QLabel* v5 = new QLabel(dialog); QLabel* v6 = new QLabel(dialog);
        QLabel* v7 = new QLabel(dialog); QLabel* v8 = new QLabel(dialog);
        QLabel* v9 = new QLabel(dialog); QLabel* v10 = new QLabel(dialog);
        QLabel* v11 = new QLabel(dialog); QLabel* v12 = new QLabel(dialog);
        QLabel* v13 = new QLabel(dialog); QLabel* v14 = new QLabel(dialog);
        QLabel* label_all[]={v1,v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14};
        QPushButton* btradom = new QPushButton(dialog);
        btradom->setText("生成权值");
        btradom->move(650,30);
        btradom->setFont(QFont("楷体",14,12,0));
        btradom->resize(160,42);
        btradom->setStyleSheet("QPushButton{background-color:#B0C4DE; boder:none;color:#000000; -webkit-transition-duration:0.4S}\
                             QPushButton:hover{background-color:#F0F8FF; color:#000000;}");
        btradom->setVisible(true);
                connect(btradom,&QPushButton::clicked,xbbmap,[=](){
            xbbmap->buildmap_xbb(v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14);
        });
        //构建网络，蓝色画线
        QLabel* v_1 = new QLabel(dialog); QLabel* v_2 = new QLabel(dialog);
        QLabel* v_3 = new QLabel(dialog); QLabel* v_4 = new QLabel(dialog);
        QLabel* v_5 = new QLabel(dialog); QLabel* v_6 = new QLabel(dialog);
        QLabel* v_7 = new QLabel(dialog); QLabel* v_8 = new QLabel(dialog);
        QLabel* v_9 = new QLabel(dialog); QLabel* v_10 = new QLabel(dialog);
        QLabel* v_11 = new QLabel(dialog); QLabel* v_12 = new QLabel(dialog);
        QLabel* v_13 = new QLabel(dialog); QLabel* v_14 = new QLabel(dialog);
        v_1->setPixmap(QPixmap(":/img/xbb_13.png")); v_1->setStyleSheet("QLabel {background-color: transparent;}");
        v_1->move(271,41);   v_1->show(); v_1->setVisible(false);
        v_2->setPixmap(QPixmap(":/img/xbb_38.png")); v_2->setStyleSheet("QLabel {background-color: transparent;}");
        v_2->move(328,142);  v_2->show();  v_2->setVisible(false);
        v_3->setPixmap(QPixmap(":/img/xbb_87.png"));v_3->setStyleSheet("QLabel {background-color: transparent;}");
        v_3->move(436,224);v_3->show();v_3->setVisible(false);
        v_4->setPixmap(QPixmap(":/img/xbb_39.png")); v_4->setStyleSheet("QLabel {background-color: transparent;}");
        v_4->move(70,145); v_4->show(); v_4->setVisible(false);
        v_5->setPixmap(QPixmap(":/img/xbb_92.png")); v_5->setStyleSheet("QLabel {background-color: transparent;}");
        v_5->move(51,241); v_5->show(); v_5->setVisible(false);
        v_6->setPixmap(QPixmap(":/img/xbb_25.png")); v_6->setStyleSheet("QLabel {background-color: transparent;}");
        v_6->move(62,268); v_6->show(); v_6->setVisible(false);
        v_7->setPixmap(QPixmap(":/img/xbb_57.png")); v_7->setStyleSheet("QLabel {background-color: transparent;}");
        v_7->move(217,282); v_7->show(); v_7->setVisible(false);
        v_8->setPixmap(QPixmap(":/img/xbb_35.png")); v_8->setStyleSheet("QLabel {background-color: transparent;}");
        v_8->move(209,141); v_8->show(); v_8->setVisible(false);
        v_9->setPixmap(QPixmap(":/img/xbb_710.png")); v_9->setStyleSheet("QLabel {background-color: transparent;}");
        v_9->move(429,369); v_9->show(); v_9->setVisible(false);
        v_10->setPixmap(QPixmap(":/img/xbb_410.png")); v_10->setStyleSheet("QLabel {background-color: transparent;}");
        v_10->move(212,429); v_10->show(); v_10->setVisible(false);
        v_11->setPixmap(QPixmap(":/img/xbb_611.png")); v_11->setStyleSheet("QLabel {background-color: transparent;}");
        v_11->move(430,491); v_11->show(); v_11->setVisible(false);
        v_12->setPixmap(QPixmap(":/img/xbb_411.png")); v_12->setStyleSheet("QLabel {background-color: transparent;}");
        v_12->move(215,547); v_12->show(); v_12->setVisible(false);
        v_13->setPixmap(QPixmap(":/img/xbb_610.png")); v_13->setStyleSheet("QLabel {background-color: transparent;}");
        v_13->move(442,464); v_13->show(); v_13->setVisible(false);
        v_14->setPixmap(QPixmap(":/img/xbb_1011.png")); v_14->setStyleSheet("QLabel {background-color: transparent;}");
        v_14->move(418,468); v_14->show(); v_14->setVisible(false);
        QPushButton* btbud = new QPushButton(dialog);
        btbud->setText("构建网络");
        btbud->move(650,90);
        btbud->setFont(QFont("楷体",14,12,0));
        btbud->resize(160,42);
        btbud->setStyleSheet("QPushButton{background-color:#B0C4DE; boder:none;color:#000000; -webkit-transition-duration:0.4S}\
                                     QPushButton:hover{background-color:#F0F8FF; color:#000000;}");
        btbud->setVisible(true);
                connect(btbud,&QPushButton::clicked,xbbmap,[=](){
            xbbmap->prime_in_xbb(title,v_1,v_2,v_3,v_4,v_5,v_6,v_7,v_8,v_9,v_10,v_11,v_12,v_13,v_14);
        });
        //起点选择
        QLabel* begainlabel = new QLabel(dialog);
        QComboBox* begainbox = new QComboBox(dialog);
        begainlabel->setText("请选择起点：");
        begainlabel->setFont(QFont("楷体",14,10,0));
        begainlabel->setStyleSheet("color:#696969");
        begainlabel->move(650,170);
        begainbox->move(780,170);
        for(int i=1;i<=11;i++)
            begainbox->addItem(QString::number(i,10));//按十进制转换为字符
        begainbox->resize(50,25);
        begainbox->setFont(QFont("楷体",14,10,0));
        begainbox->setMaxVisibleItems(12);
        begainbox->setStyleSheet("QComboBox{background-color:#FFFFFF;color:	#778899;border: none;cursor: pointer;}\
                                 QComboBox::drop-down{ border-style: none;}");
        begainbox->show(); begainlabel->show();
        //终点选择
        QLabel* endlabel = new QLabel(dialog);
        QComboBox* endbox = new QComboBox(dialog);
        endlabel->setText("请选择终点：");
        endlabel->setFont(QFont("楷体",14,10,0));
        endlabel->setStyleSheet("color:#696969");
        endlabel->move(650,205);
        endbox->move(780,205);
        for(int i=1;i<=11;i++)
            endbox->addItem(QString::number(i,10));//按十进制转换为字符
        endbox->resize(50,25);
        endbox->setFont(QFont("楷体",14,10,0));
        endbox->setMaxVisibleItems(12);
        endbox->setStyleSheet("QComboBox{background-color:#FFFFFF;color:#696969;border: none;cursor: pointer;}\
                                QComboBox::drop-down{ border-style: none;}");
        endlabel->show();  endbox->show();
        //查询按钮
        QLabel* v__1 = new QLabel(dialog); QLabel* v__2 = new QLabel(dialog);
        QLabel* v__3 = new QLabel(dialog); QLabel* v__4 = new QLabel(dialog);
        QLabel* v__5 = new QLabel(dialog); QLabel* v__6 = new QLabel(dialog);
        QLabel* v__7 = new QLabel(dialog); QLabel* v__8 = new QLabel(dialog);
        QLabel* v__9 = new QLabel(dialog); QLabel* v__10 = new QLabel(dialog);
        QLabel* v__11 = new QLabel(dialog); QLabel* v__12 = new QLabel(dialog);
        QLabel* v__13 = new QLabel(dialog); QLabel* v__14 = new QLabel(dialog);
        v__1->setPixmap(QPixmap(":/img/xbb_13_r.png")); v__1->setStyleSheet("QLabel {background-color: transparent;}");
        v__1->move(271,41);   v__1->show(); v__1->setVisible(false);
        v__2->setPixmap(QPixmap(":/img/xbb_38_r.png")); v__2->setStyleSheet("QLabel {background-color: transparent;}");
        v__2->move(328,142);  v__2->show();  v__2->setVisible(false);
        v__3->setPixmap(QPixmap(":/img/xbb_87_r.png"));v__3->setStyleSheet("QLabel {background-color: transparent;}");
        v__3->move(436,224);v__3->show();v__3->setVisible(false);
        v__4->setPixmap(QPixmap(":/img/xbb_39_r.png")); v__4->setStyleSheet("QLabel {background-color: transparent;}");
        v__4->move(70,145); v__4->show(); v__4->setVisible(false);
        v__5->setPixmap(QPixmap(":/img/xbb_92_r.png")); v__5->setStyleSheet("QLabel {background-color: transparent;}");
        v__5->move(51,241); v__5->show(); v__5->setVisible(false);
        v__6->setPixmap(QPixmap(":/img/xbb_25_r.png")); v__6->setStyleSheet("QLabel {background-color: transparent;}");
        v__6->move(62,268); v__6->show(); v__6->setVisible(false);
        v__7->setPixmap(QPixmap(":/img/xbb_57_r.png")); v__7->setStyleSheet("QLabel {background-color: transparent;}");
        v__7->move(217,282); v__7->show(); v__7->setVisible(false);
        v__8->setPixmap(QPixmap(":/img/xbb_35_r.png")); v__8->setStyleSheet("QLabel {background-color: transparent;}");
        v__8->move(209,141); v__8->show(); v__8->setVisible(false);
        v__9->setPixmap(QPixmap(":/img/xbb_710_r.png")); v__9->setStyleSheet("QLabel {background-color: transparent;}");
        v__9->move(429,369); v__9->show(); v__9->setVisible(false);
        v__10->setPixmap(QPixmap(":/img/xbb_410_r.png")); v__10->setStyleSheet("QLabel {background-color: transparent;}");
        v__10->move(212,429); v__10->show(); v__10->setVisible(false);
        v__11->setPixmap(QPixmap(":/img/xbb_611_r.png")); v__11->setStyleSheet("QLabel {background-color: transparent;}");
        v__11->move(430,491); v__11->show(); v__11->setVisible(false);
        v__12->setPixmap(QPixmap(":/img/xbb_411_r.png")); v__12->setStyleSheet("QLabel {background-color: transparent;}");
        v__12->move(215,547); v__12->show(); v__12->setVisible(false);
        v__13->setPixmap(QPixmap(":/img/xbb_610_r.png")); v__13->setStyleSheet("QLabel {background-color: transparent;}");
        v__13->move(442,464); v__13->show(); v__13->setVisible(false);
        v__14->setPixmap(QPixmap(":/img/xbb_1011_r.png")); v__14->setStyleSheet("QLabel {background-color: transparent;}");
        v__14->move(418,468); v__14->show(); v__14->setVisible(false);
        QPushButton* btfind = new QPushButton(dialog);
        btfind->setText("查询路径");
        btfind->move(650,240);
        btfind->setFont(QFont("楷体",14,12,0));
        btfind->resize(160,42);
        btfind->setStyleSheet("QPushButton{background-color:#B0C4DE; boder:none;color:#000000; -webkit-transition-duration:0.4S}\
                             QPushButton:hover{background-color:#F0F8FF; color:#000000;}");
        btfind->setVisible(true);
        connect(btfind,&QPushButton::clicked,xbbmap,[=](){
            int x2 = begainbox->currentIndex()+1;
            int y2 = endbox->currentIndex()+1;
            xbbmap->Dijkstra(x2,y2,title,v__1,v__2,v__3,v__4,v__5,v__6,v__7,v__8,v__9,v__10,v__11,v__12,v__13,v__14);
        });
//        //查询所有路径
//        QPushButton* btfindall = new QPushButton(dialog);
//        btfindall->setText("显示所有路径");
//        btfindall->move(650,300);
//        btfindall->setFont(QFont("楷体",14,12,0));
//        btfindall->resize(160,42);
//        btfindall->setStyleSheet("QPushButton{background-color:#B0C4DE; boder:none;color:#000000; -webkit-transition-duration:0.4S}\
//                             QPushButton:hover{background-color:#F0F8FF; color:#000000;}");
//        btfindall->setVisible(true);
//        connect(btfindall,&QPushButton::clicked,xbbmap,[=](){
//                    int x2 = begainbox->currentIndex()+1;
//                    int y2 = endbox->currentIndex()+1;
//                    xbbmap->find_all_path_xbb(x2,y2);
//                });

}


void Widget::window_in_nxq(QString title){
    //创建窗口
    Widget* dialog = new Widget();
    auto nxqmap = new Digraph(dialog,14,11);//南校区的有14条边，11个点
    dialog->move(700,70);
    dialog->resize(840,890);
    dialog->show();
    qDebug() << title;
    dialog->setWindowTitle(title);
    QLabel* bglabel = new QLabel(dialog);
    bglabel->move(0,0);
    //背景图片
    bglabel->setPixmap(QPixmap(":/img/nxq.jpg"));
    bglabel->resize(dialog->frameGeometry().width(),dialog->frameGeometry().height());
    bglabel->setScaledContents(true);
    bglabel->setVisible(true);

    //随机生成权值
    QLabel* v1 = new QLabel(dialog); QLabel* v2 = new QLabel(dialog);
    QLabel* v3 = new QLabel(dialog); QLabel* v4 = new QLabel(dialog);
    QLabel* v5 = new QLabel(dialog); QLabel* v6 = new QLabel(dialog);
    QLabel* v7 = new QLabel(dialog); QLabel* v8 = new QLabel(dialog);
    QLabel* v9 = new QLabel(dialog); QLabel* v10 = new QLabel(dialog);
    QLabel* v11 = new QLabel(dialog); QLabel* v12 = new QLabel(dialog);
    QLabel* v13 = new QLabel(dialog); QLabel* v14 = new QLabel(dialog);
    QPushButton* btradom = new QPushButton(dialog);
    btradom->setText("生成权值");
    btradom->move(650,30);
    btradom->setFont(QFont("楷体",14,12,0));
    btradom->resize(160,42);
    btradom->setStyleSheet("QPushButton{background-color:#B0C4DE; boder:none;color:#000000; -webkit-transition-duration:0.4S}\
                         QPushButton:hover{background-color:#F0F8FF; color:#000000;}");
    btradom->setVisible(true);
            connect(btradom,&QPushButton::clicked,nxqmap,[=](){
        nxqmap->buildmap_nxq(v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14);
    });
    //构建网络，蓝色画线
    QLabel* v_1 = new QLabel(dialog); QLabel* v_2 = new QLabel(dialog);
    QLabel* v_3 = new QLabel(dialog); QLabel* v_4 = new QLabel(dialog);
    QLabel* v_5 = new QLabel(dialog); QLabel* v_6 = new QLabel(dialog);
    QLabel* v_7 = new QLabel(dialog); QLabel* v_8 = new QLabel(dialog);
    QLabel* v_9 = new QLabel(dialog); QLabel* v_10 = new QLabel(dialog);
    QLabel* v_11 = new QLabel(dialog); QLabel* v_12 = new QLabel(dialog);
    QLabel* v_13 = new QLabel(dialog);QLabel* v_14 = new QLabel(dialog);
    v_1->setPixmap(QPixmap(":/img/nxq_12_b.png")); v_1->setStyleSheet("QLabel {background-color: transparent;}");
    v_1->move(325,154);   v_1->show(); v_1->setVisible(false);
    v_2->setPixmap(QPixmap(":/img/nxq_13_b.png")); v_2->setStyleSheet("QLabel {background-color: transparent;}");
    v_2->move(436,182);  v_2->show();  v_2->setVisible(false);
    v_3->setPixmap(QPixmap(":/img/nxq_25_b.png"));v_3->setStyleSheet("QLabel {background-color: transparent;}");
    v_3->move(253,161);v_3->show();v_3->setVisible(false);
    v_4->setPixmap(QPixmap(":/img/nxq_27_b.png")); v_4->setStyleSheet("QLabel {background-color: transparent;}");
    v_4->move(220,130); v_4->show(); v_4->setVisible(false);
    v_5->setPixmap(QPixmap(":/img/nxq_34_b.png")); v_5->setStyleSheet("QLabel {background-color: transparent;}");
    v_5->move(415,292); v_5->show(); v_5->setVisible(false);
    v_6->setPixmap(QPixmap(":/img/nxq_36_b.png")); v_6->setStyleSheet("QLabel {background-color: transparent;}");
    v_6->move(85,202); v_6->show(); v_6->setVisible(false);
    v_7->setPixmap(QPixmap(":/img/nxq_45_b.png")); v_7->setStyleSheet("QLabel {background-color: transparent;}");
    v_7->move(239,285); v_7->show(); v_7->setVisible(false);
    v_8->setPixmap(QPixmap(":/img/nxq_56_b.png")); v_8->setStyleSheet("QLabel {background-color: transparent;}");
    v_8->move(89,255); v_8->show(); v_8->setVisible(false);
    v_9->setPixmap(QPixmap(":/img/nxq_58_b.png")); v_9->setStyleSheet("QLabel {background-color: transparent;}");
    v_9->move(96,316); v_9->show(); v_9->setVisible(false);
    v_10->setPixmap(QPixmap(":/img/nxq_89_b.png")); v_10->setStyleSheet("QLabel {background-color: transparent;}");
    v_10->move(91,437); v_10->show(); v_10->setVisible(false);
    v_11->setPixmap(QPixmap(":/img/nxq_811_b.png")); v_11->setStyleSheet("QLabel {background-color: transparent;}");
    v_11->move(74,438); v_11->show(); v_11->setVisible(false);
    v_12->setPixmap(QPixmap(":/img/nxq_911_b.png")); v_12->setStyleSheet("QLabel {background-color: transparent;}");
    v_12->move(155,496); v_12->show(); v_12->setVisible(false);
    v_13->setPixmap(QPixmap(":/img/nxq_1011_b.png")); v_13->setStyleSheet("QLabel {background-color: transparent;}");
    v_13->move(169,619); v_13->show(); v_13->setVisible(false);
    v_14->setPixmap(QPixmap(":/img/nxq_910_b.png")); v_14->setStyleSheet("QLabel {background-color: transparent;}");
    v_14->move(369,542); v_14->show(); v_14->setVisible(false);
    QPushButton* btbud = new QPushButton(dialog);
    btbud->setText("构建网络");
    btbud->move(650,90);
    btbud->setFont(QFont("楷体",14,12,0));
    btbud->resize(160,42);
    btbud->setStyleSheet("QPushButton{background-color:#B0C4DE; boder:none;color:#000000; -webkit-transition-duration:0.4S}\
                                 QPushButton:hover{background-color:#F0F8FF; color:#000000;}");
    btbud->setVisible(true);
            connect(btbud,&QPushButton::clicked,nxqmap,[=](){
        nxqmap->prime_in_nxq(title,v_1,v_2,v_3,v_4,v_5,v_6,v_7,v_8,v_9,v_10,v_11,v_12,v_13,v_14);
    });
    //起点选择
    QLabel* begainlabel = new QLabel(dialog);
    QComboBox* begainbox = new QComboBox(dialog);
    begainlabel->setText("请选择起点：");
    begainlabel->setFont(QFont("楷体",14,10,0));
    begainlabel->setStyleSheet("color:#696969");
    begainlabel->move(650,170);
    begainbox->move(780,170);
    for(int i=1;i<=11;i++)
        begainbox->addItem(QString::number(i,10));//按十进制转换为字符
    begainbox->resize(50,25);
    begainbox->setFont(QFont("楷体",14,10,0));
    begainbox->setMaxVisibleItems(12);
    begainbox->setStyleSheet("QComboBox{background-color:#FFFFFF;color:	#778899;border: none;cursor: pointer;}\
                             QComboBox::drop-down{ border-style: none;}");
    begainbox->show(); begainlabel->show();
    //终点选择
    QLabel* endlabel = new QLabel(dialog);
    QComboBox* endbox = new QComboBox(dialog);
    endlabel->setText("请选择终点：");
    endlabel->setFont(QFont("楷体",14,10,0));
    endlabel->setStyleSheet("color:#696969");
    endlabel->move(650,205);
    endbox->move(780,205);
    for(int i=1;i<=11;i++)
        endbox->addItem(QString::number(i,10));//按十进制转换为字符
    endbox->resize(50,25);
    endbox->setFont(QFont("楷体",14,10,0));
    endbox->setMaxVisibleItems(12);
    endbox->setStyleSheet("QComboBox{background-color:#FFFFFF;color:#696969;border: none;cursor: pointer;}\
                            QComboBox::drop-down{ border-style: none;}");
    endlabel->show();  endbox->show();
    //查询按钮
    QLabel* v__1 = new QLabel(dialog); QLabel* v__2 = new QLabel(dialog);
    QLabel* v__3 = new QLabel(dialog); QLabel* v__4 = new QLabel(dialog);
    QLabel* v__5 = new QLabel(dialog); QLabel* v__6 = new QLabel(dialog);
    QLabel* v__7 = new QLabel(dialog); QLabel* v__8 = new QLabel(dialog);
    QLabel* v__9 = new QLabel(dialog); QLabel* v__10 = new QLabel(dialog);
    QLabel* v__11 = new QLabel(dialog); QLabel* v__12 = new QLabel(dialog);
    QLabel* v__13 = new QLabel(dialog); QLabel* v__14 = new QLabel(dialog);
    v__1->setPixmap(QPixmap(":/img/nxq_12.png")); v__1->setStyleSheet("QLabel {background-color: transparent;}");
    v__1->move(325,154);   v__1->show(); v__1->setVisible(false);
    v__2->setPixmap(QPixmap(":/img/nxq_13.png")); v__2->setStyleSheet("QLabel {background-color: transparent;}");
    v__2->move(436,182);  v__2->show();  v__2->setVisible(false);
    v__3->setPixmap(QPixmap(":/img/nxq_25.png"));v__3->setStyleSheet("QLabel {background-color: transparent;}");
    v__3->move(253,161);v__3->show();v__3->setVisible(false);
    v__4->setPixmap(QPixmap(":/img/nxq_27.png")); v__4->setStyleSheet("QLabel {background-color: transparent;}");
    v__4->move(220,130); v__4->show(); v__4->setVisible(false);
    v__5->setPixmap(QPixmap(":/img/nxq_34.png")); v__5->setStyleSheet("QLabel {background-color: transparent;}");
    v__5->move(415,292); v__5->show(); v__5->setVisible(false);
    v__6->setPixmap(QPixmap(":/img/nxq_36.png")); v__6->setStyleSheet("QLabel {background-color: transparent;}");
    v__6->move(85,202); v__6->show(); v__6->setVisible(false);
    v__7->setPixmap(QPixmap(":/img/nxq_45.png")); v__7->setStyleSheet("QLabel {background-color: transparent;}");
    v__7->move(239,285); v__7->show(); v__7->setVisible(false);
    v__8->setPixmap(QPixmap(":/img/nxq_56.png")); v__8->setStyleSheet("QLabel {background-color: transparent;}");
    v__8->move(89,255); v__8->show(); v__8->setVisible(false);
    v__9->setPixmap(QPixmap(":/img/nxq_58.png")); v__9->setStyleSheet("QLabel {background-color: transparent;}");
    v__9->move(96,316); v__9->show(); v__9->setVisible(false);
    v__10->setPixmap(QPixmap(":/img/nxq_89.png")); v__10->setStyleSheet("QLabel {background-color: transparent;}");
    v__10->move(91,437); v__10->show(); v__10->setVisible(false);
    v__11->setPixmap(QPixmap(":/img/nxq_811.png")); v__11->setStyleSheet("QLabel {background-color: transparent;}");
    v__11->move(74,438); v__11->show(); v__11->setVisible(false);
    v__12->setPixmap(QPixmap(":/img/nxq_911.png")); v__12->setStyleSheet("QLabel {background-color: transparent;}");
    v__12->move(155,496); v__12->show(); v__12->setVisible(false);
    v__13->setPixmap(QPixmap(":/img/nxq_1011.png")); v__13->setStyleSheet("QLabel {background-color: transparent;}");
    v__13->move(169,619); v__13->show(); v__13->setVisible(false);
    v__14->setPixmap(QPixmap(":/img/nxq_910.png")); v__14->setStyleSheet("QLabel {background-color: transparent;}");
    v__14->move(369,542); v__14->show(); v__14->setVisible(false);
    QPushButton* btfind = new QPushButton(dialog);
    btfind->setText("查询路径");
    btfind->move(650,240);
    btfind->setFont(QFont("楷体",14,12,0));
    btfind->resize(160,42);
    btfind->setStyleSheet("QPushButton{background-color:#B0C4DE; boder:none;color:#000000; -webkit-transition-duration:0.4S}\
                         QPushButton:hover{background-color:#F0F8FF; color:#000000;}");
    btfind->setVisible(true);
    connect(btfind,&QPushButton::clicked,nxqmap,[=](){
        int x2 = begainbox->currentIndex()+1;
        int y2 = endbox->currentIndex()+1;
        nxqmap->Dijkstra(x2,y2,title,v__1,v__2,v__3,v__4,v__5,v__6,v__7,v__8,v__9,v__10,v__11,v__12,v__13,v__14);
    });
//    //查询所有路径
//    QPushButton* btfindall = new QPushButton(dialog);
//    btfindall->setText("显示所有路径");
//    btfindall->move(650,300);
//    btfindall->setFont(QFont("楷体",14,12,0));
//    btfindall->resize(160,42);
//    btfindall->setStyleSheet("QPushButton{background-color:#B0C4DE; boder:none;color:#000000; -webkit-transition-duration:0.4S}\
//                         QPushButton:hover{background-color:#F0F8FF; color:#000000;}");
//    btfindall->setVisible(true);

}

void Widget::window_in_xxq(QString title){

}
void Widget::buildwindow(QString title){

}

//void Widget::paint_in_xbb(){
//    qDebug()<<"lala";
//    QPainter p(dig);
//    QPen pen;
//    pen.setColor(QColor(Qt::green));
//    pen.setWidthF(10);
//    p.setPen(pen);
//    p.drawLine(QPoint(0,0),QPoint(300,300));
//}


Widget::~Widget()
{
    delete ui;
}
void Widget::resizeEvent(QResizeEvent *event){
    bglabel->resize(this->size());
}

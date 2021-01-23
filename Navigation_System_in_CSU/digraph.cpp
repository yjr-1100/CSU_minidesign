#include "digraph.h"
#include "widget.h"
#include "cvertex.h"
#include "stack.h"
#include <QDebug>
#include <QPixmap>
#include <QString>
#include <QDialog>
#include <QWidget>
#include <QLabel>
#include <QPushButton>
#define SIZE 0;
#define INF 65535
int path_index=0;
int pa[100]={0};
struct PATH_PAIR{
    int first; int next;
}mp[100];
Digraph::Digraph(Widget *parent,int m1,int n1) : Widget(parent)
{
    m = m1;
    n = n1;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
             adjma[i][j]=INF;
        }
    }
}

void Digraph::find_all_path_xbb(int x,int y){
    if((x==1&&y==2)||(x==2&&y==1)){
        QWidget *f = new QWidget();
        f->resize(470,400);
        f->move(290,150);
        QLabel * findlabel = new QLabel(f);
        findlabel->move(0,0);
        findlabel->setFont(QFont("黑体",14,14,0));
        findlabel->setStyleSheet("color:black;");
        findlabel->setVisible(true);
        findlabel->resize(400,360);
        findlabel->setText("1->3->5->2\n1->3->8->7->5->2\n1->3->9->2");
        f->show();
    }
    if((x==4&&y==2)||(x==2&&y==4)){
        QWidget *f = new QWidget();
        QLabel * findlabel = new QLabel(f);
        f->resize(470,400);
        f->move(290,150);
        findlabel->move(0,0);
        findlabel->setFont(QFont("黑体",14,14,0));
        findlabel->setStyleSheet("color:black;");
        findlabel->setVisible(true);
        findlabel->resize(400,360);
        findlabel->setText("2->5->3->8->7->10->4\n2->5->3->8->7->10->6->11->4\n2->5->3->8->7->10->11->4\n2->5->7->10->4\n2->5->7->10->6->11->4\n2->5->7->10->11->4\n2->9->3->5->7->10->4\n2->9->3->5->7->10->6->11->4\n2->9->3->5->7->10->11->4\n2->9->3->8->7->10->4\n2->9->3->8->7->10->6->11->4\n2->9->3->8->7->10->11->4");
        f->show();
    }
}

void Digraph::find_all_path(int x,int y){
    Stack stk;
    stk.push(x); //将起点压栈
    vertex[x].setIsin(true);  //标记为已入栈
    while (!stk.isEmpty())  //判断栈是否空
    {
        int nei = stk.peek();
        int flag=vertex[nei].getOne();  //得到相邻的顶点
        if (flag==-1)    //如果相邻顶点全部访问过
        {
            int pop=stk.pop(); //栈弹出一个元素
            vertex[pop].resetFlag();  //该顶点相邻的顶点标记为未访问
            vertex[pop].setIsin(false); //该顶点标记为未入栈
            continue; //取栈顶的相邻节点
        }
        if (vertex[flag].isIn())  //若已经在栈中，取下一个顶点
        {
            continue;
        }
        stk.push(flag); //将该顶点入栈
        vertex[flag].setIsin(true);  //记为已入栈
        if (stk.peek()==y)   //如果栈顶已经为所求，将此路径记录
        {

           int *path=stk.getPath();
               qDebug()<<endl;
           int pop=stk.pop(); //将其弹出，继续探索
               vertex[pop].setIsin(false); //清空入栈的标志位
        }
    }
}

void Digraph::buildmap_xbb(QLabel* v1,QLabel* v2,QLabel* v3,QLabel* v4,QLabel* v5,QLabel* v6,QLabel* v7,QLabel* v8,QLabel* v9,QLabel* v10,QLabel* v11,QLabel* v12,QLabel* v13,QLabel* v14){
    //随机生成权值
    srand((unsigned)time(NULL));
    for(int i=0;i<m;i++)
        w[i]= rand()%79+10;
    adjma[1][3]=w[0];adjma[3][1]=w[0];
    adjma[3][8]=w[1];adjma[8][3]=w[1];
    adjma[3][9]=w[2];adjma[9][3]=w[2];
    adjma[3][5]=w[3];adjma[5][3]=w[3];
    adjma[2][9]=w[4];adjma[9][2]=w[4];
    adjma[2][5]=w[5];adjma[5][2]=w[5];
    adjma[7][8]=w[6];adjma[8][7]=w[6];
    adjma[7][5]=w[7];adjma[5][7]=w[7];
    adjma[10][7]=w[8];adjma[7][10]=w[8];
    adjma[10][4]=w[9];adjma[4][10]=w[9];
    adjma[10][11]=w[10];adjma[11][10]=w[10];
    adjma[4][11]=w[12];adjma[11][4]=w[12];
    adjma[11][6]=w[13];adjma[6][11]=w[13];
    adjma[10][6]=w[11];adjma[6][10]=w[11];
    int a[15];
    for (int i=1;i<=n;i++){
       int num=0;
       for (int j=1;j<=n;j++)
            if (adjma[i][j]!=INF&&i!=j)
             {
                 a[num]=j;
                 num++;
              }
        vertex[i].Initialize(num,a);
    }
    QLabel* label_all[]={v1,v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14};
    show_in_map(label_all);
}

void Digraph::buildmap_nxq(QLabel* v1,QLabel* v2,QLabel* v3,QLabel* v4,QLabel* v5,QLabel* v6,QLabel* v7,QLabel* v8,QLabel* v9,QLabel* v10,QLabel* v11,QLabel* v12,QLabel* v13,QLabel* v14){
    //随机生成权值
    srand((unsigned)time(NULL));
    for(int i=0;i<m;i++)
        w[i]= rand()%79+11;
    adjma[1][2]=w[0];adjma[2][1]=w[0];
    adjma[3][1]=w[1];adjma[1][3]=w[1];
    adjma[3][4]=w[2];adjma[4][3]=w[2];
    adjma[3][6]=w[3];adjma[6][3]=w[3];
    adjma[2][7]=w[4];adjma[7][2]=w[4];
    adjma[2][5]=w[5];adjma[5][2]=w[5];
    adjma[5][8]=w[6];adjma[8][5]=w[6];
    adjma[4][5]=w[7];adjma[5][4]=w[7];
    adjma[5][6]=w[8];adjma[6][5]=w[8];
    adjma[8][9]=w[9];adjma[9][8]=w[9];
    adjma[8][11]=w[10];adjma[11][8]=w[10];
    adjma[10][11]=w[11];adjma[11][10]=w[11];
    adjma[9][11]=w[12];adjma[11][9]=w[12];
    adjma[9][10]=w[13];adjma[10][9]=w[13];
    QLabel* label_all[]={v1,v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14};
    show_in_map_in_nxq(label_all);
}

void Digraph::show_in_map(QLabel* v_all[]){ //显示权值
//    for(int i=1;i<=n;i++){
//        for(int j=1;j<=n;j++){
//            qDebug()<<adjma[i][j]<<' ';
//        }
//        qDebug()<<endl;
//    }
    v_all[1]->setNum(w[0]);
    v_all[1]->setFont(QFont("黑体",20,20,0));
    v_all[1]->setStyleSheet("color:black;");
    v_all[1]->move(310,70);
    v_all[1]->show();
    v_all[2]->setNum(w[1]);
    v_all[2]->setFont(QFont("黑体",20,20,0));
    v_all[2]->setStyleSheet("color:black;");
    v_all[2]->move(450,190);
    v_all[2]->show();
    v_all[3]->setNum(w[2]);
    v_all[3]->setFont(QFont("黑体",20,20,0));
    v_all[3]->setStyleSheet("color:black;");
    v_all[3]->move(130,225);
    v_all[3]->show();
    v_all[4]->setNum(w[3]);
    v_all[4]->resize(50,50);
    v_all[4]->setFont(QFont("黑体",20,20,0));
    v_all[4]->setStyleSheet("color:black;");
    v_all[4]->move(230,235);
    v_all[4]->show();
    v_all[5]->setNum(w[4]);
    v_all[5]->setFont(QFont("黑体",20,20,0));
    v_all[5]->setStyleSheet("color:black;");
    v_all[5]->move(110,315);
    v_all[5]->show();
    v_all[6]->setNum(w[5]);
    v_all[6]->setFont(QFont("黑体",20,20,0));
    v_all[6]->setStyleSheet("color:black;");
    v_all[6]->move(185,405);
    v_all[6]->show();
    v_all[7]->setNum(w[6]);
    v_all[7]->setFont(QFont("黑体",20,20,0));
    v_all[7]->setStyleSheet("color:black;");
    v_all[7]->move(475,300);
    v_all[7]->show();
    v_all[8]->setNum(w[7]);
    v_all[8]->setFont(QFont("黑体",20,20,0));
    v_all[8]->setStyleSheet("color:black;");
    v_all[8]->move(350,340);
    v_all[8]->show();
    v_all[9]->setNum(w[8]);
    v_all[9]->setFont(QFont("黑体",20,20,0));
    v_all[9]->setStyleSheet("color:black;");
    v_all[9]->move(440,405);
    v_all[9]->show();
    v_all[10]->setNum(w[9]);
    v_all[10]->setFont(QFont("黑体",20,20,0));
    v_all[10]->setStyleSheet("color:black;");
    v_all[10]->move(295,450);
    v_all[10]->show();
    v_all[11]->setNum(w[10]);
    v_all[11]->setFont(QFont("黑体",20,20,0));
    v_all[11]->setStyleSheet("color:black;");
    v_all[11]->move(450,540);
    v_all[11]->show();
    v_all[12]->setNum(w[11]);
    v_all[12]->setFont(QFont("黑体",20,20,0));
    v_all[12]->setStyleSheet("color:black;");
    v_all[12]->move(490,470);
    v_all[12]->show();
    v_all[13]->setNum(w[12]);
    v_all[13]->setFont(QFont("黑体",20,20,0));
    v_all[13]->setStyleSheet("color:black;");
    v_all[13]->move(360,595);
    v_all[13]->show();
    v_all[14]->setNum(w[13]);
    v_all[14]->setFont(QFont("黑体",20,20,0));
    v_all[14]->setStyleSheet("color:black;");
    v_all[14]->move(555,580);
    v_all[14]->show();
}

void Digraph::show_in_map_in_nxq(QLabel* v_all[]){//显示权值
//    for(int i=1;i<=n;i++){
//        for(int j=1;j<=n;j++){
//            qDebug()<<adjma[i][j]<<' ';
//        }
//        qDebug()<<endl;
//    }
    v_all[1]->setNum(w[0]);
    v_all[1]->setFont(QFont("黑体",20,20,0));
    v_all[1]->setStyleSheet("color:black;");
    v_all[1]->move(366,137);
    v_all[1]->show();
    v_all[2]->setNum(w[1]);
    v_all[2]->setFont(QFont("黑体",20,20,0));
    v_all[2]->setStyleSheet("color:black;");
    v_all[2]->move(466,215);
    v_all[2]->show();
    v_all[3]->setNum(w[2]);
    v_all[3]->setFont(QFont("黑体",20,20,0));
    v_all[3]->setStyleSheet("color:black;");
    v_all[3]->move(535,332);
    v_all[3]->show();
    v_all[4]->setNum(w[3]);
    v_all[4]->resize(50,50);
    v_all[4]->setFont(QFont("黑体",20,20,0));
    v_all[4]->setStyleSheet("color:black;");
    v_all[4]->move(230,235);
    v_all[4]->show();
    v_all[5]->setNum(w[4]);
    v_all[5]->setFont(QFont("黑体",20,20,0));
    v_all[5]->setStyleSheet("color:black;");
    v_all[5]->move(245,135);
    v_all[5]->show();
    v_all[6]->setNum(w[5]);
    v_all[6]->setFont(QFont("黑体",20,20,0));
    v_all[6]->setStyleSheet("color:black;");
    v_all[6]->move(305,265);
    v_all[6]->show();
    v_all[7]->setNum(w[6]);
    v_all[7]->setFont(QFont("黑体",20,20,0));
    v_all[7]->setStyleSheet("color:black;");
    v_all[7]->move(165,400);
    v_all[7]->show();
    v_all[8]->setNum(w[7]);
    v_all[8]->setFont(QFont("黑体",20,20,0));
    v_all[8]->setStyleSheet("color:black;");
    v_all[8]->move(350,390);
    v_all[8]->show();
    v_all[9]->setNum(w[8]);
    v_all[9]->setFont(QFont("黑体",20,20,0));
    v_all[9]->setStyleSheet("color:black;");
    v_all[9]->move(175,300);
    v_all[9]->show();
    v_all[10]->setNum(w[9]);
    v_all[10]->setFont(QFont("黑体",20,20,0));
    v_all[10]->setStyleSheet("color:black;");
    v_all[10]->move(248,455);
    v_all[10]->show();
    v_all[11]->setNum(w[10]);
    v_all[11]->setFont(QFont("黑体",20,20,0));
    v_all[11]->setStyleSheet("color:black;");
    v_all[11]->move(55,500);
    v_all[11]->show();
    v_all[12]->setNum(w[11]);
    v_all[12]->setFont(QFont("黑体",20,20,0));
    v_all[12]->setStyleSheet("color:black;");
    v_all[12]->move(322,631);
    v_all[12]->show();
    v_all[13]->setNum(w[12]);
    v_all[13]->setFont(QFont("黑体",20,20,0));
    v_all[13]->setStyleSheet("color:black;");
    v_all[13]->move(217,565);
    v_all[13]->show();
    v_all[14]->setNum(w[13]);
    v_all[14]->setFont(QFont("黑体",20,20,0));
    v_all[14]->setStyleSheet("color:black;");
    v_all[14]->move(477,565);
    v_all[14]->show();
}

void Digraph::paint_in_xbb(int adj[],QLabel* p_all[]){//显示构建的网络，最小生成树的显示
    for(int i=1;i<=m;i++)
        p_all[i]->setVisible(false);//先都不可见
    for(int i=1;i<=n;i++)
       qDebug()<<i<<"-->"<<adj[i]<<endl;
    if(adj[1]==3||adj[3]==1)p_all[1]->setVisible(true);//1->3
    if(adj[8]==3||adj[3]==8)p_all[2]->setVisible(true);//3->8
    if(adj[8]==7||adj[7]==8)p_all[3]->setVisible(true);//8->7
    if(adj[9]==3||adj[3]==9)p_all[4]->setVisible(true);//3->9
    if(adj[2]==9||adj[9]==2)p_all[5]->setVisible(true);//9->2
    if(adj[2]==5||adj[5]==2)p_all[6]->setVisible(true);//2->5
    if(adj[5]==7||adj[7]==5)p_all[7]->setVisible(true);//5->7
    if(adj[5]==3||adj[3]==5)p_all[8]->setVisible(true);//3->5
    if(adj[10]==7||adj[7]==10)p_all[9]->setVisible(true);//7->10
    if(adj[10]==4||adj[4]==10)p_all[10]->setVisible(true);//4->10
    if(adj[11]==6||adj[6]==11)p_all[11]->setVisible(true);//6->11
    if(adj[11]==4||adj[4]==11)p_all[12]->setVisible(true);//4->11
    if(adj[10]==6||adj[6]==10)p_all[13]->setVisible(true);//6->10
    if(adj[10]==11||adj[11]==10)p_all[14]->setVisible(true);//10->11
}

void Digraph::prime_in_xbb(QString title,QLabel* v1,QLabel* v2,QLabel* v3,QLabel* v4,QLabel* v5,QLabel* v6,QLabel* v7,QLabel* v8,QLabel* v9,QLabel* v10,QLabel* v11,QLabel* v12,QLabel* v13,QLabel* v14){
    int index;
    int lowcost[20];//lowcost[i]=0 说明该点在树里了
    int adjvex[20]={0};//保存邻接节点下标的数组
    adjvex[1]=1; //从节点1开始，
    lowcost[1]=0;//节点1为树的根节点
    for(int i=2;i<=n;i++){
            lowcost[i]=adjma[1][i];
            adjvex[i]=1;
    }
    for(int j=2;j<=n;j++){
        int min = 65535;
        index = 1;
        for(int i=1;i<=n;i++){
            if(lowcost[i]!=0&&lowcost[i]<min){
                min = lowcost[i];
                index = i;
            }
        }
        lowcost[index]=0;//j加入树里面
        for(int i=1;i<=n;i++){
            if(lowcost[i]!=0 && adjma[index][i]<lowcost[i]){
                lowcost[i]=adjma[index][i];
                adjvex[i]=index;
            }
        }
    }
    QLabel* path_all[]={v1,v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14};
    if(title == "  校本部") paint_in_xbb(adjvex,path_all);
}

void Digraph::paint_in_nxq(int adj[],QLabel* p_all[]){//显示构建的网络，最小生成树的显示
    for(int i=1;i<=m;i++)
        p_all[i]->setVisible(false);//先都不可见
    for(int i=1;i<=n;i++)
       qDebug()<<i<<"-->"<<adj[i]<<endl;
    if(adj[1]==2||adj[2]==1)        p_all[1]->setVisible(true);//1->2
    if(adj[1]==3||adj[3]==1)        p_all[2]->setVisible(true);//1->3
    if(adj[2]==5||adj[5]==2)        p_all[3]->setVisible(true);//2->5
    if(adj[2]==7||adj[7]==2)        p_all[4]->setVisible(true);//2->7
    if(adj[3]==4||adj[4]==3)        p_all[5]->setVisible(true);//3->4
    if(adj[3]==6||adj[6]==3)        p_all[6]->setVisible(true);//3->6
    if(adj[5]==4||adj[4]==5)        p_all[7]->setVisible(true);//4->5
    if(adj[5]==6||adj[6]==5)        p_all[8]->setVisible(true);//5->6
    if(adj[5]==8||adj[8]==5)        p_all[9]->setVisible(true);//5->8
    if(adj[8]==9||adj[9]==8)        p_all[10]->setVisible(true);//8->9
    if(adj[11]==8||adj[8]==11)      p_all[11]->setVisible(true);//8->11
    if(adj[11]==9||adj[9]==11)      p_all[12]->setVisible(true);//9->11
    if(adj[10]==11||adj[11]==10)    p_all[13]->setVisible(true);//10->11
    if(adj[10]==9||adj[9]==10)      p_all[14]->setVisible(true);//9->10
}

void Digraph::prime_in_nxq(QString title,QLabel* v1,QLabel* v2,QLabel* v3,QLabel* v4,QLabel* v5,QLabel* v6,QLabel* v7,QLabel* v8,QLabel* v9,QLabel* v10,QLabel* v11,QLabel* v12,QLabel* v13,QLabel* v14){
    int index;
    int lowcost[20];//lowcost[i]=0 说明该点在树里了
    int adjvex[20]={0};//保存邻接节点下标的数组
    adjvex[1]=1; //从节点1开始，
    lowcost[1]=0;//节点1为树的根节点
    for(int i=2;i<=n;i++){
            lowcost[i]=adjma[1][i];
            adjvex[i]=1;
    }
    for(int j=2;j<=n;j++){
        int min = 65535;
        index = 1;
        for(int i=1;i<=n;i++){
            if(lowcost[i]!=0&&lowcost[i]<min){
                min = lowcost[i];
                index = i;
            }
        }
        lowcost[index]=0;//j加入树里面
        for(int i=1;i<=n;i++){
            if(lowcost[i]!=0 && adjma[index][i]<lowcost[i]){
                lowcost[i]=adjma[index][i];
                adjvex[i]=index;
            }
        }
    }
    QLabel* path_all[]={v1,v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14};
    if(title == "  南校区") paint_in_nxq(adjvex,path_all);
}

void Digraph::Dijkstra(int x,int y,QString title,QLabel* v1,QLabel* v2,QLabel* v3,QLabel* v4,QLabel* v5,QLabel* v6,QLabel* v7,QLabel* v8,QLabel* v9,QLabel* v10,QLabel* v11,QLabel* v12,QLabel* v13,QLabel* v14){//x起点，y终点
    int next=0;//下一个放入集合的点
    int dis[30]; //存放从起点到各个点的最短路径
    int is_visited[30]={0};// 0 是没有访问，1是已经访问
    for(int i=1;i<=n;i++){
            dis[i]=adjma[x][i];
            path[i]=x;
            is_visited[i]=0;
    }
    is_visited[x]=1; //起点已经访问了
    dis[x]=0;
    for(int k=1;k<=n;k++){
        int min = INF;
        for(int i=1;i<=n;i++){
            if(is_visited[i]==0&&min>dis[i]){
                 min =dis[i];
                 next = i;
             }
         }

         is_visited[next]=1;

         for(int i=1;i<=n;i++){
         if(is_visited[i]==0&&dis[i]>dis[next]+adjma[next][i]){
                dis[i]=dis[next]+adjma[next][i];
                path[i]=next;//改前驱节点；
            }
        }
    }
     qDebug()<<x<<"点到"<<y<<"点的最短路径为"<<dis[y]<<endl;
     ///////////显示//////////
     if(title=="  校本部"){
         QLabel* label_all[]={v1,v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14};
         path_index=0;
         find_xbb(x,y);
         for(int i=0;i<path_index-1;i++){
                 mp[i].first=pa[i];
                 mp[i].next=pa[i+1];
             }
         show_path_in_xbb(label_all);
     }
     if(title=="  南校区"){
         QLabel* label_all[]={v1,v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14};
         path_index=0;
         find_xbb(x,y);
         for(int i=0;i<path_index-1;i++){
                 mp[i].first=pa[i];
                 mp[i].next=pa[i+1];
             }
         show_path_in_nxq(label_all);
     }
}
void Digraph::show_path_in_xbb(QLabel* label_all[]){
    for(int i=1;i<=m;i++)
        label_all[i]->setVisible(false);//先都不可见
    for(int i=0;i<path_index-1;i++)
    qDebug()<<mp[i].first<<" "<<mp[i].next<<endl;
    for(int i=0;i<path_index-1;i++){
        if((mp[i].first==1&&mp[i].next==3)||(mp[i].first==3&&mp[i].next==1))     label_all[1]->setVisible(true);//1->3
        if((mp[i].first==8&&mp[i].next==3)||(mp[i].first==3&&mp[i].next==8))     label_all[2]->setVisible(true);//3->8
        if((mp[i].first==8&&mp[i].next==7)||(mp[i].first==7&&mp[i].next==8))     label_all[3]->setVisible(true);//8->7
        if((mp[i].first==9&&mp[i].next==3)||(mp[i].first==3&&mp[i].next==9))     label_all[4]->setVisible(true);//3->9
        if((mp[i].first==2&&mp[i].next==9)||(mp[i].first==9&&mp[i].next==2))     label_all[5]->setVisible(true);//9->2
        if((mp[i].first==2&&mp[i].next==5)||(mp[i].first==5&&mp[i].next==2))     label_all[6]->setVisible(true);//2->5
        if((mp[i].first==5&&mp[i].next==7)||(mp[i].first==7&&mp[i].next==5))     label_all[7]->setVisible(true);//5->7
        if((mp[i].first==3&&mp[i].next==5)||(mp[i].first==5&&mp[i].next==3))     label_all[8]->setVisible(true);//3->5
        if((mp[i].first==10&&mp[i].next==7)||(mp[i].first==7&&mp[i].next==10))   label_all[9]->setVisible(true);//7->10
        if((mp[i].first==10&&mp[i].next==4)||(mp[i].first==4&&mp[i].next==10))  label_all[10]->setVisible(true);//4->10
        if((mp[i].first==11&&mp[i].next==6)||(mp[i].first==6&&mp[i].next==11))  label_all[11]->setVisible(true);//6->11
        if((mp[i].first==4&&mp[i].next==11)||(mp[i].first==11&&mp[i].next==4))  label_all[12]->setVisible(true);//4->11
        if((mp[i].first==10&&mp[i].next==6)||(mp[i].first==6&&mp[i].next==10))  label_all[13]->setVisible(true);//6->10
        if((mp[i].first==11&&mp[i].next==10)||(mp[i].first==10&&mp[i].next==11))label_all[14]->setVisible(true);//10->11

    }

}
void Digraph::show_path_in_nxq(QLabel* label_all[]){
    for(int i=1;i<=m;i++)
        label_all[i]->setVisible(false);//先都不可见
    for(int i=0;i<path_index-1;i++)
    qDebug()<<mp[i].first<<" "<<mp[i].next<<endl;
    for(int i=0;i<path_index-1;i++){
        if((mp[i].first==1&&mp[i].next==2)||(mp[i].first==2&&mp[i].next==1))     label_all[1]->setVisible(true);//1->3
        if((mp[i].first==1&&mp[i].next==3)||(mp[i].first==3&&mp[i].next==1))     label_all[2]->setVisible(true);//3->8
        if((mp[i].first==2&&mp[i].next==5)||(mp[i].first==5&&mp[i].next==2))     label_all[3]->setVisible(true);//8->7
        if((mp[i].first==2&&mp[i].next==7)||(mp[i].first==7&&mp[i].next==2))     label_all[4]->setVisible(true);//3->9
        if((mp[i].first==3&&mp[i].next==4)||(mp[i].first==4&&mp[i].next==3))     label_all[5]->setVisible(true);//9->2
        if((mp[i].first==3&&mp[i].next==6)||(mp[i].first==6&&mp[i].next==3))     label_all[6]->setVisible(true);//2->5
        if((mp[i].first==5&&mp[i].next==4)||(mp[i].first==4&&mp[i].next==5))     label_all[7]->setVisible(true);//5->7
        if((mp[i].first==6&&mp[i].next==5)||(mp[i].first==5&&mp[i].next==6))     label_all[8]->setVisible(true);//3->5
        if((mp[i].first==5&&mp[i].next==8)||(mp[i].first==8&&mp[i].next==5))   label_all[9]->setVisible(true);//7->10
        if((mp[i].first==8&&mp[i].next==9)||(mp[i].first==9&&mp[i].next==8))  label_all[10]->setVisible(true);//4->10
        if((mp[i].first==11&&mp[i].next==8)||(mp[i].first==8&&mp[i].next==11))  label_all[11]->setVisible(true);//6->11
        if((mp[i].first==9&&mp[i].next==11)||(mp[i].first==11&&mp[i].next==9))  label_all[12]->setVisible(true);//4->11
        if((mp[i].first==10&&mp[i].next==11)||(mp[i].first==11&&mp[i].next==10))  label_all[13]->setVisible(true);//6->10
        if((mp[i].first==9&&mp[i].next==10)||(mp[i].first==10&&mp[i].next==9))label_all[14]->setVisible(true);//10->11

    }

}

void Digraph:: find_xbb(int x1,int y1){
    if (path[y1] == x1) {
        pa[path_index++]=x1;
    }
    else {
        find_xbb(x1,path[y1]);
    }
    pa[path_index++]=y1;
    return;
}









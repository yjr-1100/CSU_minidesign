#ifndef DIGRAPH_H
#define DIGRAPH_H
#include "cvertex.h"
#include "stack.h"
#include "widget.h"
#include <QWidget>
#include <QLabel>
class Digraph : public Widget
{
    Q_OBJECT
public:
    int n;//点
    int m;//边
    int adjma[100][100]; //邻接矩阵
    int w[100]; //随机权值存放
    int path[50];//存放最短路
    CVertex vertex[15];
    //修饰函数参数不能转换类型
    explicit Digraph(Widget *parent = nullptr,int m1=0,int n1=0);

signals:

public slots://定义槽函数
    //随机生成权值
    void buildmap_xbb(QLabel* v1,QLabel* v2,QLabel* v3,QLabel* v4,QLabel* v5,QLabel* v6,QLabel* v7,QLabel* v8,QLabel* v9,QLabel* v10,QLabel* v11,QLabel* v12,QLabel* v13,QLabel* v14);
    void buildmap_nxq(QLabel* v1,QLabel* v2,QLabel* v3,QLabel* v4,QLabel* v5,QLabel* v6,QLabel* v7,QLabel* v8,QLabel* v9,QLabel* v10,QLabel* v11,QLabel* v12,QLabel* v13,QLabel* v14);
     //显示权值，在buildmap_xbb里调用
     void show_in_map(QLabel* v_all[]);
     void show_in_map_in_nxq(QLabel* v_all[]);
     //普利姆算法 构建网络用的，显示红色的最小生成树
     void prime_in_xbb(QString title,QLabel* v1,QLabel* v2,QLabel* v3,QLabel* v4,QLabel* v5,QLabel* v6,QLabel* v7,QLabel* v8,QLabel* v9,QLabel* v10,QLabel* v11,QLabel* v12,QLabel* v13,QLabel* v14);
     void paint_in_xbb(int adj[],QLabel* p_all[]);//显示构建的网络，最小生成树的显示
     void prime_in_nxq(QString title,QLabel* v1,QLabel* v2,QLabel* v3,QLabel* v4,QLabel* v5,QLabel* v6,QLabel* v7,QLabel* v8,QLabel* v9,QLabel* v10,QLabel* v11,QLabel* v12,QLabel* v13,QLabel* v14);
     void paint_in_nxq(int adj[],QLabel* p_all[]);//显示构建的网络，最小生成树的显示
     //Dijkstra，两点之间的最短路
     void Dijkstra(int x,int y,QString title,QLabel* v1,QLabel* v2,QLabel* v3,QLabel* v4,QLabel* v5,QLabel* v6,QLabel* v7,QLabel* v8,QLabel* v9,QLabel* v10,QLabel* v11,QLabel* v12,QLabel* v13,QLabel* v14);//x起点，y终点
     void find_xbb(int x1,int y1);//生成路径
     //显示最短路
     void show_path_in_xbb(QLabel* label_all[]);//地图上红色显示查询的最短路；
     void show_path_in_nxq(QLabel* label_all[]);
     //显示所有路径
     void find_all_path(int x,int y);//x为起点，y为终点
     void find_all_path_xbb(int x,int y);//x为起点，y为终点
};

#endif // DIGRAPH_H

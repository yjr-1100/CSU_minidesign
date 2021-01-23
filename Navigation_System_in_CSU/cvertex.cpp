#include "digraph.h"
#include "widget.h"
#include "stack.h"
#include "cvertex.h"
#include <QDebug>
#define INF 65535
#define SIZE 0;
CVertex::CVertex(){
    m_num=SIZE; m_nei=new int[m_num]; m_flag=new int[m_num]; isin=false;
    for (int i=0;i<m_num;i++){	m_flag[i]=0; }
}
void CVertex::Initialize(int num,int a[]){ //初始化
    m_num=num;
    for (int i=0;i<m_num;i++){
        m_nei[i]=a[i];
        m_flag[m_nei[i]]=0;
    }
}

int CVertex::getOne(){ //拿到一个与改节点邻接的节点
    int i=0;
    for (i=0;i<m_num;i++)
        if (m_flag[m_nei[i]]==0){   //判断是否访问过,0没有被访问
            m_flag[m_nei[i]]=1;     //表示这个顶点已经被访问，并将其返回
            return m_nei[i];
        }
    return -1;  //所有顶点都已访问过则返回-1
}
void CVertex::resetFlag()//与该顶点相邻的顶点全被标记为未访问
{	for (int i=0;i<m_num;i++)
    {	m_flag[m_nei[i]]=0;	}
}

void CVertex::setIsin(bool a) {	isin=a; } //设置入栈

bool CVertex::isIn(){	return isin; }//判断该顶点是否入栈

void CVertex::Reset(){//将isin和所有flag置0
    for (int i=0;i<m_num;i++)
        m_flag[i]=0;
    isin=false;
}

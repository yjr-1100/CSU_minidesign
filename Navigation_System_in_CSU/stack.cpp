#include "digraph.h"
#include "widget.h"
#include "stack.h"
#include <QDebug>
#define INF 65535
#define SIZE 0;
Stack::Stack(){
    m_size=0;
    m_maxsize=100;
    data=new int[m_maxsize];
}
Stack::~Stack(){	delete []data; }
int Stack::pop(){
    m_size--;
    return data[m_size];
}
void Stack::push(int d){	data[m_size]=d; m_size++;}
int Stack::peek(){	return data[m_size-1]; }//查看栈顶元素
bool Stack::isEmpty(){
    if (m_size==0){  return true;}
    else          { return false;}
}
int* Stack::getPath(){
    int* path=new int[m_size];
    for (int i=0;i<m_size;i++)
    {
        qDebug()<<data[i]<<"  ";
        path[i]=data[i];
    }
    return path;
}
int Stack::getSize(){	return m_size; }

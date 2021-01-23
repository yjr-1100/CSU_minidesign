#ifndef STACK_H
#define STACK_H
class Stack
{
    private:
        int m_maxsize;
        int* data;
    public:
        int m_size;
        Stack(); ~Stack();
        void push(int data);  //压栈
        int pop(); //出栈，并返回弹出的元素
        int peek(); //查看栈顶元素
        bool isEmpty();  //判断是否空
        int getSize();  //得到栈的中元素个数
        int* getPath();  //返回栈中所有元素
};
#endif // STACK_H

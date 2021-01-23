#ifndef CVERTEX_H
#define CVERTEX_H
class CVertex
{
    private:
        int *m_flag; //与该顶点相邻的顶点是否访问过
        bool isin; //该顶点是否入栈
    public:
        int m_num;//保存与该顶点相邻的顶点个数
        int *m_nei; //与该顶点相邻的顶点序号
        CVertex();
        void Initialize(int num,int a[]);
        int getOne(); //得到一个与该顶点相邻的顶点
        void resetFlag(); //与该顶点相邻的顶点全被标记为未访问
        void setIsin(bool);//标记该顶点是否入栈
        bool isIn();  //判断该顶点是否入栈
        void Reset();//将isin和所有flag置0
};
#endif // CVERTEX_H

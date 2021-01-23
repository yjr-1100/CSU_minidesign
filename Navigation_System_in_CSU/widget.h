#ifndef WIDGET_H
#define WIDGET_H

#include <QWidget>
#include <QLabel>
#include <QPushButton>
#include <QComboBox>
#include <QPainter>
namespace Ui {
class Widget;
}

class Widget : public QWidget
{
    Q_OBJECT

public:
    explicit Widget(QWidget *parent = nullptr);
    ~Widget();
    virtual void resizeEvent(QResizeEvent *event);
    void window_in_xbb(QString title);
    void window_in_nxq(QString title);
    void window_in_xxq(QString title);

    void buildwindow(QString title);
    void randommake();

protected:
//    void paintEvent(QPaintEvent *event);


private:
    Ui::Widget *ui;
    QLabel * bglabel;//背景图片的label
    QLabel * wclabel;//欢迎进入校园导航系统的label
    QPushButton* btwl;//在学校建立网络的按钮
    QPushButton* btdh;//在学校导航的按钮
    QLabel* schoolmap;//地图选择提示label
    QComboBox* sltmap;//学校地图选择

};

#endif // WIDGET_H

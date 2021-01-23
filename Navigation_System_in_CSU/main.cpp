#include "widget.h"
#include "digraph.h"
#include "cvertex.h"
#include "stack.h"
#include <QApplication>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    Widget w;
    w.show();

    return a.exec();
}

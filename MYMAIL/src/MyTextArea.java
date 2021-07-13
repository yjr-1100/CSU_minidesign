import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

class MyTextArea extends JTextArea implements DropTargetListener {
    public MyTextArea(){
        new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, this);
    }
    public void dragEnter(DropTargetDragEvent dtde) {
    }
    public void dragOver(DropTargetDragEvent dtde) {
    }
    public void dropActionChanged(DropTargetDragEvent dtde) {
    }
    public void dragExit(DropTargetEvent dte) {
    }
    public void drop(DropTargetDropEvent dtde) {
        try {
            Transferable tr = dtde.getTransferable();
            if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);//接收拖拽来的数据
                List<File> list =  (List<File>) (dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor));
                String temp="";
                for(File file:list)
                    temp+=file.getAbsolutePath()+"\n";
                JOptionPane.showMessageDialog(null, temp);
                dtde.dropComplete(true);//指示拖拽操作已完成
                this.setText(temp);

                dtde.dropComplete(true);

                this.updateUI();

            }else {
                dtde.rejectDrop();

            }

        } catch (IOException ioe) {
            ioe.printStackTrace();

        } catch (UnsupportedFlavorException ufe) {
            ufe.printStackTrace();

        }

    }

}
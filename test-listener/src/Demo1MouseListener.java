import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Demo1MouseListener {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(514,538);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(3);

        JButton btn = new JButton("按钮");
        btn.setBounds(0,0,100,100);
        frame.getContentPane().add(btn);

        //1 确认事件源---按钮
        //2 确定事件动作---点击反馈ActionListener
        //3 绑定监听
        btn.addMouseListener(new MouseListenerImpl());

        frame.setVisible(true);

    }

}
class MouseListenerImpl implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("点击");

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("按下");

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("释放");

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("进入");

    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("离开");

    }
}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Demo1ActionListener {
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
        btn.addActionListener(new ActionListenerImpl());//创建实现类对象ActionListenerImpl

        frame.setVisible(true);

    }
}

class ActionListenerImpl implements ActionListener{   //因为接口不能new 所以创建一个接口的实现类对象 让ActionListenerImpl实现ActionListener接口
    @Override
    public void actionPerformed(ActionEvent e) {   //实现接口就要重写接口内部抽象方法
        System.out.println("你不要过来");
    }
}
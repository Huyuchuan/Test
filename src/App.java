package src;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        new MainFrame();
    }

    /*
        int [][] datas = {
                {0,2,2,4},
                {2,2,4,4},
                {0,8,2,4},
                {0,32,0,64}
        };
        //创建窗体对象
        JFrame frame = new JFrame();
        //调用成员方法 设置宽高
        frame.setSize(514,538);
        //调用成员方法 设置窗体居中
        frame.setLocationRelativeTo(null);
        //调用成员方法 设置窗体置顶
        frame.setAlwaysOnTop(true);
        //调用成员方法 设置关闭模式
        frame.setDefaultCloseOperation(3);
        //调用成员方法 设置窗体标题
        frame.setTitle("2048小游戏");
        //取消默认布局
        frame.setLayout(null);
        //加载图片模板
        for(int i = 0;i<4;i++ ){
            for(int j = 0 ; j <4;j++){
                JLabel image = new JLabel(new ImageIcon("盘符"+datas[i][j]+".png"));
                image.setBounds(50 + j * 100, 50+ i * 100, 100, 100);
                frame.getContentPane().add(image);
            }
        }
        //加载背景模板
        JLabel backgroud = new JLabel(new ImageIcon("盘符.jpg"));
        backgroud.setBounds(40 , 40, 420, 420);
        frame.getContentPane().add(backgroud);

        //调用成员方法 设置窗体可见
        frame.setVisible(true);

     */
}

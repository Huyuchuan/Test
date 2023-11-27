package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class MainFrame extends JFrame implements KeyListener, ActionListener {  //继承Jfarme
    int[][] datas = new int[4][4];
    int lostflag = 1;
    //得分
    int score = 0;
    //图片资源的标识
    String theme = "A-";

    //将item 提取至成员变量位置  为了便于其他方法使用
    JMenuItem item1 = new JMenuItem("经典");
    JMenuItem item2 = new JMenuItem("霓虹");
    JMenuItem item3 = new JMenuItem("糖果");

    /**
     * 该方法用于初始化数据 -- datas数组进行初始化
     */
    public void initData() {
        generatorNum();  //初始状态下 调用两次 产生两个随机二号数字块
        generatorNum();
    }

    /**
     * 创建空参构造 并调用initFrame 和 paintView 方法
     */
    public MainFrame() {
        initFrame();//初始化窗体
        //初始化菜单
        initMenu();
        initData(); //初始化数据
        paintView(); //绘制界面
        //调用成员方法 设置窗体可见
        this.addKeyListener(this);//键盘监听
        setVisible(true);
    }

    /**
     * 此方法用于初始化菜单（换肤 关于我们）
     */
    public void initMenu() {
        //1 创建JMenuBar
        JMenuBar menuBar = new JMenuBar(); //创建对象
        //2 创建栏目对象 JMenu （换肤 关于我们）
        JMenu menu1 = new JMenu("换肤");
        JMenu menu2 = new JMenu("关于我们");

        menuBar.add(menu1);
        menuBar.add(menu2);

        menu1.add(item1);
        menu1.add(item2);
        menu1.add(item3);

        item1.addActionListener(this);// 为 item1,2,3 注册监听
        item2.addActionListener(this);
        item3.addActionListener(this);

        //4 给窗体对象设置菜单
        setJMenuBar(menuBar);

    }

    /**
     * 此方法用于初始化窗体 所有窗体的相关设置都在这个方法中完成
     */
    //initFrame(初始化窗体）
    public void initFrame() {

        //创建窗体对象
        //JFrame frame = new JFrame();
        /*
         * 调用父类方法，子类未重写。可省略super.
         *         super.setSize(514, 538);
         *        super.setLocationRelativeTo(null);
         *        super.setAlwaysOnTop(true);
         *         super.setDefaultCloseOperation(3);
         *        super.setTitle("2048小游戏");
         *         super.setLayout(null);
         *         super.setVisible(true);
         */
        //调用成员方法 设置宽高
        setSize(514, 538);
        //调用成员方法 设置窗体居中
        setLocationRelativeTo(null);
        //调用成员方法 设置窗体置顶
        setAlwaysOnTop(true);
        //调用成员方法 设置关闭模式
        setDefaultCloseOperation(3);
        //调用成员方法 设置窗体标题
        setTitle("2048小游戏");
        //取消默认布局
        setLayout(null);

    }

    /**
     * // 此方法用于绘制游戏界面
     */
    public void paintView() {
        //加载新内容前。移除所有内容
        getContentPane().removeAll();
        //加载失败图片
        if (lostflag == 2) {
            JLabel lostLabel = new JLabel(new ImageIcon("D:\\2048\\" + theme + "lost.png"));
            lostLabel.setBounds(90, 100, 220, 148);
            getContentPane().add(lostLabel);
        }
        //加载图片模板
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JLabel image = new JLabel(new ImageIcon("D:\\2048\\" + theme + datas[i][j] + ".png"));
                image.setBounds(50 + j * 100, 50 + i * 100, 100, 100);
                getContentPane().add(image);// super.getContentPane().add(image); 调用父类方法，子类未重写。可省略super.
            }
        }
        //加载背景模板
        JLabel backgroud = new JLabel(new ImageIcon("D:\\2048\\" + theme + "backgroud.png"));
        backgroud.setBounds(40, 40, 420, 420);
        getContentPane().add(backgroud);//super.getContentPane().add(backgroud);调用父类方法，子类未重写。可省略super.
        //得分面板
        JLabel scoreLable = new JLabel("得分" + score);
        scoreLable.setBounds(50, 20, 100, 20);
        getContentPane().add(scoreLable);

        //刷新界面方法
        getContentPane().repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int Keycode = e.getKeyCode();
        if (Keycode == 37) {
            moveToLeft(1);//调用左移动方法
            generatorNum();//产生新数字块
        } else if (Keycode == 38) {
            moveToTop(1);
            generatorNum();
        } else if (Keycode == 39) {
            moveToRight(1);//调用右移动方法
            generatorNum();
        } else if (Keycode == 40) {
            moveToBottom(1);
            generatorNum();
        } else {
            System.out.println("输入错误");
            return;
        }
        //每次移动逻辑结束，都要调用check方法 检查游戏是否为失败状态
        check();

        //重新绘制界面


        paintView();


    }

    /**
     * 一维数组的反转
     */
    public void reverseArray(int[] arr) {
        for (int start = 0, end = arr.length - 1; start < end; start++, end--) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
        }
    }

    /**
     * 二维数组的反转
     */
    public void horizontalSwap() {
        for (int i = 0; i < datas.length; i++) {
            reverseArray(datas[i]);//将一维数组传入
        }
    }

    /**
     * 此方法用于数据左移动
     */
    public void moveToLeft(int flag) {
        for (int i = 0; i < datas.length; i++) { //.fori遍历二维数组 获得每一个一维数组datas[i]
            /*1.后置0
             */
            int[] newArr = new int[4];  //创建一维数组newArr
            int index = 0;
            //取出原数组中的每一个元素，并判断是否为0
            for (int x = 0; x < datas[i].length; x++) {
                //非0数字存入新数组
                if (datas[i][x] != 0) {
                    newArr[index] = datas[i][x]; //将arr[x]放入新数组newArr[index]中
                    index++;

                }

            }
            datas[i] = newArr;//原数组记录新数组的地址
            //2. 合并元素后，后续元素前移，并在末尾补0

            for (int x = 0; x < 3; x++) {
                if (datas[i][x] == datas[i][x + 1]) {
                    datas[i][x] *= 2;

                    if (flag == 1) {
                        //计算得分
                        score += datas[i][x];
                    } //score与每次移动累加 即为得分
                    for (int j = x + 1; j < 3; j++) {
                        datas[i][j] = datas[i][j + 1];
                    }
                    datas[i][3] = 0;

                }

            }
        }


    }

    /**
     * 此方法用于数据上移动
     */
    public void moveToTop(int flag) {
        Anticlockwise();
        moveToLeft(flag);
        clockwise();
    }

    /**
     * 此方法用于数据下移动
     */
    public void moveToBottom(int flag) {
        clockwise();
        moveToLeft(flag);
        Anticlockwise();
    }

    /**
     * 检查失败状态
     */
    public void check() {
        if (checkLeft() == false && checkRight() == false && checkTop() == false && checkBottom() == false) {
            lostflag = 2;
        }
    }

    /**
     * 二维数组的拷贝
     */
    public void copyArray(int[][] src, int[][] dest) { //接受两个二维数组 int[][] src原数组 int[][] dest目标数组
        for (int i = 0; i < src.length; i++) {   //遍历原数组
            for (int j = 0; j < src[i].length; j++) {
                dest[i][j] = src[i][j];            //让目标数组接受数据 备份

            }

        }

    }

    /**
     * 此方法判断是否可以左移动
     */
    public boolean checkLeft() {
        //1 创建新的空数组 用于备份原文件
        int[][] newArr = new int[4][4];
        //2 将原数组数据 拷贝进新数组 谁在后面拷贝进谁
        copyArray(datas, newArr);
        //3 调用左移动方法 对原数组左移动
        moveToLeft(2);
        //4 使用移动后的数据，对备份数据做比对，并用flag变量记录
        //ture 可以移动
        //false 不可以移动
        boolean flag = false;


        lo:
//给循环起名 方便break退出
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                if (datas[i][j] != newArr[i][j]) { //只要一个元素不同，就可以移动
                    flag = true;
                    break lo;//有一个不同，立刻结束lo循环 提高效率
                }

            }

        }
        //5 数据还原
        copyArray(newArr, datas);
        //6 返回结果信息
        return flag;

    }

    /**
     * 此方法判断是否可以右移动
     */
    public boolean checkRight() {
        int[][] newArr = new int[4][4];
        copyArray(datas, newArr);
        moveToRight(2);
        boolean flag = false;
        lo:
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                if (datas[i][j] != newArr[i][j]) {
                    flag = true;
                    break lo;
                }

            }

        }
        //5 数据还原
        copyArray(newArr, datas);

        //6 返回结果信息
        return flag;


    }

    /**
     * 此方法判断是否可以上移动
     */
    public boolean checkTop() {
        int[][] newArr = new int[4][4];
        copyArray(datas, newArr);
        moveToTop(2);
        boolean flag = false;
        lo:
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                if (datas[i][j] != newArr[i][j]) {
                    flag = true;
                    break lo;
                }

            }

        }
        //5 数据还原
        copyArray(newArr, datas);
        //6 返回结果信息
        return flag;

    }

    /**
     * 此方法判断是否可以下移动
     */
    public boolean checkBottom() {
        int[][] newArr = new int[4][4];
        copyArray(datas, newArr);
        moveToBottom(2);
        boolean flag = false;
        lo:
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                if (datas[i][j] != newArr[i][j]) {
                    flag = true;
                    break lo;
                }

            }

        }
        //5 数据还原
        copyArray(newArr, datas);
        //6 返回结果信息
        return flag;

    }


    /**
     * 此方法用于数据右移动
     */
    public void moveToRight(int flag) {
        //1 反转数组
        horizontalSwap();
        //2 左移动
        moveToLeft(flag);
        //3 反转数组
        horizontalSwap();


    }

    /**
     * 此方法用于数据顺时针旋转
     */
    public void clockwise() {
        int[][] newdata = new int[4][4];
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas.length; j++) {
                newdata[j][3 - i] = datas[i][j];
            }
        }
        datas = newdata;
    }

    /**
     * 此方法用于数据逆时针旋转
     */
    public void Anticlockwise() {
        int[][] newdata = new int[4][4];
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas.length; j++) {
                newdata[3 - j][i] = datas[i][j];
            }
        }
        datas = newdata;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * 此方法用于在空白位置 随机产生2号数字块
     */
    public void generatorNum() {
        int w = 0;
        //创建两个一维数组arrayI arrayJ 用于记录二维数组中空白格子i和j的索引位置
        int[] arrayI = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        int[] arrayJ = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        //遍历二维数组，取出每一个元素，并判断当前元素是否为空白格式--是否为0
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                //是0 将索引存入arrayI和arrayJ
                if (datas[i][j] == 0) {
                    arrayI[w] = i;
                    arrayJ[w] = j;
                    w++;
                }
            }
        }
        //若w不为0 则代表数组中还有空白位置 就可以产生新的数字方块
        if (w != 0) {
            Random r = new Random();
            int index = r.nextInt(w);
            int x = arrayI[index];   //获取元素
            int y = arrayJ[index];
            datas[x][y] = 2;
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == item1) {
            theme = "A-";
            System.out.println("换肤为经典");
        } else if (e.getSource() == item2) {
            System.out.println("换肤为霓虹");
            theme = "B-";
        } else if (e.getSource() == item3) {
            System.out.println("换肤为糖果");
            theme = "C-";
        }
        paintView();

    }
}



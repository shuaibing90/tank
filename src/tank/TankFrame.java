package tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author Fedeline
 * @Date 2020/11/23 下午6:45
 */
public class TankFrame extends Frame {
    Tank myTank = new Tank(200,400,Dir.DOWN,Group.GOOD,this);
    List<Bullet> bulletList = new ArrayList();

    List<Tank> tankList =new ArrayList<Tank>();

    Explode e = new Explode(100,100,this);






    static final int GAME_WIDTH = 800,GAME_HEIGHT = 600;


    public TankFrame() throws HeadlessException {
        this.setTitle("tank war");
        this.setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setVisible(true);
        this.addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    Image offScreenImage = null;


    @Override
    public void update(Graphics g) {
        if (offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);


    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量："+bulletList.size(),10,60);
        g.drawString("敌人的数量："+tankList.size(),10,120);
        g.setColor(color);
        myTank.paint(g);


        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }

        for (int i = 0; i < tankList.size(); i++) {
            tankList.get(i).paint(g);
        }

        for (int i = 0; i < bulletList.size(); i++) {
            for (int j = 0; j <tankList.size() ; j++) {
                bulletList.get(i).collideWith(tankList.get(j));

            }
        }
        e.paint(g);


     /*   for (Iterator<Bullet> it = bulletList.iterator(); it.hasNext();){
            Bullet b = it.next();
            if (!b.isLive()){
                it.remove();
            }
        }*/



    }

    //处理对键盘的监听  由于只需要作用当前窗口 因此写在TankFrame内部
    class MyKeyListener extends KeyAdapter{
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        //按键按下时候起作用
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    bL = true;

                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();

            System.out.println("按键按下");

         /*   x += 30;
            repaint();*/

        }

        //按键弹起时候起作用
        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    bL = false;

                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                default:
                    break;

            }
            setMainTankDir();
            System.out.println("按键弹起");
        }

        private void setMainTankDir() {
            myTank.setMoving(true);
            if (!bL && !bD && !bR && !bU){
                myTank.setMoving(false);
            }
            {

                if (bL) {
                    myTank.setDir(Dir.LEFT);
                }
                if (bU) {
                    myTank.setDir(Dir.UP);
                }
                if (bR) {
                    myTank.setDir(Dir.RIGHT);
                }
                if (bD) {
                    myTank.setDir(Dir.DOWN);
                }
            }

        }
    }

}

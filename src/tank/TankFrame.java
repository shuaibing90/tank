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
    GameModel gameModel = new GameModel();



    static final int GAME_WIDTH = Integer.valueOf(PropertyMgr.get("gameWidth")),GAME_HEIGHT = Integer.valueOf(PropertyMgr.get("gameHeight"));


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
        gameModel.paint(g);

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



         /*   x += 30;
            repaint();*/

        }

        //按键弹起时候起作用
        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            gameModel.myTank.setMoving(true);
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
                    gameModel.getMainTank().fire();
                default:
                    break;

            }
            setMainTankDir();

        }

        private void setMainTankDir() {
            Tank myTank = gameModel.getMainTank();

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

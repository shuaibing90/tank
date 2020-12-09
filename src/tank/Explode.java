package tank;

import java.awt.*;

/**
 * @Description TODO
 * @Author Fedeline
 * @Date 2020/12/2 下午12:26
 */
public class Explode {
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
    private int x,y;
    GameModel gameModel;
//    private boolean living = true;
    private int setp = 0;

    public Explode(int x, int y, GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.gameModel = gameModel;


    }

    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explodes[setp++],x,y,null);
        if (setp >= ResourceMgr.explodes.length){
            gameModel.explodes.remove(this);
        }

    }






}

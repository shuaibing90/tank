package tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author Fedeline
 * @Date 2020/12/9 上午11:54
 */
public class GameModel {
    Tank myTank = new Tank(200,400,Dir.DOWN,Group.GOOD,this);
    java.util.List<Bullet> bulletList = new ArrayList();

    java.util.List<Tank> tankList =new ArrayList<Tank>();

    List<Explode> explodes = new ArrayList<>();
    public GameModel() {
        int initCountTank =Integer.parseInt(PropertyMgr.get("initCountTank")) ;
        //初始化地方坦克
        for (int i = 0; i < initCountTank; i++) {
            tankList.add(new Tank(50+i*80,200, Dir.DOWN,Group.BAD,this));
        }


    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量："+bulletList.size(),10,60);
        g.drawString("敌人的数量："+tankList.size(),10,80);
        g.drawString("爆炸的数量："+explodes.size(),10,100);
        g.setColor(color);
        myTank.paint(g);



        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }

        for (int i = 0; i < tankList.size(); i++) {
            tankList.get(i).paint(g);
        }
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
        //collision detect

        for (int i = 0; i < bulletList.size(); i++) {
            for (int j = 0; j <tankList.size() ; j++) {
                bulletList.get(i).collideWith(tankList.get(j));

            }
        }



     /*   for (Iterator<Bullet> it = bulletList.iterator(); it.hasNext();){
            Bullet b = it.next();
            if (!b.isLive()){
                it.remove();
            }
        }*/


    }

    public Tank getMainTank() {

        return myTank;
    }
}

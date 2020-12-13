package cn.xysycx.tank.cor;

import tank.Bullet;
import tank.GameObject;
import tank.Tank;

/**
 * 只负责子弹和tank相撞的类
 */
public class TankTankCollider implements Collider{
    @Override
    public Boolean collider(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if (t1.rect.intersects(t2.rect)){
//                t1.stop();
//                t2.stop();

            }
        }
            return true;



    }




}

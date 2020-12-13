package cn.xysycx.tank.cor;

import tank.Bullet;
import tank.GameObject;
import tank.Tank;

/**
 * 只负责子弹和tank相撞的类
 */
public class BulletTankCollider implements Collider{
    @Override
    public Boolean collider(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank){
            Bullet b = (Bullet) o1;
            Tank t =(Tank) o2;
            //TODO：copy code from method collideWith
            if (b.collideWith(t)){
                return false;
            }
        }else if (o1 instanceof Tank  && o2 instanceof Bullet){
            collider(o2,o1);
        }else {
            return false;
        }
        return false;


    }




}
